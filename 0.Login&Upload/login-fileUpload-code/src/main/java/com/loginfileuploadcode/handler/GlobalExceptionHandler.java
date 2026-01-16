package com.loginfileuploadcode.handler;
import com.loginfileuploadcode.exception.PasswordErrorException;
import com.loginfileuploadcode.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountNotFoundException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public Result exceptionHandler(Exception e){
        log.error("异常 {}", e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler
    public Result handleException(AccountNotFoundException e){
        log.error("异常 {}", e.getMessage());
        return Result.error("アカウントは存在しない");
    }

    @ExceptionHandler
    public Result handleException(PasswordErrorException e){
        log.error("异常 {}", e.getMessage());
        return Result.error("パスワードは正しくない");
    }
}
