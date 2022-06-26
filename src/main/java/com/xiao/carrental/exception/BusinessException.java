package com.xiao.carrental.exception;

import com.xiao.carrental.common.ReturnCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author kongx
 * @date 2022/6/26
 * @description
 */
@Getter
@Setter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private ReturnCode returnCode;
}
