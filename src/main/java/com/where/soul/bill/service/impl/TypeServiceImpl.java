package com.where.soul.bill.service.impl;

import com.where.soul.bill.entity.Type;
import com.where.soul.bill.mapper.TypeMapper;
import com.where.soul.bill.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
