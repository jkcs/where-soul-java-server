package com.where.soul.common.base;

/**
 * @author lw
 */
public interface BaseErrorInterface  {
    /**
     * 获取错误码
     * @return 错误码
     */
    Integer getResultCode();

    /**
     * 获取错误描述
     * @return 错误描述
     */
    String getResultMessage();
}
