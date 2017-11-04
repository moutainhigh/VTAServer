package com.hgsoft.ygz.vtams.transfer.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hgsoft.ygz.vtams.transfer.services.IJsonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * json处理类：主要用于处理json数据，包括但不限于objectToStr，strToObject等<br/>
 * 便于统一处理，如果有需要可以自定义实现IJsonService接口
 *
 * @author 赖少涯
 * @date 2017-10-17
 */
@Service
public class JsonServiceImpl implements IJsonService {

    @Resource(name = "objectMapper")
    private ObjectMapper objectMapper;

    @Override
    public <T> T getObject(byte[] src, Class<T> valueType) {
        T result = null;
        try {
            result = objectMapper.readValue(src, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public <T> T getObject(String src, Class<T> valueType) {
        T result = null;
        try {
            result = objectMapper.readValue(src, valueType);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getString(Object object) {
        String result = "";
        if (null == object) {
            return result;
        }

        try {
            result = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}
