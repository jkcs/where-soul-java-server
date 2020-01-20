package com.where.soul.bill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.Contract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lw
 */
@Data
public class BillDTO {

    public BillDTO() {
        this.tags = new ArrayList<>();
        this.types = new ArrayList<>();
    }

    private Integer id;

    /**
     * 标签id
     */
    private List<BillTagDTO> tags;

    /**
     * 类型id
     */
    private List<BillTypesDTO> types;

    /**
     * 0 收入 1 支出
     */
    private Boolean status;

    /**
     * 金额
     */
    private BigDecimal money;

    @Data
    @AllArgsConstructor
    public static class BillTagDTO {
        private Integer id;

        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class BillTypesDTO {
        private Integer id;

        private String name;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        BillDTO obj1 = (BillDTO) obj;
        if (obj1.getId() == null) {
            return false;
        }
        return (obj1.getId().equals(this.id));
    }
}
