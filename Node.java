public class Node {
    private int value;
    private Node next;

    Node () {
        this.next = null;
    }

    public void setValue (int value) {
        this.value = value;
    }

    public void linkNext (Node next) {
        this.next = next;
    }

    public int getValue () {
        return this.value;
    }

    public Node getNext () {
        return this.next;
    }
}
