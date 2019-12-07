package com.where.soul.users.service;

import com.where.soul.users.entity.Security;
import com.baomidou.mybatisplus.extension.service.IService;
import com.where.soul.users.entity.Users;

/**
 * <p>
 * 用户安全表 服务类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
public interface ISecurityService extends IService<Security> {
    /**
     * 新增安全信息
     *
     * @param security 实体对象
     * @return 0 失败 其余 返回id
     */
    Integer addSecurity(Security security);

    /**
     * 根据电话找到用户
     *
     * @param phone 手机号码
     * @return 用户对象
     */
    Users findUserByPhone(String phone);

    /**
     * 根据邮箱找到用户
     *
     * @param email 邮箱
     * @return 用户对象
     */
    Users findUserByEmail(String email);

    /**
     * 根据用户id更新密码
     *
     * @param userId   用户id
     * @param password 密码
     * @return -1 用户不存在 其余成功
     */
    Integer updateUserPasswordByUserId(Integer userId, String password);
}
