package com.where.soul.bill.service.impl;

import com.where.soul.bill.entity.Bill;
import com.where.soul.bill.mapper.BillMapper;
import com.where.soul.bill.service.IBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账单表 服务实现类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements IBillService {

}
