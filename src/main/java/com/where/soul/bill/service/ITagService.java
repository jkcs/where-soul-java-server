package com.where.soul.bill.service;

import com.where.soul.bill.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 账单标签表 服务类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
public interface ITagService extends IService<Tag> {

    /**
     * 插入标签
     *
     * @param userId   用户id
     * @param name     标签名称
     * @param parentId 父标签id
     * @return -1失败, 其余成功
     */
    Integer insetTag(Integer userId, String name, Integer parentId);

    /**
     * 检查标签是否重复
     *
     * @param userId   用户id
     * @param name     标签名称
     * @param parentId 父标签id
     * @return -1失败, 其余成功
     */
    Boolean isTagRepeat(Integer userId, String name, Integer parentId);

    /**
     * 搜索用户的所有标签
     *
     * @param userId   用户id
     * @return List<Tag>
     */
    List<Tag> selectListByUserId(Integer userId);
}
