package com.where.soul.bill.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.where.soul.bill.dto.BillDTO;
import com.where.soul.bill.entity.Bill;
import com.where.soul.bill.entity.Tag;
import com.where.soul.bill.service.IBillService;
import com.where.soul.bill.vo.BillAddVO;
import com.where.soul.bill.vo.BillVO;
import com.where.soul.common.Result;
import com.where.soul.common.base.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    @GetMapping("/list")
    public Result restBill(HttpServletRequest request, BillVO billVO) {
        Integer userId = getUserId(request);
        if (billVO.getPageSize() == null || billVO.getPageSize() == 0) {
            billVO.setPageSize(10);
        }
        List<BillDTO> billDTOList = billService.selectBills(userId, billVO.getTagId(), billVO.getTypeId(), billVO.getStartTime(), billVO.getEndTime(), billVO.getPageSize(), billVO.getLastId());

        return Result.success(billDTOList);
    }

    @PostMapping("/add")
    public Result restAdd(HttpServletRequest request, BillAddVO billAddVO) {
        Integer userId = getUserId(request);
        if (billAddVO.getStatus() == null) {
            billAddVO.setStatus(true);
        }
        Bill bill = new Bill();
        bill.setUserId(userId);
        BeanUtils.copyProperties(billAddVO, bill);
        bill.setId(null);
        List<Tag> tags1 = generatorTagList(billAddVO.getTags());

        Integer integer = billService.addBill(bill, tags1);
        if (integer > 0) {
            return Result.success("操作成功！");
        }

        return Result.error();
    }

    @PostMapping("/update")
    public Result restUpdate(HttpServletRequest request, BillAddVO billAddVO) {
        Integer id = billAddVO.getId();
        if (id == null) {
            return Result.error("id 不能为空");
        }
        Integer userId = getUserId(request);
        Bill bill = new Bill();
        BeanUtils.copyProperties(billAddVO, bill);
        bill.setUserId(userId);
        List<Tag> tags1 = generatorTagList(billAddVO.getTags());
        billService.updateBill(bill, tags1);

        return Result.success();
    }

    @PostMapping("/remove")
    public Result restUpdate(HttpServletRequest request, Integer id) {
        Integer userId = getUserId(request);
        QueryWrapper<Bill> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.eq("user_id", userId);
        billService.remove(wrapper);
        return Result.success();
    }

    private List<Tag> generatorTagList(List<BillDTO.BillTagDTO> tags) {
        List<Tag> tagsList = new ArrayList<>();
        if (tags != null && tags.size() != 0) {
            tags.forEach(t -> {
                Tag tag = new Tag();
                tag.setId(t.getId());
                tagsList.add(tag);
            });
        }
        return tagsList;
    }
}
