package com.cto.cloud.exception;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-12-16
 */
public class CustomException extends Exception{
    private Integer code;
    private String message;

    public CustomException(String message){
        super(message);
        this.code = -1;
        this.message = message;
    }

    public CustomException(Integer code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
}
