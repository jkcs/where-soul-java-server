package com.where.soul.bill.entity;

import com.where.soul.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账单类型表
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bill_type")
public class Type extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 父类型id
     */
    private Integer parentId;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 结构链 id_id_...
     */
    private String structureChain;

    /**
     * 用户id
     */
    private Integer userId;

    private Integer billTypeInfoId;


}
