package com.aa12501.community.model;

import lombok.Data;

/**
 * user的属性，对应数据库表t_user
 */

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
