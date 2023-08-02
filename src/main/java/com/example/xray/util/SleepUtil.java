package com.example.xray.util;

public class SleepUtil {
    public static void sleep(int i) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
