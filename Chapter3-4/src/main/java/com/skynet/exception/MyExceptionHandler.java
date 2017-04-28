package com.skynet.exception;


import com.skynet.dto.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Skynet
 * @date 2017年04月28日 16:40
 */
@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 处理Exception，即所有异常
     * ExceptionHandler处理优先级在HandlerExceptionResolver之前
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        return mav;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result<String> jsonErrorHandler(HttpServletRequest request, MyException e) {
        Result<String> result = new Result<String>();
        result.setCode(Result.ERROR);
        result.setMessage(e.getMessage());
        result.setUrl(request.getRequestURL().toString());
        result.setData("Some Date");
        return result;
    }


}
