package com.hgsoft.ygz.vtams.transfer.service;

import com.hgsoft.ygz.vtams.transfer.common.enums.BusinessTypeEnum;
import com.hgsoft.ygz.vtams.transfer.exception.AsyncException;
import com.hgsoft.ygz.vtams.transfer.model.business.AgencyMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.model.business.HallMiddle;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.services.IAgencyService;
import com.hgsoft.ygz.vtams.transfer.services.IHallService;
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

import java.util.Date;


/**
 * 服务网点、自助服务终端、流动服务网点、线上服务网点
 *
 * @author 赖少涯
 * @date 2017-10-15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@Transactional
@Rollback
@ActiveProfiles({"production", "flyway"})
public class ServiceTest {

    @Autowired
    private IAgencyService agencyService;

    @Autowired
    private IHallService hallService;

    @Autowired
    private IJsonService jsonService;

    @Test(expected = AsyncException.class)
    public void testInputStockAfterMappingForAgency() {
        AgencyMiddle agencyMiddle = new AgencyMiddle();
        agencyMiddle.setId("44010199999");
        agencyMiddle.setContact("Sawyer");
        agencyMiddle.setTel("15813383164");
        agencyMiddle.setOperation(1);
        agencyMiddle.setStartTime(new Date());

        BusinessReq asyncBusinessReq = new BusinessReq();
        asyncBusinessReq.setBatchNo(10000L);
        asyncBusinessReq.setBusinessContent(jsonService.getString(agencyMiddle));
        asyncBusinessReq.setBatchNo(10000L);
        asyncBusinessReq.setBusinessType(BusinessTypeEnum.AGENCYUPLOAD.name());
        asyncBusinessReq.setCreateTime(DateUtil.getCurrentSqlTimestamp());
        agencyService.inputStockAfterMapping(asyncBusinessReq);
    }

    @Test
    public void testInputStockAfterMappingForHall() {
        HallMiddle hallMiddle = new HallMiddle();
        hallMiddle.setAgencyId("44010199999");
        hallMiddle.setCityId("01");
        hallMiddle.setCountyId("01");
        hallMiddle.setAddress("飞天之梦1号城堡");
        hallMiddle.setContact("Sawyer");
        hallMiddle.setName("飞天之梦1号网点");
        hallMiddle.setTel("15813383164");
        hallMiddle.setOriginalCode("SAWYER003");
        hallMiddle.setStartTime(new Date());
        hallMiddle.setEndTime(new Date());
        hallMiddle.setBusinessHours("周一到周五:07:30 -- 18:30");
        hallMiddle.setServiceItems("01,02,03");

        BusinessReq asyncBusinessReq = new BusinessReq();
        asyncBusinessReq.setBusinessContent(jsonService.getString(hallMiddle));
        asyncBusinessReq.setBatchNo(10000L);
        asyncBusinessReq.setBusinessType(BusinessTypeEnum.HALLUPLOAD.name());
        asyncBusinessReq.setCreateTime(DateUtil.getCurrentSqlTimestamp());
        asyncBusinessReq.setOperation(1);

        try {
            hallService.inputStockAfterMapping(asyncBusinessReq);
        } catch (AsyncException e) {
            System.out.println(e.getStatusDesc());
        }

    }

}
