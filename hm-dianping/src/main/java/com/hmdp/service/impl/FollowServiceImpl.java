package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.Follow;
import com.hmdp.entity.User;
import com.hmdp.mapper.FollowMapper;
import com.hmdp.service.IFollowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.service.IUserService;
import com.hmdp.utils.UserHolder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements IFollowService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private IUserService userService;

    @Override
    public Result follow(Long followUserId, Boolean isFollow) {
        // 1.获取登录用户
        Long userId = UserHolder.getUser().getId();
        String key = "follows:" + userId;
        // 1.判断到底是关注还是取关
        if (isFollow) {
            // 2.关注，新增数据
            Follow follow = new Follow();
            follow.setUserId(userId);
            follow.setFollowUserId(followUserId);
            boolean isSuccess = save(follow);
            if (isSuccess) {
                // 把关注用户的id，放入redis的set集合 sadd userId followUserId
                stringRedisTemplate.opsForSet().add(key, followUserId.toString());
            }
        }else {
            // 3.取关，删除数据 delete from tb_follow where user_id = ? and follow_user_id = ?
            boolean isSuccess = remove(new QueryWrapper<Follow>()
                    .eq("user_id", userId).eq("follow_user_id", followUserId));
            if (isSuccess) {
                // 把关注用户的id从Redis集合中移除 srem userId followUserId
                stringRedisTemplate.opsForSet().remove(key, followUserId.toString());
            }
        }
        // 3.取关，删除数据
        return Result.ok();
    }

    @Override
    public Result isFollow(Long followUserId) {
        //1.获取登录用户
        Long userId = UserHolder.getUser().getId();
        //2.查询是否关注 select count(*) from tb_follow where user_id = ? and follow_user_id = ?
        Integer count = query().eq("user_id", userId).eq("follow_user_id", followUserId).count();
        //3.判断是否关注
        return Result.ok(count > 0);
    }

    //TODO 共同关注
    @Override
    public Result commonFollow(Long followUserId) {
        //1.获取登录用户
        Long userId = UserHolder.getUser().getId();
        //2.查询是否关注 select count(*) from tb_follow where user_id = ? and follow_user_id = ?
//        Integer count = query().eq("user_id", userId).eq("follow_user_id", followUserId).count();
        Set<String> intersect = stringRedisTemplate.opsForSet().intersect("follows:" + userId, "follows:" + followUserId);
        if(intersect == null || intersect.isEmpty()){
            return Result.ok(Collections.emptyList());
        }
        //3.判断是否关注
        List<Long> collect = intersect.stream().map(Long::valueOf).collect(Collectors.toList());
        List<UserDTO> users = userService.listByIds(collect)
                .stream()
                .map(user -> BeanUtil.copyProperties(user, UserDTO.class))
                .collect(Collectors.toList());
        //4.返回结果

        return Result.ok(users);
    }
}
