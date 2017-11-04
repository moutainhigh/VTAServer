package com.hgsoft.ygz.vtams.transfer.services;

import com.hgsoft.ygz.vtams.transfer.exception.SyncException;

/**
 * 通信接口
 *
 * @author 赖少涯
 * @date 2017-10-17
 */
public interface ICommunicationService {

    /**
     * 将消息发往部中心
     *
     * @param jsonStr      需要发送的json消息
     * @param businessType 业务类型
     * @return SyncException 返回自定义的异常日志类型
     */
    SyncException sendMsg(String jsonStr, String businessType);
}
