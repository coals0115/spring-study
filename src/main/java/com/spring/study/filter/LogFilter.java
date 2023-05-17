package com.spring.study.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // filter초기화 작업 작성
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1. 서블릿이 요청을 받기 전에 처리할 작업 작성(요청 전처리 작업)**
        System.out.println("[start]" + new Date());

        // 2. 다음 filter가 작업을 할 수 있게 요청과 응답을 전달(그대로 사용)
        filterChain.doFilter(request, response);

        // 3. 서블릿이 응답한 직후에 처리할 작업 작성(응답 후처리 작업)**
        System.out.println("[end]" + new Date());
    }

    @Override
    public void destroy() {
        // filter가 제거되기 전에 수행되어야 할 마무리 작업 작성
    }
}
