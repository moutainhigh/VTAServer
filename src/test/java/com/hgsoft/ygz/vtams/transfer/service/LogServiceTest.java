package com.hgsoft.ygz.vtams.transfer.service;

import com.hgsoft.ygz.vtams.transfer.exception.NotBusinessException;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.services.ILogService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务信息服务类测试
 *
 * @author 赖少涯
 * @date 2017-10-15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@Transactional
@Rollback
@ActiveProfiles({"production", "flyway"})
public class LogServiceTest {

    @Autowired
    private ILogService logService;

    @Test
    public void testBatchSaveSuccessfulAsyncLog() {
        long start = System.currentTimeMillis();
        List<AsyncLog> asyncLogList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            AsyncLog asyncLog = new AsyncLog();
            asyncLog.setBatchNo(10000L + i);
            asyncLog.setBusinessContent("异步信息的成功日志" + i);
            asyncLog.setBusinessType("type" + i);
            asyncLog.setReceivedTime(DateUtil.getCurrentSqlTimestamp());
            asyncLog.setMappingStartTime(DateUtil.getCurrentSqlTimestamp());
            asyncLog.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncLog.setInputStockStartTime(DateUtil.getCurrentSqlTimestamp());
            asyncLog.setInputStockEndTime(DateUtil.getCurrentSqlTimestamp());
            asyncLog.setOperation(1);
            asyncLog.setStatusDesc("批量插入测试" + i);
            asyncLogList.add(asyncLog);
        }
        int effectiveCount = logService.batchSaveSuccessfulAsyncLog(asyncLogList);
        Assert.assertEquals(100, effectiveCount);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }

    @Test
    public void testBatchSaveSuccessfulSyncLog() {
        long start = System.currentTimeMillis();
        List<SyncLog> syncLogList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SyncLog syncLog = new SyncLog();
            syncLog.setBatchNo(10000L + i);
            syncLog.setBusinessContent("同步信息的成功日志" + i);
            syncLog.setBusinessType("type" + i);
            syncLog.setReceivedTime(DateUtil.getCurrentSqlTimestamp());
            syncLog.setMappingStartTime(DateUtil.getCurrentSqlTimestamp());
            syncLog.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncLog.setReqFileMd5("md5" + i);
            syncLog.setReqJsonStr("jsonStr" + i);
            syncLog.setReqFileName("fileName" + i);
            syncLog.setResponseCode(200);
            syncLog.setResponseTime(DateUtil.getCurrentSqlTimestamp());
            syncLog.setRetryTimes(1);
            syncLog.setOperation(1);
            syncLog.setStatusDesc("批量插入测试" + i);
            syncLogList.add(syncLog);
        }
        int effectiveCount = logService.batchSaveSuccessfulSyncLog(syncLogList);
        Assert.assertEquals(100, effectiveCount);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }


    @Test
    public void testBatchSaveFailedSyncLog() {
        long start = System.currentTimeMillis();
        List<SyncException> syncExceptionList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            SyncException syncException = new SyncException();
            syncException.setBatchNo(10000L + i);
            syncException.setBusinessContent("同步信息的失败日志" + i);
            syncException.setBusinessType("type" + i);
            syncException.setReceivedTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setMappingStartTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setMappingEndTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setReqFileMd5("md5" + i);
            syncException.setReqJsonStr("jsonStr" + i);
            syncException.setReqFileName("fileName" + i);
            syncException.setResponseCode(200);
            syncException.setResponseTime(DateUtil.getCurrentSqlTimestamp());
            syncException.setRetryTimes(1);
            syncException.setOperation(1);
            syncException.setStatusDesc("批量插入测试" + i);
            syncExceptionList.add(syncException);
        }
        int effectiveCount = logService.batchSaveFailedSyncLog(syncExceptionList);
        Assert.assertEquals(100, effectiveCount);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }


    @Test
    public void testBatchSaveNotBusinessException() {
        long start = System.currentTimeMillis();
        List<NotBusinessException> notBusinessExceptionList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            NotBusinessException notBusinessException = new NotBusinessException();
            notBusinessException.setBatchNo(10000L + i);
            notBusinessException.setBusinessContent("非业务性异常日志" + i);
            notBusinessException.setBusinessType("type" + i);
            notBusinessException.setReceivedTime(DateUtil.getCurrentSqlTimestamp());
            notBusinessException.setOperation(1);
            notBusinessException.setStatusDesc("批量插入测试" + i);
            notBusinessExceptionList.add(notBusinessException);
        }
        int effectiveCount = logService.batchSaveNotBusinessException(notBusinessExceptionList);
        Assert.assertEquals(100, effectiveCount);
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }


}
