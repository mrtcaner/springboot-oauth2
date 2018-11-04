package com.my.edu.oauth2.client.oauth2client;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.ArrayList;
import java.util.List;


@EnableOAuth2Sso
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.oauth2Login().and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().
                antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> registrations = new ArrayList<>();
        registrations.add(getRegistration());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration() {
        return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook")
                .scope("public_profile", "email")
                .authorizationUri("http://localhost:8081/auth/oauth/authorize")
                .tokenUri("http://localhost:8081/auth/oauth/token")
                .userInfoUri("http://localhost:8081/auth/user")
                .userNameAttributeName("id")
                .clientName("Facebook")
                .clientId("SampleClientId").clientSecret("secret").build();
    }

}