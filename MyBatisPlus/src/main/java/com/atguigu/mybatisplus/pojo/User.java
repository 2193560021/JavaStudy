package com.atguigu.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@TableName("use")
public class User {
    private Long id;

    private String name;

    private Integer age;

    private String email;

}
