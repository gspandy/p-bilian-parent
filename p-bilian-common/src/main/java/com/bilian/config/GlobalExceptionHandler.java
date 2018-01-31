package com.bilian.config;

import com.bilian.result.ResultCode;
import com.bilian.result.ResultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局统一异常处理
 * Controller不处理异常
 * Created by renpeng on 2016/11/28.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultResponse<Object> handleException(HttpServletRequest request, Exception ex) {
        ResultResponse<Object> result = new ResultResponse<>();
        result.setCode(ResultCode.C500.getCode());
        result.setMessage("哎呀，服务器出现异常，请稍后再试");
        result.setData(ex.getMessage());
        logger.error("GlobalExceptionHandler handleException error", ex);
        return result;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultResponse<Object> httpMessageNotReadableException(HttpServletRequest request, MissingServletRequestParameterException ex) {
        ResultResponse<Object> result = new ResultResponse<>();
        result.setCode(ResultCode.C500.getCode());
        result.setMessage("必要参数字段没有传递，请检查参数字段后再进行访问");
        result.setData(ex.getMessage());
        logger.error("GlobalExceptionHandler MissingServletRequestParameterException error", ex);
        return result;
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultResponse<Object> httpMessageNotReadableException(HttpServletRequest request, HttpMessageNotReadableException ex) {
        ResultResponse<Object> result = new ResultResponse<>();
        result.setCode(ResultCode.C500.getCode());
        result.setMessage("传入的参数类型有问题，请检查参数类型后再进行访问");
        result.setData(ex.getMessage());
        logger.error("GlobalExceptionHandler httpMessageNotReadableException error", ex);
        return result;
    }

}
