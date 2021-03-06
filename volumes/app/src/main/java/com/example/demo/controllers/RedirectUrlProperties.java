package com.example.demo.controllers;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@ConfigurationProperties(prefix="spring.redirect")	//getting spring.redirectinapplication.properties
@Data
public class RedirectUrlProperties {
    private String url;
}