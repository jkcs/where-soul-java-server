package com.where.soul.common.util;


import com.where.soul.common.Constant;

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

    /**
     * 密码正则
     */
    public static final String PASSWORD = "^(?!([^(0-9a-zA-Z)])+$).{" + Constant.MIN_PASSWORD + "," + Constant.MAX_PASSWORD + "}$";

    /**
     * 时间日期正则 YYYY-MM-DD HH:mm:ss 格式 或 YYYY-MM-DD 格式
     */
    public static final String DATE_TIME = "^[1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9]$";
}
