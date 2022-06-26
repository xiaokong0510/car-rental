package com.xiao.carrental.exception;

import com.xiao.carrental.dto.response.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Component
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private HttpServletRequest request;

    /**
     * 处理 query 参数缺失异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public CommonResult exceptionHandler(Exception e) {
        log.warn("异常：MissingServletRequestParameterException，原因: {},请求方式: {},请求url: {},请求参数: {}",
                e.getMessage(), request.getMethod(), request.getRequestURI(), request.getQueryString());
        return CommonResult.error(e.getMessage());
    }

    /**
     * 处理参数类型转换异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public CommonResult mismatchErrorHandler(MethodArgumentTypeMismatchException e) {
        log.warn("异常：MissingServletRequestParameterException，原因: {},请求方式: {},请求url: {},请求参数: {}",
                e.getMessage(), request.getMethod(), request.getRequestURI(), request.getQueryString());
        return CommonResult.error(e.getMessage());
    }


    /**
     * 处理 query 参数校验 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public CommonResult constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.warn("异常：MissingServletRequestParameterException，原因: {},请求方式: {},请求url: {},请求参数: {}",
                e.getMessage(), request.getMethod(), request.getRequestURI(), request.getQueryString());
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return CommonResult.error(message);
    }

    /**
     * 处理用实体类接受 query 参数的校验异常 BindException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public CommonResult bindExceptionExceptionHandler(BindException e) {
        log.warn("异常：MissingServletRequestParameterException，原因: {},请求方式: {},请求url: {},请求参数: {}",
                e.getMessage(), request.getMethod(), request.getRequestURI(), request.getQueryString());
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return CommonResult.error(message);
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.warn("异常：MissingServletRequestParameterException，原因: {},请求方式: {},请求url: {},请求参数: {}",
                e.getMessage(), request.getMethod(), request.getRequestURI(), request.getQueryString());
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return CommonResult.error(message);
    }

    /**
     * 处理一般异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public CommonResult BusinessExceptionHandler(BusinessException e) {
        return CommonResult.error(e.getReturnCode().getCode(), e.getReturnCode().getMsg());
    }
}
