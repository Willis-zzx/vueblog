package com.zzx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzx.common.lang.Result;
import com.zzx.entity.Tag;
import com.zzx.mapper.TagMapper;
import com.zzx.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/12 22:57
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    private void setService(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 分页查看标签列表
     *
     * @param pageNum 起始页码
     * @return 标签分页列表
     */
    @GetMapping("/tagListByPage")
    public Result getTagListByPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(name = "tagName") String tagName) {
        PageHelper.startPage(pageNum, pageSize);
        Tag tag = new Tag();
        if (org.apache.commons.lang3.StringUtils.isNoneBlank(tagName)) {
            tag.setTagName(tagName);
        }
        List<Tag> tagList = tagService.getTagList(tag);
        PageInfo<Tag> pageInfo = new PageInfo<>(tagList);
        return Result.success(pageInfo);
    }

    /**
     * 查看标签列表
     *
     * @return 标签列表
     */
    @GetMapping("/tagList")
    public Result getTagList() {
        List<Tag> tagList = tagService.getTagList(new Tag());
        return Result.success(tagList);
    }

    /**
     * 新增标签
     *
     * @param tag 标签实体类
     * @return 执行结果
     */
    @RequestMapping("/insertTag")
    public Result insertTag(@Validated @RequestBody Tag tag) {
        if (StringUtils.isEmpty(tag.getTagName())) {
            return Result.error("标签不得为空");
        }
        tagService.insertTag(tag);
        return Result.success(null);
    }

    /**
     * 更新标签
     *
     * @param tag 标签实体类
     * @return 执行结果
     */
    @RequestMapping("/updateTag")
    public Result updateTag(@Validated @RequestBody Tag tag) {
        if (tag == null) {
            return Result.error("标签不能为空");
        }
        tagService.updateTag(tag);
        return Result.success(null);
    }

    /**
     * 删除标签
     *
     * @param id 标签id
     * @return 执行结果
     */
    @RequestMapping("/deleteTag/{id}")
    public Result deleteTag(@PathVariable(name = "id") String id) {
        if (tagService.deleteTag(Long.parseLong(id)) == 1) {
            return Result.success(null);
        } else {
            return Result.error(null);
        }
    }

}
