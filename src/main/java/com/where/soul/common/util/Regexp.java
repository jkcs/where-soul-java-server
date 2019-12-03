package com.where.soul.common.util;


/**
 * @author lw
 */
public class Regexp {

    /**
     * 性别正则
     */
    public static final String GENDER = "^[0|1]$";

    /**
     * 手机号码正则
     */
    public static final String PHONE = "^1[3-9]\\d{9}$";

    /**
     * 邮箱正则
     */
    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
}
