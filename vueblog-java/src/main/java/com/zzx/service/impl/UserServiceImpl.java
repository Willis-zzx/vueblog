package com.zzx.service.impl;

import com.zzx.entity.User;
import com.zzx.mapper.UserMapper;
import com.zzx.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zzx
 * @since 2022-04-04
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 通过id获取user
     *
     * @param id id
     * @return user实体类
     */
    @Override
    public User getUserById(Long id) {
        return userMapper.getById(id);
    }

    /**
     * 通过用户姓名获取user
     *
     * @param userName 用户姓名
     * @return user实体类
     */
    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }
}
