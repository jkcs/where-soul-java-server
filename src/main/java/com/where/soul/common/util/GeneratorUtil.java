package com.where.soul.common.util;

import cn.hutool.crypto.SecureUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author lw
 */
@Component
public class GeneratorUtil {

    /**
     * 随机生成一个用户名， 可重复
     * @param arg 字符串
     * @return 用户——
     */
    public static String generatorUsername(String arg) {
        if (StringUtils.isEmpty(arg)) {
            throw new NullPointerException();
        }
        int num = 0;
        char[] chars = arg.toCharArray();
        for (char c : chars) {
            num += c;
        }

        return "用户" + num;
    }

    /**
     * MD5加密
     * @param salt 盐
     * @param str 加密字符
     * @return 密码
     */
    public static String generatorMd5(String salt, String str) {
        return SecureUtil.md5(salt + str).toString();
    }
}
