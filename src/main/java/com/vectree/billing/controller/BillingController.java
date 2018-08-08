package com.vectree.billing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * class BillingController Spring MVC.
 *
 * @version 0.1
 */
@Controller
//@RequestMapping("/")
public class BillingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingController.class);

    @RequestMapping(value = "/billing", method = RequestMethod.POST)
    public String billingPost(Model model) {
        LOGGER.info("method post controller /billing");
        return "billing";
    }

    @RequestMapping(value = "/billing", method = RequestMethod.GET)
    public String billingGet(Model model) {
        LOGGER.info("method get controller /billing");
        return "billing";
    }
}
