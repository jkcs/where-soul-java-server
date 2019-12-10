package com.where.soul.bill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.where.soul.bill.entity.Tag;
import com.where.soul.bill.mapper.TagMapper;
import com.where.soul.bill.service.ITagService;
import com.where.soul.common.Constant;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.where.soul.users.service.IUsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 账单标签表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    private final IUsersService usersService;
    private final TagMapper tagMapper;

    public TagServiceImpl(IUsersService usersService, TagMapper tagMapper) {
        this.usersService = usersService;
        this.tagMapper = tagMapper;
    }

    /**
     * 生成结构链
     *
     * @return pid_pid...
     */
    private String buildStructureChain(Integer parentId) {
        if (StringUtils.isEmpty(parentId)) {
            return "";
        }
        Tag tag = tagMapper.selectById(parentId);
        if (tag.getParentId() == null) {
            return String.valueOf(parentId);
        }

        if (tag.getParentId().equals(parentId)) {
            return "";
        }

        String pId = String.valueOf(tag.getParentId());
        return buildStructureChain(Integer.valueOf(pId)) + Constant.UNDERLINE + parentId;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer insetTag(Integer userId, String name, Integer parentId) {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setParentId(parentId);
        tag.setUserId(userId);
        tag.setCreateTime(LocalDateTime.now());
        tag.setStructureChain(buildStructureChain(parentId));
        return tagMapper.insert(tag);
    }

    @Override
    public Boolean isTagRepeat(Integer userId, String name, Integer parentId) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<Tag>();
        wrapper.eq("name", name).eq("user_id", userId);
        if (parentId != null) {
            wrapper.eq("parent_id", parentId);
        }
        List<Tag> list = tagMapper.selectList(wrapper);
        return list != null && list.size() > 0;
    }

    @Override
    public List<Tag> selectListByUserId(Integer userId) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return tagMapper.selectList(wrapper);
    }

    @Override
    public List<Tag> selectChildById(Integer userId, Integer tagId) {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return tagMapper.selectList(wrapper);
    }

    @Override
    public List<Tag> selectParentById(Integer userId, Integer tagId) {
        return null;
    }
}
