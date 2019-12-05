package com.where.soul.common.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author lw
 */
@Component
public class GeneratorUtil {
    /**
     * 公共盐
     */
    private static final String PUBLIC_SALT = "where";

    /**
     * AES_KEY
     */
//    private static final byte[] AES_KEY = new byte[]{102, 69, -58, 3, 102, -71, -81, 89, -70, -125, 9, -90, 77, 77, 114, 51};
    private static final byte[] AES_KEY = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();

    /**
     * 随机生成一个用户名， 可重复
     * @param arg 字符串
     * @return 用户 某某某
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
        String string = PUBLIC_SALT + salt + str;
        return SecureUtil.md5(string);
    }

    public static String generatorAesCode(String content) {
        AES aes = SecureUtil.aes(AES_KEY);
        return aes.encryptHex(content);
    }

    public static String decryptAesCode(String content) {
        AES aes = SecureUtil.aes(AES_KEY);
        return aes.decryptStr(content, CharsetUtil.CHARSET_UTF_8);
    }

}
