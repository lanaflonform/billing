package com.vectree.billing.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * class LoggingHandlerExceptionResolverfor logging errors.
 *
 * @version 0.1
 */
public class LoggingHandlerExceptionResolver
        implements HandlerExceptionResolver, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(HandlerExceptionResolver.class);

    public int getOrder() {
        return Integer.MIN_VALUE; // we're first in line, yay!
    }

    @Override
    public ModelAndView resolveException(
            HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception e
    ) {
        logger.error("HandlerExceptionResolver: ", e);
        return null; // trigger other HandlerExceptionResolver's
    }
}
