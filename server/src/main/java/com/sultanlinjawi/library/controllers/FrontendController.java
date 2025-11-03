package com.sultanlinjawi.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {
    @GetMapping({"/{path:[^\\.]*}", "/*/{path:[^\\.]*}"})
    public String react() {
        return "forward:/";
    }
}
