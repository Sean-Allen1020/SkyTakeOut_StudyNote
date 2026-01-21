package com.loginfileuploadcode.controller;

import com.loginfileuploadcode.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
public class UploadController {


    @GetMapping("/file")
    public Result getFile() {

        return Result.success();
    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException {
        log.info("文件 {}", file);
        //1. 检查本地磁盘是否有目录，如果没有就创建
        File dir = new File("C:\\file");
        if (!dir.exists()) {dir.mkdir();}

        //2. 防止同名文件覆盖，重设文件名
            // 先获取文件尾缀
        String fileName = file.getOriginalFilename();
        String extention = fileName.substring(fileName.lastIndexOf("."));
            // 修改文件名为 UUID
        fileName = UUID.randomUUID().toString() + "." + extention;

        //3. 储存文件
        file.transferTo(new File("C:\\file\\" +  fileName));
        return Result.success();
    }
}
