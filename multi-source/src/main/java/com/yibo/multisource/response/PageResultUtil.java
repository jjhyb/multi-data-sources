package com.yibo.multisource.response;

/**
 * @author: huangyibo
 * @Date: 2020/6/14 3:02
 * @Description:
 */
public class PageResultUtil {


    /**
     * 成功
     *
     * @return
     */
    public static PageResult success() {
        return success(null);
    }

    /**
     * 成功
     * @param content
     * @return
     */
    public static PageResult success(Page content) {
        PageResult pageResult = new PageResult();
        pageResult.setCode(CommonEnum.SUCCESS.getCode());
        pageResult.setMessage(CommonEnum.SUCCESS.getMessage());
        pageResult.setContent(content);
        return pageResult;
    }

    /**
     * 失败
     */
    public static PageResult fail(BaseErrorInfoInterface baseErrorInfoInterface) {
        PageResult pageResult = new PageResult();
        pageResult.setCode(baseErrorInfoInterface.getCode());
        pageResult.setMessage(baseErrorInfoInterface.getMessage());
        pageResult.setContent(null);
        return pageResult;
    }

    /**
     * 失败
     */
    public static PageResult fail(Integer code, String message) {
        PageResult pageResult = new PageResult();
        pageResult.setCode(code);
        pageResult.setMessage(message);
        pageResult.setContent(null);
        return pageResult;
    }

    /**
     * 失败
     */
    public static PageResult fail( String message) {
        PageResult pageResult = new PageResult();
        pageResult.setCode(-1);
        pageResult.setMessage(message);
        pageResult.setContent(null);
        return pageResult;
    }
}
