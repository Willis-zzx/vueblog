package com.zzx.controller;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zzx.common.lang.Result;
import com.zzx.config.RedisKeyConfig;
import com.zzx.entity.Friend;
import com.zzx.service.FriendService;
import com.zzx.service.RedisService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/10 10:43
 */
@RestController
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private RedisService redisService;

    /**
     * 查询所有公开的友链
     */
    @RequestMapping("/friend/all")
    public Result getFriendList() {
        if (redisService.hasHashKey(RedisKeyConfig.FRIEND_INFO_CACHE, RedisKeyConfig.All)) {
            return Result.success(redisService.getValueByHashKey(RedisKeyConfig.FRIEND_INFO_CACHE, RedisKeyConfig.All));
        }
        List<Friend> list = friendService.getFriendList(new Friend().setIsPublished(true));
        redisService.saveKVToHash(RedisKeyConfig.FRIEND_INFO_CACHE, RedisKeyConfig.All, list);
        return Result.success(list);
    }


    /**
     * 分页查询所有友链
     */
    @GetMapping("/friendList")
    public Result friendList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Friend> list = friendService.getFriendList(new Friend().setSort("desc"));
        PageInfo pageInfo = new PageInfo<>(list);
        return Result.success(pageInfo);
    }

    /**
     * 友链浏览次数加一
     */
    @RequestMapping("/friend/onclick")
    public Result addView(@RequestParam(name = "") String nickname) {
        if ("".equals(nickname)) {
            return Result.error("访问出错");
        }
        Friend friend = friendService.getFriend(new Friend().setNickname(nickname));
        friend.setViews(friend.getViews() + 1);
        friendService.updateFriend(friend);
        return Result.success(null);
    }


    /**
     * 修改友链的状态
     */
    @RequestMapping("friend/publish/{id}")
    public Result publish(@PathVariable(name = "id") Long id) {
        Friend friend = friendService.getFriend(new Friend().setId(id));
        friend.setIsPublished(!friend.getIsPublished());
        friendService.updateFriend(friend);
        redisService.deleteCacheByKey(RedisKeyConfig.FRIEND_INFO_CACHE);
        return Result.success(null);

    }


    /**
     * 修改友链
     */
    @PostMapping("/friend/update")
    public Result updateFriend(@Validated @RequestBody Friend friend) {
        if (friend == null) {
            return Result.error("不能为空");

        } else {
            if (friend.getId() == null) {
                friend.setCreateTime(new Timestamp(System.currentTimeMillis()));
            }
            friendService.updateFriend(friend);
            redisService.deleteCacheByKey(RedisKeyConfig.FRIEND_INFO_CACHE);
        }
        return Result.success(null);
    }

    /**
     * 增加友链
     */
    @PostMapping("/friend/create")
    public Result createFriend(@Validated @RequestBody Friend friend) {
        if (friend == null) {
            return Result.error("不能为空");
        } else {
            if (friend.getId() == null) {
                friend.setCreateTime(new Timestamp(System.currentTimeMillis()));
            }
            friendService.createFriend(friend);
            redisService.deleteCacheByKey(RedisKeyConfig.FRIEND_INFO_CACHE);
        }
        return Result.success(null);
    }


    /**
     * 删除友链
     */
    @GetMapping("/friend/delete/{id}")
    public Result delete(@PathVariable(name = "id") Long id) {

        if (friendService.deleteFriendById(id) == 1) {
            redisService.deleteCacheByKey(RedisKeyConfig.FRIEND_INFO_CACHE);
            return Result.success(null);
        } else {
            return Result.error("删除失败");
        }
    }
}
