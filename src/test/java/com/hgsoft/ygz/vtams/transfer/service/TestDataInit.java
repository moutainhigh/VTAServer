package com.hgsoft.ygz.vtams.transfer.service;

import com.hgsoft.ygz.vtams.transfer.common.enums.BusinessTypeEnum;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.CardBlackMiddle;
import com.hgsoft.ygz.vtams.transfer.services.IBusinessReqService;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试数据准备
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-04 09:10:57
 * @since 1.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@Transactional
@Rollback(false)
@ActiveProfiles({"production", "flyway"})
public class TestDataInit {

    @Autowired
    private IBusinessReqService businessReqService;

    @Autowired
    private IJsonService jsonService;

    @Test
    public void testPreparingAsyncData() {
        List<BusinessReq> asyncList = new ArrayList<>(100);

        CardBlackMiddle cardBlackMiddle = new CardBlackMiddle();
        cardBlackMiddle.setCardId("1721233205232142");
        cardBlackMiddle.setType(1);
        cardBlackMiddle.setStatus(1);
        cardBlackMiddle.setCreationTime(new Date().getTime());

        for (int i = 0; i < 100; i++) {
            BusinessReq businessReq = new BusinessReq();
            businessReq.setOperation(1);
            businessReq.setCreateTime(DateUtil.getCurrentSqlTimestamp());
            businessReq.setBusinessType(BusinessTypeEnum.CARDBLACKLISTUPLOAD.name());
            businessReq.setBusinessContent(jsonService.getString(cardBlackMiddle));
            asyncList.add(businessReq);
        }

        businessReqService.batchSaveAsyncInfo(asyncList);
    }


    /**
     * @param type
     */
    public void dataSource(String type) {

    }

}
