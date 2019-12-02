package com.where.soul.common.base;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lw
 */
@Data
public class BaseEntity {

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
