package java_algorithms.find;

import java.util.Arrays;

/**
 * Поиск пары чисел из массива, которые в сумме дадут искомое число (в нашем случае 3).
 * Известно, что массив упорядочен по возрастанию.
 */
public class TwoNumbersBySummFromArray {
    public static void main(String[] args) {
       int[] arr = {-15, -11, -10, -5, 0, 3, 5, 7, 15, 19};

       // способ 1
       System.out.println(
               Arrays.toString(
                       findBinary(arr, 3)
               )
       );

       // лучший способ 2
       System.out.println(
               Arrays.toString(
                       find(arr, 3)
               )
       );
    }

    public static int[] findBinary(int[] arr, int findSumm) {

        for (int i = 0; i < arr.length; i++) {

            // число, которое в паре с текущим i даст искомую сумму
            int findNumber = findSumm - arr[i];

            int a = i + 1;
            int b = arr.length - 1;

            while (a <= b) {

                // индекс среднего элемента
                int mid = a + (b - a) / 2;

                if (arr[mid] == findNumber) {
                    return new int[]{arr[i], arr[mid]};
                }

                if (findNumber < arr[mid]) {
                    b--;
                } else {
                    a++;
                }
            }
        }
        // ничего не удалось найти
        return new int[0];
    }

    public static int[] find(int[] arr, int findSumm) {
        int a = 0; // индекс левой границы
        int b = arr.length - 1; // индекс правой границы
        while (a < b) {
            int sum = arr[a] + arr[b]; // считаем сумму

            if (sum == findSumm) {
                // нашли
                return new int[]{arr[a], arr[b]};
            }

            if (sum < findSumm) {
                // сумму нужно увеличить, значит двигаем левую границу вправо
                a++;
            } else {
                // сумму нужно уменьшить, значит двигаем правую границу влево
                b--;
            }
        }
        // ничего не удалось найти
        return new int[0];
    }
}
