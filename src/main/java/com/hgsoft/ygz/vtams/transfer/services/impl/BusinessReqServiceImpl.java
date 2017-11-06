package com.hgsoft.ygz.vtams.transfer.services.impl;

import com.hgsoft.ygz.vtams.transfer.common.enums.BusinessTypeEnum;
import com.hgsoft.ygz.vtams.transfer.dao.BusinessReqMapper;
import com.hgsoft.ygz.vtams.transfer.exception.NotBusinessException;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;
import com.hgsoft.ygz.vtams.transfer.model.business.log.MsgLog;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.services.IBusinessReqService;
import com.hgsoft.ygz.vtams.transfer.services.ILogService;
import com.hgsoft.ygz.vtams.transfer.services.IMsgAsyncService;
import com.hgsoft.ygz.vtams.transfer.services.IMsgSyncService;
import com.hgsoft.ygz.vtams.transfer.util.SpringContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.List;

/**
 * 业务请求信息服务类
 *
 * @author 赖少涯
 * @date 2017-10-16
 */
@Service("businessReqService")
public class BusinessReqServiceImpl implements IBusinessReqService {

    @Autowired
    private BusinessReqMapper businessReqMapper;

    @Autowired
    private ILogService logService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 业务类型
     */
    private static final EnumMap<BusinessTypeEnum, String> businessTypeMap;

    static {
        businessTypeMap = new EnumMap<>(BusinessTypeEnum.class);
        for (BusinessTypeEnum businessTypeEnum : BusinessTypeEnum.values()) {
            businessTypeMap.put(businessTypeEnum, businessTypeEnum.name());
        }
    }


    @Override
    public List<BusinessReq> findSyncMsg(int pageNumber, int pageSize) {
        return businessReqMapper.findSyncMsg((pageNumber - 1) * pageSize, pageNumber * pageSize);
    }

    @Override
    public List<BusinessReq> findAsyncMsg(int pageNumber, int pageSize) {
        return businessReqMapper.findAsyncMsg((pageNumber - 1) * pageSize, pageNumber * pageSize);
    }


    @Override
    public int removeAsyncBusinessReqByPrimaryKey(Long id) {
        if (null == id) {
            return 0;
        }
        return businessReqMapper.removeAsyncBusinessReqByPrimaryKey(id);
    }

    @Override
    public int removeSyncBusinessReqByPrimaryKey(Long id) {
        if (null == id) {
            return 0;
        }
        return businessReqMapper.removeSyncBusinessReqByPrimaryKey(id);
    }

    @Override
    public int batchRemoveAsyncBusinessReqByPrimaryKey(List<Long> idList) {
        if (null == idList || idList.isEmpty()) {
            return 0;
        }
        return businessReqMapper.batchRemoveAsyncBusinessReqByPrimaryKey(idList);
    }

    @Override
    public int batchRemoveSyncBusinessReqByPrimaryKey(List<Long> idList) {
        if (null == idList || idList.isEmpty()) {
            return 0;
        }
        return businessReqMapper.batchRemoveSyncBusinessReqByPrimaryKey(idList);
    }

    @Override
    public int batchSaveSyncInfo(List<BusinessReq> syncBusinessReqList) {
        if (null == syncBusinessReqList || syncBusinessReqList.isEmpty()) {
            return 0;
        }
        return businessReqMapper.batchSaveSyncInfo(syncBusinessReqList);
    }

    @Override
    public int batchSaveAsyncInfo(List<BusinessReq> asyncBusinessReqList) {
        if (null == asyncBusinessReqList || asyncBusinessReqList.isEmpty()) {
            return 0;
        }
        return businessReqMapper.batchSaveAsyncInfo(asyncBusinessReqList);
    }

    /**
     * 分发同步信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SyncLog distributeSyncMsg(BusinessReq businessReq) {
        return (SyncLog) distributeMsg(businessReq, "sync");
    }

    /**
     * 分发异步信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AsyncLog distributeAsyncMsg(BusinessReq businessReq) {
        return (AsyncLog) distributeMsg(businessReq, "async");
    }


    private MsgLog distributeMsg(final BusinessReq businessReq, final String serviceType) {
        NotBusinessException notBusinessException = new NotBusinessException();
        notBusinessException.setBatchNo(businessReq.getBatchNo());
        notBusinessException.setBusinessType(businessReq.getBusinessType());
        notBusinessException.setBusinessContent(businessReq.getBusinessContent());
        notBusinessException.setOperation(businessReq.getOperation());
        notBusinessException.setReceivedTime(businessReq.getCreateTime());

        //业务内容为空
        if ("".equals(StringUtils.trimToEmpty(businessReq.getBusinessContent()))) {
            notBusinessException.setStatusDesc("业务内容不能为空");
            //由于业务异常表中业务内容字段不能为null，所以这里设置为空
            notBusinessException.setBusinessContent("");
            throw notBusinessException;
        }

        //判断业务类型是否存在
        final String businessType = StringUtils.trimToEmpty(businessReq.getBusinessType());
        if (!businessTypeMap.containsValue(businessType)) {
            notBusinessException.setStatusDesc("业务类型不存在");
            throw notBusinessException;
        }

        //判断操作类型是否
        final Integer operation = businessReq.getOperation();
        if (null == operation) {
            notBusinessException.setStatusDesc("操作类型不能为空");
            throw notBusinessException;
        }


        //根据业务类型获取对应的服务名
        final String serviceName = BusinessTypeEnum.valueOf(businessType).getServiceName();
        Object object = SpringContextUtil.getBean(serviceName);
        if (null == object) {
            notBusinessException.setStatusDesc("业务类型对应的服务不存在");
            throw notBusinessException;
        }


        //直接强转为服务类，如果这里出现异常，则说明业务类型对应的服务类实现的接口有误，必须处理
        if (serviceType.equals("async")) {
            try {
                IMsgAsyncService service = (IMsgAsyncService) object;
                return service.inputStockAfterMapping(businessReq);
            } catch (ClassCastException e) {
                notBusinessException.setStatusDesc("消息源存储位置有误或业务类型对应的服务类未实现异步消息处理接口");
                throw notBusinessException;
            }
        } else {
            try {
                IMsgSyncService service = (IMsgSyncService) object;
                return service.transportAfterMapping(businessReq);
            } catch (ClassCastException e) {
                notBusinessException.setStatusDesc("消息源存储位置有误或业务类型对应的服务类未实现同步消息处理接口");
                throw notBusinessException;
            }
        }

    }
}
