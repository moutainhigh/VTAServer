package com.hgsoft.ygz.vtams.transfer.service;

import com.hgsoft.yfzx.webapp.util.IdWorkerInit;
import com.hgsoft.ygz.vtams.transfer.common.enums.BusinessTypeEnum;
import com.hgsoft.ygz.vtams.transfer.model.business.BusinessReq;
import com.hgsoft.ygz.vtams.transfer.services.IBusinessReqService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 业务信息服务类测试
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
public class BusinessReqServiceTest {

    @Autowired
    private IBusinessReqService businessReqService;

    @Test
    public void testFindRealtimeInfo() {
        System.out.println("===============实时信息开始======================");
        final int pageNumber = 1;
        final int pageSize = 100;
        List<BusinessReq> businessReqList = businessReqService.findSyncMsg(pageNumber, pageSize);
        System.out.println(businessReqList);
        System.out.println("=================实时信息开始====================");
    }


    @Test
    public void testNonFindRealtimeInfo() {
        System.out.println("===============非实时信息开始======================");
        final int pageNumber = 1;
        final int pageSize = 100;
        List<BusinessReq> businessReqList = businessReqService.findAsyncMsg(pageNumber, pageSize);
        System.out.println(new Date().getTime());
        System.out.println("时间戳:" + new Timestamp(System.currentTimeMillis()));
        System.out.println("时间戳:" + new Timestamp(new Date().getTime()));
        System.out.println(businessReqList);
        System.out.println("===============非实时信息开始======================");
    }

    @Test
    public void testBusinessType() {
        System.out.println(BusinessTypeEnum.AGENCYUPLOAD.getDesc());
        System.out.println(BusinessTypeEnum.AGENCYUPLOAD.name());
    }


    @Test
    public void testDistributeAsyncMsg() {
        BusinessReq businessReq = new BusinessReq();
        BusinessReq msg = new BusinessReq();
        msg.setBusinessType(BusinessTypeEnum.CARDBLACKLISTUPLOAD.name());
        msg.setCreateTime(new Timestamp(System.currentTimeMillis()));
        msg.setId(1L);
        msg.setOperation(1);
        msg.setBatchNo(IdWorkerInit.generateId());
        businessReqService.distributeAsyncMsg(msg);
    }

    @Test
    public void testDistributeSyncMsg() {
        BusinessReq businessReq = new BusinessReq();
        BusinessReq msg = new BusinessReq();
        msg.setBusinessType(BusinessTypeEnum.CARDUPLOAD.name());
        msg.setCreateTime(new Timestamp(System.currentTimeMillis()));
        msg.setId(1L);
        msg.setOperation(1);
        msg.setBatchNo(IdWorkerInit.generateId());
        businessReqService.distributeSyncMsg(msg);
    }

    @Test
    public void testBatchRemoveSyncBusinessReqByPrimaryKey() {
        List<Long> idList=new ArrayList<>(100);
        for(int i=0;i<100;i++){
            idList.add(Long.valueOf(i));
        }
        businessReqService.batchRemoveSyncBusinessReqByPrimaryKey(idList);
    }

    @Test
    public void testBatchRemoveAsyncBusinessReqByPrimaryKey() {
        List<Long> idList=new ArrayList<>(100);
        for(int i=0;i<100;i++){
            idList.add(Long.valueOf(i));
        }
        businessReqService.batchRemoveAsyncBusinessReqByPrimaryKey(idList);
    }
}
