package com.hgsoft.ygz.vtams.transfer.services;

import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;

/**
 * 消息准同步同步处理接口<br/>
 * 同步信息：如客户信息上传及变更，映射->发送
 *
 * @author 赖少涯
 * @date 2017-10-15
 */
public interface IMsgSyncService {

    /**
     * 将实时消息映射后直接发送到部中心
     *
     * @param msg 消息内容
     * @return SyncLog 同步信息日志
     * @throws AsyncException 业务异常
     */
    SyncLog transportAfterMapping(BusinessReq msg);
}
