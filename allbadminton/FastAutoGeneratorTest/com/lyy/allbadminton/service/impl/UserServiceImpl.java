package com.lyy.allbadminton.service.impl;

import com.lyy.allbadminton.entity.User;
import com.lyy.allbadminton.mapper.UserMapper;
import com.lyy.allbadminton.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
