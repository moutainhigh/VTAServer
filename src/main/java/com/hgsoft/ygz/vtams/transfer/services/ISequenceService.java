package com.hgsoft.ygz.vtams.transfer.services;

import com.hgsoft.ygz.vtams.transfer.model.map.*;

/**
 * 序列服务接口：用于获取指定位数的序列
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-19 14:17:21
 * @since 1.7
 */
public interface ISequenceService {

    /**
     * 根据合作机构、地址码、区县码获取服务网点信息顺序码<br/>
     * 该方法使用递归方式获取顺序码，最大尝试次数为3，当第一次失败时会休眠300毫秒
     *
     * @param hallSeq 流动服务网点信息顺序码对象
     * @return 返回4位数的顺序码 or null
     */
    String getHallSeq(HallSeq hallSeq);

    /**
     * 根据合作机构获取流动服务网点信息顺序码<br/>
     * 该方法使用递归方式获取顺序码，最大尝试次数为3，当第一次失败时会休眠300毫秒
     *
     * @param mobileServiceSeq 流动服务网点顺序码对象
     * @return 返回4位数的顺序码 or null
     */
    String getMobileServiceSeq(MobileServiceSeq mobileServiceSeq);

    /**
     * 根据合作机构、地址码、区县码获取自助服务终端顺序码<br/>
     * 该方法使用递归方式获取顺序码，最大尝试次数为3，当第一次失败时会休眠300毫秒
     *
     * @param terminalSeq 自助服务终端顺序码对象
     * @return 返回4位数的顺序码 or null
     */
    String getTerminalSeq(TerminalSeq terminalSeq);

    /**
     * 根据合作机构、渠道方式获取流动服务网点信息顺序码<br/>
     * 该方法使用递归方式获取顺序码，最大尝试次数为3，当第一次失败时会休眠300毫秒
     *
     * @param onlineSeq 线上渠道信息顺序码对象
     * @return 返回2位数的顺序码 or null
     */
    String getOnlineSeq(OnlineSeq onlineSeq);

    /**
     * 根据发行方编号、开户年月日获取顺序码<br/>
     * 该方法使用递归方式获取顺序码，最大尝试次数为3，当第一次失败时会休眠300毫秒
     *
     * @param customerSeq 客户信息顺序码对象
     * @return 返回5位数的顺序码 or null
     */
    String getCustomerSeq(CustomerSeq customerSeq);

    /**
     * 根据渠道类型、渠道编号、年月日时分秒获取顺序码<br/>
     * 该方法使用递归方式获取顺序码，最大尝试次数为3，当第一次失败时会休眠300毫秒
     *
     * @param refundSeq 退款顺序码对象
     * @return 返回2位数的顺序码 or null
     */
    String getRefundSeq(RefundSeq refundSeq);

    /**
     * 根据渠道类型、渠道编号、年月日时分秒获取顺序码<br/>
     * 该方法使用递归方式获取顺序码，最大尝试次数为3，当第一次失败时会休眠300毫秒
     *
     * @param rechargeSeq 充值交易顺序码对象
     * @return 返回2位数的顺序码 or null
     */
    String getRechargeSeq(RechargeSeq rechargeSeq);
}
