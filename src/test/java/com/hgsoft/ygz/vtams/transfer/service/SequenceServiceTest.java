package com.hgsoft.ygz.vtams.transfer.service;

import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.model.map.*;
import com.hgsoft.ygz.vtams.transfer.services.ISequenceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 序列服务类测试
 *
 * @author 赖少涯
 * @date 2017-10-19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@Transactional
@Rollback
@ActiveProfiles({"production", "flyway"})
public class SequenceServiceTest {

    private final Logger log = LoggerFactory.getLogger(SequenceServiceTest.class);

    @Autowired
    private ISequenceService sequenceService;

    @Test
    @Repeat(2)
    public void testGetHallSeq() {
        log.info("==============testGetHallSeq==============================");
        Date curDate = new Date();
        HallSeq hallSeq = new HallSeq();
        hallSeq.setAgencyId("12345678903");
        hallSeq.setCityCode("01");
        hallSeq.setDistrictCode("01");
        hallSeq.setLastUpdateDate(curDate);
        hallSeq.setCreationDate(curDate);
        hallSeq.setStatus(1);
        String seqStr = sequenceService.getHallSeq(hallSeq);
        log.info("============testGetHallSeq本次最终结果hallSeq:{}，耗时：{}", seqStr, System.currentTimeMillis() - curDate.getTime());
    }

    @Test
    @Repeat(2)
    public void testGetMobileServiceSeq() {
        log.info("==============testGetMobileServiceSeq==============================");
        Date curDate = new Date();
        MobileServiceSeq mobileServiceSeq = new MobileServiceSeq();
        mobileServiceSeq.setAgencyId("12345678901");
        mobileServiceSeq.setLastUpdateDate(curDate);
        mobileServiceSeq.setCreationDate(curDate);
        mobileServiceSeq.setStatus(1);
        String seqStr = sequenceService.getMobileServiceSeq(mobileServiceSeq);
        log.info("============testGetMobileServiceSeq本次最终结果hallSeq:{}，耗时：{}", seqStr, System.currentTimeMillis() - curDate.getTime());
    }

    @Test
    @Repeat(2)
    public void testGetTerminalSeq() {
        log.info("==============testGetTerminalSeq==============================");
        Date curDate = new Date();
        TerminalSeq terminalSeq = new TerminalSeq();
        terminalSeq.setAgencyId("12345678901");
        terminalSeq.setCityCode("01");
        terminalSeq.setDistrictCode("01");
        terminalSeq.setLastUpdateDate(curDate);
        terminalSeq.setCreationDate(curDate);
        terminalSeq.setStatus(1);
        String seqStr = sequenceService.getTerminalSeq(terminalSeq);
        log.info("============testGetTerminalSeq本次最终结果hallSeq:{}，耗时：{}", seqStr, System.currentTimeMillis() - curDate.getTime());
    }

    @Test
    @Repeat(2)
    public void testGetOnlineSeq() {
        log.info("==============testGetOnlineSeq==============================");
        Date curDate = new Date();
        OnlineSeq onlineSeq = new OnlineSeq();
        onlineSeq.setAgencyId("12345678901");
        onlineSeq.setChannel("01");
        onlineSeq.setLastUpdateDate(curDate);
        onlineSeq.setCreationDate(curDate);
        onlineSeq.setStatus(1);
        String seqStr = sequenceService.getOnlineSeq(onlineSeq);
        log.info("============testGetOnlineSeq本次最终结果hallSeq:{}，耗时：{}", seqStr, System.currentTimeMillis() - curDate.getTime());
    }

    @Test
    @Repeat(2)
    public void testGetCustomerSeq() {
        log.info("==============testGetCustomerSeq==============================");
        Date curDate = new Date();
        CustomerSeq customerSeq = new CustomerSeq();
        customerSeq.setIssuerId(VTAMsgContant.ISSUER_ID);
        customerSeq.setYearMonthDay("171102");
        customerSeq.setLastUpdateDate(curDate);
        customerSeq.setCreationDate(curDate);
        customerSeq.setStatus(1);
        String seqStr = sequenceService.getCustomerSeq(customerSeq);
        log.info("============testGetCustomerSeq 本次最终结果:{}，耗时：{}", seqStr, System.currentTimeMillis() - curDate.getTime());
    }

    @Test
    @Repeat(2)
    public void testGetRefundSeq() {
        log.info("==============testGetRefundSeq==============================");
        Date curDate = new Date();
        RefundSeq refundSeq = new RefundSeq();
        refundSeq.setChannelType("01");
        refundSeq.setPointCode("440101010001");
        refundSeq.setDateStr("20171104020203");
        refundSeq.setStatus(1);
        refundSeq.setCreationDate(new Date());
        String seqStr = sequenceService.getRefundSeq(refundSeq);
        log.info("============testGetRefundSeq 本次最终结果:{}，耗时：{}", seqStr, System.currentTimeMillis() - curDate.getTime());
    }

    @Test
    @Repeat(2)
    public void testGetRechargeSeq() {
        log.info("==============testGetRechargeSeq==============================");
        Date curDate = new Date();
        RechargeSeq rechargeSeq = new RechargeSeq();
        rechargeSeq.setChannelType("01");
        rechargeSeq.setPointCode("440101010001");
        rechargeSeq.setDateStr("20171104020203");
        rechargeSeq.setStatus(1);
        rechargeSeq.setCreationDate(new Date());
        String seqStr = sequenceService.getRechargeSeq(rechargeSeq);
        log.info("============testGetRechargeSeq 本次最终结果:{}，耗时：{}", seqStr, System.currentTimeMillis() - curDate.getTime());
    }
}
