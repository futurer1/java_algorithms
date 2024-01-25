package java_algorithms;

/**
 * Вычислить максимальную длину подстроки из неповторяющихся символов
 */
public class SubstringMaxLength {

    public static void main(String[] args) {
        String str = new String("aahhd8wjksgssjjdkkewlleekvjjff88");
        String str1 = new String("aahhnnffkkdddkeell");
        String str2 = new String("1234567890");

        System.out.printf("str max substring length: %d%n", getMaxLengthUniqueSubstring(str).length());
        System.out.println(getMaxLengthUniqueSubstring(str));
        // str max substring length: 8
        // hd8wjksg

        System.out.printf("str1 max substring length: %d%n", getMaxLengthUniqueSubstring(str1).length());
        System.out.println(getMaxLengthUniqueSubstring(str1));
        // str1 max substring length: 3
        // dke

        System.out.printf("str2 max substring length: %d%n", getMaxLengthUniqueSubstring(str2).length());
        System.out.println(getMaxLengthUniqueSubstring(str2));
        // str2 max substring length: 1
        // 1
    }

    private static String getMaxLengthUniqueSubstring(String str) {
        LinkedHashSet<Character> curCharSequence = new LinkedHashSet<>();
        LinkedHashSet<Character> maxCharSequence = new LinkedHashSet<>();

        Character lastChar;

        if (!str.isEmpty()) {
            maxCharSequence.add(str.charAt(0));
        }

        for (int i = 0; i < str.length(); i++) {
            lastChar = str.charAt(i);

            if (curCharSequence.contains(str.charAt(i))) {

                if (maxCharSequence.size() < curCharSequence.size()) {
                    maxCharSequence.clear();
                    maxCharSequence.addAll(curCharSequence);
                }
                curCharSequence.clear();
                curCharSequence.add(lastChar);
            }
            curCharSequence.add(str.charAt(i));
        }
        
        return maxCharSequence.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }
}
