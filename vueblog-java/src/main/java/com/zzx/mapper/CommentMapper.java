package com.zzx.mapper;


import com.zzx.common.vo.PageComment;
import com.zzx.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzx
 * @since 2022-04-09
 */
@Mapper
@Repository
public interface CommentMapper {

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
