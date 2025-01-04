package com.atguigu.mybatisplus.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author atguigu
 * @since 2025-01-02
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    private Integer sex;

    private Integer isDeleted;

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
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name=" + name +
            ", age=" + age +
            ", email=" + email +
            ", sex=" + sex +
            ", isDeleted=" + isDeleted +
        "}";
    }
}
