package com.zzx.service.impl;

import com.zzx.entity.Type;
import com.zzx.mapper.TypeMapper;
import com.zzx.service.TypeService;
import jdk.nashorn.internal.codegen.TypeMap;
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
     * 获取分类列表
     *
     * @return 分类列表集合
     */
    @Override
    public List<Type> getTypeList() {
        return typeMapper.getTypeList();
    }
}
