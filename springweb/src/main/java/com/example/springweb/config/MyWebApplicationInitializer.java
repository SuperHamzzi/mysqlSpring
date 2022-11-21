package com.example.springweb.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//WebApplicationInitializer 이걸 구현해줌
//AbstractAnnotationConfigDispatcherServletInitializer 3.2
public class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
                encodingFilter.setEncoding("UTF-8");

        return new Filter[] {encodingFilter};
    }

    //스프링 컨테이너 --- 웹과 관련된 설정, 비지니스로직과 관련된 설정(Spring MVC -getServletConfigClasses), 비지니스 로직과 괸련된 설정(트랙잭션,DB프로그래밍 -getRootConfigClasses)
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; //모든 요청을 DispatcherServlet으로 보낸다.
    }
    //    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        System.out.println("onStartUp 실행");
//
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(WebConfig.class);
//
//        DispatcherServlet servlet = new DispatcherServlet(context);
//        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcherServlet", servlet);
//        registration.setLoadOnStartup(1);
//        registration.addMapping("/");
//    }
}
