package com.xiao.carrental.dto.response;

import com.xiao.carrental.common.ReturnCode;
import lombok.Data;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Data
public class CommonResult<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> CommonResult<T> success(T data, String msg) {
        CommonResult<T> result = new CommonResult<>();
        result.setCode(ReturnCode.SUCCESS.getCode());
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        return success(data, ReturnCode.SUCCESS.getMsg());
    }

    public static <T> CommonResult<T> error(Integer code, String msg) {
        CommonResult<T> result = new CommonResult<T>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static <T> CommonResult<T> error(String msg) {
        return error(ReturnCode.VALIDATE_FAILED.getCode(), msg);
    }
}
