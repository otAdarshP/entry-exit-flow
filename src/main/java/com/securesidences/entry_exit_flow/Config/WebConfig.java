// src/main/java/com/securesidences/entry_exit_flow/Config/WebConfig.java
package com.securesidences.entry_exit_flow.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")            // during dev, allow all; lock this down in prod
                .allowedMethods("GET","POST",
                        "PUT","DELETE","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
