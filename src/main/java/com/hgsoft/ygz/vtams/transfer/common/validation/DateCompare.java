package com.hgsoft.ygz.vtams.transfer.common.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Date;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 日期校验注解
 *
 * @author 赖少涯
 * @version 1.0
 * @date 2017-11-02 11:19:28
 * @since 1.7
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {DateCompareImpl.class})
public @interface DateCompare {

    String message() default "{javax.validation.constraints.DateCompare.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String before() default "";

    String after() default "";


    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        DateCompare[] value();
    }

}
