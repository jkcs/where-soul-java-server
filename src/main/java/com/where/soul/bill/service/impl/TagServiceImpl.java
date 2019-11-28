package com.where.soul.bill.service.impl;

import com.where.soul.bill.entity.Tag;
import com.where.soul.bill.mapper.TagMapper;
import com.where.soul.bill.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
