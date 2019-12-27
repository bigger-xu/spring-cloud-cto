package com.cto.cloud.advice;

import com.cto.cloud.exception.CustomException;
import com.cto.cloud.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-12-16
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = CustomException.class)
    public CommonResponse<String> handleCustomException(CustomException e){
        CommonResponse<String> response = new CommonResponse<>(e.getCode(),e.getMessage());
        return response;
    }
}
