package com.my.edu.oauth2.client.oauth2client.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableJpaRepositories("com.my.edu.oauth2.client.oauth2client.web.data")
@EntityScan("com.my.edu.oauth2.client.oauth2client.web.data.model")
@ComponentScan({ "com.my.edu.oauth2.client.oauth2client.web.controller" })
public class ResourceServerWebConfig implements WebMvcConfigurer {
    //
}
