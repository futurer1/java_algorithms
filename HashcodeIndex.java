package java_algorithms;

public class Hashcode {

    // количество корзин, в которое должны укладываться все элементы HashMap
    static final int SIZE = 16;
    
    public static void main(String[] args) {
        String s1 = "qwe";
        String s2 = "asd";
        String s3 = "zxc";
        System.out.println(getBucketIndex(s1)); // 15
        System.out.println(getBucketIndex(s2)); // 2
        System.out.println(getBucketIndex(s3)); // 5
    }

    /**
      * Возвращает индекс ячейки для объекта по его хэшкоду
      */
    public static int getBucketIndex(Object o) {
        int bucket = Math.abs(o.hashCode()) % SIZE;
        return bucket;
    }
    
}
