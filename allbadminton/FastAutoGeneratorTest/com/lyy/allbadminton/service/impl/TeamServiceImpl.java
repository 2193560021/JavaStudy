package com.lyy.allbadminton.service.impl;

import com.lyy.allbadminton.entity.Team;
import com.lyy.allbadminton.mapper.TeamMapper;
import com.lyy.allbadminton.service.ITeamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 组队信息 服务实现类
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements ITeamService {

}
