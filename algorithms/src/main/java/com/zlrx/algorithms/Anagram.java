package com.zlrx.algorithms;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Anagram {

    public static void main(String[] args) {
        System.out.println(solve("listen", "silent"));
        System.out.println(solve("listen", "silint"));
    }


    private static boolean solve(@NotNull String str1, @NotNull String str2) {
        //null check
        if (str1.length() != str2.length()) {
            return false;
        }

        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        //O(NlogN)
        Arrays.sort(c1);
        Arrays.sort(c2);

        //O(N)
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i]) {
                return false;
            }
        }

        //O(NlogN)+O(N)=O(NlogN)

        return true;
    }


}
