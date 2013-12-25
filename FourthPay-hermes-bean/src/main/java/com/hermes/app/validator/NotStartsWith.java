package com.hermes.app.validator;

import com.hermes.app.validator.impl.NotStartsWithValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 判断一个字符串是否以某个字符串开头
 * @author of821
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {NotStartsWithValidator.class})
public @interface NotStartsWith {
    String message() default "must not start with {value}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default {};
    String value();

    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        NotStartsWith[] value();
    }
}
