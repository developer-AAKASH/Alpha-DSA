import java.util.*;
import java.util.LinkedList;

public class HashMapICImpl {
    static class HashMap<K, V> {
        private class Node {
            K key;
            V value;

            public Node (final K key, final V value) {
                this.key = key;
                this.value = value;
            }
        }

        private int size;
        private int n, N;
        private LinkedList<Node> buckets[];

        public HashMap() {
            this.size = 0;
            this.N = 4;

            this.buckets = new LinkedList[4];
            for (int i = 0; i < 4; i++) {
                this.buckets[i] = new LinkedList<>();
            }
        }

        private int hashFunction (final K key) {
            final int hashCode = Math.abs(key.hashCode()) % N;

            return hashCode;
        }

        private int searchInLinkedList (final K key, final int bucketIndex) {
            LinkedList<Node> linkedList = buckets[bucketIndex];
            int dataIndex = 0;

            for (int i = 0; i < linkedList.size(); i++) {
                Node node = linkedList.get(i);
                if (node.key == key) {
                    return dataIndex;
                }

                dataIndex++;
            }

            return -1;
        }
        public void put (final K key, final V value) {
            final int bucketIndex = hashFunction(key);
            final int dataIndex = searchInLinkedList (key, bucketIndex);

            if (dataIndex != -1) {
                Node node = buckets[bucketIndex].get(dataIndex);
                node.value = value;
            } else {
                buckets[bucketIndex].add(new Node(key, value));
                size++;
                n++;
            }

            final double lamda = (double) n/N;
            if (lamda > 2.0) {
                rehash();
            }
        }

        private void rehash () {
            LinkedList<Node> oldBucket[] = buckets;
            buckets = new LinkedList[N * 2];
            N = 2 * N;

            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList<>();
            }

            // nodes -> add in bucket...
            for (int i = 0; i < oldBucket.length; i++) {
                LinkedList<Node> linkedList = oldBucket[i];

                for (int j = 0; j < linkedList.size(); j++) {
                    Node node = linkedList.remove();
                    put(node.key, node.value);
                }
            }
        }

        public boolean containsKey (final K key) {
            final int bucketIndex = hashFunction(key);
            final int dataIndex = searchInLinkedList (key, bucketIndex);

            return dataIndex != -1;
        }

        public V remove (final K key) {
            final int bucketIndex = hashFunction(key);
            final int dataIndex = searchInLinkedList (key, bucketIndex);

            if (dataIndex != -1) {
                Node node = buckets[bucketIndex].remove(dataIndex);
                n--;

                return node.value;
            }

            return null;
        }

        public V get (final K key) {
            final int bucketIndex = hashFunction(key);
            final int dataIndex = searchInLinkedList (key, bucketIndex);

            if (dataIndex != -1) {
                Node node = buckets[bucketIndex].get(dataIndex);

                return node.value;
            }

            return null;
        }

        public ArrayList<K> keySet () {
            ArrayList<K> keys = new ArrayList<>();

            for (LinkedList<Node> linkedList : buckets) {
                for (Node node : linkedList) {
                    keys.add(node.key);
                }
            }

            return keys;
        }

        public boolean isEmpty () {
            return n == 0;
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();

        hm.put("India", 150);
        hm.put("China", 140);
        hm.put("Russia", 50);
        hm.put("France", 30);
        hm.put("USA", 40);

        ArrayList<String> keys = hm.keySet();
        for (String key : keys) {
            System.out.println(key);
        }
    }
}
