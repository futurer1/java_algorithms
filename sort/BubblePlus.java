package java_algorithms.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Сортировка пузырьком сразу в 2х направлениях с начала массива и с конца массива
 * Не перебираются элементы, которые уже были отсортированы (параметр done)
 */
public class BubblePlus {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int elements = scanner.nextInt();

        //int[] array = new int [] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] array = new int[elements];

        Random rand = new Random();
        for (int i = 0; i < elements; i++) {
            array[i] = rand.nextInt(elements);
        }

        System.out.println("Исходный массив: " + Arrays.toString(array));


        int done = 0;
        boolean isSorted = false;
        while (!isSorted) {
            done++;

            System.out.println("Шаг " + done);
            isSorted = true;

            for (int i = done; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    int tmp = array[i - 1];
                    array[i - 1] = array[i];
                    array[i] = tmp;
                    isSorted = false;
                    //System.out.println(array[i] + " <- " + array[i - 1]);
                }

                if (array[array.length - i] < array[array.length - i - 1]) {
                    int tmp = array[array.length - i - 1];
                    array[array.length - i - 1] = array[array.length - i];
                    array[array.length - i] = tmp;
                    isSorted = false;
                    //System.out.println(array[array.length - i] + " <- " + array[array.length - i - 1]);
                }

            }
            System.out.println("Результат шага " + done + ": " + Arrays.toString(array));
        }

        System.out.println("-----------------------");
        System.out.println("За " + done + " шагов");
        System.out.println("Отсортированный массив: " + Arrays.toString(array));

        Double percent = (Math.ceil ( (double)done / (double)elements * 10000)) / 100;
        System.out.println("шагов " + percent + "% от кол-ва элементов");

    }
}
