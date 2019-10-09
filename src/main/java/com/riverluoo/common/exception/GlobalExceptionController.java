package com.riverluoo.common.exception;

import com.riverluoo.common.base.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther: wangyang
 * @since: 2019-09-27 13:16
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

    @ResponseBody
    @ExceptionHandler(UserException.class)
    public HttpResult userException(UserException userException) {
        String message = userException.getMessage();

        return HttpResult.fail(message);
    }

    @ResponseBody
    @ExceptionHandler(AuthorizeException.class)
    public HttpResult userException(AuthorizeException authorizeException) {

        return HttpResult.fail("token过期 请重新登录!");
    }
}
