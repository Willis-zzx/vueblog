package com.zzx.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzx.common.lang.Result;
import com.zzx.common.vo.PageBlog;
import com.zzx.config.RedisKeyConfig;
import com.zzx.entity.Blog;
import com.zzx.entity.Type;
import com.zzx.service.BlogService;
import com.zzx.service.RedisService;
import com.zzx.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;


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
    RedisService redisService;
    TypeService typeService;

    Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private void setService(BlogService blogService, RedisService redisService, TypeService typeService) {
        this.blogService = blogService;
        this.redisService = redisService;
        this.typeService = typeService;
    }

    /**
     * 首页查看所有公开的博客列表
     *
     * @param pageNum 起始页码
     * @return 博客列表
     */
    @GetMapping("/blogs")
    public Result getBlogs(@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<PageBlog> blogList = blogService.getBlogList(new Blog().setStatus(1));
        PageInfo<PageBlog> pageInfo = new PageInfo<>(blogList);
        return Result.success(pageInfo);
    }

    /**
     * 查询某个公开博客详情
     */
    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Integer id) {
        List<PageBlog> list = blogService.getBlogList(new Blog().setId(id.longValue()).setStatus(1));
        if (list.size() == 0) {
            Assert.notNull(list, "该博客已删除！");
        }
        if (list.get(0).getStatus() != 1) {
            return Result.error("你没有权限查阅此博客");
        }

        if (redisService.getMapByHash(RedisKeyConfig.BLOG_VIEWS_MAP).containsKey(id)) {
            redisService.incrementByHashKey(RedisKeyConfig.BLOG_VIEWS_MAP, id, 1);
        } else {
            redisService.saveKVToHash(RedisKeyConfig.BLOG_VIEWS_MAP, id, 1);
        }
        return Result.success(list.get(0));

    }

    /**
     * 按创建时间排序 分类 分页查询公开的博客简要信息列表
     *
     * @param typeName 分类名称
     * @param pageNum  起始页码
     * @return 返回集合
     */
    @GetMapping("/getBlogsByTypeName")
    public Result getBlogsByTypeName(@RequestParam(name = "typeName") String typeName, @RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, 5);
        Type type = typeService.getType(new Type().setTypeName(typeName));
        if (type == null) {
            return Result.error("分类不存在，查询失败");
        }
        List<PageBlog> blogInfoList = blogService.getBlogList(new Blog().setTypeId(type.getId()).setStatus(1));
        PageInfo pageInfo = new PageInfo<>(blogInfoList);
        return Result.success(pageInfo);
    }

    /**
     * 查询归档页面博客列表
     *
     * @param pageNum 起始页码
     * @return 返回结果集
     */
    @GetMapping("/blog/archives")
    public Result getBlogsArchives(@RequestParam(defaultValue = "1") Integer pageNum) {
        if (redisService.hasHashKey(RedisKeyConfig.ARCHIVE_INFO_CACHE, pageNum)) {
            return Result.success(redisService.getValueByHashKey(RedisKeyConfig.ARCHIVE_INFO_CACHE, pageNum));
        }
        int pageSize = 15;
        PageHelper.startPage(pageNum, pageSize);
        List<PageBlog> blogInfoList = blogService.getBlogList(new Blog().setStatus(1));
        PageInfo pageInfo = new PageInfo<>(blogInfoList);

        //进行缓存
        redisService.saveKVToHash(RedisKeyConfig.ARCHIVE_INFO_CACHE, pageNum, pageInfo);
        return Result.success(pageInfo);

    }

    /**
     * 查询友链的博客
     */
    @GetMapping("/friends")
    public Result friends() {
        if (redisService.hasHashKey(RedisKeyConfig.BLOG_INFO_CACHE, RedisKeyConfig.FRIEND_BLOG_CACHE)) {
            return Result.success(redisService.getValueByHashKey(RedisKeyConfig.BLOG_INFO_CACHE, RedisKeyConfig.FRIEND_BLOG_CACHE));
        }
        List<PageBlog> list = blogService.getBlogList(new Blog().setTitle("友情链接").setStatus(1));
        redisService.saveKVToHash(RedisKeyConfig.BLOG_INFO_CACHE, RedisKeyConfig.FRIEND_BLOG_CACHE, list.get(0));
        return Result.success(list.get(0));
    }

    /**
     * 查询关于我的博客
     */
    @GetMapping("/about")
    public Result aboutMe() {
        if (redisService.hasHashKey(RedisKeyConfig.BLOG_INFO_CACHE, RedisKeyConfig.ABOUT_INFO_CACHE)) {
            return Result.success(redisService.getValueByHashKey(RedisKeyConfig.BLOG_INFO_CACHE, RedisKeyConfig.ABOUT_INFO_CACHE));
        }
        List<PageBlog> list = blogService.getBlogList(new Blog().setTitle("关于我！！").setStatus(1));
        redisService.saveKVToHash(RedisKeyConfig.BLOG_INFO_CACHE, RedisKeyConfig.ABOUT_INFO_CACHE, list.get(0));
        return Result.success(list.get(0));
    }


    /**
     * 按创建时间排序 分页查询所有博客
     */
    @GetMapping("/blogList")
    public Result blogList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<PageBlog> list = blogService.getBlogList(new Blog().setStatus(1));
        PageInfo pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);
    }

    /**
     * 查询所有博客
     */
    @GetMapping("/blog/all")
    public Result blogAll() {
        List<PageBlog> list = blogService.getBlogList(new Blog().setStatus(1));
        return Result.success(list);
    }

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

