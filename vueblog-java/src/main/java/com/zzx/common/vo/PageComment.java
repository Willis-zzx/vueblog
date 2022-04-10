package com.zzx.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 传输给前端的评论视图对象
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/9 19:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PageComment {
    private Long id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 头像(图片路径)
     */
    private String avatar;

    /**
     * 评论时间
     */
    private LocalDateTime createTime;

    /**
     * 评论者ip地址
     */
    private String ip;

    /**
     * 博主回复
     */
    private Integer isAdminComment;

    /**
     * 公开或非公开
     */
    private Boolean isPublished;

    /**
     * 所属的文章
     */
    private Long blogId;

    /**
     * 父评论id，-1为根评论
     */
    private Long parentCommentId;

    /**
     * 个人网站
     */
    private String website;

    /**
     * 被回复昵称
     */
    private String parentCommentNickname;

    /**
     * 如果评论昵称为QQ号，则将昵称和头像置为QQ昵称和QQ头像，并将此字段置为QQ号备份
     */
    private String qq;
    /**
     * 回复该评论的评论
     */
    private List<PageComment> replyComments = new ArrayList<>();
}
