package com.where.soul.users.service.impl;

import com.where.soul.users.entity.Security;
import com.where.soul.users.mapper.SecurityMapper;
import com.where.soul.users.service.ISecurityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
