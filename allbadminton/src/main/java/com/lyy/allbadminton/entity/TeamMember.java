package com.lyy.allbadminton.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 队伍成员关系
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
@TableName("team_member")
public class TeamMember implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long teamId;

    private Long userId;

    private LocalDateTime joinTime;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public LocalDateTime getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    public String toString() {
        return "TeamMember{" +
            "teamId=" + teamId +
            ", userId=" + userId +
            ", joinTime=" + joinTime +
        "}";
    }
}
