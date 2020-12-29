package com.megshan.splitnot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // To disable security
//        http.authorizeRequests(authorizeRequests ->
//                        authorizeRequests.antMatchers("/**").permitAll()
//                                .anyRequest().authenticated())
//                .csrf().disable();

        // To enable JWT authorization
        http
                .cors() // This needs to be added to exclude the OPTIONS call from needing authentication
                        // More info here - https://www.baeldung.com/spring-security-cors-preflight
                .and()
                .authorizeRequests()    // Allow health check url public access
                    .antMatchers("/actuator/**", "/webhook")
                        .permitAll()
                .and()
                .authorizeRequests()
                    .anyRequest()
                        .authenticated()
                .and()
                .oauth2ResourceServer()
                    .jwt();
    }

    // This is to allow cross origin requests from all origins. Do not allow this on production.
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000", "https://www.splitnot.com").allowedMethods(HttpMethod.GET.name(), HttpMethod.HEAD.name(),
                        HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.OPTIONS.name(), HttpMethod.DELETE.name(),
                        HttpMethod.PATCH.name());
            }
        };
    }
}
