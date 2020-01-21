package com.where.soul.bill.vo;

import com.where.soul.bill.dto.BillDTO;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;


/**
 * @author lw
 */
@Data
public class BillAddVO {
    private Integer id;

    private Integer typeId;

    private Boolean status;

    @NotNull(message = "金额不能为空")
    private BigDecimal money;

    private List<BillDTO.BillTagDTO> tags;

    @Data
    static class BilAddTagVO {
        private Integer id;
    }
}
