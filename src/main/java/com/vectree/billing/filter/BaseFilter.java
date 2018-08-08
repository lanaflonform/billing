package com.vectree.billing.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Abstract filter.
 *
 * @version 0.1
 */
public abstract class BaseFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // NONE
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    public abstract void doFilter(HttpServletRequest req, HttpServletResponse resp,
                                  FilterChain chain) throws ServletException, IOException;

    @Override
    public void destroy() {
        // NONE
    }
}
