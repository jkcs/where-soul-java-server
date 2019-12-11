package com.where.soul.bill.controller;


import com.where.soul.common.Result;
import com.where.soul.common.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
public class BillController extends BaseController {

    @GetMapping
    public Result restBill(HttpServletRequest request) {
        Integer userId = getUserId(request);

        return null;
    }
}
