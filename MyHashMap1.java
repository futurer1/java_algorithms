package java_algorithms;

import java.util.*;

public class MyHashMap1 {

    public static void main(String[] args) {

        Map<String, Integer> myMap = new SimpleHashMap<>();

        myMap.put("Test1", 1);
        myMap.put("Test3", 3);
        myMap.put("Test2", 2);
        myMap.put("Test2", 5);

        System.out.println(myMap);
        
    }

    public static class Node<K,V> implements Iterator {

        private K key;
        private V value;
        private Node<K,V> next;
        private int hashCode;

        public Node(K key, V value, int hashCode, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.hashCode = hashCode;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Object next() {
            return next;
        }

        public Node<K, V> getNext() {
            return next;
        }
    }

    public static class SimpleHashMap<K, V> extends AbstractMap<K, V> {

        private static final int SIZE = 16;

        private Node<K,V>[] buckets = new Node[SIZE];

        @Override
        public Set<Entry<K, V>> entrySet() {
            return null;
        }

        @Override
        public V put(K key, V value) {
            int hashCode = hash(key);
            int index = index(hashCode);
            if (buckets[index] == null) {
                buckets[index] = new Node<K, V>(key, value, hashCode, null);
            } else {

                Node<K,V> curBacket = buckets[index];
                while (curBacket.hasNext()) {
                    K curKey = curBacket.getKey();
                    if ( (key == null && curKey == null) || (key != null && key.equals(curKey))) {
                        V oldValue = curBacket.getValue();
                        curBacket.setValue(value);
                        return oldValue;
                    }
                    curBacket = curBacket.next;
                }

                if (curBacket.next == null) {
                    if (key.equals(curBacket.getKey())) {
                        V oldValue = curBacket.getValue();
                        curBacket.setValue(value);
                        return oldValue;
                    } else {
                        curBacket.next = new Node<K, V>(key, value, hashCode, null);
                    }
                }
            }
            return null;
        }

        @Override
        public V get(Object key) {
            return super.get(key);
        }

        public int hash(Object key) {
            return key == null ? 0 : key.hashCode();
        }

        public int index(int hashCode) {
            return Math.abs(hashCode) % SIZE;
        }

        @Override
        public String toString() {

            StringBuilder sb = new StringBuilder();
            sb.append("{");
            boolean first = true;
            for (Node<K, V> bucket: buckets) {

                if (bucket != null) {
                    if (!first) {
                        sb.append(",");
                    }
                    first = false;
                    sb.append(" [");
                    sb.append(bucket);

                    Node<K, V> curNode = bucket;
                    while (curNode.hasNext()) {
                        sb.append(", ");
                        sb.append(curNode.getNext().toString());
                        curNode = bucket.getNext();
                    }
                    sb.append("]");
                } else {
                    sb.append(", null");
                }
            }
            sb.append("}");
            return sb.toString();
        }
    }
}
