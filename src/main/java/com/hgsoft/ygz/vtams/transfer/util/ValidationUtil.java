package com.hgsoft.ygz.vtams.transfer.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

/**
 * 校验工具类
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-02 10:10:06
 * @since 1.7
 */
public class ValidationUtil {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**
     * 使用JSR303 Bean Validation规范校验对象
     *
     * @param obj 目标对象
     * @return 如果对象校验通过则返回空字符串，否则返回以分号分隔的错误描述
     */
    public static <T> String validate(T obj) {
        Validator validator = validatorFactory.getValidator();
        return getErrorMsg(validator.validate(obj));
    }

    /**
     * 使用JSR303 Bean Validation规范校验对象
     *
     * @param obj    目标对象
     * @param groups 组别，可以是多个
     * @return 如果对象校验通过则返回空字符串，否则返回以分号分隔的错误描述
     */
    public static <T> String validate(T obj, Class<?>... groups) {
        Validator validator = validatorFactory.getValidator();
        return getErrorMsg(validator.validate(obj, groups));
    }


    /**
     * 获取错误信息
     */
    private static <T> String getErrorMsg(Set<ConstraintViolation<T>> validateResult) {
        StringBuilder errMsgBuilder = new StringBuilder(50);
        if (!validateResult.isEmpty()) {
            Iterator<ConstraintViolation<T>> iterator = validateResult.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation<T> cv = iterator.next();
                errMsgBuilder.append(cv.getMessage()).append(";");
            }
            errMsgBuilder.deleteCharAt(errMsgBuilder.length() - 1);
        }
        return errMsgBuilder.toString();
    }
}
