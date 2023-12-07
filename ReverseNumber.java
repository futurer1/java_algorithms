// Обратить число в обратном порядке следования цифр

package java_algorithms;

public class ReverseNumber {
    public static void main(String[] args) {

        int val = 592123423;

        int y = 0;
        for (int x = val; x > 0; x = Math.floorDiv(x, 10)) {
            y *= 10;
            y += x % 10;
        }

        System.out.println(y); // 324321295
    }
}
