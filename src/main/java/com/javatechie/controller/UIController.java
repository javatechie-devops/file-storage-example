package com.javatechie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("downloadFileName", "");
        model.addAttribute("viewFileName", "");
        return "index";
    }
}
