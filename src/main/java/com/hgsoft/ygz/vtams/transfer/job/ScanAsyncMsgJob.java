package com.hgsoft.ygz.vtams.transfer.job;

import com.hgsoft.yfzx.webapp.util.IdWorkerInit;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.exception.NotBusinessException;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.services.*;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 定时扫描数据库中异步信息表，该表中存储的是非实时信息，只需要映射入库即可
 *
 * @author 赖少涯
 * @date 2017-10-16
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ScanAsyncMsgJob implements Job {

    private final Logger log = LoggerFactory.getLogger(ScanAsyncMsgJob.class);

    @Autowired
    private IBusinessReqService businessReqService;

    @Autowired
    private ILogService logService;


    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            final long startTime = System.currentTimeMillis();

            //从jobDataMap中取出页码和每页记录数
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            final int pageNumber = Integer.parseInt(jobDataMap.get("pageNumber").toString());
            final int pageSize = Integer.parseInt(jobDataMap.get("pageSize").toString());
            log.info("扫描异步信息表，区间信息为：pageNumber={},pageSize={}.", pageNumber, pageSize);

            //读取异步信息表
            List<BusinessReq> businessReqList = businessReqService.findAsyncMsg(pageNumber, pageSize);
            if (null == businessReqList || businessReqList.isEmpty()) {
                log.info("异步信息表中没有记录，本次任务结束...");
                return;
            }

            //当前扫描结果中的实际记录数
            final long currentCount = businessReqList.size();
            //生成批次号
            final long batchNo = IdWorkerInit.generateId();

            //遍历记录列表，使用线程分别处理，同时将task放入里列表中；countDownLatch计数完毕后，批量处理结果
            final int countIntVal = new Long(currentCount).intValue();
            final CountDownLatch countDownLatch = new CountDownLatch(countIntVal);
            List<FutureTask<AsyncLog>> taskList = new ArrayList<>(countIntVal);
            List<Long> idList = new ArrayList<>(countIntVal);
            for (final BusinessReq businessReq : businessReqList) {
                businessReq.setBatchNo(batchNo);
                idList.add(businessReq.getId());
                FutureTask<AsyncLog> asyncLogFutureTask = new FutureTask<>(new AsyncMsgCallable(countDownLatch, businessReq, businessReqService, logService));
                taskExecutor.submit(asyncLogFutureTask);
                taskList.add(asyncLogFutureTask);
            }
            countDownLatch.await();

            //获取执行结果，执行结果为log 或 null
            List<AsyncLog> asyncLogList = new ArrayList<>(countIntVal);
            for (FutureTask<AsyncLog> futureTask : taskList) {
                AsyncLog asyncLog = futureTask.get();
                if (null != asyncLog) {
                    asyncLogList.add(asyncLog);
                }
            }

            //批量保存成功日志、批量删除数据源
            logService.batchSaveSuccessfulAsyncLog(asyncLogList);
            businessReqService.batchRemoveAsyncBusinessReqByPrimaryKey(idList);

            final long endTime = System.currentTimeMillis();
            log.info("扫描异步信息表[pageNumber={},pageSize={},count={}]完毕，本次任务结束，耗时:{}毫秒", pageNumber, pageSize, currentCount, endTime - startTime);

        } catch (Exception e) {
            //判断如果是非业务异常，则抛出
            if (!(e instanceof AsyncException) && !(e instanceof SyncException) && !(e instanceof NotBusinessException)) {
                e.printStackTrace();
                JobExecutionException je = new JobExecutionException(e);
                log.error("扫描异步信息表后，处理失败，原因可能是：{}", e.getMessage());
                throw je;
            }
        }
    }

}
