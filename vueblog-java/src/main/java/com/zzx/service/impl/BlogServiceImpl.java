package com.zzx.service.impl;

import com.zzx.entity.Blog;
import com.zzx.mapper.BlogMapper;
import com.zzx.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzx
 * @since 2022-04-04
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
