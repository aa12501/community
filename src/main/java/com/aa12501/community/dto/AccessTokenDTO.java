package com.aa12501.community.dto;

import lombok.Data;

/**
 * 获取授权的token值
 *
 * @client_id       授权id
 * @client_secret   授权密钥
 * @code            页面信息状态码
 * @redirect_uri    跳转地址
 * @state           状态信息
 */

@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
