package cn.ybzy.qihangkt.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AuthClass {
    public String value() default "";
}
