package com.stock.config;

import com.stock.dialect.StockDialect;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

@Configuration
public class VelocityConfig {

    @Value("${velocity_folder}")
    private String velocity_folder;

    @Bean
    public VelocityEngine velocityEngine() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.setProperty("classpath.resource.loader.path", velocity_folder);
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.init();
        return velocityEngine;
    }

    @Bean
    public StockDialect stockDialect(VelocityEngine velocityEngine) {
        return new StockDialect(velocityEngine);
    }
}
