package com.zzx.mapper;

import com.zzx.entity.Friend;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/10 10:44
 */
@Mapper
public interface FriendMapper {


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

    int updateFriend(Friend friend);

    void createFriend(Friend friend);

    int deleteFriendById(Long id);
}
