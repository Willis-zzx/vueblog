package com.zzx.mapper;


import com.zzx.common.vo.BlogInfo;
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
