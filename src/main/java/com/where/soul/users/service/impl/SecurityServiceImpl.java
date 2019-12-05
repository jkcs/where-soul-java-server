package com.where.soul.users.service.impl;

import com.where.soul.users.entity.Security;
import com.where.soul.users.entity.Users;
import com.where.soul.users.mapper.SecurityMapper;
import com.where.soul.users.service.ISecurityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public SecurityServiceImpl(SecurityMapper securityMapper) {
        this.securityMapper = securityMapper;
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
        return null;
    }

    @Override
    public Users findUserByEmail(String email) {
        return null;
    }
}
