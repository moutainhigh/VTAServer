package com.hgsoft.ygz.vtams.transfer.util;

import com.hgsoft.ygz.vtams.transfer.model.business.BalanceMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.HallMiddle;
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
    public void testBalanceMiddle() {
        BalanceMiddle balanceMiddle = new BalanceMiddle();
        balanceMiddle.setFee(100L);
        balanceMiddle.setUserId("12345678901234567890");
        balanceMiddle.setCardId("1234567890123456");
        String result = ValidationUtil.validate(balanceMiddle);
        if ("".equals(result)) {
            System.out.println("校验通过");
        } else {
            System.out.println("校验失败:" + result);
        }

        System.out.println("=========================");
        balanceMiddle.setCardId(null);
        result = ValidationUtil.validate(balanceMiddle);
        if ("".equals(result)) {
            System.out.println("校验通过");
        } else {
            System.out.println("校验失败:" + result);
        }
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

        String result = ValidationUtil.validate(hallMiddle);
        if ("".equals(result)) {
            System.out.println("校验通过");
        } else {
            System.out.println("校验失败:" + result);
        }
    }
}
