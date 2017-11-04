package com.hgsoft.ygz.vtams.transfer.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

/**
 * 日期校验实现类
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-02 11:21:46
 * @since 1.7
 */
public class DateCompareImpl implements ConstraintValidator<DateCompare, Date> {


    @Override
    public void initialize(DateCompare constraintAnnotation) {

    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {

        return false;
    }
}
