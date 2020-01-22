package com.where.soul.bill.mapper;

import com.where.soul.bill.dto.TypeDTO;
import com.where.soul.bill.entity.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 账单类型表 Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
public interface TypeMapper extends BaseMapper<Type> {

    List<TypeDTO> selectTypeList(Integer userId, Integer parentId);
}
