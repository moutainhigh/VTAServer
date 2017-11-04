package com.hgsoft.ygz.vtams.transfer.dao;

import com.hgsoft.yfzx.webapp.util.MyBatisRepository;
import com.hgsoft.ygz.vtams.transfer.model.map.*;

/**
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-19 14:27:11
 * @since 1.7
 */
@MyBatisRepository
public interface SequenceMapper {

    /**
     * 获取上一条服务网点信息的顺序码
     *
     * @return 顺序码
     */
    HallSeq getLastHallSeq(HallSeq hallSeq);

    /**
     * 新增一条服务网点顺序码的记录
     *
     * @param hallSeq 服务网点顺序码对象
     * @return 受影响记录数
     */
    int saveHallSeq(HallSeq hallSeq);

    /**
     * 获取上一条流动服务网点信息的顺序码
     *
     * @return 顺序码
     */
    MobileServiceSeq getLastMobileServiceSeq(MobileServiceSeq mobileServiceSeq);

    /**
     * 新增一条流动服务网点顺序码的记录
     *
     * @param mobileServiceSeq 流动服务网点顺序码对象
     * @return 受影响记录数
     */
    int saveMobileServiceSeq(MobileServiceSeq mobileServiceSeq);

    /**
     * 获取上一条自助服务终端信息的顺序码
     *
     * @return 顺序码
     */
    TerminalSeq getLastTerminalSeq(TerminalSeq terminalSeq);

    /**
     * 新增一条自助服务终端信息顺序码的记录
     *
     * @param terminalSeq 自助服务终端顺序码对象
     * @return 受影响记录数
     */
    int saveTerminalSeq(TerminalSeq terminalSeq);

    /**
     * 获取上一条线上服务渠道信息的顺序码
     *
     * @return 顺序码
     */
    OnlineSeq getLastOnlineSeq(OnlineSeq onlineSeq);

    /**
     * 新增一条线上服务渠道信息顺序码的记录
     *
     * @param onlineSeq 线上服务渠道顺序码对象
     * @return 受影响记录数
     */
    int saveOnlineSeq(OnlineSeq onlineSeq);

    /**
     * 获取上一条客户信息的顺序码记录
     *
     * @return 顺序码 or null
     */
    CustomerSeq getLastCustomerSeq(CustomerSeq customerSeq);

    /**
     * 新增一条客户信息顺序码的记录
     *
     * @param customerSeq 客户信息顺序码对象
     * @return 受影响记录数
     */
    int saveCustomerSeq(CustomerSeq customerSeq);

    /**
     * 获取上一条退款信息顺序码记录
     *
     * @param refundSeq 退款信息顺序码对象
     * @return 顺序码 or null
     */
    CustomerSeq getLastRefundSeq(RefundSeq refundSeq);

    /**
     * 新增一条退款信息顺序码的记录
     *
     * @param refundSeq 退款信息顺序码对象
     * @return 受影响记录数
     */
    int saveRefundSeq(RefundSeq refundSeq);

    /**
     * 新增一条充值信息顺序码的记录
     *
     * @param rechargeSeq 充值信息顺序码对象
     * @return 受影响记录数
     */
    int saveRechargeSeq(RechargeSeq rechargeSeq);

    /**
     * 获取上一条充值信息顺序码记录
     *
     * @param rechargeSeq 充值信息顺序码对象
     * @return 顺序码 or null
     */
    RechargeSeq getLastRechargeSeq(RechargeSeq rechargeSeq);
}
