package com.aa12501.community.dto;

import lombok.Data;

/**
 * 获取授权的token值
 *
 */

@Data
public class AccessTokenDTO {
    private String client_id;   //授权id
    private String client_secret;   //授权密钥
    private String code;        //页面信息状态码
    private String redirect_uri;    //跳转地址
    private String state;       //状态信息
}
