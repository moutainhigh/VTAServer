package com.hgsoft.ygz.vtams.transfer.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.SQLException;

/**
 * 异常处理工具类
 *
 * @author 赖少涯
 * @date 2017-10-17
 */
public class ExceptionUtil {


    /**
     * 当前处理的是sql异常
     * 根据异常获取简要的错误信息
     *
     * @return string 简要信息
     */
    public static String getSimpleMsg(Exception e) {
        String cause = StringUtils.trimToEmpty(e.getMessage());
        String[] causeMsgs = cause.split(":");
        return causeMsgs.length >= 4 ? causeMsgs[3] : e.getClass().getName();
    }
}
