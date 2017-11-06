package com.hgsoft.ygz.vtams.transfer.service;


import com.hgsoft.ygz.vtams.transfer.common.enums.BusinessTypeEnum;
import com.hgsoft.ygz.vtams.transfer.exception.SyncException;
import com.hgsoft.ygz.vtams.transfer.model.Customer;
import com.hgsoft.ygz.vtams.transfer.model.business.HgReponse;
import com.hgsoft.ygz.vtams.transfer.model.business.MsgResult;
import com.hgsoft.ygz.vtams.transfer.model.business.log.SyncLog;
import com.hgsoft.ygz.vtams.transfer.services.ICommunicationService;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


/**
 * 通信服务测试
 *
 * @author 赖少涯
 * @date 2017-11-04 02:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@Transactional
@Rollback
@ActiveProfiles({"production", "flyway"})
public class CommunicationServiceTest {

    @Autowired
    private ICommunicationService communicationService;

    @Autowired
    private IJsonService jsonService;

    @Test
    public void testSendMsg() {

        Customer customer = new Customer();
        customer.setId("440101");
        customer.setUserName("Sawyer");
        customer.setAddress("address");

        final String jsonStr = jsonService.getString(customer);

        MsgResult msgResult=communicationService.sendMsg(jsonStr, BusinessTypeEnum.USERUPLOAD.name());
        System.out.println(msgResult);

    }

    @Test
    public void testParseHgReponse() {
        String msg = "{\"code\":555,\"msgContent\":{\"info\":\"成功\",\"receiveTime\":\"2017-11-04T08:24:33\",\"result\":\"这是结果?\",\"reason\":\"原因\"}}";

        HgReponse hgReponse = jsonService.getObject(msg, HgReponse.class);
        System.out.println("==========> " + hgReponse);

    }

}
