package com.where.soul.bill.entity;

import com.where.soul.common.base.BaseEntity;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账单类型信息表
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bill_type_info")
public class TypeInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 还款日期
     */
    private LocalDateTime repaymentDate;

    /**
     * 提醒日期
     */
    private LocalDateTime reminderDate;

}
