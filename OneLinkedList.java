public class OneLinkedList {
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

    public void reverse() {

        MyList reverseList = new MyList();
        int count = getLastIndex();
        for (int i = count; i >= 0; i--) {
            Integer last = getValueByIndex(i);
            reverseList.add(last);
        }

        Node curNode = container;

        int k = 0;
        while (curNode.next != null) {
            curNode.setValue(reverseList.getValueByIndex(k));
            curNode = curNode.next;
            k++;
        }
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

    class Node {
        private Integer value;
        private Node next;

        public Node(Integer value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
