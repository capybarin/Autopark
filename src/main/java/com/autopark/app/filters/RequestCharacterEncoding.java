package com.autopark.app.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтр выставляет кодировку для всех вызовов
 * @author Bezdushnyi Vladyslav
 */

public class RequestCharacterEncoding implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
