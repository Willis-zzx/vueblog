package com.zzx.service.impl;

import com.zzx.entity.Type;
import com.zzx.mapper.TypeMapper;
import com.zzx.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2022-04-09
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    /**
     * 通过查询条件获取分类
     *
     * @param type 分类查询条件
     * @return 分类实体类
     */
    @Override
    public Type getType(Type type) {
        return typeMapper.getType(type);
    }

    /**
     * 根据查询条件获取分类列表
     *
     * @param type 分类查询条件
     * @return 分类列表
     */
    @Override
    public List<Type> getTypeList(Type type) {
        return typeMapper.getTypeList(type);
    }

    /**
     * 根据id删除分类
     *
     * @param id 分类id
     * @return 执行结果
     */
    @Override
    public int deleteTypeById(Long id) {
        return typeMapper.deleteTypeById(id);
    }

    /**
     * 修改分类
     *
     * @param type 分类实体
     * @return 执行结果
     */
    @Override
    public int updateType(Type type) {
        return typeMapper.updateType(type);
    }

    /**
     * 新增分类
     *
     * @param type 分类实体
     * @return 执行结果
     */
    @Override
    public int createType(Type type) {
        return typeMapper.createType(type);
    }
}
