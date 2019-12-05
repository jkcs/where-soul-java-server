package com.where.soul.users.service.impl;

import cn.hutool.Hutool;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.where.soul.common.exception.BizException;
import com.where.soul.common.util.GeneratorUtil;
import com.where.soul.users.entity.Avatar;
import com.where.soul.users.entity.Security;
import com.where.soul.users.entity.Users;
import com.where.soul.users.mapper.UsersMapper;
import com.where.soul.users.service.IAvatarService;
import com.where.soul.users.service.ISecurityService;
import com.where.soul.users.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    private final UsersMapper usersMapper;
    private final IAvatarService avatarService;
    private final ISecurityService securityService;

    public UsersServiceImpl(UsersMapper usersMapper, IAvatarService avatarService, ISecurityService securityService) {
        this.usersMapper = usersMapper;
        this.avatarService = avatarService;
        this.securityService = securityService;
    }

    @Override
    public Integer getUserCountByLoginNameAndPassword(String loginName, String password) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName);
        queryWrapper.eq("password", GeneratorUtil.generatorMd5(loginName, password));
        Users users = usersMapper.selectOne(queryWrapper);
        if (users == null) {
            return 0;
        }
        return users.getId();
    }

    @Override
    public Integer getUserCountByLoginName(String loginName) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName);
        return usersMapper.selectCount(queryWrapper);
    }

    @Override
    public Users addUser(String loginName, String password) {
        Users users = new Users();
        users.setLoginName(loginName);
        users.setPassword(GeneratorUtil.generatorMd5(loginName, password));
        users.setUsername(GeneratorUtil.generatorUsername(loginName));
        users.setCreateTime(LocalDateTime.now());
        int insert = usersMapper.insert(users);
        if (insert < 1) {
            return null;
        }
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName);
        return usersMapper.selectOne(queryWrapper);
    }

    @Override
    public Integer updateUserById(Users users) {
        return usersMapper.updateById(users);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Users updateUserById(Users users, Security security, Avatar avatar) {
        Integer avatarId = users.getAvatarId();
        Integer securityId = users.getUsersSecurityId();
        if (avatarId == null) {
            // 插入 获取 id
            Integer integer = avatarService.addAvatar(avatar);
            if (integer != 0) {
                avatarId = integer;
            } else {
                throw new BizException("操作异常 avatar");
            }
        }
        if (securityId == null) {
            Integer integer = securityService.addSecurity(security);
            if (integer != 0) {
                securityId = integer;
            } else {
                throw new BizException("操作异常 security");
            }
        }

        users.setAvatarId(avatarId);
        users.setUsersSecurityId(securityId);
        usersMapper.updateById(users);
        return usersMapper.selectById(users.getId());
    }


}
