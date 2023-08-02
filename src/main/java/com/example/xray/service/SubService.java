package com.example.xray.service;

import com.example.xray.util.SleepUtil;
import org.springframework.stereotype.Service;

@Service
public class SubService {

    public void sub1() {
        SleepUtil.sleep(100);
    }

    public void sub2() {
        SleepUtil.sleep(100);
        this.sub2_1();
        this.sub2_2();
        this.sub2_3();
    }

    private void sub2_1() {
        SleepUtil.sleep(100);
    }

    private void sub2_2() {
        SleepUtil.sleep(100);
    }

    private void sub2_3() {
        SleepUtil.sleep(100);
    }
}
