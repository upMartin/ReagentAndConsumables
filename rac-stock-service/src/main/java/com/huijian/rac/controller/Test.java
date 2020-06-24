package com.huijian.rac.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Test {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("a", a);
        int[] b = (int[]) map.get("a");
    }
}
