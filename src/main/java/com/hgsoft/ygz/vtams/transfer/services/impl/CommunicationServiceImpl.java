package com.hgsoft.ygz.vtams.transfer.services.impl;


import com.hgsoft.ygz.vtams.transfer.model.business.MsgResult;
import com.hgsoft.ygz.vtams.transfer.services.ICommunicationService;
import com.hgsoft.ygz.vtams.transfer.util.CommunicationUtil;
import com.hgsoft.ygz.vtams.transfer.util.DateUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hgsoft.ygz.vtams.transfer.common.enums.BusinessTypeEnum;


/**
 * 通信服务类
 *
 * @author 赖少涯
 * @date 2017-10-17
 */
@Service
public class CommunicationServiceImpl implements ICommunicationService {

    @Autowired
    private CommunicationUtil communicationUtil;

    private final Logger log = LoggerFactory.getLogger(CommunicationServiceImpl.class);

    private final String COMMUNICATION_URL = "http://10.173.232.66:8080/BTVDEXTransferIntime/openApi/standardApi";

    @Override
    public MsgResult sendMsg(final String jsonStr, final String businessType) {

        //根据businessType生成文件名
        final BusinessTypeEnum businessT = BusinessTypeEnum.valueOf(businessType);
        StringBuilder fileNameBuilder = new StringBuilder(businessT.getReqPrefix()).append(businessT.name())
                .append("_REQ_44_").append(DateUtil.format(new Date(), "yyyyMMddHHmmssSSS")).append(".json");
        final String fileName = fileNameBuilder.toString();
        final String md5Str = DigestUtils.md5Hex(jsonStr);

        HttpPost httpPost = new HttpPost(COMMUNICATION_URL);
        // 设置请求头（headers)、请求参数
        httpPost.setHeader("binfile-md5", md5Str);

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("jsonStr", jsonStr));
        nvps.add(new BasicNameValuePair("fileName", fileName));

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            MsgResult msgResult = communicationUtil.execute(httpPost, new ResponseHandler<MsgResult>() {
                @Override
                public MsgResult handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

                    MsgResult msgResult = new MsgResult();

                    //获取响应码、响应内容、响应时间
                    final int statusCode = response.getStatusLine().getStatusCode();
                    msgResult.setResponseCode(statusCode);
                    msgResult.setResponseTime(DateUtil.getCurrentSqlTimestamp());
                    msgResult.setReqFileMd5(md5Str);
                    msgResult.setReqFileName(fileName);

                    if (statusCode >= 200 && statusCode < 300) {
                        HttpEntity entity = response.getEntity();
                        final String content = null != entity ? EntityUtils.toString(entity, "UTF-8") : "";
                        msgResult.setResponseContent(content);
                        msgResult.setStatusDesc(businessT.getDesc() + " 消息发送成功");
                    } else if (statusCode >= 300 && statusCode < 700) {
                        msgResult.setStatusDesc("发送失败:http通信异常");
                    } else if (statusCode >= 700) {
                        HttpEntity entity = response.getEntity();
                        final String content = null != entity ? EntityUtils.toString(entity, "UTF-8") : "";
                        msgResult.setResponseContent(content);
                        msgResult.setStatusDesc("发送失败:" + businessT.getDesc() + " 消息发送失败，请根据状态码查询异常信息");
                    }
                    return msgResult;
                }
            });
            return msgResult;
        } catch (Exception e) {
            e.printStackTrace();
            MsgResult msgResult = new MsgResult();
            StringBuilder tempBuilder = new StringBuilder(100);
            tempBuilder.append("发送失败:").append(businessT.getDesc()).append(" 消息发送失败,可能是").append(e.getMessage());
            msgResult.setStatusDesc(tempBuilder.toString());
            msgResult.setReqFileMd5(md5Str);
            msgResult.setReqFileName(fileName);
            return msgResult;
        }
    }
}
