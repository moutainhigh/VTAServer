package com.hgsoft.ygz.vtams.transfer.services;

import com.hgsoft.ygz.vtams.transfer.model.map.*;

/**
 * 映射关系维护接口
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-19 09:32:52
 * @since 1.7
 */
public interface IMappingService {

    /**
     * 根据旧网点编号查找服务网点映射信息<br/>
     * 先从缓存中获取，如果没有则查询数据库,并将返回的结果放入缓存中
     *
     * @param oldPointCode 旧网点编号
     * @return 如果缓存中及数据库中都没有对应值，则返回null;否者返回PointMapping
     */
    PointMapping getPointMapping(String oldPointCode);

    /**
     * 保存服务网点映射信息，同时放入缓存中
     *
     * @param pointMapping 服务网点映射信息
     * @return 受影响记录数
     */
    int savePointMapping(PointMapping pointMapping);

    /**
     * 保存客户映射信息
     *
     * @param customerMapping 客户映射信息
     * @return 受影响记录数
     */
    int saveCustomerMapping(CustomerMapping customerMapping);

    /**
     * 根据旧客户编号查找客户映射信息<br/>
     *
     * @param oldUserNo 旧客户编号
     * @return 如果数据库中没有对应值，则返回null;否者返回 CustomerMapping
     */
    CustomerMapping getCustomerMapping(String oldUserNo);

    /**
     * 保存充值交易映射信息
     *
     * @param rechargeMapping 充值交易映射信息
     * @return 受影响记录数
     */
    int saveRechargeMapping(RechargeMapping rechargeMapping);

    /**
     * 根据旧充值交易标识查找充值交易映射信息
     *
     * @param oldRechargeId 旧充值交易标识
     * @return 如果数据库中没有对应值，则返回null;否者返回 RechargeMapping
     */
    RechargeMapping getRechargeMapping(String oldRechargeId);
}
