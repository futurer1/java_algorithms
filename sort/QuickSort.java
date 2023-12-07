package java_algorithms.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[] {64, 72, 34, 87, 12, 11, 16, 88, 40, 70};

        System.out.println("     " + Arrays.toString(arr));

        quickSortAlg(arr, 0,  arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    public static void quickSortAlg(int[] arr, int from, int to) {

        if (from < to) {

            // делим примерно на пополам
            int devideIndex = devideArray(arr, from, to);

            // сортируем каждую часть
            // внутри она тоже будет поделена попоплам
            quickSortAlg(arr, from, devideIndex - 1);

            System.out.println("");

            quickSortAlg(arr, devideIndex, to);
        }
    }

    public static int devideArray(int[] arr, int from, int to) {
        int rightIndex = to;
        int leftIndex = from;

        int centerIndex = from + (to - from) / 2;

        // примерно середина, берем значение этого элемента
        int centerElem = arr[centerIndex];

        while (leftIndex <= rightIndex) {

            while (arr[leftIndex] < centerElem) {
                leftIndex++;
            }

            while (arr[rightIndex] > centerElem) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                changePositions(arr, rightIndex, leftIndex);

                leftIndex++;
                rightIndex--;
            }

        }
        return leftIndex;

    }

    /**
     * меняет местами элементы
     */
    public static void changePositions(int[] arr, int from, int to) {
        if (from == to) {
            return;
        }

        int tmp = arr[to];
        arr[to] = arr[from];
        arr[from] = tmp;

        System.out.println("шаг: " + Arrays.toString(arr));
    }
}
