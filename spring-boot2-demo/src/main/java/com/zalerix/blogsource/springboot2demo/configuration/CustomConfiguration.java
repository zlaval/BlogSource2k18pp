package com.zalerix.blogsource.springboot2demo.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:custom-config.properties")
@ConfigurationProperties
@Getter
@Setter
public class CustomConfiguration {

    private String property;

}
