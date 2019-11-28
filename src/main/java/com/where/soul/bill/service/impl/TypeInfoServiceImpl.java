package com.where.soul.bill.service.impl;

import com.where.soul.bill.entity.TypeInfo;
import com.where.soul.bill.mapper.TypeInfoMapper;
import com.where.soul.bill.service.ITypeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账单类型信息表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Service
public class TypeInfoServiceImpl extends ServiceImpl<TypeInfoMapper, TypeInfo> implements ITypeInfoService {

}
