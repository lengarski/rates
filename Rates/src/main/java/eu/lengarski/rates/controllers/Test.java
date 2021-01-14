package eu.lengarski.rates.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
