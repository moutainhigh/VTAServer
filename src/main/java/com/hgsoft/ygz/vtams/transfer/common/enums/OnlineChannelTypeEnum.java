package com.hgsoft.ygz.vtams.transfer.common.enums;

/**
 * 线上服务渠道类型
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-26 16:46:30
 * @since 1.7
 */
public enum OnlineChannelTypeEnum {

    webId("网站", "01"),
    appId("APP", "02"),
    wechatId("微信号", "03");

    OnlineChannelTypeEnum(String desc, String value) {
        this.desc = desc;
        this.value = value;
    }

    private String desc;
    private String value;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
