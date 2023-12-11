import java.util.Arrays;

public class SortByInsertion {
    public static void main(String[] args) {

        int[] arr = new int[]{90, 15, 18, 100, 2, 7, 8, 10, 10};

        for (int i = 1; i < arr.length; i++) {
            int cur = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > cur) {
                arr[j] = arr[j - 1];
                j--;
            }

            arr[j] = cur;
        }

        System.out.println(Arrays.toString(arr));
    }
}
