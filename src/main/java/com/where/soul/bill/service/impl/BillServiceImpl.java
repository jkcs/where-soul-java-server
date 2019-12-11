package com.where.soul.bill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.where.soul.bill.entity.Bill;
import com.where.soul.bill.mapper.BillMapper;
import com.where.soul.bill.service.IBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    private final BillMapper billMapper;

    public BillServiceImpl(BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    @Override
    public List<Bill> selectBills(Integer userId, Integer tagId, Integer typeId, String startTime, String endTime, Integer page, Integer lastId) {
        QueryWrapper<Bill> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
//        if (tagId != null) {
//            wrapper.eq("tag")
//        }
        wrapper.eq("type_id", typeId);
        if (!StringUtils.isEmpty(startTime) && !StringUtils.isEmpty(endTime)) {
            wrapper.between("create_time", startTime, endTime);
        }
        if (!StringUtils.isEmpty(lastId)){
            wrapper.lt("id", lastId);
        }
        wrapper.orderByDesc("create_time");
        wrapper.last("limit " + page);

        return billMapper.selectList(wrapper);
    }
}
