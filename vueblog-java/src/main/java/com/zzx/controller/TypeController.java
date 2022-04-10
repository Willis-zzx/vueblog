package com.zzx.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzx.common.lang.Result;
import com.zzx.config.RedisKeyConfig;
import com.zzx.entity.Type;
import com.zzx.service.RedisService;
import com.zzx.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
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

    @Autowired
    private RedisService redisService;

    /**
     * 查询所有分类
     */
    @GetMapping("/types")
    public Result getTypes() {
        if (redisService.hasHashKey(RedisKeyConfig.CATEGORY_NAME_CACHE, RedisKeyConfig.All)) {
            return Result.success(redisService.getValueByHashKey(RedisKeyConfig.CATEGORY_NAME_CACHE, RedisKeyConfig.All));
        }
        List<Type> list = typeService.getTypeList(new Type());
        redisService.saveKVToHash(RedisKeyConfig.CATEGORY_NAME_CACHE, RedisKeyConfig.All, list);
        return Result.success(list);
    }


    /**
     * 分页查询分类
     */
    @GetMapping("/type/list")
    public Result getTypeList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Type> list = typeService.getTypeList(new Type());
        PageInfo pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);
    }

    /**
     * 根据id删除分类
     *
     * @param id 分类id
     * @return 执行结果
     */
    @GetMapping("/type/delete/{id}")
    public Result deleteTypeById(@PathVariable(name = "id") Long id) {
        int code = typeService.deleteTypeById(id);
        if (code == 1) {
            redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_NAME_CACHE);
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    @PostMapping("/type/update")
    public Result updateTypeById(@Validated @RequestBody Type type) {
        if (type == null) {
            return Result.error("不能为空");
        } else {
            typeService.updateType(type);
            redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_NAME_CACHE);
        }
        return Result.success("修改成功");
    }

    @PostMapping("/type/create")
    public Result createType(@Validated @RequestBody Type type) {
        if (type == null) {
            return Result.error("不能为空");
        } else {
            typeService.createType(type);
            redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_NAME_CACHE);
        }
        return Result.success("新增成功");
    }
}
