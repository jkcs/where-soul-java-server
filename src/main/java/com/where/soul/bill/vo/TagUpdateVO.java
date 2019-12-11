package com.where.soul.bill.vo;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author lw
 */
@Data
public class TagUpdateVO {

    @NotNull(message = "标签id不能为空")
    private Integer id;

    @NotBlank(message = "标签名称不能为空")
    @Size(min = 1, max = 6, message = "标签名称最多为6个字")
    private String name;

    @Min(value = 1, message = "父标签不存在")
    private Integer parentId;
}
