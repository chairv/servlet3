package com.base.servlet;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by tancw on 2016/5/19.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
    String value();
}
