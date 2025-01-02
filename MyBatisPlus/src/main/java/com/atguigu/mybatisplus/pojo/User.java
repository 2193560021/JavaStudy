package com.atguigu.mybatisplus.pojo;

import com.atguigu.mybatisplus.enums.SexEnum;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@TableName("user")
public class User {
    private Long id;

//    @TableField("name")
    private String name;

    private Integer age;

    private String email;

    private SexEnum sex;


    @TableLogic
    private Integer isDeleted;

}
