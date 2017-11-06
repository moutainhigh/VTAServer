package com.hgsoft.ygz.vtams.transfer.services;

import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.business.log.MsgLog;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;

import java.util.List;

/**
 * 业务请求信息服务接口
 *
 * @author 赖少涯
 * @date 2017-10-16
 */
public interface IBusinessReqService {

    /**
     * 查找指定区间内的同步信息
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     * @return list
     */
    List<BusinessReq> findSyncMsg(int pageNumber, int pageSize);

    /**
     * 查找指定区间内的异步信息
     *
     * @param pageNumber 页码
     * @param pageSize   每页记录数
     */
    List<BusinessReq> findAsyncMsg(int pageNumber, int pageSize);

    /**
     * 根据业务类型分发同步消息
     *
     * @param businessReq 业务数据
     * @return SyncLog 成功日志信息
     */
    SyncLog distributeSyncMsg(BusinessReq businessReq);

    /**
     * 根据业务类型分发异步消息
     *
     * @param businessReq 业务数据
     * @return AsyncLog 成功日志信息
     */
    AsyncLog distributeAsyncMsg(BusinessReq businessReq);


    /**
     * 根据主键删除指定的异步消息
     *
     * @param id 主键id
     * @return 受影响记录数
     */
    int removeAsyncBusinessReqByPrimaryKey(Long id);

    /**
     * 根据主键删除指定的同步消息
     *
     * @param id 主键id
     * @return 受影响记录数
     */
    int removeSyncBusinessReqByPrimaryKey(Long id);

    /**
     * 根据主键列表，批量删除异步信息
     *
     * @param idList id列表
     * @return 受影响记录数
     */
    int batchRemoveAsyncBusinessReqByPrimaryKey(List<Long> idList);

    /**
     * 根据主键列表，批量删除同步信息
     *
     * @param idList id 列表
     * @return 受影响的记录数
     */
    int batchRemoveSyncBusinessReqByPrimaryKey(List<Long> idList);

    /**
     * 批量插入同步信息对象
     *
     * @param syncBusinessReqList 同步信息列表
     */
    int batchSaveSyncInfo(List<BusinessReq> syncBusinessReqList);

    /**
     * 批量插入异步信息对象
     *
     * @param asyncBusinessReqList 异步信息列表
     */
    int batchSaveAsyncInfo(List<BusinessReq> asyncBusinessReqList);

}
