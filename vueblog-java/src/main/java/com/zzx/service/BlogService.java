package com.zzx.service;

import com.github.pagehelper.PageInfo;
import com.zzx.common.vo.PageBlog;
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
     * 根据查询条件获取博客列表
     *
     * @param blog 查询条件实体类
     * @return 博客列表
     */
    List<PageBlog> getBlogList(Blog blog);


    /**
     * 通过查询条件获取博客明细
     *
     * @param blog 查询条件实体类
     * @return 博客详情
     */
    PageBlog getBlog(Blog blog);


}
