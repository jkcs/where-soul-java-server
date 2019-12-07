package com.where.soul.users.service.impl;

import com.where.soul.users.entity.Avatar;
import com.where.soul.users.mapper.AvatarMapper;
import com.where.soul.users.service.IAvatarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    private final AvatarMapper avatarMapper;

    public AvatarServiceImpl(AvatarMapper avatarMapper) {
        this.avatarMapper = avatarMapper;
    }

    @Override
    public Integer addAvatar(Avatar avatar) {
        avatar.setCreateTime(LocalDateTime.now());
        int insert = avatarMapper.insert(avatar);
        if (insert < 1) {
            return 0;
        }
        return avatar.getId();
    }
}
