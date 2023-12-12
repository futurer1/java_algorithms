/*
Подсчет кол-ва вхождений букв в строку
*/
public class CountLettersInString {
    public static void main(String[] args) {

        String str = "ashfduqwyfskdhkjdvbsduhfskjdfhksdjfh";
        HashMap<Character, Integer> stat = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            int nextVal = 1;
            if (stat.containsKey(ch)) {
                nextVal = stat.get(ch) + 1;
            }
            stat.put(ch, nextVal);
        }

        System.out.println(stat);
        // вывод: {a=1, b=1, d=6, f=5, h=5, j=3, k=4, q=1, s=5, u=2, v=1, w=1, y=1}
  }
}
