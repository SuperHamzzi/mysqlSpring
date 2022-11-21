package com.example.springweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HelloController {
    @GetMapping("/hello") //hello요청이오면 해당메소드를 실행하라.
    public String hello(){
      return "hello";
    }
}
