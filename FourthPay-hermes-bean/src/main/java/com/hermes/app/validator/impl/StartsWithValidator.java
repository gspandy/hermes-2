package com.hermes.app.validator.impl;

import com.hermes.app.validator.StartsWith;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-10-24
 * Time: 下午3:46
 * To change this template use File | Settings | File Templates.
 */
public class StartsWithValidator implements ConstraintValidator<StartsWith, String> {
    private String value;
    public void initialize(StartsWith parameters) {
        value = parameters.value();
    }
    public boolean isValid(String argStr,
                           ConstraintValidatorContext constraintValidatorContext) {
        if(null == argStr){
            return false;
        }
        else if(argStr.length()<1){
            return false;
        }
        else
        {
            return argStr.startsWith(value);
        }
    }
}
