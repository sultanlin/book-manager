// package com.sultanlinjawi.library.cfg;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
// @Configuration
// public class MyConfig {
//
//     @Bean
//     WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(CorsRegistry registry) {
//                 registry.addMapping("/api/**")
//                         // .allowedOrigins("*")
//                         .allowedOriginPatterns("*")
//                         .allowedMethods("*")
//                         .exposedHeaders("")
//                 // .allowedHeaders("*")
//                 ;
//                 // .allowedOrigins("http://192.168.1.44:5173")
//                 // .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
//                 // .allowedHeaders(
//                 //         "Origin", "X-Requested-With", "Content-Type", "Accept",
// "Authorization")
//                 // .allowCredentials(true)
//                 // .maxAge(3600);
//                 // .exposedHeaders("header1", "header2")
//             }
//         };
//     }
// }
