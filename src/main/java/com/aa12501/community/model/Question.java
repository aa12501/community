package com.aa12501.community.model;

import lombok.Data;

/**
 * question的属性，对应数据库表t_question
 */

@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}
