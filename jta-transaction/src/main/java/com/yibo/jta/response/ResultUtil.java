package com.yibo.jta.response;

/**
 * @author: huangyibo
 * @Date: 2020/6/14 3:02
 * @Description:
 */
public class ResultUtil {

    /**
     * 成功
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(CommonEnum.SUCCESS.getCode());
        result.setMessage(CommonEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 失败
     */
    public static Result fail(BaseErrorInfoInterface baseErrorInfoInterface) {
        Result result = new Result();
        result.setCode(baseErrorInfoInterface.getCode());
        result.setMessage(baseErrorInfoInterface.getMessage());
        result.setData(null);
        return result;
    }

    /**
     * 失败
     */
    public static Result fail(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
    }

    /**
     * 失败
     */
    public static Result fail( String message) {
        Result result = new Result();
        result.setCode(-1);
        result.setMessage(message);
        result.setData(null);
        return result;
    }
}
