package com.zzx.service.impl;

import com.zzx.entity.Tag;
import com.zzx.mapper.TagMapper;
import com.zzx.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/12 22:57
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;

    /**
     * 根据查询条件获取标签详情
     *
     * @param tag 查询条件
     * @return 标签详情
     */
    @Override
    public Tag getTag(Tag tag) {
        return tagMapper.getTag(tag);
    }

    /**
     * 根据查询条件获取标签列表
     *
     * @param tag 查询条件
     * @return 标签列表
     */
    @Override
    public List<Tag> getTagList(Tag tag) {
        return tagMapper.getTagList(tag);
    }

    /**
     * 插入新的标签
     *
     * @param tag 标签实体类
     * @return 执行结果
     */
    @Override
    public int insertTag(Tag tag) {
        return tagMapper.insertTag(tag);
    }

    /**
     * 删除标签
     *
     * @param id 标签id
     * @return 执行结果
     */
    @Override
    public int deleteTag(Long id) {
        return tagMapper.deleteTag(id);
    }

    /**
     * 更新标签
     *
     * @param tag 标签实体类
     * @return 执行结果
     */
    @Override
    public int updateTag(Tag tag) {
        return tagMapper.updateTag(tag);
    }
}
