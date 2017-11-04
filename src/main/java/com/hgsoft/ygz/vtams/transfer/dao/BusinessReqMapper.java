/**
 * Copyright &copy; 2012-2016 华软开发平台 All rights reserved.
 */
package com.hgsoft.ygz.vtams.transfer.dao;


import com.hgsoft.yfzx.webapp.util.MyBatisRepository;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * 业务请求信息dao接口
 *
 * @author 赖少涯
 * @date 2017-10-16
 */
@MyBatisRepository
public interface BusinessReqMapper {

    /**
     * 查找指定区间内的同步信息
     *
     * @param start 起始位=(currentPage-1)*pageSize
     * @param end   结束位=currentPage*pageSize
     * @return list
     */
    List<BusinessReq> findSyncMsg(@Param("start") int start, @Param("end") int end);

    /**
     * 查找指定区间内的异步信息
     *
     * @param start 起始位=(currentPage-1)*pageSize
     * @param end   结束位=currentPage*pageSize
     * @return list
     */
    List<BusinessReq> findAsyncMsg(@Param("start") int start, @Param("end") int end);

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
}