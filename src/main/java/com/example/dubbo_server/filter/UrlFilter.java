package com.example.dubbo_server.filter;

import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * <p>
 * 無
 * </p>
 *
 * @author NWT)hxl
 * @version 1.0
 * <p>
 * 変更履歴:
 * 2020/08/17 ： NWT)hxl ： 新規作成
 * @date 2020/08/17 15:15
 */
@Component
public class UrlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(((RequestFacade) servletRequest).getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
