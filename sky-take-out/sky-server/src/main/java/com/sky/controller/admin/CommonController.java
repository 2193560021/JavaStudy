package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * 通用controller
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    public AliOssUtil aliOssUtil;

    @PostMapping("/upload") //上传
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) throws IOException {
        log.info("文件上传：{}",file);
        String originalFilename = file.getOriginalFilename();
        if(originalFilename==null){
            return Result.error("文件为空");
        }
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName = UUID.randomUUID().toString() + extension;
        //文件的请求路径
        String filePath = aliOssUtil.upload(file.getBytes(), objectName);
        return Result.success(filePath);
    }
}
