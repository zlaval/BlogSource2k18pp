package com.zlrx.algorithms;

public class Palindrome {

    public static void main(String[] args) {
        slowPalindrome();
        fastPalindrome();
    }

    public static void slowPalindrome() {
        String maybePalindrom = "madam";
        String reverse = "";
        for (int i = maybePalindrom.length(); i > 0; ) {
            reverse += maybePalindrom.charAt(--i);
        }
        String palindromeResult = maybePalindrom.equals(reverse) ? "a palindrome" : "not a palindrome";
        System.out.println(maybePalindrom + " is " + palindromeResult);

    }

    public static void fastPalindrome() {
        String maybePalindrom = "wasitacaroracatisaw";
        int i = maybePalindrom.length() - 1;
        int j = 0;
        int k = i / 2;
        while (i >= k) {
            if (maybePalindrom.charAt(i--) != maybePalindrom.charAt(j++)) {
                System.out.println(maybePalindrom + " is not a palindrome");
                return;
            }
        }
        System.out.println(maybePalindrom + " is a palindrome");

    }


}
