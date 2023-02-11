import java.util.*;

public class LinkedList {

    Node head;
    Node tail;
    class Node {
        int value;
        Node next;

        Node (final int value) {
            this.value = value;
            this.next = null;
        }

//        public void setValue (int value) {
//            this.value = value;
//        }
//
//        public void linkNext (Node next) {
//            this.next = next;
//        }
//
//        public int getValue () {
//            return this.value;
//        }
//
//        public Node getNext () {
//            return this.next;
//        }
    }

    public void insertAtBegining (final int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertAtEnd (final int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            Node traversingNode = head;

            while (traversingNode.next != null) {
                traversingNode = traversingNode.next;
            }

            traversingNode.next = newNode;
            tail = newNode;

        }
    }

    public void deleteFirst () {
        Node deletingNode = head;
        head = head.next;
        deletingNode = null;
    }

    public void deleteLast () {
        Node traversingNode = head;

        while (traversingNode.next.next != null) {
            traversingNode = traversingNode.next;
        }

        Node deletingNode = traversingNode.next;
        tail = traversingNode.next;
        traversingNode.next = null;
        deletingNode = null;
    }

    public void printList () {
        Node tempNode = head;

        while (tempNode != null) {
            System.out.println(tempNode.value);
            tempNode = tempNode.next;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertAtBegining(5);
        list.insertAtBegining(6);
        list.insertAtBegining(7);
        list.insertAtEnd(4);
        list.insertAtEnd(3);
        list.printList();
        list.deleteFirst();
        list.deleteLast();
        list.printList();
    }
}