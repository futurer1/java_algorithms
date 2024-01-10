// Вычисляет значение числа Фибоначчи по индексу 13
// 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, ...

package java_algorithms;

public class CalculateFibonacci {

    public static void main(String[] args) {
        int n = 13;

        long result = fibonacci(n);
        System.out.println("Fibonacci number at index " + n + ": " + result);
    }

    /**
     * Calculate the Fibonacci number at index n
     * @param n index
     * @return
     */
    public static long fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        long prev = 0;
        long current = 1;

        for (int i = 2; i <= n; i++) {
            long next = (prev + current);
            prev = current;
            current = next;
        }
        return current;
    }
}
