package com.jqubian.wechat.config;

import com.jqubian.wechat.common.Msg;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Copyright (c) 2018, Chen_9g 陈刚 (80588183@qq.com).
 * @DateTime 2018/4/26
 *
 * <p>
 * A novice on the road, please give me a suggestion.
 * 众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。
 * Find a way for success and not make excuses for failure.
 * </p>
 * 所有的异常处理.
 */
@RestControllerAdvice
public class GXFExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    private Msg exceptionhandler(Exception e){

        return Msg.fail().put("err",e.getMessage());
    }
}
