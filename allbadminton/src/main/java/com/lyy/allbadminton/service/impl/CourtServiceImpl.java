package com.lyy.allbadminton.service.impl;

import com.lyy.allbadminton.entity.Court;
import com.lyy.allbadminton.mapper.CourtMapper;
import com.lyy.allbadminton.result.Result;
import com.lyy.allbadminton.service.ICourtService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 羽毛球场地 服务实现类
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
@Service
public class CourtServiceImpl extends ServiceImpl<CourtMapper, Court> implements ICourtService {

    @Override
    public Result<List<Court>> getAvailableCourt() {
        List<Court> courtList = query().eq("status", 1).list();

        return Result.success(courtList);
    }
}
