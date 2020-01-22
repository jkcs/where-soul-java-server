package com.where.soul.bill.controller;


import com.where.soul.bill.dto.TagDTO;
import com.where.soul.bill.dto.TypeDTO;
import com.where.soul.bill.entity.Tag;
import com.where.soul.bill.service.ITagService;
import com.where.soul.bill.service.ITypeService;
import com.where.soul.common.Result;
import com.where.soul.common.base.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 账单类型表 前端控制器
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@RestController
@RequestMapping("/bill/type")
public class TypeController extends BaseController {

    private final ITypeService typeService;

    public TypeController(ITypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/list")
    public Result restList(HttpServletRequest request, Integer parentId) {
        Integer id = getUserId(request);
        List<TypeDTO> types = typeService.selectList(id, parentId);
        return Result.success(types);
    }

}
