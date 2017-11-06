package com.hgsoft.ygz.vtams.transfer.util;


import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * http连接通用类，可通过xml配置属性
 *
 * @author 赖少涯
 * @date 2017-10-30
 */
public class CommunicationUtil implements InitializingBean {

    /**
     * 连接池最大连接数
     */
    private int maxTotal = 200;

    /**
     * 单路由默认最大连接数
     */
    private int defaultPerRoute = 20;

    /**
     * 从连接池获取连接的超时时间，单位毫秒
     */
    private int connectionRequestTimeout = -1;

    /**
     * 通过一个异步线程去创建与服务器的socket连接时的超时时间,单位毫秒
     */
    private int connectTimeout = -1;

    /**
     * socket读数据的超时时间，即从服务器获取响应数据需要等待的时间，单位毫秒
     */
    private int socketTimeout = -1;

    /**
     * 忽略身份验证，默认为true
     */
    private boolean ignoreAuthentication = true;

    /**
     * 设置超时重试次数
     */
    //TODO:自定义实现超时重连机制
    DefaultHttpRequestRetryHandler requestRetryHandler = new DefaultHttpRequestRetryHandler(1, true);

    /**
     * 连接池
     */
    private PoolingHttpClientConnectionManager poolConnManager;

    private transient Logger log = LoggerFactory.getLogger(CommunicationUtil.class);

    @Override
    public void afterPropertiesSet() throws Exception {

        SSLContext sslContext = null;
        HostnameVerifier hostnameVerifier = null;
        if (ignoreAuthentication) {
            //对SSL/TLS，信任所有证书
            sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
                public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                    return true;
                }
            }).build();
            //关闭主机验证
            hostnameVerifier = NoopHostnameVerifier.INSTANCE;
        } else {
            sslContext = SSLContexts.createSystemDefault();
            //默认实现，遵从RFC2818。主机名必须符合指定证书上任意备选名称
            hostnameVerifier = new DefaultHostnameVerifier();
        }

        LayeredConnectionSocketFactory sslcsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", sslcsf)
                .build();

        //初始化连接管理器
        poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        poolConnManager.setMaxTotal(maxTotal);
        poolConnManager.setDefaultMaxPerRoute(defaultPerRoute);
    }

    /**
     * 从连接池中获取一个连接
     */
    public CloseableHttpClient getConnection() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout)
                .setSocketTimeout(socketTimeout).build();

        return HttpClients.custom().setRetryHandler(requestRetryHandler)
                .setConnectionManager(poolConnManager)
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    /**
     * 执行httpUriRequest，该方法会自动释放连接
     *
     * @param request         httpPost or httpGet
     * @param responseHandler 响应处理器，根据需要设置
     * @return 返回具体的类型
     */
    public <T> T execute(HttpUriRequest request, ResponseHandler<T> responseHandler) {
        try {
            return getConnection().execute(request, responseHandler);
        } catch (IOException e) {
            log.warn("Can't not connect to server.", e);
        }
        return null;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getDefaultPerRoute() {
        return defaultPerRoute;
    }

    public void setDefaultPerRoute(int defaultPerRoute) {
        this.defaultPerRoute = defaultPerRoute;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isIgnoreAuthentication() {
        return ignoreAuthentication;
    }

    public void setIgnoreAuthentication(boolean ignoreAuthentication) {
        this.ignoreAuthentication = ignoreAuthentication;
    }
}