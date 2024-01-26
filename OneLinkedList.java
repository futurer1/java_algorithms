// Односвязный список, который может работать с различной типизации

package java_algorithms;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneLinkedList<T> implements Iterable<T> {
    // первый узел, в него вставляются все другие
    private Node<T> firstNode;

    // текущее кол-во элементов в списке
    private int countElements;

    public void add(T val) {
        if (firstNode == null) {
            firstNode = new Node<T>(val, null);
        } else {
            Node<T> curNode = firstNode;
            while (curNode.next != null) {

                curNode = curNode.next;
            }
            curNode.next = new Node<T>(val, null);
        }
    }

    public T get(int index) {
        if (index > countElements - 1) {
            throw new IndexOutOfBoundsException("No such index " + index);
        }

        int k = 0;
        Node<T> curNode = firstNode;
        while (curNode.getNext() != null) {
            if (k == index) {
                break;
            }
            curNode = curNode.getNext();
            k++;
        }
        return curNode.getValue();
    }

    public int getCount() {
        return countElements;
    }

    /**
     * Список перестраивается в обратном порядке
     */
    public void reverse() {

        // в tmpNode собираем новый список с обратным порядком
        Node tmpNode = null; 
        Node curNode = firstNode;
        while (curNode.getNext() != null) {
            // добавление элемента в начало
            tmpNode = new Node(curNode.getValue(), tmpNode);
            // переход к следующему элементу
            curNode = curNode.getNext();
        }
        // добавление последнего элемента в начало
        tmpNode = new Node(curNode.getValue(), tmpNode);

        firstNode = tmpNode;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        Node curNode = firstNode;
        while (curNode.getNext() != null) {
            str.append(curNode.getValue()).append(", ");
            curNode = curNode.getNext();
        }
        str.append(curNode.getValue());

        return "[" + str.toString() + "]";
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Node<T> nextNode;
            private int curIndex = -1;
            @Override
            public boolean hasNext() {
                return curIndex + 1 < countElems;
            }

            @Override
            public T next() {
                curIndex++;

                if (curIndex >= 0 && curIndex < countElems) {
                    if (nextNode == null) {
                        nextNode = firstNode;
                    } else {
                        nextNode = nextNode.getNext();
                    }
                    return nextNode.getValue();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    public static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
}
