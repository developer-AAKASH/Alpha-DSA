public class LinkedListInClassroom {
    static class Node {
        int data;
        Node next;

        public Node (final int data) {
            this.data = data;
            this.next = null;
        }
    }
    public static Node head, tail;
    public static int size = 0;
    public static void printList (final Node head) {

    }
    public static void insertAtBegining (final int data) {
    }
    public static void insertAtEnd (final int data) {

    }
    public static void insert (final int index, final int data) {
        if (index == 0) {
            insertAtBegining(data);
            return;
        }

        Node newNode = new Node(data);
        size++;
        Node iterator = head;
        int i = 0;

        while (i < index - 1) {
            iterator = iterator.next;
            i++;
        }

        newNode.next = iterator.next;
        iterator.next = newNode;
    }
    public static int deleteFirst () {
        if (size == 0) {
            System.out.println("Linked List is Empty !!");
            return Integer.MIN_VALUE;
        }

        if (size == 1) {
            final int dataToBeDeleted = head.data;
            head = tail = null;
            size--;

            return dataToBeDeleted;
        }

        final int dataToBeDeleted = head.data;

        head = head.next;
        size--;

        return dataToBeDeleted;
    }
    public static int deleteLast () {
        if (size == 0) {
            System.out.println("Linked List is Empty !!");

            return Integer.MIN_VALUE;
        }

        if (size == 1) {
            final int dataToBeDeleted = head.data;
            head = tail = null;
            size--;

            return dataToBeDeleted;
        }

        Node iterator = head;

//        while (iterator.next != null) { // .next.next
//            iterator = iterator.next;
//        }
        for (int i = 0; i < size - 2; i++) {
            iterator = iterator.next;
        }

        final int dataToBeDeleted = iterator.next.data; // tail.data
        iterator.next = null;
        tail = iterator;
        size--;

        return dataToBeDeleted;
    }
    public static int searchInLinkedList (final int value) {
        Node iterator = head;
        int i = 0;

        while (iterator != null) {
            if (iterator.data == value) {
                return i;
            }

            iterator = iterator.next;
            i++;
        }

        return -1;
    }

    public static int searchInLinkedListRMine (final Node list, final int currentIndex, final int value) {
        if (list == null) {
            return -1;
        }

        if (list.data == value) {
            return currentIndex;
        }

        return searchInLinkedListRMine(list.next, currentIndex + 1, value);
    }
    public static int searchInLinkedListR (final int data) {
        return searchInLinkedListRHelper(head, data);
    }
    public static int searchInLinkedListRHelper (final Node head, final int key) {
        if (head == null) {
            return -1;
        }

        if (head.data == key) {
            return 0;
        }

        final int index = searchInLinkedListRHelper(head.next, key);
        if (index == -1) {
            return -1;
        }

        return index + 1;
    }
    public static void reverse () {
        // will have 3 helper pointres...
        // previous -- to hold the previous element
        // current -- current element for which we are making it reverse...
        // next -- for holding the next element so that we cant lost the list as we are keep reversing the list for current element...
        Node prev = null, current = tail = head, next;

        // so approch is our current element will hold current element which will have all
        // the previous element in reverse manner and all element including is still in forward direction...

        // so to make our current list as part of our reversed list will keep linking it to
        // previous node untill it becomes null

        // thats the idea lets see how its going...

        // initially previous will be null as our first node which is going to be last node,
        // last node will hold null as last node's next so initialising it with null only...

        // current will hold head, our first element to be reversed...
        // tail will be head as head going to be last node of the list...

        // next element will be null initially, we will hold next element onward list on the go...

        // till current becomes null...
        while (current != null) {
            // first will hold the reference of the upcoming list to make whole list reverse...
            next = current.next;
            // now the heart of the logic, we will reverse the link to make the whole list reverse...
            current.next = prev;
            // now including current list is reversed now, we will updated the helper pointers...
            // our previous will become current as previous pointer for the upcoming node in the lists...
            prev = current;
            // and current element will be next element to be reverse...
            current = next;
        }

        // now we are iterating till next element becomes null means null is the end of list and
        // as per our analogy, all the nodes before current nodes are reveresed so now when current
        // becomes null, means our whole list is reversed and our previous will hold the first
        // node of reversed list, will assign it to head and eventually will have reversed list...
        head = prev;
    }
    public static void deleteNthNodeFromEnd (final int n) {
        // Calculate size of List if we don't have size variable.

        // n == size means it will be first element because we are looking from the end...
        if (n == size) {
            deleteFirst();
            return;
        }

        int i = 1, iToFind = size - n;
        Node iterator = head;
        while (i < iToFind) {
            iterator = iterator.next;
            i++;
        }

        iterator.next = iterator.next.next;
    }
    public static Node findMiddle (final Node head) {
        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // turtle
            fast = fast.next.next; // hare
        }

        return slow;
    }
    public static boolean isPalindromLinkedList () {
        if (head == null || head.next == null) {
            return true;
        }

        // So idea here is will find middle point of a list...
        // 1 - Find mid
        final Node middleNode = findMiddle(head);

        // Now we will divide the list in two equal part using middle node...
        // Now idea is, we will reverse the second half of the list as we have to check
        // palindrome, palindrom is same from start and the end,
        // as we can't traverse in backward direction in singly linked list, we are reversing
        // the second half of the list and as we can move forward in reverse linked list, we can
        // check the list is same from start and end or not...

        // previous, next and current will be helper in reversing the as per our reversing list logic...
        Node previous = null;
        // current element will be middle node as we are reversing the second half of the list...
        Node current = middleNode; // as we have to reverse second half starting from mid
        Node next;

        // our classic reversing list logic...
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        // now rightHalf will hold second half of the list...
        // and previous will hold start point of reversed list as par our classic reversing list logic...
        Node rightHalf = previous;
        // leftHalf will hold start point of our first half...
        Node leftHalf = head;

        // 3 - check left half and right half
        // Now we are checking our list from starting and ending as per our logic,
        // rightHalf will hold point to half reversed list so will keep going till it becomes null...
        while (rightHalf != null) {
            // if its not same from start and end at any point will return false as its not palindrom any more..
            if (leftHalf.data != rightHalf.data) {
                return false;
            }

            // increase left and right pointer in respective forward and backward direction...
            // both seems moving in forward direction, but actually right list moving backward as its reversed so...
            leftHalf = leftHalf.next;
            rightHalf = rightHalf.next;
        }

        // till the end if start and end are going same. than our list is palindrome only...
        return true;
    }
    public static boolean isCycleInLinkedList () {
        Node slow = head;
        Node fast = head;

        // so idea here is, we are using 2 helper iterators, on will be increased by one
        // and another will be by 2 like turtle and rabbit like rabbit is 2 times faster
        // than turtle ...

        // Idea here is, if there is cycle which means list is kind of circular than
        // our helper pointer will meet each other at one point for sure (because of circular nature of a list)
        // and if they are meeting each other than there is cycle in list because that's why
        // our turtle and rabbit are meeting each other, of-course turtle will overlaped by rabbit
        // but how much time, that depends on list...

        // so there is logical and mathamatical proof for this algorithem too..

        // untill our fast and fast's next cant become null, as fast goes 2x fast, we have to take care of its next...
        while (fast != null && fast.next != null) {
            // slow will be increases by 1...
            slow = slow.next;
            // Fast will be increased by 2
            fast = fast.next.next;

            // and at any point, they meet, there is cycle for sure so will return true...
            if (slow == fast) {
                return true;
            }
        }

        // and if fast become null at one point, means list in linear and no cycle is there...
        return false;
    }
    public static void removeCycleInLinkedList () {
        // 1 - detect cycle
        Node slow = head, fast = head;
        boolean isCycle = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                isCycle = true;
                break;
            }
        }

        // till here we are following the cycle detecting logic...
        if (isCycle) {
            // Now, if we got the cycle, we have to Find meeting point from where it becoming cyclic...
            // after detecting the cycle, we will re-initialise the slow with head again...
            slow = head;
            // previous is helper to find/track the meeting point...
            Node previous = null;
            // again will checking -> untill slow and fast can't become same...
            while (slow != fast) {
                // Now, we are moving both of them at same speed like in 1x...
                slow = slow.next;
                // we are tracking the fast element by previous pointer and when..
                previous = fast;
                fast = fast.next;
            }
            // so idea here is when we detect cycle and slow and fast will be at some point on the list...
            // so if we keep moving by one value from that point, fast and slow will meet at one point
            // again and that will be a point from where the list is being cyclic...

            // the proof of this approch is pretty mathamatically based on distance they are travelling
            // but it works...

            // so as per our approch, previous will hold that point from where cycle is there
            // we will update previous's next to Remove the Cycle...
            previous.next = null;
        }
    }
    // thinking of removing Full Cycle like that corner case in class...
    public static Node middleNode (final Node head) {
        // originally in this algorithm of fast-slow, we initialize fast also with head only
        /*
        But here, we are doing it bit differently because in case of odd nodes list,
        we will have exact middle node like 3 in if list 1->2->3->4->5

        But if we have list with even numbers 1->2->3->4

        with the original algo, we will get 3 as our middle node so if we treat our middle
        node as head of second half of our list than it will make sense but as we are making
        our middle node as last node of our first half of the list we have to handle two scenarios
        in a code so to avoid the hurdle we are initialising the fast pointer with head.next
        so in case even or odd, kind of same node as middle...

        */
        Node slow = head, fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    public static Node merge (Node left, Node right) {
        // pointer to hold the whole sorted list...
        // initialising with -1 for easyness...
        Node mergedList = new Node(-1);
        // iterator to merge the both lists as we can't loss the starting point of merged list, we are taking help...
        Node iterator = mergedList;

        // basic merging two list approch -- until left and right list cant become empty/null...
        while (left != null && right != null) {
            // as these both the list is sorted indivisually, we have to take are while merging them
            // that when the getting merged, it also remain sorted...

            // so basic comparison of sorting them in asscending order...
            if (left.data <= right.data) {
                // Now, here is the catch, if current-left is smaller than currentRight
                // out currentLeft will be next element of merged list...
                iterator.next = left;
                // left will be increased so that we can't iterate over same node again and again..
                left = left.next;
                // also increasing the iterator so will link all node one after another and don't override the list...
                iterator = iterator.next;
            } else {
                // out currentRight will be next element of merged list otherwise...
                iterator.next = right;
                // right will be increased so that we can't iterate over same node again and again..
                right = right.next;
                // also increasing the iterator so will link all node one after another and don't override the list...
                iterator = iterator.next;
            }
        }

        // Loop will stopped once one of them become null or one of the list get merged in sorted fashion ...
        // so we iterating over the ramining list and just appending them in the list as
        // we are only left with this list and its sorted so...

        while (left != null) {
            iterator.next = left;
            left = left.next;
            iterator = iterator.next;
        }

        while (right != null) {
            iterator.next = right;
            right = right.next;
            iterator = iterator.next;
        }

        // returning mergedList's next as first element is -1 so...
        return mergedList.next;
    }
    public static Node mergeSortOnLinkedList (Node head) {
        // If list is null or having one element only, then its sorted already
        // so just return head from here only...
        if (head == null || head.next == null) {
            return head;
        }
        // find mid to divide the list...
        // as mid will be last node of our first half of the list...
        Node mid = middleNode(head);

        // left and right Merge sort
        // as our middle node is last of left, middle's next will become first of right/second list...
        Node rightHead = mid.next;
        // breaking the reference/list...
        mid.next = null;
        // will call merge sort on both the divided list...
        Node newLeft = mergeSortOnLinkedList(head);
        Node newRight = mergeSortOnLinkedList(rightHead);

        // as both the list are sorted invidually, we just have to merge them to make our whole list sorted...
        return merge (newLeft, newRight);
    }
    public static void zigZagLinkedList () {
        /*
        -> Basically idea/approach here we are using to make the job done is same as we are
        using in checking list is palindrome or not...
        */

        // 1st will find middle of List and our mid will be last node of our 1st half of the list...
        Node middleNode = middleNode(head);

        // taking previous pointer as helper to reverse the second half of the list...
        Node previous = null;
        // current is also helper to reverse the second half of the list...
        Node current = middleNode.next;
        // breaking the reference of from middle node to make 2 lists from one...
        middleNode.next = null;
        // another helper to reverse the second half of the list...
        Node next;
        // till the current cant become null...
        while (current != null) {
            // our next element will be current's next...
            next = current.next;
            // current's next will be previous, as we are reversing the list, this line will reverse the link...
            current.next = previous;
            // Now as our current's link is reversed, previous will be current in order to reverse the link of next element...
            previous = current;
            // and our current will be next as we have to reverse the link of next element so...
            current = next;
        }

        // so left pointer will hold head pointer which is kind of first node of the first half of the list or simply our left list...
        Node left = head;
        // right pointer will hold starting point of right/second half of the list's first node...
        // as we have reverse the list of second half, our previous pointer will be the node
        // who hold the reversed list's start point... as our current pointer has become the null so
        // eventually, previous holds the first node of reversed list of second half...
        Node right = previous;
        // declaring the nextLeft element and nextRight element pointer to hold the next elements of respective lists...
        Node nextL, nextR;

        // Alternate merging - zig-zag merging
        // untill the left and right list cant becomes the empty...
        while (left != null && right != null) {
            // as said, nextL will hold next element to the left list, as left holds current element of a list and same goes for right list...
            nextL = left.next;
            // left's next element will be current right element as we have to make the list in the fashion...
            left.next = right;
            // same as left, nextR will hold the next right list's element...
            nextR = right.next;
            // right's next will be next left element... as we have to make zig-zag list..
            right.next = nextL;

            // So after completing the one iteration of a zig-zag-ing...
            // we will move our left and right list's current element with
            // nextL and nextR helper pointer and process will be repeated...
            left = nextL;
            right = nextR;
        }

        // basically idea is we have access of our starting of list but to make it zig-zag,
        // we need also access of reverse list which is half of the original list so than
        // we can make the list zig-zag so before this loop, we are getting that list and this
        // loop will be just process of zig-zag-ing or we can say updating the links in zig-zag fasion...
    }
    
    public static void main(String[] args) {
        LinkedListInClassroom list = new LinkedListInClassroom();


    }

}
