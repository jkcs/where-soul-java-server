package com.where.soul.bill.service;

import com.where.soul.bill.dto.TypeDTO;
import com.where.soul.bill.entity.Tag;
import com.where.soul.bill.entity.Type;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 账单类型表 服务类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
public interface ITypeService extends IService<Type> {
    
    /**
     * 搜索用户的所有类型
     *
     * @param userId 用户id
     * @param parentId 父类型id
     * @return List<TypeDTO>
     */
    List<TypeDTO> selectList(Integer userId, Integer parentId);

}
