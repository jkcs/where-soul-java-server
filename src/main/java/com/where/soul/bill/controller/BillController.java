package com.where.soul.bill.controller;


import com.where.soul.bill.dto.BillDTO;
import com.where.soul.bill.service.IBillService;
import com.where.soul.bill.vo.BillVO;
import com.where.soul.common.Result;
import com.where.soul.common.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 账单表 前端控制器
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@RestController
@RequestMapping("/bill")
@Slf4j
public class BillController extends BaseController {

    private final IBillService billService;

    public BillController(IBillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public Result restBill(HttpServletRequest request, BillVO billVO) {
        log.info(billVO.toString());
        Integer userId = getUserId(request);
        if (billVO.getPageSize() == null || billVO.getPageSize() == 0) {
            billVO.setPageSize(10);
        }
        List<BillDTO> billDTOList = billService.selectBills(userId, billVO.getTagId(), billVO.getTypeId(), billVO.getStartTime(), billVO.getEndTime(), billVO.getPageSize(), billVO.getLastId());

        return Result.success(billDTOList);
    }

    @GetMapping("/test")
    public Result restTest(HttpServletRequest request, BillVO billVO) {
        log.error("测试失败是否会发送邮件");
        return Result.success(null);
    }
}
