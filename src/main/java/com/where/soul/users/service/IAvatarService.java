package com.where.soul.users.service;

import com.where.soul.users.entity.Avatar;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户头像表 服务类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
public interface IAvatarService extends IService<Avatar> {
    /**
     * 增加头像
     * @param avatar 实体
     * @return 0 失败 其他返回头像id
     */
    Integer addAvatar(Avatar avatar);
}
