package com.hgsoft.ygz.vtams.transfer.services;

import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.log.AsyncLog;

/**
 * 消息异步处理接口<br/>
 * 处理异步信息：即非实时信息，映射入库即可
 *
 * @author 赖少涯
 * @date 2017-10-15
 */
public interface IMsgAsyncService {


    /**
     * 将消息映射后存储进数据库
     *
     * @param msg 消息内容
     * @return AsyncLog 异步信息日志
     * @throws AsyncException 业务异常
     */
    AsyncLog inputStockAfterMapping(BusinessReq msg);
}
