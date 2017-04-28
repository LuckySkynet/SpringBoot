package com.skynet.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 实现HandlerExceptionResolver接口处理全局异常
 *
 * @author Skynet
 * @date 2017年04月28日 17:07
 */
@Component
public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("exception", ex);
        mav.addObject("url", request.getRequestURL());
        return mav;
    }
}
