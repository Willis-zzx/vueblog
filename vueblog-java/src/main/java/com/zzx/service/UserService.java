package com.zzx.service;

import com.zzx.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zzx
 * @since 2022-04-04
 */
public interface UserService {

    /**
     * 通过id获取user
     *
     * @param id id
     * @return user实体类
     */
    User getUserById(Long id);

    /**
     * 通过用户姓名获取user
     *
     * @param userName 用户姓名
     * @return user实体类
     */
    User getUserByName(String userName);


}
