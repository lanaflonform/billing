package com.vectree.billing.controller;

import com.vectree.billing.domain.User;
import com.vectree.billing.domain.UserTransaction;
import com.vectree.billing.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * class TransactionController Spring MVC.
 *
 * @version 0.1
 */
@Controller
@SuppressWarnings("unused")
@PropertySource("/WEB-INF/properties/messages.properties")
public class TransactionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/billing/authorized/user", method = RequestMethod.GET)
    public String loggedUserTransaction(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User sender = userService.findByUsername(auth.getName());

        model.addAttribute("userTransaction", new UserTransaction(sender, new User()));
        return "authorized-user";
    }

    @RequestMapping(value = "/billing/authorized/user", method = RequestMethod.POST)
    public String loggedUserTransactionSend(
            @ModelAttribute("userTransaction") UserTransaction userTransaction, Model model) {
        model.addAttribute("userTransaction", userTransaction);
        return "authorized-user-send";
    }
}

