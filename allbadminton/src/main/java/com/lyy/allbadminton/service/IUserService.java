package com.lyy.allbadminton.service;

import com.lyy.allbadminton.dto.UserLoginDTO;
import com.lyy.allbadminton.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyy.allbadminton.result.Result;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
public interface IUserService extends IService<User> {


    Result login(UserLoginDTO userLoginDTO);
}
