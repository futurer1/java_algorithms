/**
 * Вычислить максимальную длину подстроки из неповторяющихся символов
 */
public class SubstringMaxLength {
    String str = new String("aahdgssjjdkkewlleekvjjff888wjks");

    LinkedHashSet<Character> charSequence = new LinkedHashSet<>();
    
    for (int i = 0; i < str.length(); i++) {
        if (charSequence.contains(str.charAt(i))) {
            charSequence.clear();
        }
        charSequence.add(str.charAt(i));
    }

    System.out.printf("str max substring length: %d%n", charSequence.size());
    charSequence.forEach(System.out::print);
}
