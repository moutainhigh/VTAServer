package com.hgsoft.ygz.vtams.transfer.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义正则注解
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-04 14:55:18
 * @since 1.7
 */
public class CustomPatternImpl implements ConstraintValidator<CustomPattern, Object> {


    private String regexp;
    private CustomPattern.Flag[] flags;

    @Override
    public void initialize(CustomPattern constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
        this.flags = constraintAnnotation.flags();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        //如果值为null，则直接返回true.如果需要保证值非空，则配合其他注解一起使用
        if (null == value) {
            return true;
        }

        //判断flag是否存在
        Pattern pattern;
        if (null != flags && flags.length > 0) {
            int flagVal = flags[0].getValue();
            for (int i = 1; i < flags.length; i++) {
                flagVal = flagVal | flags[i].getValue();
            }
            pattern = Pattern.compile(regexp, flagVal);
        } else {
            pattern = Pattern.compile(regexp);
        }
        String target = String.valueOf(value);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }
}
