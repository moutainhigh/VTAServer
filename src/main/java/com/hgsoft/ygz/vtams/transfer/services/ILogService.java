package com.hgsoft.ygz.vtams.transfer.services;

import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.exception.NotBusinessException;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;

import java.util.List;

/**
 * 日志服务接口：用于记录异常、成功、未定义信息
 *
 * @author 赖少涯
 * @date 2017-10-17
 */
public interface ILogService {

    /**
     * 保存处理成功的异步消息日志记录
     *
     * @param asyncLog 日志对象
     * @return int 受影响记录数
     */
    int saveSuccessfulAsyncLog(AsyncLog asyncLog);

    /**
     * 保存处理失败的异步消息日志记录
     *
     * @param asyncException 异常对象
     * @return int 受影响记录数
     */
    int saveFailedAsyncLog(AsyncException asyncException);


    /**
     * 保存处理成功的同步消息日志记录
     *
     * @param syncLog 日志对象
     * @return int 受影响记录数
     */
    int saveSuccessfulSyncLog(SyncLog syncLog);


    /**
     * 保存处理失败的同步消息日志记录
     *
     * @param syncException 异常信息
     * @return int 受影响记录数
     */
    int saveFailedSyncLog(SyncException syncException);

    /**
     * 保存非业务性异常，如BusinessReq对象操作类型、businessContent为空等情况
     *
     * @param notBusinessException 日志对象
     * @return int 受影响记录数
     */
    int saveNotBusinessException(NotBusinessException notBusinessException);

    /**
     * 批量保存处理成功的同步消息日志记录
     *
     * @param asyncLogList
     * @return int 受影响的记录数
     */
    int batchSaveSuccessfulAsyncLog(List<AsyncLog> asyncLogList);


    /**
     * 批量保存处理成功的异步消息日志记录
     *
     * @param syncLogList
     * @return int 受影响的记录数
     */
    int batchSaveSuccessfulSyncLog(List<SyncLog> syncLogList);


    /**
     * 批量保存处理失败的同步消息日志记录
     *
     * @param syncExceptionList 日志列表
     * @return int 受影响的记录数
     */
    int batchSaveFailedSyncLog(List<SyncException> syncExceptionList);

    /**
     * 批量保存非业务性异常，如BusinessReq对象操作类型、businessContent为空等情况
     *
     * @param notBusinessExceptionList 日志列表
     * @return int 受影响的记录数
     */
    int batchSaveNotBusinessException(List<NotBusinessException> notBusinessExceptionList);
}
