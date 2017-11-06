package com.hgsoft.ygz.vtams.transfer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hgsoft.ygz.vtams.transfer.common.enums.BusinessTypeEnum;
import com.hgsoft.ygz.vtams.transfer.model.Customer;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author laishaoya
 * @date 2017-10-15
 */
public class test {

    private final String COMMUNICATION_URL = "http://10.173.232.66:8080/BTVDEXTransferIntime/openApi/standardApi";


    @Test
    public void testA() {

        Customer customer = new Customer();
        customer.setId("44010117110500001");
        customer.setUserName("Sawyer");
        customer.setUserType(1);
        customer.setAddress("address");

        ObjectMapper objectMapper = new ObjectMapper();


        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        final String md5Str = DigestUtils.md5Hex(jsonStr);

        StringBuilder fileNameBuilder = new StringBuilder(BusinessTypeEnum.USERUPLOAD.getReqPrefix()).append(BusinessTypeEnum.USERUPLOAD.name())
                .append("_REQ_44_").append(DateUtil.format(new Date(), "yyyyMMddHHmmssSSS")).append(".json");
        final String fileName = fileNameBuilder.toString();

        HttpPost httpPost = new HttpPost(COMMUNICATION_URL);
        // 设置请求头（headers)、请求参数
        httpPost.setHeader("binfile-md5", md5Str);

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("jsonStr", jsonStr));
        nvps.add(new BasicNameValuePair("fileName", fileName));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
