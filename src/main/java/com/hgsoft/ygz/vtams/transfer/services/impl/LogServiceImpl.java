package com.hgsoft.ygz.vtams.transfer.services.impl;

import com.hgsoft.ygz.vtams.transfer.dao.LogMapper;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.exception.NotBusinessException;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.services.ILogService;
import net.sf.ehcache.concurrent.Sync;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日志服务接口：用于记录异常、成功、未定义信息
 *
 * @author 赖少涯
 * @date 2017-10-17
 */
@Service("logService")
public class LogServiceImpl implements ILogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public int saveSuccessfulAsyncLog(AsyncLog asyncLog) {
        return logMapper.saveSuccessfulAsyncLog(asyncLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSaveSuccessfulAsyncLog(List<AsyncLog> asyncLogList) {
        if (null == asyncLogList || asyncLogList.isEmpty()) {
            return 0;
        }
        return logMapper.batchSaveSuccessfulAsyncLog(asyncLogList);
    }

    @Override
    public int saveFailedAsyncLog(AsyncException asyncException) {
        return logMapper.saveFailedAsyncLog(asyncException);
    }

    @Override
    public int saveSuccessfulSyncLog(SyncLog syncLog) {
        return logMapper.saveSuccessfulSyncLog(syncLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSaveSuccessfulSyncLog(List<SyncLog> syncLogList) {
        if (null == syncLogList || syncLogList.isEmpty()) {
            return 0;
        }
        return logMapper.batchSaveSuccessfulSyncLog(syncLogList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSaveFailedSyncLog(List<SyncException> syncExceptionList) {
        if (null == syncExceptionList || syncExceptionList.isEmpty()) {
            return 0;
        }
        return logMapper.batchSaveFailedSyncLog(syncExceptionList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSaveNotBusinessException(List<NotBusinessException> notBusinessExceptionList) {
        if (null == notBusinessExceptionList || notBusinessExceptionList.isEmpty()) {
            return 0;
        }
        return logMapper.batchSaveNotBusinessException(notBusinessExceptionList);
    }

    @Override
    public int saveFailedSyncLog(SyncException syncException) {
        return logMapper.saveFailedSyncLog(syncException);
    }

    @Override
    public int saveNotBusinessException(NotBusinessException notBusinessException) {
        return logMapper.saveNotBusinessException(notBusinessException);
    }
}
