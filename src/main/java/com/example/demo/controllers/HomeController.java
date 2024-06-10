package com.example.demo.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class HomeController {
    
    @GetMapping("/")
    public String home(Model model, @RequestParam(value = "message", required = false) String message) {
        System.out.println("message: " + message);
        if (message != null){
            model.addAttribute("message", message);
        }
        return "index";
    }
}
