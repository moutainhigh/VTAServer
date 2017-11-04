package com.hgsoft.ygz.vtams.transfer.service;

import com.hgsoft.ygz.vtams.transfer.model.map.CustomerMapping;
import com.hgsoft.ygz.vtams.transfer.model.map.PointMapping;
import com.hgsoft.ygz.vtams.transfer.model.map.RechargeMapping;
import com.hgsoft.ygz.vtams.transfer.services.IMappingService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * 映射关系维护接口测试
 *
 * @author 赖少涯
 * @date 2017-10-20 01:32:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@Transactional
@Rollback
@ActiveProfiles({"production", "flyway"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MappingServiceTest {

    @Autowired
    private IMappingService mappingService;

    @Test
    public void testBGetPointMapping() {
        PointMapping pointMapping = mappingService.getPointMapping("SAWYER001");
        System.out.println("=================>" + pointMapping);
    }

    @Test
    public void testASavePointMapping() {
        PointMapping pointMapping = new PointMapping();
        pointMapping.setOldPointCode("SAWYER001");
        pointMapping.setNewPointCode("4401010100101010001");
        pointMapping.setStatus(1);
        pointMapping.setCreationDate(new Date());
        pointMapping.setAgencyId("44010199999");
        pointMapping.setChannelType("01");
        int effectiveCount = mappingService.savePointMapping(pointMapping);
        Assert.assertEquals(1, effectiveCount);
    }

    @Test
    public void testBGetCustomerMapping() {
        System.out.println("===========testBGetCustomerMapping===============");
        CustomerMapping customerMapping = mappingService.getCustomerMapping("20171102141931257087");
        System.out.println("运行结果" + customerMapping);
    }

    @Test
    public void testASaveCustomerMapping() {
        System.out.println("===========testASaveCustomerMapping===============");
        CustomerMapping customerMapping = new CustomerMapping();
        customerMapping.setOldUserNo("20171102141931257087");
        customerMapping.setNewUserNo("44010117110200001");
        customerMapping.setStatus(1);
        customerMapping.setCreationDate(new Date());
        int effectiveCount = mappingService.saveCustomerMapping(customerMapping);
        Assert.assertEquals(1, effectiveCount);
    }


    @Test
    public void testBGetRechargeMapping() {
        System.out.println("===========testBGetRechargeMapping===============");
        RechargeMapping rechargeMapping = mappingService.getRechargeMapping("1440101000120171104020701");
        System.out.println("运行结果" + rechargeMapping);
    }

    @Test
    public void testASaveRechargeMapping() {
        System.out.println("===========testASaveRechargeMapping===============");
        RechargeMapping rechargeMapping = new RechargeMapping();
        rechargeMapping.setOldRechargeId("11945");
        rechargeMapping.setNewRechargeId("1440101000120171104020701");
        rechargeMapping.setStatus(1);
        rechargeMapping.setCreationDate(new Date());
        int effectiveCount = mappingService.saveRechargeMapping(rechargeMapping);
        Assert.assertEquals(1, effectiveCount);
    }

}
