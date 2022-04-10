package com.zzx.mapper;


import com.zzx.common.vo.PageBlog;
import com.zzx.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzx
 * @since 2022-04-04
 */
@Mapper
public interface BlogMapper {

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
