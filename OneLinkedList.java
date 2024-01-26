public class OneLinkedList implements Iterable<T> {
    private Node container;

    public void add(Integer val) {
        if (container == null) {
            container = new Node(val, null);
        } else {
            Node curNode = container;
            while (curNode.next != null) {

                curNode = curNode.next;
            }
            curNode.next = new Node(val, null);
        }
    }

    /**
     * Список перестраивается в обратном порядке
     */
    public void reverse() {

        MyList reverseList = new MyList();
        int count = getLastIndex();
        // идём от последнего элемента к первому и сохраняем в новый список
        for (int i = count; i >= 0; i--) {
            Integer last = getValueByIndex(i);
            reverseList.add(last);
        }

        Node curNode = container;

        int k = 0;
        // новый список копируется взамен старого
        while (curNode.next != null) {
            curNode.setValue(reverseList.getValueByIndex(k));
            curNode = curNode.next;
            k++;
        }
    }

    /**
     * второй способ перестроения списка в обратном порядке
     */
    public void reverse1() {
        Node curNode = container;
        int k = 0;
        while (curNode.next != null) {
            insertFirst(curNode.value);
            curNode = curNode.next;
            k++;
        }
        insertFirst(curNode.value);

        curNode = container;
        int l = 0;
        while (curNode.next != null) {
            if (l + 1 == k) {
                curNode.next = null;
                break;
            }

            curNode = curNode.next;
            l++;
        }
    }

    /**
     * Вставка элемента в начало списка 
     */
    public void insertFirst(int val){
        Node newnode = new Node(val, null);

        if (container.getNext() != null) {
            newnode.setNext(container.getNext());
        }
        container.setNext(newnode);

        Integer tmp = container.getValue();
        container.setValue(
            container.getNext().getValue()
        );
        container.getNext().setValue(tmp);
    }

    public int getLastIndex() {
        Node curNode = container;
        int k = 0;
        while (curNode.next != null) {
            curNode = curNode.next;
            k++;
        }

        return k;
    }

    public Integer getValueByIndex(int index) {
        Node curNode = container;
        int k = 0;
        while (curNode.next != null && k < index) {
            curNode = curNode.next;
            k++;
        }
        return curNode.getValue();
    }

    @Override
    public String toString() {
        StringBuilder resStr = new StringBuilder();

        Node curNode = container;
        while (curNode.next != null) {

            resStr.append(curNode.getValue());
            resStr.append(", ");
            curNode = curNode.next;
        }
        resStr.append(curNode.getValue());

        return resStr.toString();
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
