package com.example.xray.controller;

import com.example.xray.service.MainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/main1")
    public String main1() {
        return this.mainService.main1();
    }

    @GetMapping("/main2")
    public String main2() {
        return "{}";
    }
}
