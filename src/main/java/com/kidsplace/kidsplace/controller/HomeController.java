package com.kidsplace.kidsplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public String home(Model model){
        model.addAttribute("data","HomeController 데이터 테스트");
        return "/page/home";
    }

}
