package com.hgsoft.ygz.vtams.transfer.job;

import com.hgsoft.yfzx.webapp.util.IdWorkerInit;
import com.hgsoft.ygz.vtams.transfer.common.enums.BusinessTypeEnum;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.exception.NotBusinessException;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.services.IBusinessReqService;
import com.hgsoft.ygz.vtams.transfer.services.ILogService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * 定时扫描数据库同步信息表,该表中存在两种信息：同步信息和准同步信息<br/>
 * 同步信息：如车牌唯一性校验，无需映射，直接发送到部中心即可<br/>
 * 准同步信息:如客户信息上传及变更，映射后发送消息到部中心
 *
 * @author 赖少涯
 * @date 2017-10-16
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ScanSyncMsgJob implements Job {

    private final Logger log = LoggerFactory.getLogger(ScanSyncMsgJob.class);

    @Autowired
    private IBusinessReqService businessReqService;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ILogService logService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            final long startTime = System.currentTimeMillis();

            //从jobDataMap中取出页码和每页记录数
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            final int pageNumber = Integer.parseInt(jobDataMap.get("pageNumber").toString());
            final int pageSize = Integer.parseInt(jobDataMap.get("pageSize").toString());
            log.info("开始扫描同步信息表，区间信息为：pageNumber={},pageSize={}.", pageNumber, pageSize);

            //读取同步信息表
            List<BusinessReq> businessReqList = businessReqService.findSyncMsg(pageNumber, pageSize);
            if (null == businessReqList || businessReqList.isEmpty()) {
                log.info("同步信息表中没有记录，本次任务结束...");
                return;
            }

            //当前扫描结果中的实际记录数
            final long currentCount = businessReqList.size();
            final int countIntVal = new Long(currentCount).intValue();

            //生成批次号
            final long batchNo = IdWorkerInit.generateId();

            //遍历记录列表，使用线程分别处理，同时将task和businessReqId放入对应的列表中，等countDownLatch计数完毕后，批量处理结果

            //定义列表对象分别存储非异常对象、成功日志、失败日志、数据源ID列表
            List<NotBusinessException> notBusinessExceptionList = new ArrayList<>();
            List<SyncException> syncExceptionList = new ArrayList<>();
            List<SyncLog> syncLogList = new ArrayList<>(countIntVal);
            List<Long> idList = new ArrayList<>(countIntVal);

            for (final BusinessReq businessReq : businessReqList) {
                businessReq.setBatchNo(batchNo);
                idList.add(businessReq.getId());
                handlerSyncMsg(notBusinessExceptionList, syncExceptionList, syncLogList, businessReq);
            }

            //批量保存成功日志、异常日志、批量删除数据源
            logService.batchSaveFailedSyncLog(syncExceptionList);
            logService.batchSaveNotBusinessException(notBusinessExceptionList);
            logService.batchSaveSuccessfulSyncLog(syncLogList);
            businessReqService.batchRemoveSyncBusinessReqByPrimaryKey(idList);

            final long endTime = System.currentTimeMillis();
            log.info("扫描同步信息表[pageNumber={},pageSize={},count={}]完毕，本次任务结束，耗时:{}毫秒", pageNumber, pageSize, currentCount, endTime - startTime);

        } catch (Exception e) {
            //判断如果是非业务异常，则抛出
            if (!(e instanceof AsyncException) && !(e instanceof SyncException) && !(e instanceof NotBusinessException)) {
                e.printStackTrace();
                JobExecutionException je = new JobExecutionException(e);
                log.error("扫描同步信息表后，处理失败，原因可能是：{}", e.getMessage());
                throw je;
            }
        }
    }


    /**
     * 处理同步信息
     */
    private void handlerSyncMsg(List<NotBusinessException> notBusinessExceptionList, List<SyncException> syncExceptionList,
                                List<SyncLog> syncLogList, final BusinessReq businessReq) {
        try {
            SyncLog syncLog = businessReqService.distributeSyncMsg(businessReq);
            syncLog.setBatchNo(businessReq.getBatchNo());
            syncLog.setBusinessContent(businessReq.getBusinessContent());
            syncLog.setBusinessType(businessReq.getBusinessType());
            syncLog.setOperation(businessReq.getOperation());
            syncLog.setReceivedTime(businessReq.getCreateTime());
            //放入列表中
            syncLogList.add(syncLog);
            //打印日志、写入成功日志
            log.info("业务类型：{},操作类型：{},执行结果：{}", BusinessTypeEnum.valueOf(syncLog.getBusinessType()).getDesc(),
                    syncLog.getOperation(), syncLog.getStatusDesc());
        } catch (Exception e) {
            if (e instanceof SyncException) {
                SyncException syncException = (SyncException) e;
                syncException.setBatchNo(businessReq.getBatchNo());
                syncException.setBusinessContent(businessReq.getBusinessContent());
                syncException.setBusinessType(businessReq.getBusinessType());
                syncException.setReceivedTime(businessReq.getCreateTime());
                syncException.setOperation(businessReq.getOperation());
                //放入列表中
                syncExceptionList.add(syncException);
                log.error("批次号：{},业务类型：{},操作类型：{},执行结果：{}", businessReq.getBatchNo(),
                        businessReq.getBusinessType(), businessReq.getOperation(), syncException.getStatusDesc());
            } else {
                NotBusinessException notBusinessException;
                if (e instanceof NotBusinessException) {
                    notBusinessException = (NotBusinessException) e;
                } else {
                    notBusinessException = new NotBusinessException();
                    notBusinessException.setStatusDesc("目标方法[ distributeSyncMsg ]出现未处理异常[" + e.getClass().getName() + "]");
                }
                notBusinessException.setBatchNo(businessReq.getBatchNo());
                notBusinessException.setBusinessType(businessReq.getBusinessType());
                notBusinessException.setBusinessContent(businessReq.getBusinessContent());
                notBusinessException.setOperation(businessReq.getOperation());
                notBusinessException.setReceivedTime(businessReq.getCreateTime());
                //放入列表中
                notBusinessExceptionList.add(notBusinessException);
                log.error("批次号：{},业务类型：{},操作类型：{},执行结果：{}", businessReq.getBatchNo(),
                        businessReq.getBusinessType(), businessReq.getOperation(), notBusinessException.getStatusDesc());
            }
        }
    }

}
