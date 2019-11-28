package com.where.soul.bill.entity;

import com.where.soul.common.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账单标签表
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bill_tag")
public class Tag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 用户id， 有为用户自定义标签
     */
    private Integer userId;

    /**
     * 父节点id
     */
    private Integer parentId;

    /**
     * 结构链 id_id
     */
    private String structureChain;

    /**
     * 0 不展示 1展示
     */
    private Integer status;


}
