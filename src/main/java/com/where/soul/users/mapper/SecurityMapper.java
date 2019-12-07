package com.where.soul.users.mapper;

import com.where.soul.users.entity.Security;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.where.soul.users.entity.Users;

/**
 * <p>
 * 用户安全表 Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
public interface SecurityMapper extends BaseMapper<Security> {

    /**
     * 根据电话号码找到用户
     *
     * @param phone 手机号码
     * @return 用户对象
     */
    Users selectUserByPhone(String phone);

    /**
     * 根据邮箱号码找到用户
     *
     * @param email 邮箱
     * @return 用户对象
     */
    Users selectUserByEmail(String email);
}
