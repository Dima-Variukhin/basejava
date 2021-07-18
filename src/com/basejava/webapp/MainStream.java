package com.basejava.webapp;

import java.util.*;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        int[] a = {3, 4, 4, 5, 1, 6, 6, 9, 7, 8};
        System.out.println(minValue(a));
        List<Integer> integers = Arrays.stream(a).boxed().collect(Collectors.toList());
        System.out.println(oddOrEven(integers));
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0,(x, y)-> (x*10)+y);
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        final Map<Boolean, List<Integer>> oddsAndEvens = integers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        return oddsAndEvens.get(oddsAndEvens.get(false).size() % 2 != 0);
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

