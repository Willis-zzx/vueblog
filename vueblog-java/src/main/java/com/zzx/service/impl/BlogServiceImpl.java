package com.zzx.service.impl;

import com.zzx.common.vo.BlogInfo;
import com.zzx.entity.Blog;
import com.zzx.mapper.BlogMapper;
import com.zzx.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2022-04-04
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    /**
     * 获取博客列表
     *
     * @return 博客列表
     */
    @Override
    public List<BlogInfo> getBlogs() {
        return blogMapper.getBlogs();
    }

    /**
     * 通过博客id获取博客明细
     *
     * @param id id
     * @return blog实体类
     */
    @Override
    public BlogInfo getBlogById(Integer id) {
        return blogMapper.getBlogById(id);
    }
}
