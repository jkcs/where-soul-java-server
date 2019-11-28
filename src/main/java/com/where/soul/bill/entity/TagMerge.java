package com.where.soul.bill.entity;

import com.where.soul.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账单和标签关联表
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bill_tag_merge")
public class TagMerge extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账单id
     */
    private Integer billId;

    /**
     * 账单标签id
     */
    private Integer billTagId;

}
