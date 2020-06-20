package com.yibo.center.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: huangyibo
 * @Date: 2020/6/14 3:31
 * @Description:
 */

@Data
@ToString
@ApiModel(value="user对象",description="用户对象user")
public class UserAo implements Serializable {

    @ApiModelProperty(value="用户id",name="id",required=false,dataType="Integer")
    private Integer id;

    @ApiModelProperty(value="微信id",name="wxId",required=true,dataType="String")
    private String wxId;

    @ApiModelProperty(value="微信昵称",name="wxNickname",required=true,dataType="String")
    private String wxNickname;

    @ApiModelProperty(value="角色",name="roles",required=true,dataType="String")
    private String roles;

    @ApiModelProperty(value="头像地址",name="avatarUrl",required=true,dataType="String")
    private String avatarUrl;

    @ApiModelProperty(value="创建时间",name="createTime",required=false,dataType="Date")
    private Date createTime;

    @ApiModelProperty(value="修改时间",name="updateTime",required=false,dataType="Date")
    private Date updateTime;

    @ApiModelProperty(value="积分",name="bonus",required=true,dataType="Integer")
    private Integer bonus;

}
