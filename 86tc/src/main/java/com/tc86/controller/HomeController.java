package com.tc86.controller;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
@Controller
public class HomeController {

    @RequestMapping("/index")

    public ModelAndView index(){

              ModelAndView mv =new ModelAndView("index");

              mv.addObject("spring", "spring mvc");

             

              return mv;

    }
}


