package com.zzx.controller;


import com.zzx.common.lang.Result;
import com.zzx.entity.Type;
import com.zzx.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zzx
 * @since 2022-04-09
 */
@RestController
public class TypeController {

    @Autowired
    private TypeService typeService;

    /**
     * 查询所有分类
     */
    @GetMapping("/types")
    public Result types() {
//        if (redisService.hasHashKey(RedisKeyConfig.CATEGORY_NAME_CACHE, RedisKeyConfig.All)) {
//            return Result.succ(redisService.getValueByHashKey(RedisKeyConfig.CATEGORY_NAME_CACHE, RedisKeyConfig.All));
//        }
        List<Type> list = typeService.getTypeList();
        //redisService.saveKVToHash(RedisKeyConfig.CATEGORY_NAME_CACHE, RedisKeyConfig.All, list);
        return Result.success(list);
    }
}
