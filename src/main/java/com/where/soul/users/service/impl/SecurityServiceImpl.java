package com.where.soul.users.service.impl;

import com.where.soul.common.util.GeneratorUtil;
import com.where.soul.users.entity.Security;
import com.where.soul.users.entity.Users;
import com.where.soul.users.mapper.SecurityMapper;
import com.where.soul.users.mapper.UsersMapper;
import com.where.soul.users.service.ISecurityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户安全表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Service
public class SecurityServiceImpl extends ServiceImpl<SecurityMapper, Security> implements ISecurityService {

    private final SecurityMapper securityMapper;
    private final UsersMapper usersMapper;

    public SecurityServiceImpl(SecurityMapper securityMapper, UsersMapper usersMapper) {
        this.securityMapper = securityMapper;
        this.usersMapper = usersMapper;
    }

    @Override
    public Integer addSecurity(Security security) {
        security.setCreateTime(LocalDateTime.now());
        int insert = securityMapper.insert(security);
        if (insert < 1) {
            return 0;
        }
        return security.getId();
    }

    @Override
    public Users findUserByPhone(String phone) {
        return securityMapper.selectUserByPhone(phone);
    }

    @Override
    public Users findUserByEmail(String email) {
        return securityMapper.selectUserByPhone(email);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer updateUserPasswordByUserId(Integer userId, String password) {
        Users users = usersMapper.selectById(userId);
        if (users == null) {
            return -1;
        }
        users.setUpdateTime(LocalDateTime.now());
        users.setId(userId);
        users.setPassword(GeneratorUtil.generatorMd5(users.getLoginName(), password));
        return usersMapper.updateById(users);
    }
}
