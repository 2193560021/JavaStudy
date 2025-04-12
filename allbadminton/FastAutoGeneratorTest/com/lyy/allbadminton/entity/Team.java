package com.lyy.allbadminton.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 组队信息
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 队长ID
     */
    private Long leaderId;

    /**
     * 场地ID
     */
    private Long courtId;

    /**
     * 活动时间
     */
    private LocalDateTime playTime;

    /**
     * 最大人数
     */
    private Integer maxMembers;

    /**
     * 当前人数
     */
    private Integer currentMembers;

    /**
     * 状态(0-招募中 1-已满员 2-已过期)
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }
    public Long getCourtId() {
        return courtId;
    }

    public void setCourtId(Long courtId) {
        this.courtId = courtId;
    }
    public LocalDateTime getPlayTime() {
        return playTime;
    }

    public void setPlayTime(LocalDateTime playTime) {
        this.playTime = playTime;
    }
    public Integer getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }
    public Integer getCurrentMembers() {
        return currentMembers;
    }

    public void setCurrentMembers(Integer currentMembers) {
        this.currentMembers = currentMembers;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Team{" +
            "id=" + id +
            ", leaderId=" + leaderId +
            ", courtId=" + courtId +
            ", playTime=" + playTime +
            ", maxMembers=" + maxMembers +
            ", currentMembers=" + currentMembers +
            ", status=" + status +
        "}";
    }
}
