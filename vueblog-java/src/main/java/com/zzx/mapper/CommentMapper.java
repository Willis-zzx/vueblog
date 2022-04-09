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
     * 根据创建时间倒序 根据博客id和父评论id查询所有子评论
     *
     * @param blogId
     * @param parentCommentId
     * @return
     */
    @Select("select id, nickname, content, website,avatar, create_time, is_admin_comment,parent_comment_nickname  from comment where blog_id=#{blogId} and parent_comment_id=#{parentCommentId} order by create_time desc")
    List<PageComment> getPageCommentListByPageAndParentCommentIdByDesc(@Param("blogId") long blogId, @Param("parentCommentId") long parentCommentId);

    /**
     * 根据博客id和父评论id查询所有子评论
     *
     * @param blogId
     * @param parentCommentId
     * @return
     */
    @Select("select id, nickname, content, website,avatar, create_time, is_admin_comment,parent_comment_nickname  from comment where blog_id=#{blogId} and parent_comment_id=#{parentCommentId} order by create_time")
    List<PageComment> getPageCommentListByPageAndParentCommentId(@Param("blogId") long blogId, @Param("parentCommentId") long parentCommentId);


}
