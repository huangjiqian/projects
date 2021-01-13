package com.myprojects.jvm.tuning;

import java.util.ArrayList;
import java.util.List;

public class Hello {
    private static List<byte[]> list=new ArrayList<>();

    public static void main(String[] args) {
        for(;;) {
            list.add(new byte[1024 * 1024]);
        }
    }
}
