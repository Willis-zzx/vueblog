package com.zzx.service;

import com.zzx.common.vo.BlogInfo;
import com.zzx.entity.Blog;

import java.util.List;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzx
 * @since 2022-04-04
 */
public interface BlogService {

    /**
     * 获取博客列表
     *
     * @return 博客列表
     */
    List<BlogInfo> getBlogs();

    /**
     * 通过博客id获取博客明细
     *
     * @param id id
     * @return blog实体类
     */
    BlogInfo getBlogById(Integer id);
}
