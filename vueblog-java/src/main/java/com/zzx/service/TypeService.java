package com.zzx.service;

import com.zzx.entity.Type;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzx
 * @since 2022-04-09
 */
public interface TypeService {

    /**
     * 通过查询条件获取分类
     *
     * @param type 分类查询条件
     * @return 分类实体类
     */
    Type getType(Type type);

    /**
     * 根据查询条件获取分类列表
     *
     * @param type 分类查询条件
     * @return 分类列表
     */
    List<Type> getTypeList(Type type);

    /**
     * 根据id删除分类
     *
     * @param id 分类id
     * @return 执行结果
     */
    int deleteTypeById(Long id);

    /**
     * 修改分类
     *
     * @param type 分类实体
     * @return 执行结果
     */
    int updateType(Type type);

    /**
     * 新增分类
     *
     * @param type 分类实体
     * @return 执行结果
     */
    int createType(Type type);

}
