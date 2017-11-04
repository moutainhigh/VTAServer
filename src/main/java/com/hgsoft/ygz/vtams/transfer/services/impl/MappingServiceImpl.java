package com.hgsoft.ygz.vtams.transfer.services.impl;

import com.hgsoft.ygz.vtams.transfer.exception.NotBusinessException;
import com.hgsoft.ygz.vtams.transfer.model.map.*;
import com.hgsoft.ygz.vtams.transfer.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.hgsoft.ygz.vtams.transfer.dao.MappingMapper;
import com.hgsoft.ygz.vtams.transfer.services.IMappingService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-19 09:57:05
 * @since 1.7
 */
@Service
public class MappingServiceImpl implements IMappingService {

    /**
     * 网点映射信息 缓存name
     */
    private static final String CACHE_POINT_MAPPING = "point_mapping_cache";

    @Autowired
    private MappingMapper mappingMapper;

    @Autowired
    private CacheManager cacheManager;

    @Override
    @Transactional(readOnly = true)
    public PointMapping getPointMapping(final String oldPointCode) {
        Cache cache = cacheManager.getCache(CACHE_POINT_MAPPING);
        PointMapping pointMapping = cache.get(oldPointCode, PointMapping.class);
        if (null != pointMapping) {
            return pointMapping;
        }

        //从数据库获取对象
        pointMapping = mappingMapper.getPointMapping(oldPointCode);
        if (null != pointMapping) {
            cache.put(oldPointCode, pointMapping);
            return pointMapping;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int savePointMapping(final PointMapping pointMapping) {
        try {
            int effectiveCount = mappingMapper.savePointMapping(pointMapping);
            if (1 != effectiveCount) {
                StringBuilder errorMsg = new StringBuilder(100);
                errorMsg.append("新增网点映射关系[oldPointCode=").append(pointMapping.getOldPointCode())
                        .append(",newPointCode=").append(pointMapping.getNewPointCode()).append(",agencyId=")
                        .append(pointMapping.getAgencyId()).append("]时，受影响记录数为:").append(effectiveCount);
                throw new NotBusinessException(errorMsg.toString());
            }
            Cache cache = cacheManager.getCache(CACHE_POINT_MAPPING);
            cache.put(pointMapping.getOldPointCode(), pointMapping);
            return effectiveCount;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotBusinessException(ExceptionUtil.getSimpleMsg(e));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveCustomerMapping(CustomerMapping customerMapping) {
        try {
            int effectiveCount = mappingMapper.saveCustomerMapping(customerMapping);
            if (1 != effectiveCount) {
                StringBuilder errorMsg = new StringBuilder(100);
                errorMsg.append("新增客户射关系[oldUserNo=").append(customerMapping.getOldUserNo())
                        .append(",newUserNo=").append(customerMapping.getNewUserNo())
                        .append("]时，受影响记录数为:").append(effectiveCount);
                throw new NotBusinessException(errorMsg.toString());
            }
            return effectiveCount;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotBusinessException(ExceptionUtil.getSimpleMsg(e));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerMapping getCustomerMapping(String oldUserNo) {
        return mappingMapper.getCustomerMapping(oldUserNo);
    }

    @Override
    @Transactional(readOnly = true)
    public RechargeMapping getRechargeMapping(String oldRechargeId) {
        return mappingMapper.getRechargeMapping(oldRechargeId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRechargeMapping(RechargeMapping rechargeMapping) {
        try {
            int effectiveCount = mappingMapper.saveRechargeMapping(rechargeMapping);
            if (1 != effectiveCount) {
                StringBuilder errorMsg = new StringBuilder(100);
                errorMsg.append("新增充值交易射关系[oldRechargeId=").append(rechargeMapping.getOldRechargeId())
                        .append(",newRechargeId=").append(rechargeMapping.getNewRechargeId())
                        .append("]时，受影响记录数为:").append(effectiveCount);
                throw new NotBusinessException(errorMsg.toString());
            }
            return effectiveCount;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotBusinessException(ExceptionUtil.getSimpleMsg(e));
        }
    }
}
