package com.loginfileuploadcode.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {

    @Insert("insert into upload_test (url) value(#{url})")
    public void insert(String url);
}
