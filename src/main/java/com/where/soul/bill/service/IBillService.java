package com.where.soul.bill.service;

import com.where.soul.bill.entity.Bill;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 账单表 服务类
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
public interface IBillService extends IService<Bill> {

    /**
     * 查找账单
     * @param userId 用户id
     * @param tagId 标签
     * @param typeId 类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param page 页数
     * @param lastId 上一列表的结束 id
     * @return 账单列表
     */
    List<Bill> selectBills(Integer userId, Integer tagId, Integer typeId, String startTime, String endTime, Integer page, Integer lastId);
}
