package com.where.soul.users.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.where.soul.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Users extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 头像
     */
    private Integer avatarId;

    /**
     * 性别 0 女 1 男
     */
    private Boolean gender;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 安全绑定的账号
     */
    private Integer usersSecurityId;


}
