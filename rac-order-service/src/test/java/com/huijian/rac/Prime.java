package com.huijian.rac;

import java.util.List;
import java.util.stream.*;
import static java.util.stream.LongStream.*;
public class Prime {
    public static Boolean isPrime(long n) {
        return rangeClosed(2, (long)Math.sqrt(n))
                .noneMatch(i -> n % i == 0);
    }
    public LongStream numbers() {
        LongStream l = iterate(2, i -> i + 1);
        return iterate(2, i -> i + 1)
                .filter(Prime::isPrime);
    }
    public static void main(String[] args) {
        System.out.println((long)Math.sqrt(3));
        new Prime().numbers()
                .limit(10)
                .forEach(n -> System.out.format("%d ", n));
        System.out.println();
        new Prime().numbers()
                .skip(90)
                .limit(10)
                .forEach(n -> System.out.format("%d ", n));
    }
}
