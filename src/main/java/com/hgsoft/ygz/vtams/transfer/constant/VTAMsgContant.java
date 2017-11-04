package com.hgsoft.ygz.vtams.transfer.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量配置
 *
 * @author 赖少涯
 * @date 2017-10-14
 */
public class VTAMsgContant {

    /**
     * 消息对象中业务标识的key值
     */
    public static final String MQ_BUSINESS_CODE = "businessCode";

    /**
     * 用户卡、OBU等发行方编号
     */
    public static final String ISSUSER_ID = "440101";

    /**
     * 地区编号
     */
    public static final String AREA_CODE = "4401";

    /**
     * 默认终止日期
     */
    public static final String DEFAULT_END_DATE = "2999-12-31";


    /**
     * 日期格式化形式
     */
    public static final String DATE_PATTERN_YMD = "yyyy-MM-dd";

    /**
     * 网点默认营业时间
     */
    public static final String DEFAULT_BUSINESSHOURS = "周一至周五 8:30-17:30";


    /**
     * 客户证件类型
     * 105-军官证
     * 101-身份证（含临时身份证）
     * 203-营业执照
     * 199-个人证件缺省值
     * 101-身份证（含临时身份证）
     * 202-组织机构代码证
     * 102-护照（限外籍人士）
     * 201-统一社会信用代码证书
     * 103-港澳居民来往内地通行证
     * 104-台湾居民来往大陆通行证
     * 106-武警警察身份证
     */
    public static final Map<String, Integer> idTypeMap = new HashMap<String, Integer>() {
        {
            put("0", 105);
            put("1", 101);
            put("2", 203);
            put("3", 199);
            put("4", 101);
            /*put("5",?);
            put("6",?);*/
            put("7", 202);
            put("8", 102);
            put("9", 201);
            put("10", 103);
            put("11", 104);
            put("12", 106);
        }
    };

}
