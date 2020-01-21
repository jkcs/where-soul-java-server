package com.where.soul.bill.service;

import com.where.soul.bill.dto.BillDTO;
import com.where.soul.bill.entity.Bill;
import com.baomidou.mybatisplus.extension.service.IService;
import com.where.soul.bill.entity.Tag;

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
    List<BillDTO> selectBills(Integer userId, Integer tagId, Integer typeId, String startTime, String endTime, Integer page, Integer lastId);

    /**
     * 增加记账
     * @param bill 账单对象
     * @param tags 关联标签
     * @return < 0 失败 其余成功
     */
    Integer addBill(Bill bill, List<Tag>  tags);

    /**
     * 更新记账
     * @param bill 账单对象
     * @param tags 关联标签
     * @return < 0 失败 其余成功
     */
    Integer updateBill(Bill bill, List<Tag>  tags);
}
