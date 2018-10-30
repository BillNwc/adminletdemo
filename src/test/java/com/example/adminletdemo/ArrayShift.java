package com.example.adminletdemo;

import java.util.Arrays;

/**
 * 这里需要写注释
 */

public class ArrayShift {
    public static void shift(int[] array,int k){
        //交换
        for (int i =0;i<array.length/2;i++){
            int t = array[i];
            array[i] = array[array.length-1-i];
            array[array.length-1-i] = t;
        }
        for (int i = 0; i < k / 2; i++) {
            int tmp = array[i];
            array[i] = array[k - 1 - i];
            array[k - 1 - i] = tmp;
        }
        for (int i = k; i < k + (array.length - k ) / 2; i ++) {
            int tmp = array[i];
            array[i] = array[array.length - 1 - i + k];
            array[array.length - 1 - i + k] = tmp;
        }
        Arrays.stream(array).forEach(System.out::print);
    }
    public static void main(String[] args) {
        shift(new int[]{1,2,3,4,5,6},4);
        System.out.println("\t"+(3|1));
        System.out.println("\t"+(3^1));
    }
}
