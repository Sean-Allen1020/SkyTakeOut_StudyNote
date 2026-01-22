package com.loginfileuploadcode.controller;

import com.aliyuncs.exceptions.ClientException;
import com.loginfileuploadcode.pojo.Result;
import com.loginfileuploadcode.service.FileService;
import com.loginfileuploadcode.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@Slf4j
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @Autowired
    private FileService fileService;


//    @GetMapping("/file")
//    public Result getFile() {
//
//        return Result.success();
//    }

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws IOException, ClientException {
        log.info("文件 {}", file);
        String url = aliyunOSSOperator.upload(file);
        fileService.upload(url);

        return Result.success(url);
    }
}
