package org.example.itkissues.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("/home")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/private")
    public String secret() {
        return "welcome!";
    }
}
