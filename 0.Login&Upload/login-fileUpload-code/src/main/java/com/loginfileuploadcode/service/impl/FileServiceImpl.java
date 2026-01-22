package com.loginfileuploadcode.service.impl;

import com.loginfileuploadcode.mapper.FileMapper;
import com.loginfileuploadcode.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    FileMapper fileMapper;

    @Override
    public void upload(String url) {
        fileMapper.insert(url);
    }
}
