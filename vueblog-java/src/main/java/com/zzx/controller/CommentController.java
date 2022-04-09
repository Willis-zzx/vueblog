package com.zzx.controller;


import com.zzx.common.lang.Result;
import com.zzx.common.vo.PageComment;
import com.zzx.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2022-04-09
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 获取某个博客下的所有评论
     */
    @GetMapping("/comment/{blogId}")
    public Result getCommentByBlogId(@PathVariable(name = "blogId") Long blogId) {

        //实体模型集合对象转换为VO对象集合
        List<PageComment> pageComments = commentService.getPageCommentListByDesc(blogId, (long) -1);

        for (PageComment pageComment : pageComments) {

            List<PageComment> reply = commentService.getPageCommentList(blogId, pageComment.getId());
            pageComment.setReplyComments(reply);
        }
        return Result.success(pageComments);
    }
}
