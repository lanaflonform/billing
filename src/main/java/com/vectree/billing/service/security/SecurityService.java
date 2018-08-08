package com.vectree.billing.service.security;

/**
 * Service interface for Spring Security.
 *
 * @version 0.1
 */
public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
