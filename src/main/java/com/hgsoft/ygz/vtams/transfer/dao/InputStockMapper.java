/**
 * Copyright &copy; 2012-2016 华软开发平台 All rights reserved.
 */
package com.hgsoft.ygz.vtams.transfer.dao;


import com.hgsoft.yfzx.webapp.util.MyBatisRepository;
import com.hgsoft.ygz.vtams.transfer.model.*;

/**
 * 处理入库操作：由于消息入库后，该系统中并不涉及其他的CURD操作，所以在这里统一处理
 *
 * @author 赖少涯
 * @date 2017-10-17
 */
@MyBatisRepository
public interface InputStockMapper {

    /**
     * 保存用户黑名单记录
     *
     * @param cardBlack
     * @return int 受影响的记录数
     */
    int saveCardBlack(CardBlack cardBlack);

    /**
     * 保存客户合作机构信息
     *
     * @param agency
     * @return int 受影响的记录数
     */
    int saveAgency(Agency agency);

    /**
     * 保存服务网点信息
     *
     * @param hall
     * @return int 受影响的记录数
     */
    int saveHall(Hall hall);

    /**
     * 保存流动服务网点信息
     *
     * @param mobileNet
     * @return int 受影响的记录数
     */
    int saveMobileNet(MobileNet mobileNet);

    /**
     * 保存自助服务终端信息
     *
     * @param terminal
     * @return int 受影响的记录数
     */
    int saveTerminal(Terminal terminal);

    /**
     * 保存线上服务渠道信息
     *
     * @param online
     * @return int 受影响的记录数
     */
    int saveOnline(Online online);

    /**
     * 保存发行方信息
     *
     * @param issuer
     * @return int 受影响的记录数
     */
    int saveIssuer(Issuer issuer);

    /**
     * 保存卡帐余额信息
     *
     * @param balance
     * @return int 受影响的记录数
     */
    int saveBalance(Balance balance);

    /**
     * 保存OBU黑名单信息
     *
     * @param obuBlack
     * @return int 受影响的记录数
     */
    int saveObuBlack(ObuBlack obuBlack);

    /**
     * 保存退款交易信息
     *
     * @param refund
     * @return int 受影响的记录数
     */
    int saveRefund(Refund refund);

    /**
     * 保存退费交易信息
     *
     * @param refee
     * @return 受影响的记录数
     */
    int saveRefee(Refee refee);

    /**
     * 保存非现金补交交易信息
     *
     * @param etcRestitution
     * @return 受影响的记录数
     */
    int saveEtcRestitution(EtcRestitution etcRestitution);

    /**
     * 保存其他补交交易信息
     *
     * @param otherRestitution
     * @return 受影响的记录数
     */
    int saveOtherRestitution(OtherRestitution otherRestitution);
}