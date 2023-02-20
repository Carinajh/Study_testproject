package com.example.testproject.interceptor;

import com.example.testproject.service.impl.ShortUrlServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class HttpInterceptor implements HandlerInterceptor {
    private final Logger LOGGER = LoggerFactory.getLogger(HttpInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        LOGGER.info("[preHandle] preHandle is performed");
        LOGGER.info("[preHandle] preHandle : {}",request);
        LOGGER.info("[preHandle] preHandle path info : {}",request.getPathInfo());
        LOGGER.info("[preHandle] preHandle header names: {}",request.getHeaderNames());
        LOGGER.info("[preHandle] preHandle request URL: {}",request.getRequestURL());
        LOGGER.info("[preHandle] preHandle request URI: {}",request.getRequestURI());
        LOGGER.info("[preHandle] preHandle requested Session Id : {}",request.getRequestedSessionId());

        //TODO HttpServletRequsetWrapper 구현하여 Body값 확인할 수 있게 코드 추가

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        LOGGER.info("[postHandle] postHandle is performed");
        LOGGER.info("[postHandle] postHandle : {}",request);
        LOGGER.info("[postHandle] postHandle : {}",response);
        LOGGER.info("[postHandle] postHandle header names: {}",response.getHeaderNames());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
        Object handler, Exception ex) throws Exception {
        LOGGER.info("[afterCompletion] afterCompletion is performed");
    }
}
