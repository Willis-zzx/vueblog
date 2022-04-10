package com.zzx.service;

import com.zzx.common.vo.PageComment;
import com.zzx.entity.Comment;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzx
 * @since 2022-04-09
 */
public interface CommentService {
    /**
     * 通过查询条件查询评论列表
     *
     * @param comment 查询条件
     * @return 评论列表
     */
    List<PageComment> getPageCommentList(Comment comment);

    /**
     * 通过查询条件查询评论详情
     *
     * @param comment 查询条件
     * @return 评论详情
     */
    PageComment getPageComment(Comment comment);

    /**
     * 通过查询条件查询评论详情
     *
     * @param comment 查询条件
     * @return 评论详情
     */
    Comment getComment(Comment comment);

    /**
     * 修改评论
     *
     * @param comment
     */
    void updateComment(Comment comment);

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    int deleteCommentById(Long id);

    void createComment(Comment comment);
}
