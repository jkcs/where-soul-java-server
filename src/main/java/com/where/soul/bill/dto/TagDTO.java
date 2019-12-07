package com.where.soul.bill.dto;

import lombok.Data;

/**
 * @author lw
 */
@Data
class TagDTO {

    private Integer id;

    private String name;

    private TagDTO children;
}
