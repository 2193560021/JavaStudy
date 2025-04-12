package com.lyy.allbadminton;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class FastAutoGeneratorTest {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/allbadminton?characterEncoding=utf-8&userSSL=false", "root", "123456")
                        .globalConfig(builder -> {
                            builder.author("lyy") // 设置作者
                                    //.enableSwagger() // 开启 swagger 模式
                                    .fileOverride() // 覆盖已生成文件
                                    .outputDir("F://Code//JavaStudy//allbadminton//FastAutoGeneratorTest"); // 指定输出目录
                        })
                        .packageConfig(builder -> {
                            builder.parent("com.lyy") // 设置父包名
                                    .moduleName("allbadminton") // 设置父包模块名

                                    .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "F://Code//JavaStudy//allbadminton//FastAutoGeneratorTest"));
// 设置mapperXml生成路径
                        })
                        .strategyConfig(builder -> {
                            builder.addInclude("user") // 设置需要生成的表名
                                    .addInclude("team_member")
                                    .addInclude("team")
                                    .addInclude("notification")
                                    .addInclude("goods")
                                    .addInclude("court")
                                    .addInclude("booking_order");

                                    //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                        })
                        .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                        .execute();
    }
}
