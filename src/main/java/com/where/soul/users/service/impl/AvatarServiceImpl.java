package com.where.soul.users.service.impl;

import com.where.soul.users.entity.Avatar;
import com.where.soul.users.mapper.AvatarMapper;
import com.where.soul.users.service.IAvatarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户头像表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Service
public class AvatarServiceImpl extends ServiceImpl<AvatarMapper, Avatar> implements IAvatarService {

}
