package com.zzx.service.impl;

import com.zzx.common.vo.PageComment;
import com.zzx.entity.Comment;
import com.zzx.mapper.CommentMapper;
import com.zzx.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2022-04-09
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentMapper commentMapper;

    /**
     * 通过查询条件查询评论列表
     *
     * @param comment 查询条件
     * @return 评论列表
     */
    @Override
    public List<PageComment> getPageCommentList(Comment comment) {
        return commentMapper.getPageCommentList(comment);
    }

    /**
     * 通过查询条件查询评论详情
     *
     * @param comment 查询条件
     * @return 评论详情
     */
    @Override
    public PageComment getPageComment(Comment comment) {
        return commentMapper.getPageComment(comment);
    }

    /**
     * 通过查询条件查询评论详情
     *
     * @param comment 查询条件
     * @return 评论详情
     */
    @Override
    public Comment getComment(Comment comment) {
        return commentMapper.getComment(comment);
    }

    /**
     * 修改评论
     *
     * @param comment
     */
    @Override
    public void updateComment(Comment comment) {
        commentMapper.updateComment(comment);
    }

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    @Override
    public int deleteCommentById(Long id) {
        return commentMapper.deleteCommentById(id);
    }

    @Override
    public void createComment(Comment comment) {
        commentMapper.createComment(comment);

    }


}
