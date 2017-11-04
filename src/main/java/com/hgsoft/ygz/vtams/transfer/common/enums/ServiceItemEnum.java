package com.hgsoft.ygz.vtams.transfer.common.enums;

/**
 * 服务项目
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-10-26 16:46:30
 * @since 1.7
 */
public enum ServiceItemEnum {

    openAccount("开户", "01"),
    vehicleInfoInput("车辆信息录入", "02"),
    openCard("开卡", "03"),
    registerTag("注册电子标签", "04"),
    installAndActiveTag("安装和激活电子标签", "05"),
    lossCard("卡挂失", "06"),
    handDownAfterLossCard("卡解挂", "07"),
    makeUpCard("补卡", "08"),
    replacementOfCard("换卡", "09"),
    renewalOfCard("卡续期", "10"),
    handUpCard("卡挂起", "11"),
    handDownAfterHandUpCard("卡解除挂起", "12"),
    cancelCard("卡注销", "13"),
    reInstallTag("重新安装电子标签", "14"),
    lossTag("标签挂失", "15"),
    handDownAfterLossTag("标签解挂", "16"),
    makeUpTag("标签补办", "17"),
    replacementOfTag("标签更换", "18"),
    repairOfTag("标签维修", "19"),
    renewalOfTag("标签续期", "20"),
    handUpTag("标签挂起", "21"),
    handUpOnTouchTag("标签接触挂起", "22"),
    transferTag("标签过户", "23"),
    cancelTag("标签注销", "24"),
    rechargeTag("充值", "25"),
    transference("圈存", "26"),
    refee("退费", "27"),
    additionalFeeOfCard("补卡额", "28"),
    backFee("补交", "29"),
    clearAccount("清账", "30"),
    clearBankAccount("清户", "31"),
    refund("退款", "32"),
    query("查询", "33"),
    consult("咨询", "34"),
    dealingWithComplaint("投诉处理", "35"),
    changeInformation("信息变更", "36"),
    destroyAccount("销户", "37");

    ServiceItemEnum(String desc, String value) {
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
