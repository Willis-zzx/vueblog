package com.zzx.controller;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzx.common.lang.Result;
import com.zzx.common.vo.BlogInfo;
import com.zzx.entity.Blog;
import com.zzx.service.BlogService;
import com.zzx.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2022-04-04
 */
@RestController
public class BlogController {


    BlogService blogService;

    Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blogs")
    public Result getBlogByPage(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<BlogInfo> blogList = blogService.getBlogs();
        PageInfo pageInfo = new PageInfo<>(blogList);
        return Result.success(pageInfo);
    }

    /**
     * 查询某个公开博客详情
     */
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Integer id) {


        BlogInfo blog = blogService.getBlogById(id);
        Assert.notNull(blog, "该博客已删除！");
        if (blog.getStatus() != 1) {
            return Result.error("你没有权限查阅此博客");
        }

//        if (redisService.getMapByHash(RedisKeyConfig.BLOG_VIEWS_MAP).containsKey(id)) {
//            redisService.incrementByHashKey(RedisKeyConfig.BLOG_VIEWS_MAP, id, 1);
//        } else {
//            redisService.saveKVToHash(RedisKeyConfig.BLOG_VIEWS_MAP, id, 1);
//        }
        return Result.success(blog);

    }

//    @GetMapping("/blog/{id}")
//    public Result detail(@PathVariable(name = "id") Long id) {
//        Blog blog = blogService.getById(id);
//        Assert.notNull(blog, "该博客已被删除");
//
//        return Result.success(blog);
//    }

//    /**
//     * 注解：@RequiresAuthentication说明需要登录之后才能访问的接口，其他需要权限的接口可以添加shiro的相关注解
//     *
//     * @param blog
//     * @return
//     */
//    @RequiresAuthentication
//    @PostMapping("/blog/edit")
//    public Result edit(@Validated @RequestBody Blog blog) {
//
////        Assert.isTrue(false, "公开版不能任意编辑！");
//
//        Blog temp = null;
//        if (blog.getId() != null) {
//            temp = blogService.getById(blog.getId());
//            // 只能编辑自己的文章
//            System.out.println(ShiroUtil.getProfile().getId());
//            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");
//
//        } else {
//
//            temp = new Blog();
//            temp.setUserId(ShiroUtil.getProfile().getId());
//            temp.setCreateTime(new Timestamp(System.currentTimeMillis()));
//            temp.setStatus(0);
//        }
//
//        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
//        blogService.saveOrUpdate(temp);
//
//        return Result.success(null);
//    }


}

