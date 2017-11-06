package com.hgsoft.ygz.vtams.transfer.job;

import com.hgsoft.ygz.vtams.transfer.common.enums.BusinessTypeEnum;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.exception.NotBusinessException;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.services.IBusinessReqService;
import com.hgsoft.ygz.vtams.transfer.services.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * 异步消息线程类
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-01 09:19:38
 * @since 1.7
 */
public class AsyncMsgCallable implements Callable<AsyncLog> {

    private final Logger log = LoggerFactory.getLogger(AsyncMsgCallable.class);

    private IBusinessReqService businessReqService;

    private ILogService logService;

    private CountDownLatch countDownLatch;

    private BusinessReq businessReq;

    public AsyncMsgCallable(CountDownLatch countDownLatch, BusinessReq businessReq, IBusinessReqService businessReqService, ILogService logService) {
        this.countDownLatch = countDownLatch;
        this.businessReq = businessReq;
        this.businessReqService = businessReqService;
        this.logService = logService;
    }

    @Override
    public AsyncLog call() throws Exception {
        try {
            AsyncLog asyncLog = businessReqService.distributeAsyncMsg(businessReq);

            //在日志对象中保存源对象信息
            asyncLog.setBatchNo(businessReq.getBatchNo());
            asyncLog.setBusinessContent(businessReq.getBusinessContent());
            asyncLog.setBusinessType(businessReq.getBusinessType());
            asyncLog.setReceivedTime(businessReq.getCreateTime());
            asyncLog.setOperation(businessReq.getOperation());

            //打印日志、写入成功日志
            log.info("业务类型：{},操作类型：{},执行结果：{}", BusinessTypeEnum.valueOf(asyncLog.getBusinessType()).getDesc(),
                    asyncLog.getOperation(), asyncLog.getStatusDesc());

            countDownLatch.countDown();
            return asyncLog;
        } catch (Exception e) {
            if (e instanceof AsyncException) {
                AsyncException asyncException = (AsyncException) e;
                asyncException.setBatchNo(businessReq.getBatchNo());
                asyncException.setBusinessContent(businessReq.getBusinessContent());
                asyncException.setBusinessType(businessReq.getBusinessType());
                asyncException.setReceivedTime(businessReq.getCreateTime());
                asyncException.setOperation(businessReq.getOperation());
                logService.saveFailedAsyncLog(asyncException);
                log.error("批次号：{},业务类型：{},操作类型：{},执行结果：{}", businessReq.getBatchNo(),
                        businessReq.getBusinessType(), businessReq.getOperation(), asyncException.getStatusDesc());
            } else {
                NotBusinessException notBusinessException;
                if (e instanceof NotBusinessException) {
                    notBusinessException = (NotBusinessException) e;
                } else {
                    e.printStackTrace();
                    notBusinessException = new NotBusinessException();
                    notBusinessException.setStatusDesc("目标方法[ distributeAsyncMsg ]出现未处理异常[" + e.getClass().getName() + "]");
                }
                notBusinessException.setBatchNo(businessReq.getBatchNo());
                notBusinessException.setBusinessType(businessReq.getBusinessType());
                notBusinessException.setBusinessContent(businessReq.getBusinessContent());
                notBusinessException.setOperation(businessReq.getOperation());
                notBusinessException.setReceivedTime(businessReq.getCreateTime());
                logService.saveNotBusinessException(notBusinessException);
                log.error("批次号：{},业务类型：{},操作类型：{},执行结果：{}", businessReq.getBatchNo(),
                        businessReq.getBusinessType(), businessReq.getOperation(), notBusinessException.getStatusDesc());
            }

            countDownLatch.countDown();
            return null;
        }
    }
}
