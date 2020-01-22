package com.where.soul.bill.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lw
 */
@Data
public class TypeDTO {

    private Integer id;

    /**
     * 类型名称
     */
    private String name;

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
