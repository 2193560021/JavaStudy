package com.lyy.allbadminton.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * <p>
 * 羽毛球场地
 * </p>
 *
 * @author lyy
 * @since 2025-04-06
 */
public class Court implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 场地名称
     */
    private String name;

    /**
     * 详细地址
     */
    private String location;

    /**
     * 状态(0-不可用 1-可用)
     */
    private Integer status;

    /**
     * 每小时价格
     */
    private BigDecimal price;

    /**
     * 开放时间
     */
    private LocalTime openTime;

    /**
     * 关闭时间
     */
    private LocalTime closeTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }
    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    @Override
    public String toString() {
        return "Court{" +
            "id=" + id +
            ", name=" + name +
            ", location=" + location +
            ", status=" + status +
            ", price=" + price +
            ", openTime=" + openTime +
            ", closeTime=" + closeTime +
        "}";
    }
}
