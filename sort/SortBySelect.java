package java_algorithms.sort;

import java.util.Arrays;

// Сортировка выбором
// Сложность алгоритма O(n)
// Проходим по массиву, каждый раз ищем минимальный элемент справа от отсортированной части массива
// Найденный минимальный элемент меняется местами с первым элементом среди неотсортированных
// Каждый цикл отсортированная часть становится больше на 1 элемент
public class SortBySelect {

    public static void main(String[] args) {

        int[] arr = new int[]{15, 12, 66, 34, 10, 1, 7, 3, 80};

        for (int i = 0; i < arr.length; i++) {
            int minIndex = findMinIndex(arr, i);
            int tmpValue = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = tmpValue;
        }

        System.out.println(Arrays.toString(arr));
        // [1, 3, 7, 10, 12, 15, 34, 66, 80]
    }

    public static int findMinIndex(int[] arr, int start) {

        int minValue = arr[start];
        int indexMinValue = start;
        for (int i = start; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
                indexMinValue = i;
            }
        }
        return indexMinValue;
    }
}
