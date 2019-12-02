package com.where.soul.users.entity;

import com.where.soul.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户头像表
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("users_avatar")
public class Avatar extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 头像
     */
    private String avatar;
}
