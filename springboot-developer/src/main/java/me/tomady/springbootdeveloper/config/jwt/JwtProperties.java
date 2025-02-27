package me.tomady.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@Configuration
@ConfigurationProperties("jwt")
public class JwtProperties {
//    private String issuer;
//    private String secretKey;
    private String issuer = "ajufresh@gmail.com";
    private String secretKey = "study-springboot";
}