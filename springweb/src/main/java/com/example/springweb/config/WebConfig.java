package com.example.springweb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration //Spring Java Config 설정파일이다라는걸 알려줌
@EnableWebMvc //웹을 사용할거라는것을 알려준다. 웹기능을켜줘!! <mvc:annotation-driven />

//com.example패키지 이하에서 @Controller가 붙은 클래스를 다 Bean으로 등록
@ComponentScan(basePackages = {"com.example"},
        includeFilters = { @ComponentScan.Filter(Controller.class)})
public class WebConfig implements WebMvcConfigurer {
    public WebConfig() {
        System.out.println("WebConfig가 실행됩니다.");
    }


    //Tomcat이 기본으로 제공하는 서블릿(정적자원)을 사용하도록 설정
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        System.out.println("configureDefaultServletHandling이 실행됩니다.");
        configurer.enable(); //디폴트 서블릿을 사용하도록 설정
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}



