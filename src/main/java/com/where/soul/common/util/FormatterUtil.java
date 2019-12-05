package com.where.soul.common.util;

/**
 * @author lw
 */
public class FormatterUtil {

    public static Integer formatterGender(Boolean b) {
        if (b == null) {
            return null;
        }
        return b ? 1 : 0;
    }

}
