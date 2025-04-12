package com.lyy.allbadminton.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import com.lyy.allbadminton.dto.UserDTO;
import com.lyy.allbadminton.dto.UserLoginDTO;
import com.lyy.allbadminton.entity.User;
import com.lyy.allbadminton.mapper.UserMapper;
import com.lyy.allbadminton.result.Result;
import com.lyy.allbadminton.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.lyy.allbadminton.util.RedisConstants.LOGIN_USER_KEY;
import static com.lyy.allbadminton.util.RedisConstants.LOGIN_USER_TTL;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();
        User user = query().eq("username", username).eq("password", password).one();
        if (user == null) {
            return Result.error("用户名或密码错误");
        }

        String token = UUID.randomUUID().toString(true);

        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);

        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));

        String redis_token = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForHash().putAll(redis_token, userMap);
        //设置有效期
        stringRedisTemplate.expire(redis_token, LOGIN_USER_TTL, TimeUnit.MINUTES);

        return Result.success(token);
    }
}
