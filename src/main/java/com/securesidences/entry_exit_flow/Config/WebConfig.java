//package com.securesidences.entry_exit_flow.Config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry
//                // apply to all endpoints
//                .addMapping("/**")
//                // allow the emulator’s host (10.0.2.2) or any origin if you prefer
//                // — use allowedOrigins() if you know the exact origin(s):
//                .allowedOrigins("http://10.0.2.2:8080")
//                // — or use patterns to permit everything but still send credentials:
////                .allowedOriginPatterns("*")
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                .allowedHeaders("*")
//                .allowCredentials(true);
//    }
//
//}
