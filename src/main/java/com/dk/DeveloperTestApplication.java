package com.dk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan
public class DeveloperTestApplication {

//    @Bean
//    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("home.html");
//            }
//        };
//    }

    public static void main(String[] args) {
        SpringApplication.run(DeveloperTestApplication.class, args);
    }
}
