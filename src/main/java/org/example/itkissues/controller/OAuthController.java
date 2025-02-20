package org.example.itkissues.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    @GetMapping("/home")
    public String home() {
        return "you are home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "you can administrate";
    }
}