package com.vectree.billing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * class BillingControllerAdvice for loggong errors.
 *
 * @version 0.1
 */
@ControllerAdvice
public class BillingControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(BillingControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("BillingControllerAdvice: ", e);
        throw e;
    }
}
