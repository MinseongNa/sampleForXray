package com.example.xray.service;

import java.time.LocalDateTime;

import com.example.xray.util.SleepUtil;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    private final SubService subService;

    public MainService(SubService subService) {
        this.subService = subService;
    }

    public String main1() {
        this.subService.sub1();
        this.main2();
        this.subService.sub2();
        this.main3();
        return String.format("{\"timeStamp:\":\"%s\"}", LocalDateTime.now());
    }

    private void main2() {
        SleepUtil.sleep(100);
    }

    private void main3() {
        SleepUtil.sleep(100);
    }
}
