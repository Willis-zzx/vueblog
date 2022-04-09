package com.zzx.mapper;

import com.zzx.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zzx
 * @since 2022-04-04
 */
@Mapper
public interface UserMapper {
    /**
     * 通过id获取user
     *
     * @param id id
     * @return user实体类
     */
    User getById(Long id);

    /**
     * 通过用户姓名获取user
     *
     * @param userName 用户姓名
     * @return user实体类
     */
    User getUserByName(String userName);
}
