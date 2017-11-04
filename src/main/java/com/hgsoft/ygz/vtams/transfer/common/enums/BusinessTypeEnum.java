package com.hgsoft.ygz.vtams.transfer.common.enums;

/**
 * 业务类型定义：该枚举类型的name值不允许修改
 */
public enum BusinessTypeEnum {
    ISSUERUPLOAD("发行方信息映射", "issuerService"),
    AGENCYUPLOAD("客服合作机构信息映射", "agencyService"),
    HALLUPLOAD("服务网点信息映射", "hallService"),
    MOBILESERVICEUPLOAD("流动服务网点信息映射", "mobileService"),
    TERMINALUPLOAD("自助服务终端信息映射", "terminalService"),
    ONLINEUNLOAD("线上服务渠道信息映射", "onlineService"),
    USERUPLOAD("客户信息映射", "customerService"),
    VEHICLEUPLOAD("客户车辆信息映射", "vehicleService"),
    CARDUPLOAD("记账卡信息映射or储值卡信息映射", "userCardService"),
    OBUUPLOAD("OBU信息映射", "obuService"),
    CARDBLACKLISTUPLOAD("用户卡黑名单", "cardBlackService"),
    OBUBLACKLISTUPLOAD("OBU黑名单", "obuBlackService"),
    RECHARGEUPLOAD("充值交易", "rechargeService"),
    REVERSALUPLOAD("冲正交易映射", "reversalService"),
    REIMBUSREUPLOAD("退款交易映射", "refundService"),
    BALANCEUPLOAD("卡账余额映射", "balanceService"),
    REFEEUPLOAD("退费交易上传", "refeeService"),
    ETCRESTITUTIONUPLOAD("非现金补交交易上传", "etcRestitutionService"),
    OTHERRESTITUTIONUPLOAD("其他补交交易上传", "otherRestitutionService");

    private String desc;

    private String serviceName;

    BusinessTypeEnum(String desc, String serviceName) {
        this.desc = desc;
        this.serviceName = serviceName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
