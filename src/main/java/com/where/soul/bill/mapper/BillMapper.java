package com.where.soul.bill.mapper;

import com.where.soul.bill.entity.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 账单表 Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
public interface BillMapper extends BaseMapper<Bill> {

    /**
     * 查询账单信息
     * @param userId 用户id
     * @param tagId 标签id
     * @param typeId 类型id
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param pageSize 列表大小
     * @param lastId 最后一个id
     * @return 查询账单信息
     */
    public List<Bill> selectBills(Integer userId, Integer tagId, Integer typeId, String startTime, String endTime, Integer pageSize, Integer lastId);
}
