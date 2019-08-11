package com.bsu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ShowInfoFilter implements Filter {
    private FilterConfig filterConfig;
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (this.filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
            long start = System.currentTimeMillis();
            filterChain.doFilter(request, response);
            long end = System.currentTimeMillis();
            long time = end - start;
            HttpServletRequest httpRequest = (HttpServletRequest)request;
        }

    }

    public void destroy() {
        this.filterConfig = null;
    }
}