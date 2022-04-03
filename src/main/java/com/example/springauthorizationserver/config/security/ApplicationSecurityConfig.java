package com.example.springauthorizationserver.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //Support GET logout
        http.csrf().disable();

        //Form Login And Http Security Config
        configureFormLogin(http);

        //Logout Configuration
        configureLogout(http);

        return http.build();
    }

    private HttpSecurity configureFormLogin(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/html/**", "/css/**").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(loginCustomizer ->
                        loginCustomizer.loginPage("/api/authentication/login").permitAll()
                                .loginProcessingUrl("/auth/processing")
                                .failureUrl("/api/authentication/failure")
                );
    }

    private void configureLogout(HttpSecurity http) throws Exception {
        http
                .logout(logoutConfigurer ->
                        logoutConfigurer.logoutUrl("/api/authentication/logout")
                                .deleteCookies("JSESSIONID")
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/api/authentication/login")
                                .clearAuthentication(true)
                );
    }

    @Bean
    UserDetailsService users() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
