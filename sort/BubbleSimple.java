package java_algorithms.sort;

import java.util.Arrays;

public class BubbleSimple {
    public static void main(String[] args) {
        int[] arr = new int[] {64, 72, 34, 87, 12, 11, 16, 88, 40, 70};

        boolean isSorted = false;
        while (!isSorted) {

            isSorted = true;

            for (int i = 1; i < arr.length; i++) {
                if (arr[i] < arr[i-1]) {
                    int tmp = arr[i];
                    arr[i] = arr[i-1];
                    arr[i-1] = tmp;

                    isSorted = false;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
