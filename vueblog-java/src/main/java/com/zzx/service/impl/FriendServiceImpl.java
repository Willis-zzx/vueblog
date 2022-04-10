package com.zzx.service.impl;

import com.zzx.entity.Friend;
import com.zzx.mapper.FriendMapper;
import com.zzx.service.FriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/10 10:44
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    private FriendMapper friendMapper;

    /**
     * 根据查询获取友联列表
     *
     * @param friend 查询条件
     * @return friend列表
     */
    @Override
    public List<Friend> getFriendList(Friend friend) {
        return friendMapper.getFriendList(friend);
    }

    /**
     * 根据查询获取友联详情
     *
     * @param friend 查询条件
     * @return friend详情
     */
    @Override
    public Friend getFriend(Friend friend) {
        return friendMapper.getFriend(friend);
    }

    /**
     * @param friend
     * @return
     */
    @Override
    public int updateFriend(Friend friend) {
        return friendMapper.updateFriend(friend);
    }

    @Override
    public void createFriend(Friend friend) {
        friendMapper.createFriend(friend);
    }

    @Override
    public int deleteFriendById(Long id) {
        return friendMapper.deleteFriendById(id);
    }
}
