package com.zzx.service.impl;

import com.zzx.common.vo.PageBlog;
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
     * 根据查询条件获取博客列表
     *
     * @param blog 查询条件实体类
     * @return 博客列表
     */
    @Override
    public List<PageBlog> getBlogList(Blog blog) {
        return blogMapper.getBlogList(blog);
    }

    /**
     * 通过查询条件获取博客明细
     *
     * @param blog 查询条件实体类
     * @return 博客详情
     */
    @Override
    public PageBlog getBlog(Blog blog) {
        return blogMapper.getBlog(blog);
    }


}
