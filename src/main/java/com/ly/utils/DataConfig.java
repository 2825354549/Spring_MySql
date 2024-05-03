package com.ly.utils;
//import lombok.Data;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

//@Data
@Configuration
@ConfigurationProperties(prefix="data")
public class DataConfig {

//    private Float MD;
//    private Float WOB;
    public Map<String, Paras> parameters;

    // getters and setters
    public Map<String, Paras> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Paras> parameters) {
        this.parameters = parameters;
    }

}
