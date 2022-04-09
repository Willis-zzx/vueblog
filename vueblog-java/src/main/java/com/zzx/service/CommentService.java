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
     * 通过博客id和父评论id查找所有子评论 并按照时间倒序排序
     *
     * @param blogId
     * @param parentCommentId
     * @return
     */
    List<PageComment> getPageCommentListByDesc(Long blogId, Long parentCommentId);

    /**
     * 通过博客id和父评论id查找所有子评论
     *
     * @param blogId
     * @param parentCommentId
     * @return
     */
    List<PageComment> getPageCommentList(Long blogId, Long parentCommentId);
}
