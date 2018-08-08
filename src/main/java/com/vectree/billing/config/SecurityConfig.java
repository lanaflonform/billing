package com.vectree.billing.config;

import com.vectree.billing.filter.FormEncodingSetterFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;

/**
 * Spring Seurity confiruration.
 *
 * @version 0.1
 */
@Configuration
@EnableWebSecurity
@SuppressWarnings("unused SpringJavaAutowiringInspection")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FormEncodingSetterFilter formEncodingSetterFilter;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/**/*.css", "/**/*.png", "/**/*.js", "/fonts/*",
                "/**/*.ico", "/**/*.eot", "/**/*.svg", "/**/*.ttf", "/**/*.woff*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable();
        http.addFilterBefore(formEncodingSetterFilter, CsrfFilter.class);

        http.formLogin()
                .loginPage("/billing/login").permitAll()
                .failureUrl("/billing/login?error")
                .defaultSuccessUrl("/billing/authorized/user", true);

        http.logout()
                .logoutUrl("/billing/logout")
                .logoutSuccessUrl("/billing")
                .permitAll()
                .and()
                .rememberMe();

        http.authorizeRequests()
                .antMatchers("/billing/").permitAll()
                .antMatchers("/billing/login/**").permitAll()
                .antMatchers("/billing/login/cancel/").permitAll()
                .antMatchers("/billing/registration/**").permitAll()

                .antMatchers("/billing/authorized/user/**").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/billing/authorized/admin/**").access("hasRole('ADMIN')")

                .antMatchers("/billing/logout/").access("isAuthenticated()")
                .anyRequest().authenticated()

                .and().exceptionHandling().accessDeniedPage("/403");
    }
}
