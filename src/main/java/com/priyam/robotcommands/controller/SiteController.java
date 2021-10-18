package com.priyam.robotcommands.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SiteController {

    public String generateIndexPage(){
        return "index";
    }
}
