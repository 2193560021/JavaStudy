package com.lyy.allbadminton.service;

import com.lyy.allbadminton.entity.Court;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lyy.allbadminton.result.Result;

import java.util.List;

/**
 * <p>
 * 羽毛球场地 服务类
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
public interface ICourtService extends IService<Court> {

    Result<List<Court>> getAvailableCourt();
}
