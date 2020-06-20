package com.yibo.response;

/**
 * @author: huangyibo
 * @Date: 2020/6/14 2:59
 * @Description:
 */
public interface BaseErrorInfoInterface {

    /**
     * 错误码
     */
    Integer getCode();

    /**
     * 错误描述
     */
    String getMessage();
}
