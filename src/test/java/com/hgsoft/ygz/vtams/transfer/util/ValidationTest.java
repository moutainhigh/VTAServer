package com.hgsoft.ygz.vtams.transfer.util;

import com.hgsoft.ygz.vtams.transfer.common.validation.CustomGroup;
import com.hgsoft.ygz.vtams.transfer.model.business.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;


/**
 * 测试校验工具类
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-02 09:14:50
 * @since 1.7
 */
public class ValidationTest {

    @Test
    public void testAgencyMiddle() {
        AgencyMiddle agencyMiddle = new AgencyMiddle();
        agencyMiddle.setId("44010101001");
        agencyMiddle.setName("飞天之梦");
        agencyMiddle.setContact("Sawyer");
        agencyMiddle.setTel("15813383164");
        agencyMiddle.setEnabledFlag("");
        agencyMiddle.setStartTime(new Date());
        agencyMiddle.setEndTime(DateUtil.parse("2017-11-30", "yyyy-MM-dd"));
        agencyMiddle.setOperation(1);

        Assert.assertEquals("", printResult(agencyMiddle));
    }


    @Test
    public void testBalanceMiddle() {
        BalanceMiddle balanceMiddle = new BalanceMiddle();
        balanceMiddle.setFee(100L);
        balanceMiddle.setUserId("12345678901234567890");
        balanceMiddle.setCardId("1234567890123456");
        printResult(balanceMiddle);
        System.out.println("=========================");
        printResult(balanceMiddle);
    }


    @Test
    public void testCardBlackMiddle() {
        CardBlackMiddle cardBlackMiddle = new CardBlackMiddle();
        cardBlackMiddle.setCardId("1721233205232142");
        cardBlackMiddle.setType(1);
        cardBlackMiddle.setStatus(1);
        cardBlackMiddle.setCreationTime(new Date().getTime());
        printResult(cardBlackMiddle);
    }

    @Test
    public void testCustomerMiddle() {
        CustomerMiddle customerMiddle = new CustomerMiddle();

        customerMiddle.setId("12345678901234567890");
        customerMiddle.setUserType(2);

        customerMiddle.setAddress("飞天之梦一号堡垒");
        customerMiddle.setOperation(1);


        //customerMiddle.setAgentName();
        //customerMiddle.setAgentldNum();
        //customerMiddle.setAgentIdType();
        customerMiddle.setDepartment("SAWYER");


        printResult(customerMiddle, CustomGroup.Primary.class);
        if (customerMiddle.getUserType().equals(2)) {
            printResult(customerMiddle, CustomGroup.Secondary.class);
        }

    }


    @Test
    public void testEtcRestitutionMiddle() {
        EtcRestitutionMiddle etcRestitutionMiddle = new EtcRestitutionMiddle();
        etcRestitutionMiddle.setIdentification(3);
        printResult(etcRestitutionMiddle);
    }

    @Test
    public void testHallMiddle() {
        HallMiddle hallMiddle = new HallMiddle();
        hallMiddle.setAgencyId("44010199999");
        hallMiddle.setOriginalCode("SAWYER001");
        hallMiddle.setCityId("01");
        hallMiddle.setCountyId("01");
        hallMiddle.setName("网点名称");
        hallMiddle.setTel("0762-4557738");
        hallMiddle.setContact("Sawyer");
        hallMiddle.setAddress("地址信息");
        hallMiddle.setServiceItems("01");
        hallMiddle.setStartTime(new Date());
        printResult(hallMiddle);
    }

    @Test
    public void testIssuerMiddle() {
        IssuerMiddle issuerMiddle = new IssuerMiddle();
        printResult(issuerMiddle);
    }

    @Test
    public void testMobileServiceMiddle() {
        MobileServiceMiddle mobileServiceMiddle = new MobileServiceMiddle();
        mobileServiceMiddle.setAgencyId("44010199999");
        printResult(mobileServiceMiddle);
    }


    @Test
    public void testObuBlackMiddle() {
        ObuBlackMiddle obuBlackMiddle = new ObuBlackMiddle();
        obuBlackMiddle.setType(5);
        printResult(obuBlackMiddle);
    }

    @Test
    public void testObuMiddle() {
        ObuMiddle obuMiddle = new ObuMiddle();
        obuMiddle.setInstallType(3);
        printResult(obuMiddle);
    }

    @Test
    public void testOtherRestitutionMiddle() {
        OtherRestitutionMiddle otherRestitutionMiddle = new OtherRestitutionMiddle();
        otherRestitutionMiddle.setEffectiveTime("123");
        otherRestitutionMiddle.setIdentification(3);
        printResult(otherRestitutionMiddle);
    }

    @Test
    public void testReversalMiddle() {
        ReversalMiddle reversalMiddle = new ReversalMiddle();
        printResult(reversalMiddle);
    }

    @Test
    public void testTerminalMiddle() {
        TerminalMiddle terminalMiddle = new TerminalMiddle();
        printResult(terminalMiddle);
    }

    @Test
    public void testUserCardMiddle() {
        UserCardMiddle userCardMiddle = new UserCardMiddle();
        printResult(userCardMiddle);
    }


    @Test
    public void testVehicleMiddle() {
        VehicleMiddle vehicleMiddle = new VehicleMiddle();
        printResult(vehicleMiddle);
    }

    /**
     * 打印校验结果
     *
     * @param obj
     * @param <T>
     */
    private <T> String printResult(T obj, Class<?>... groups) {
        String result;
        if (null == groups) {
            result = ValidationUtil.validate(obj);
        } else {
            result = ValidationUtil.validate(obj, groups);
        }
        if ("".equals(result)) {
            System.out.println("校验通过");
        } else {
            System.out.println("校验失败:" + result);
        }
        return result;
    }

}
