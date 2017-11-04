package com.hgsoft.ygz.vtams.transfer.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.FactoryBean;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * jackson objectMapper
 *
 * @author 赖少涯
 * @date 2017-10-10
 */
public class ObjectMapperFactory implements FactoryBean<ObjectMapper> {
    private ObjectMapper objectMapper;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public ObjectMapper getObject() throws Exception {
        if (this.objectMapper == null) {
            this.objectMapper = new ObjectMapper();
            this.objectMapper.setDateFormat(sdf);
            this.objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        }
        //允许单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //属性名称允许不带双引号
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //忽略无法识别的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return this.objectMapper;
    }

    @Override
    public Class<?> getObjectType() {
        return (this.objectMapper != null ? this.objectMapper.getClass() : null);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
