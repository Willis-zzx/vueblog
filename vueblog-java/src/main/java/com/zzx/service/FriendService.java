package com.zzx.service;

import com.zzx.entity.Friend;

import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/10 10:44
 */
public interface FriendService {
    /**
     * 根据查询获取友联列表
     *
     * @param friend 查询条件
     * @return friend列表
     */
    List<Friend> getFriendList(Friend friend);

    /**
     * 根据查询获取友联详情
     *
     * @param friend 查询条件
     * @return friend详情
     */
    Friend getFriend(Friend friend);

    /**
     * @param friend
     * @return
     */
    int updateFriend(Friend friend);

    void createFriend(Friend friend);

    int deleteFriendById(Long id);
}
