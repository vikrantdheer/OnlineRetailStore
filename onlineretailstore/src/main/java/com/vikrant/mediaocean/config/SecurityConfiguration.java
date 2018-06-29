/*
package com.vikrant.mediaocean.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .authorizeRequests().antMatchers("/v1/**", "/v2/**", "/swagger-ui/**", "/api-docs/**", "/h2-console/**")
                .permitAll()
                .antMatchers("/products/**", "/bills/**").authenticated().and().httpBasic().and()
                .csrf().disable();
    }

    @Autowired
    public void configureInMemoryUsers(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("vikrant").password("vikrant").roles("ADMIN", "USER");
    }

}
*/
