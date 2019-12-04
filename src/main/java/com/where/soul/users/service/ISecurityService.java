package com.where.soul.users.service;

import com.where.soul.users.entity.Security;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * @param security 实体对象
     * @return 0 失败 其余 返回id
     */
    Integer addSecurity(Security security);
}
