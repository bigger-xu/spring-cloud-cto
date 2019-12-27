package com.cto.cloud.vo;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-12-16
 */
public enum  ResponseCode {
    SUCCESS(0,"请求成功"),
    FAIL(-1,"请求失败");


    private Integer code;
    private String message;
    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
