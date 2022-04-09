package com.zzx.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzx.entity.Type;
import com.zzx.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 传给前端的blog的视图对象
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/9 20:49
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class BlogInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 文章标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 文章首图，用于随机文章展示
     */
    private String firstPicture;

    /**
     * 描述
     */
    @NotBlank(message = "摘要不能为空")
    private String description;

    /**
     * 文章正文
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 文章字数
     */
    private Integer words;

    /**
     * 文章分类id
     */
    private Long typeId;
    /**
     * 文章类型
     */
    private String typeName;

    /**
     * 文章作者id
     */
    private Long userId;

    /**
     * 文章作者昵称
     */
    private String userName;
    /**
     * 文章状态
     */
    private Integer status;


}
