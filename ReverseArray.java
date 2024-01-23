package java_algorithms;

import java.util.Arrays;

public class ReverseArray {

    public static void main(String[] args) {
        Integer[] arr = new Integer[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        String[] arr1 = new String[10];
        for (int i = 0; i < arr.length; i++) {
            arr1[i] = i + "str";
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        reverse(arr);
        reverse(arr1);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
    }

    public static <T> void reverse(T[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {

            T tmp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = tmp;
        }
    }
}
