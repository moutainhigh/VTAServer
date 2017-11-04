package com.hgsoft.ygz.vtams.transfer.services;

/**
 * json服务类
 *
 * @author laishoaya
 * @date 2017-10-17
 */
public interface IJsonService {
    <T> T getObject(byte[] src, Class<T> valueType);

    <T> T getObject(String src, Class<T> valueType);

    String getString(Object object);
}
