package com.zzx.common.exception;

import com.zzx.common.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * <p>
 * 通过使用@ControllerAdvice来进行统一异常处理，
 * 通过使用@ExceptionHandler(value = RuntimeException.class)来指定捕获的Exception各个类型异常 ，
 * 这个异常的处理，是全局的，所有类似的异常，都会跑到这个地方处理。
 * <p>
 * 定义全局异常处理，@ControllerAdvice表示定义全局控制器异常处理，@ExceptionHandler表示针对性异常处理，可对每种异常针对性处理。
 *
 * @author zhouzixin
 * @version 1.0
 * @date 2022/4/5 12:16
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @param e ShiroException：shiro抛出的异常，比如没有权限，用户登录异常
     * @return 统一结果集
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e) {
        log.error("运行时异常：----------------{}", e);
        return Result.error(401, e.getMessage(), null);
    }

    /**
     * @param e MethodArgumentNotValidException：处理实体校验的异常
     * @return 统一结果集
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        log.error("实体校验异常：----------------{}", e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();

        return Result.error(objectError.getDefaultMessage());
    }

    /**
     * @param e IllegalArgumentException：处理Assert的异常
     * @return 统一结果集
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------{}", e);
        return Result.error(e.getMessage());
    }

    /**
     * @param e RuntimeException：捕捉其他异常
     * @return 统一结果集
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e) {
        log.error("运行时异常：----------------{}", e);
        return Result.error(e.getMessage());
    }

}

