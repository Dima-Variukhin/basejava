package com.basejava.webapp;

import java.util.Arrays;

public class HW11 {
    public static void main(String[] args) {
        int[] a = {3, 4, 5, 1, 6, 6, 9, 7, 8};
        System.out.println(minValue(a));

    }

    private static int minValue(int[] values) {
        int result = 0;
        int[] sorted = Arrays.stream(values).distinct().sorted().toArray();
        for (int n = sorted.length - 1, i = 1; n >= 0; n--, i *= 10) {
            result += Math.abs(sorted[n]) * i;
        }
        return result;
    }
}

//    private static int minValue(int[] values) {
//        int result = 0;
//        Set<Integer> integers = new HashSet<>();
//        for (Integer integer : values) {
//            integers.add(integer);
//        }
//        Integer[] arr = new Integer[integers.size()];
//        integers.toArray(arr);
//
//        for (int n = arr.length - 1, i = 1; n >= 0; n--, i *= 10) {
//            result += Math.abs(arr[n]) * i;
//        }
//        return result;
//    }
//}

