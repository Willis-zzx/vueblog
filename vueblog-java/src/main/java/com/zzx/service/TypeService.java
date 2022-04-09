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
     * 获取分类列表
     *
     * @return 分类列表集合
     */
    List<Type> getTypeList();
}
