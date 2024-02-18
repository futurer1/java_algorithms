public class Hashcode {

    // количество корзин, в которое должны укладываться все элементы HashMap
    static final int SIZE = 16;
    
    public static void main(String[] args) {
        String s1 = "qwe";
        String s2 = "asd";
        String s3 = "zxc";
        System.out.println(getBucketIndex(s1));
        System.out.println(getBucketIndex(s2));
        System.out.println(getBucketIndex(s3));
    }

    /**
      * Возвращает индекс ячейки для объекта по его хэшкоду
      */
    public static int getBucketIndex(Object o) {
        int bucket = Math.abs(o.hashCode()) % SIZE;
        return bucket;
    }
}
