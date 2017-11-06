package com.hgsoft.ygz.vtams.transfer.dao;

import com.hgsoft.ygz.vtams.transfer.constant.VTAMsgContant;
import com.hgsoft.ygz.vtams.transfer.model.*;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 入库dao层测试
 *
 * @author 赖少涯
 * @date 2017-10-17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@Transactional
@Rollback
@ActiveProfiles({"production", "flyway"})
public class InputStockMapperTest {

    @Autowired
    private InputStockMapper inputStockMapper;

    @Test
    public void testSaveCardBlack() {

        CardBlack cardBlack = new CardBlack();
        cardBlack.setStatus(1);
        cardBlack.setType(1);
        cardBlack.setCreationTime(DateUtil.format(new Date()));
        cardBlack.setIssuerId(VTAMsgContant.ISSUER_ID);
        cardBlack.setCardId(VTAMsgContant.AREA_CODE + "7353528977222288");
        final int effectiveCount = inputStockMapper.saveCardBlack(cardBlack);
        Assert.assertEquals(effectiveCount, 1);

    }


    @Test
    public void testSaveAgency() {
        Agency agency = new Agency();
        agency.setId("44010199999");
        agency.setContact("Sawyer");
        agency.setTel("15813383164");
        agency.setStartTime(DateUtil.format(new Date(), VTAMsgContant.DATE_PATTERN_YMD));
        agency.setEndTime(VTAMsgContant.DEFAULT_END_DATE);
        agency.setOperation(1);
        agency.setName("飞天之梦");
        int effectiveCount = inputStockMapper.saveAgency(agency);
        Assert.assertEquals(1, effectiveCount);
    }

    @Test
    public void testSaveHall() {
        Hall hall = new Hall();
        hall.setId("4401019999901010002");
        hall.setBusinessHours("周一到周五:08:30 -- 18:30");
        hall.setStartTime("2017-11-01");
        hall.setEndTime("2017-12-31");
        hall.setTel("15813383164");
        hall.setName("服务网点名称");
        hall.setContact("Sawyer");
        hall.setServiceItems("01,02,03");
        hall.setOperation(1);
        hall.setAddress("地址");
        int effectiveCount = inputStockMapper.saveHall(hall);
        Assert.assertEquals(1, effectiveCount);
    }


    @Test
    public void testSaveMobileNet() {
        MobileNet mobileNet = new MobileNet();
        mobileNet.setId("440101999990001");
        mobileNet.setStartTime("2017-11-01");
        mobileNet.setEndTime("2017-12-31");
        mobileNet.setOperation(1);
        int effectiveCount = inputStockMapper.saveMobileNet(mobileNet);
        Assert.assertEquals(1, effectiveCount);
    }

    @Test
    public void testSaveTerminal() {
        Terminal terminal = new Terminal();
        terminal.setId("4401019999901010002");
        terminal.setBusinessHours("周一到周五:08:30 -- 18:30");
        terminal.setStartTime("2017-11-01");
        terminal.setEndTime("2017-12-31");
        terminal.setAddress("地址");
        terminal.setServiceItems("01,02,03");
        terminal.setOperation(1);
        int effectiveCount = inputStockMapper.saveTerminal(terminal);
        Assert.assertEquals(1, effectiveCount);
    }

    @Test
    public void testSaveOnline() {
        Online online = new Online();
        online.setId("440101999990101");
        online.setStartTime("2017-11-01");
        online.setEndTime("2017-12-31");
        online.setServiceItems("01,02,03");
        online.setOperation(1);
        online.setName("名称");
        int effectiveCount = inputStockMapper.saveOnline(online);
        Assert.assertEquals(1, effectiveCount);
    }

    @Test
    public void testSaveRefund() {
        Refund refund = new Refund();
        refund.setId("1440101011234512");
        refund.setUserId("44010117110300001");
        refund.setCardId("44012017101411284492");
        refund.setFee(10000L);
        int effectiveCount = inputStockMapper.saveRefund(refund);
        Assert.assertEquals(1, effectiveCount);
    }

    @Test
    public void testSaveObuBlack() {
        ObuBlack obuBlack = new ObuBlack();
        obuBlack.setIssuerId(VTAMsgContant.ISSUER_ID);
        obuBlack.setCreationTime(DateUtil.format(new Date()));
        obuBlack.setType(1);
        obuBlack.setObuId("4401011234567890");
        obuBlack.setStatus(1);
        int effectiveCount = inputStockMapper.saveObuBlack(obuBlack);
        Assert.assertEquals(1, effectiveCount);
    }

    @Test
    public void testSaveIssuer() {
        Issuer issuer = new Issuer();
        issuer.setId("440101");
        issuer.setContact("Sawyer");
        issuer.setName("广东联合电子");
        issuer.setAddress("广州天河区星光映景3A");
        issuer.setTel("15813383164");
        issuer.setStartTime("2017-11-03");
        issuer.setEndTime("2017-12-31");
        issuer.setOperation(1);
        int effectiveCount = inputStockMapper.saveIssuer(issuer);
        Assert.assertEquals(1, effectiveCount);
    }

    @Test
    public void testSaveRefee() {
        Refee refee = new Refee();
        refee.setId("G00051100200101010010201612011512340111");
        refee.setType(1);
        refee.setFee(-1000L);
        refee.setEffectiveTime("2017-11-02T19:51:32");
        refee.setSplitTime("2017-11-02T19:51:32");
        refee.setSectionCount(4);
        refee.setDetails("details");
        refee.setIdentification(1);
        refee.setOrderNum("orderNum");
        int effectiveCount = inputStockMapper.saveRefee(refee);
        Assert.assertEquals(1, effectiveCount);
    }


    @Test
    public void testSaveEtcRestitution() {
        EtcRestitution etcRestitution = new EtcRestitution();
        etcRestitution.setId("G00051100200101010010201612011512340111");
        etcRestitution.setFee(-1000L);
        etcRestitution.setEffectiveTime("2017-11-02T19:51:32");
        etcRestitution.setSplitTime("2017-11-02T19:51:32");
        etcRestitution.setSectionCount(4);
        etcRestitution.setDetails("details");
        etcRestitution.setIdentification(1);
        etcRestitution.setOrderNum("orderNum");
        int effectiveCount = inputStockMapper.saveEtcRestitution(etcRestitution);
        Assert.assertEquals(1, effectiveCount);
    }

    @Test
    public void testSaveOtherRestitution() {
        OtherRestitution otherRestitution = new OtherRestitution();
        otherRestitution.setId("G00051100200101010010201612011512340111");
        otherRestitution.setFee(-1000L);
        otherRestitution.setEffectiveTime("2017-11-02T19:51:32");
        otherRestitution.setSplitTime("2017-11-02T19:51:32");
        otherRestitution.setSectionCount(4);
        otherRestitution.setDetails("details");
        otherRestitution.setIdentification(1);
        otherRestitution.setVehicleId("vehicleId");
        otherRestitution.setVehicleType(1);
        otherRestitution.setOrderNum("orderNum");
        otherRestitution.setEnTollLaneId("enTollLaneId");
        int effectiveCount = inputStockMapper.saveOtherRestitution(otherRestitution);
        Assert.assertEquals(1, effectiveCount);
    }

}
