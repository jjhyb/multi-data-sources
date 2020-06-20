package com.yibo.jta.response;

/**
 * @author: huangyibo
 * @Date: 2020/6/14 3:01
 * @Description:
 */
public class PageResult<T> implements BaseErrorInfoInterface {

    //返回码
    private Integer code;

    //提示信息
    private String message;

    //返回具体内容
    private Page<T> content;


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

    public Page<T> getContent() {
        return content;
    }

    public void setContent(Page<T> content) {
        this.content = content;
    }
}
