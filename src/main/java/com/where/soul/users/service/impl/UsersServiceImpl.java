package com.where.soul.users.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.where.soul.users.entity.Avatar;
import com.where.soul.users.entity.Security;
import com.where.soul.users.entity.Users;
import com.where.soul.users.mapper.UsersMapper;
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

    public UsersServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public Integer getUserCountByLoginNameAndPassword(String loginName, String password) {
        QueryWrapper<Users> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("login_name", loginName);
        queryWrapper.eq("password", password);
        return usersMapper.selectCount(queryWrapper);
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
        users.setPassword(password);
        users.setCreateTime(LocalDateTime.now());
        users.setUpdateTime(LocalDateTime.now());
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
    @Transactional(propagation= Propagation.REQUIRED, rollbackFor = Exception.class)
    public Users updateUserById(Users users, Security security, Avatar avatar) {
        Integer avatarId = users.getAvatarId();
        Integer securityId = users.getUsersSecurityId();
        if (avatarId == null) {
            // 插入 获取 id
            users.setAvatarId(avatar.getId());
        }
        if (securityId == null) {
            users.setUsersSecurityId(security.getId());
        }
//        if ()


        return null;
    }


}
