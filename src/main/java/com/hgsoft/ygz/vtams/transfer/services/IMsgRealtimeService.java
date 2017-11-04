package com.hgsoft.ygz.vtams.transfer.services;


import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;

/**
 * 消息强实时接口<br/>
 * 强实时信息：如车牌唯一性校验，直接发送到部中心，且必须等待返回结果
 *
 * @author 赖少涯
 * @date 2017-10-15
 */
public interface IMsgRealtimeService {

    /**
     * 将强实时信息发送到部中心<br/>
     * 当前方法由切面处理，无需记录日志，但中间步骤失败后，必须抛出业务异常<br/>
     * 这里操作成功后实际返回的也是异常类
     *
     * @param msg 消息内容
     * @throws AsyncException 业务异常
     */
    SyncException transport(BusinessReq msg) throws SyncException;
}
