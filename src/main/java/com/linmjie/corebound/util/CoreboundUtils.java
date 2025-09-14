package com.linmjie.corebound.util;

public class CoreboundUtils {
    public static int randomInt(int i){
        return Math.toIntExact((long) Math.ceil(Math.random() * i));
    }
    public static int randomInt(int min, int max){
        return randomInt(max-min) + min;
    }
}
