package com.hgsoft.ygz.vtams.transfer.dao;

import com.hgsoft.ygz.vtams.transfer.model.map.*;
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
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-19 10:43:46
 * @since 1.7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath*:spring*.xml"})
@Transactional
@Rollback
@ActiveProfiles({"production", "flyway"})
public class MappingMapperTest {

    @Autowired
    private MappingMapper mappingMapper;

    @Test
    public void testPointMapping() {
        PointMapping pointMapping = new PointMapping();
        pointMapping.setOldPointCode("SAWYER003");
        pointMapping.setNewPointCode("4401010100101010001");
        pointMapping.setStatus(1);
        pointMapping.setCreationDate(new Date());
        pointMapping.setAgencyId("44010199999");
        pointMapping.setChannelType("01");
        int insertCount = mappingMapper.savePointMapping(pointMapping);
        Assert.assertEquals(1, insertCount);

        PointMapping pointMapping2 = mappingMapper.getPointMapping("SAWYER003");
        Assert.assertEquals(pointMapping, pointMapping2);
    }

}
