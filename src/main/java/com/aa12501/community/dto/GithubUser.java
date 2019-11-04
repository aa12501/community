package com.aa12501.community.dto;

import lombok.Data;

/**
 * 从github中获取登录人的信息
 *
 */

@Data
public class GithubUser {
    private String name;    //用户名
    private Long id;        //用户id
    private String dio;     //用户自我介绍
    private String avatar_url;  //用户头像地址
}
