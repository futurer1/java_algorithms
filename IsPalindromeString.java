/*
Определить является ли строка палиндромом
*/
public class IsPalindromeString {
  
    public static void main(String[] args) {
      
        String str = "1aoiuytyuioa1";

        boolean isPolindrome = true;
        for (var i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                isPolindrome = false;
                break;
            }
        }

        System.out.println(isPolindrome);
    }
}
