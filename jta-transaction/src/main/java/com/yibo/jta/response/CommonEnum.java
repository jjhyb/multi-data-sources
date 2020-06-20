package com.yibo.jta.response;

/**
 * @author: huangyibo
 * @Date: 2020/6/14 2:59
 * @Description:
 */
public enum CommonEnum implements BaseErrorInfoInterface {

    // 数据操作错误定义
    SUCCESS(200, "成功!"),
    BODY_NOT_MATCH(400,"请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(401,"请求的数字签名不匹配!"),
    NOT_FOUND(404, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),
    SERVER_BUSY(503,"服务器正忙，请稍后再试!"),
    NOT_LOGIN(301,"用户未登录，请先登录！"),
    ;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String message;

    CommonEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
