package com.hgsoft.ygz.vtams.transfer.common.enums;

/**
 * 业务类型定义：该枚举类型的name值不允许修改
 */
public enum BusinessTypeEnum {
    ISSUERUPLOAD("发行方信息映射", "issuerService", "BASIC_"),
    AGENCYUPLOAD("客服合作机构信息映射", "agencyService", "BASIC_"),
    HALLUPLOAD("服务网点信息映射", "hallService", "BASIC_"),
    MOBILESERVICEUPLOAD("流动服务网点信息映射", "mobileService", "BASIC_"),
    TERMINALUPLOAD("自助服务终端信息映射", "terminalService", "BASIC_"),
    ONLINEUNLOAD("线上服务渠道信息映射", "onlineService", "BASIC_"),
    USERUPLOAD("客户信息映射", "customerService", "BASIC_"),
    VEHICLEUPLOAD("客户车辆信息映射", "vehicleService", "BASIC_"),
    CARDUPLOAD("记账卡信息映射or储值卡信息映射", "userCardService", "BASIC_"),
    OBUUPLOAD("OBU信息映射", "obuService", "BASIC_"),
    CARDBLACKLISTUPLOAD("用户卡黑名单", "cardBlackService", "BASIC_"),
    OBUBLACKLISTUPLOAD("OBU黑名单", "obuBlackService", "BASIC_"),
    RECHARGEUPLOAD("充值交易", "rechargeService", "TRANSACTION_"),
    REVERSALUPLOAD("冲正交易映射", "reversalService", "TRANSACTION_"),
    REIMBUSREUPLOAD("退款交易映射", "refundService", "BASIC_"),
    BALANCEUPLOAD("卡账余额映射", "balanceService", "BASIC_"),
    REFEEUPLOAD("退费交易上传", "refeeService", "BASIC_"),
    ETCRESTITUTIONUPLOAD("非现金补交交易上传", "etcRestitutionService", "BASIC_"),
    OTHERRESTITUTIONUPLOAD("其他补交交易上传", "otherRestitutionService", "BASIC_");

    /**
     * 该类型对应的中文描述
     */
    private String desc;

    /**
     * 该类型对应的服务类名称
     */
    private String serviceName;

    /**
     * 该类型对应的请求文件前缀
     */
    private String reqPrefix;

    BusinessTypeEnum(String desc, String serviceName, String reqPrefix) {
        this.desc = desc;
        this.serviceName = serviceName;
        this.reqPrefix = reqPrefix;
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

    public String getReqPrefix() {
        return reqPrefix;
    }

    public void setReqPrefix(String reqPrefix) {
        this.reqPrefix = reqPrefix;
    }
}
