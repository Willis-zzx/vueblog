package com.zzx.controller;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzx.common.lang.Result;
import com.zzx.common.vo.PageComment;
import com.zzx.entity.Comment;
import com.zzx.service.CommentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
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
     * 分页查询所有评论
     */
    @GetMapping("/commentList")
    public Result getCommentListByPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PageComment> list = commentService.getPageCommentList(new Comment());
        PageInfo pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);

    }

    /**
     * 获取某个博客下的所有评论
     */
    @GetMapping("/comment/{blogId}")
    public Result getCommentByBlogId(@PathVariable(name = "blogId") Long blogId) {
        //实体模型集合对象转换为VO对象集合
        List<PageComment> pageComments = commentService.getPageCommentList(new Comment().setBlogId(blogId).setParentCommentId((long) -1));
        for (PageComment pageComment : pageComments) {
            List<PageComment> reply = commentService.getPageCommentList(new Comment().setBlogId(blogId).setParentCommentId(pageComment.getId()));
            pageComment.setReplyComments(reply);
        }
        return Result.success(pageComments);
    }

    /**
     * 修改评论的状态
     */
    @RequestMapping("comment/publish/{id}")
    public Result publish(@PathVariable(name = "id") String id) {
        Comment comment = commentService.getComment(new Comment().setId(Long.parseLong(id)));
        comment.setIsPublished(!comment.getIsPublished());
        commentService.updateComment(comment);
        return Result.success("修改成功");

    }

    /**
     * 修改评论
     */
    @RequestMapping("comment/update")
    public Result updateById(@Validated @RequestBody Comment comment) {
        if (comment == null) {
            return Result.error("不能为空");
        }
        commentService.updateComment(comment);
        return Result.success("修改成功");

    }

    /**
     * 删除评论
     */
    @RequestMapping("comment/delete/{id}")
    public Result delete(@PathVariable(name = "id") String id) {
        if (commentService.deleteCommentById(Long.parseLong(id)) == 1) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败");
        }

    }

    /**
     * 提交评论
     */
    @PostMapping("/comment/add")
    public Result edit(@Validated @RequestBody Comment comment, HttpServletRequest request) {

        if (comment.getContent().contains("<script>") || comment.getEmail().contains("<script>") || comment.getNickname().contains("<script>") || comment.getWebsite().contains("<script>")) {
            return Result.error("非法输入");
        }
        System.out.println(comment.toString());
        Comment temp = new Comment();
        temp.setCreateTime(LocalDateTime.now());
        temp.setIp(request.getHeader("x-forwarded-for"));
        BeanUtil.copyProperties(comment, temp, "id", "ip", "createTime");
        commentService.createComment(temp);


        //博主的回复向被回复者发送提示邮件
//        if(comment.getIsAdminComment()==1&&comment.getParentCommentId()!=-1){
//            Comment parentComment = commentService.getOne(new QueryWrapper<Comment>().eq("nickname", comment.getParentCommentNickname()));
//            String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
//            if (parentComment.getEmail().matches(regex)) {
//                mailService.sendSimpleMail(parentComment.getEmail(), "Skymo博客评论回复", "您的的评论："+parentComment.getContent()+"\n博主回复内容："+comment.getContent());
//                logger.info("邮件发送成功");
//            }
//
//        }

        return Result.success("新增成功");
    }

    /**
     * 分页查询某个博客下的根评论
     */

    @GetMapping("/comment/detail")
    public Result getCommentListByPageId(@RequestParam(defaultValue = "1") Long blogId, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<PageComment> list = commentService.getPageCommentList(new Comment().setBlogId(blogId).setSort("desc"));
        PageInfo pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);

    }
}
