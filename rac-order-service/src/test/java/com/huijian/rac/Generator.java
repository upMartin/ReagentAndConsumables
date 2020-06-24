package com.huijian.rac;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;
/*implements Supplier<String>*/
public class Generator {
    Random rand = new Random(47);
    char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public String get() {
        return "" + letters[rand.nextInt(letters.length)];
    }

    public static void main(String[] args) {
        Supplier<String> sup = new Generator()::get;
        String word = Stream.generate(sup)
                .limit(30)
                .collect(Collectors.joining(" "));
        System.out.println(word);
    }
}