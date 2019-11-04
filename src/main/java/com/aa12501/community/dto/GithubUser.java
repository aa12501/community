package com.aa12501.community.dto;

import lombok.Data;

/**
 * 从github中获取登录人的信息
 *
 * @name    用户名
 * @id      用户id值
 * @dio     用户个性签名
 * @avatar_url  用户头像链接
 *
 */

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String dio;
    private String avatar_url;
}
