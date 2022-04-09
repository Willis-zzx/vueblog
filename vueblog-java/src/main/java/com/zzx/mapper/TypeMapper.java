package com.zzx.mapper;

import com.zzx.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzx
 * @since 2022-04-09
 */
@Mapper
public interface TypeMapper {

    /**
     * 获取分类列表
     *
     * @return 分类列表集合
     */
    List<Type> getTypeList();
}
