package com.yibo.multisource.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author: huangyibo
 * @Date: 2020/6/14 3:03
 * @Description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public Result handleException(HttpRequestMethodNotSupportedException e) {
        log.error(e.getMessage(), e);
        return ResultUtil.fail("不支持' " + e.getMethod() + "'请求");
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Result notFount(RuntimeException e) {
        log.error("运行时异常:", e);
        return ResultUtil.fail("运行时异常:" + e.getMessage());
    }


    /**
     * 校验方法参数异常处理
     * 捕获 MethodArgumentNotValidException 异常
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result MethodArgumentNotValidExceptionHandle(MethodArgumentNotValidException exception) {

        List<FieldError> errors = exception.getBindingResult().getFieldErrors();

        String errorMsg = errors.stream().map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1  + s2);
        return ResultUtil.fail(errorMsg);
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = BindException.class)
    public Result validationExceptionHandler(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMesssage = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + "!";
        }
        return ResultUtil.fail(errorMesssage);
    }

    /**
     * 校验异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        Iterator<ConstraintViolation<?>> iterator = constraintViolations.iterator();
        List<String> msgList = new ArrayList<>();
        while (iterator.hasNext()) {
            ConstraintViolation<?> cvl = iterator.next();
            msgList.add(cvl.getMessageTemplate());
        }
        return ResultUtil.fail(String.join(",",msgList));
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BizException.class)
    public Result businessException(BizException e) {
        log.error(e.getErrorMsg(), e);
        return ResultUtil.fail(e.getErrorMsg());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultUtil.fail("服务器错误，请联系管理员");
    }
}
