package com.where.soul.users.service;

import com.where.soul.users.entity.Avatar;
import com.where.soul.users.entity.Security;
import com.where.soul.users.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
public interface IUsersService extends IService<Users> {
    /**
     * 根据登录名和密码获取用户数量
     * @param loginName 登录名
     * @param password 密码
     * @return 0失败 用户 id
     */
    Integer getUserCountByLoginNameAndPassword(String loginName, String password);

    /**
     * 根据登录名获取用户数量
     * @param loginName 登录名
     * @return 用户数量
     */
    Integer getUserCountByLoginName(String loginName);

    /**
     * 新增用户
     * @param loginName 登录名
     * @param password 密码
     * @return 该用户对象
     */
    Users addUser(String loginName, String password);

    /**
     * 更新用户信息
     * @param users 用户实体
     * @return 受影响行数
     */
    Integer updateUserById(Users users);

    /**
     * 更新用户安全信息
     * @param users 用户实体
     * @param security 安全实体
     * @param avatar 头像实体
     * @return 用户对象
     */
    Users updateUserById(Users users, Security security, Avatar avatar);
}
