package com.qf.endOneKm.annotation;

import java.lang.annotation.*;

@Deprecated
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogonValidate {

}
