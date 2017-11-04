package com.hgsoft.ygz.vtams.transfer.dao;


import com.hgsoft.yfzx.webapp.util.MyBatisRepository;
import com.hgsoft.ygz.vtams.transfer.model.map.*;

/**
 * 映射关系处理dao接口
 *
 * @author 赖少涯
 * @date 2017-10-19
 */
@MyBatisRepository
public interface MappingMapper {

    /**
     * 根据旧网点编号查找服务网点映射信息
     *
     * @param oldPointCode 旧网点编号
     * @return PointMapping or null
     */
    PointMapping getPointMapping(String oldPointCode);

    /**
     * 保存服务网点映射信息
     *
     * @param pointMapping 服务网点映射信息
     * @return 受影响记录数
     */
    int savePointMapping(PointMapping pointMapping);


    /**
     * 根据旧客户编号查找客户映射信息
     *
     * @param oldUserNo 旧客户编号
     * @return CustomerMapping or null
     */
    CustomerMapping getCustomerMapping(String oldUserNo);

    /**
     * 保存客户映射信息
     *
     * @param customerMapping 客户映射信息
     * @return 受影响记录数
     */
    int saveCustomerMapping(CustomerMapping customerMapping);

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
