package com.zzx.service;

import com.zzx.entity.Tag;

import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/12 22:57
 */
public interface TagService {

    /**
     * 根据查询条件获取标签详情
     *
     * @param tag 查询条件
     * @return 标签详情
     */
    Tag getTag(Tag tag);

    /**
     * 根据查询条件获取标签列表
     *
     * @param tag 查询条件
     * @return 标签列表
     */
    List<Tag> getTagList(Tag tag);

    /**
     * 插入新的标签
     *
     * @param tag 标签实体类
     * @return 执行结果
     */
    int insertTag(Tag tag);

    /**
     * 删除标签
     *
     * @param id 标签id
     * @return 执行结果
     */
    int deleteTag(Long id);

    /**
     * 更新标签
     *
     * @param tag 标签实体类
     * @return 执行结果
     */
    int updateTag(Tag tag);
}
