package com.hmdp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.ShopType;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.LOGIN_USER_KEY;
import static com.hmdp.utils.RedisConstants.LOGIN_USER_TTL;

@SpringBootTest
public class OneThousandUser extends ServiceImpl<UserMapper, User> implements IUserService {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private UserServiceImpl userService;

    @Test
    public void test() throws IOException {

        QueryWrapper<User> user1000 = new QueryWrapper<>();
        user1000.getEntity();
        List<User> shopTypeList = list(user1000);
        System.out.println("shopTypeList = " + shopTypeList);

        // 在循环开始前创建文件（使用try-with-resources自动管理资源）
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("F:/Code/JavaStudy/hm-dianping/src/test/java/com/hmdp/tokens.txt"))) {
            for (User user : shopTypeList) {
                String token = UUID.randomUUID().toString(true);

                // 将token写入文件（立即刷新）
                writer.write(token);
                writer.newLine();  // 换行
                writer.flush();    // 确保实时写入

                // 原有业务逻辑保持不变
                UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
                Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                        CopyOptions.create()
                                .setIgnoreNullValue(true)
                                .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));

                String redis_token = LOGIN_USER_KEY + token;
                stringRedisTemplate.opsForHash().putAll(redis_token, userMap);
                // stringRedisTemplate.expire(redis_token, LOGIN_USER_TTL, TimeUnit.MINUTES);
            }
        } catch (IOException e) {
            System.err.println("文件写入失败！错误详情：");
            e.printStackTrace();
            // 这里应该根据你的异常处理策略进行调整
        }
    }

    @Override
    public Result sendCode(String phone, HttpSession session) {
        return null;
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        return null;
    }
}
