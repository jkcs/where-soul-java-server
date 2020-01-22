package com.where.soul.bill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.where.soul.bill.dto.TypeDTO;
import com.where.soul.bill.entity.Type;
import com.where.soul.bill.mapper.TypeMapper;
import com.where.soul.bill.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 账单类型表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    private final TypeMapper typeMapper;

    public TypeServiceImpl(TypeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }


    @Override
    public List<TypeDTO> selectList(Integer userId, Integer parentId) {
        return typeMapper.selectTypeList(userId, parentId);
    }
}
