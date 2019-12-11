package com.where.soul.bill.vo;

import com.where.soul.common.util.Regexp;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @author lw
 */
@Data
public class BillVO {

    private Integer tagId;

    private Integer typeId;

    @Pattern(regexp = Regexp.DATE_TIME, message = "时间格式不符合")
    private String startTime;

    @Pattern(regexp = Regexp.DATE_TIME, message = "时间格式不符合")
    private String endTime;

    private Integer pageSize;

    private Integer lastId;

}
