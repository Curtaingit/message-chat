package com.curtain.messagechat.config;

import com.curtain.messagechat.service.UserService;
import graphql.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Curtain
 * @date 2018/11/19 10:22
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private UserService userService;

    @Autowired
    private RestAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private RestAuthenticationFailureHandler myAuthenticationFailHandler;

    @Autowired
    private RestAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private RestLogoutHandler mySimpleLogoutHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.headers().frameOptions().sameOrigin();

        http.cors().and().
                csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login","/graphql").permitAll()
                .antMatchers("/**").authenticated()
                // .antMatchers("/agency/**").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .and()
                .logout().logoutSuccessHandler(mySimpleLogoutHandler);

        /*避免用户多地登录*/
        http.sessionManagement().maximumSessions(1);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().mvcMatchers("/api/**");
        web.ignoring().mvcMatchers("/graphqlapi/**");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userService)
                .passwordEncoder(new MyPasswordEncoder());
    }





}