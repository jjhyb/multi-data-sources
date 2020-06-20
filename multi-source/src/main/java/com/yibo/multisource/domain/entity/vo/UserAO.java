package com.yibo.multisource.domain.entity.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserAO {

    private Integer id;

    private String wxId;

    private String wxNickname;

    private String roles;

    private String avatarUrl;

    private Date createTime;

    private Date updateTime;

    private Integer bonus;
}