package com.where.soul.bill.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.where.soul.common.base.BaseEntity;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账单表
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Bill extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @JsonIgnore
    private Integer userId;

    /**
     * 标签id
     */
    private Integer tagId;

    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 0 收入 1 支出
     */
    private Boolean status;

    /**
     * 金额
     */
    private BigDecimal money;

}
