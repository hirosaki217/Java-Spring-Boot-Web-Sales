package com.nhom11.webseller.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // @Bean
    // @Override
    // protected UserDetailsService userDetailsService() {
    // InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    // manager.createUser(User.withDefaultPasswordEncoder()
    // .username("admin")
    // .password("admin")
    // .roles("ADMIN")
    // .build());
    // return manager;
    // }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder user = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("hieu").password("{noop}12345").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/", "/home","/admin").permitAll();
                // .anyRequest().authenticated()
                // .and()
                // .formLogin()
                // .defaultSuccessUrl("/hello")
                // .permitAll()
                // .and()
                // .logout()
                // .permitAll();
        http.headers().frameOptions().sameOrigin();
    }
}
