package com.spring.study.filter;//package com.spring.study;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = "/*") // WebServlet아니고 WebFilter임...!!
//public class RequestFilterTest implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//        String method = request.getMethod();
//        String referer = request.getHeader("referer"); // 어디서 요청했는지 알 수 있다.
//        String requestURI = request.getRequestURI();
//
//        System.out.printf("[ %s ] -> %s [ %s ]\n", referer, method, requestURI);
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//}
