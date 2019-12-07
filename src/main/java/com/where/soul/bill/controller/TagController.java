package com.where.soul.bill.controller;


import com.where.soul.bill.service.ITagService;
import com.where.soul.bill.vo.TagVO;
import com.where.soul.common.Result;
import com.where.soul.common.base.BaseController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 账单标签表 前端控制器
 * </p>
 *
 * @author lw
 * @since 2019-11-28
 */
@RestController
@RequestMapping("/bill/tag")
public class TagController extends BaseController {

    private final ITagService tagService;

    public TagController(ITagService tagService) {
        this.tagService = tagService;
    }

    @PostMapping("/add")
    public Result restAddTag(HttpServletRequest request, @Valid TagVO tagVO) {
        Integer id = getUserId(request);
        String name = tagVO.getName();
        Integer parentId = tagVO.getParentId();
        Boolean isRepeat = tagService.isTagRepeat(id, name, parentId);
        if (isRepeat) {
            return Result.customError("标签重复添加！");
        }

        Integer integer = tagService.insetTag(id, name, parentId);
        if (integer == -1){
            return Result.customError("添加失败！");
        }

        return Result.success();
    }
}
