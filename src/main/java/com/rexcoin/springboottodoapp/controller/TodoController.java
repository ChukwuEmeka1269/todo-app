package com.rexcoin.springboottodoapp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class TodoController {

    @GetMapping("/")
    public String index(){
        log.debug("request to GET index");
//        var modelAndView = new ModelAndView("index");
        return "index.html";
    }
}
