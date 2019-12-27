package com.cto.cloud.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Zhang Yongwei
 * @version 1.0
 * @date 2019-12-28
 */
@Data
public class TokenInfo {
    private boolean active;
    private String client_id;
    private String[] scope;
    private String username;
    private String[] aud;
    private Date exp;
    private String[] authorities;
}
