package com.zlrx.algorithms;

public class ReverseInteger {

    public static void main(String[] args) {
        int number = 387456;
        int reverse = 0;
        while (number > 0) {
            int reminder = number % 10;
            reverse = reverse * 10 + reminder;
            number /= 10;
        }
        System.out.println(reverse);
    }


}
