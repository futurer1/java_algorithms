public class Factorial {
    public static void main(String[] args) {
        int number = 5; // Заданное число, для которого мы хотим найти факториал
        int factorial = 1; // Изначально присваиваем факториалу значение 1

        // Используем цикл for для нахождения факториала
        for (int i = 1; i <= number; i++) {
            factorial *= i; // Умножаем текущее значение факториала на текущее значение i
        }

        // Выводим результат
        System.out.println("Факториал числа " + number + " равен " + factorial);
    }
}
