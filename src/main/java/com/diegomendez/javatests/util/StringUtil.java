package com.diegomendez.javatests.util;

public class StringUtil {

    public static String repeat(String str, int times){
        String result = "";

        if (times < 0) throw new IllegalArgumentException("Negative times is not valid");

        for (int i = 0; i < times; i++) {
            result += str;
        }

        return result;
    }
}
