import java.util.ArrayList;
import java.util.Map;

public class BinarySearchTreeInClassroom {
    static class Node {
        int data;
        Node left;
        Node right;

        Node (final int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }
    public static Node insert (Node root, final int data) {
        if (root == null) {
            root = new Node(data);

            return root;
        }

        if (root.data > data) {
            // Left sub-tree
            return insert(root.left, data);
        } else {
            // Right sub tree
            root.right = insert(root.right, data);
        }

        return root;
    }
    public static void inOrder (final Node root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        System.out.println(root.data + " ");
        inOrder(root.right);
    }
    public static boolean searchInBST (final Node root, final int key) {
        if (root == null) {
            return false;
        }

        if (root.data == key) {
            return true;
        }

        if (key > root.data) {
            return searchInBST (root.right, key);
        }

//        if (key < root.data) {
//            return searchInBST (root.left, key);
//        }

        return searchInBST (root.left, key);

//        return false;
    }
    // Inorder successor -- code it.
    public static Node findInOrderSuccessor (Node root) {
        while (root.left != null) {
            root = root.left;
        }

        return root;
    }
    public static Node deleteNode (final Node root, final int val) {
        if (root.data < val) {
            root.right = deleteNode(root.right, val);
        } else if (root.data > val) {
            root.left = deleteNode(root.left, val);
        } else { // voila
            // Case - 1 - Leaf Node
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case - 2 -- Single Child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {// case 3
                final Node inOrderSuccessor = findInOrderSuccessor(root.right);
                root.data = inOrderSuccessor.data;
                root.right = deleteNode(root.right, inOrderSuccessor.data);
            }
        }

        return root;
    }
    public static void printInRange (final Node root, final int k1, final int k2) {
        if (root == null) {
            return;
        }

        if (root.data >= k1 && root.data <= k2) {
            printInRange(root.left, k1, k2);
            System.out.println(root.data + " ");
            printInRange(root.right, k1, k2);
        } else if (root.data < k1) {
            printInRange(root.left, k1, k2);
        } else {
            printInRange(root.right, k1, k2);
        }
    }
    public static void rootToLeafPaths (final Node root, ArrayList<Integer> path) {
        if (root == null) {
            for (int i = 0; i < path.size(); i++) {
                System.out.println(path.get(i) + " ");
            }
            System.out.println();

            return;
        }

        path.add(root.data);
//        if (root.left == null && root.right == null) {
//            // Print...
//        }
        rootToLeafPaths(root.left, path);
        rootToLeafPaths(root.right, path);
        path.remove(path.size() - 1);
    }
    public static boolean isValidBST (final Node root, final Node min, final Node max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.data <= min.data) {
            return false;
        }

        else if (max != null && root.data >= max.data) {
            return false;
        }

        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }
    // Inorder Predecessor
    public static Node mirrorBST (final Node root) {
        if (root == null) {
            return null;
        }

        final Node leftMirror = mirrorBST(root.left);
        final Node rightMirror = mirrorBST(root.right);

        root.left = rightMirror;
        root.right = leftMirror;

        return root;
    }

    public static void preOrder (final Node root) {
        if (root == null) {
            return;
        }

        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }
    public static Node sortedArrayToBalancedBSTHelper (final int startIndex, final int endIndex, final int[] nums) {
        if (startIndex > endIndex) {
            return null;
        }

        final int mid = (startIndex + endIndex) / 2;
        Node root = new Node(nums[mid]);
        root.left = sortedArrayToBalancedBSTHelper(startIndex, mid - 1, nums);
        root.right = sortedArrayToBalancedBSTHelper(mid + 1, endIndex, nums);

        return root;
    }
    public static Node sortedArrayToBalancedBST (final int[] nums) {
        return sortedArrayToBalancedBSTHelper (0, nums.length, nums);
    }
    public static void getInOrder (final Node root, final ArrayList<Integer> inOrder) {
        if (root == null) {
            return;
        }

        getInOrder (root.left, inOrder);
        inOrder.add(root.data);
        getInOrder (root.right, inOrder);
    }
    public static Node createBST (final int startIndex, final int endIndex, final ArrayList<Integer> inOrder) {
        if (startIndex > endIndex) {
            return null;
        }

        final int mid = (startIndex + endIndex) / 2;
        Node root = new Node(inOrder.get(mid));
        root.left = createBST(startIndex, mid - 1, inOrder);
        root.right = createBST(mid + 1, endIndex, inOrder);

        return root;
    }
    public static Node convertBSTToBalancedBST (final Node root) {
        // inorder
        ArrayList<Integer> inOrder = new ArrayList<>();
        getInOrder (root, inOrder);

        // sorted inorder to Balanced BST
        return createBST(0, inOrder.size() - 1, inOrder);
    }
    static class Info {
        boolean isBST;
        int size;
        int min;
        int max;

        public Info (final boolean isBST, final int size, final int min, final int max) {
            this.isBST = isBST;
            this.size = size;
            this.min = min;
            this.max = max;
        }

    }
    public static int maxBST = 0;
    public static Info sizeOfLargestBSTinBinaryTree (final Node root) {
        if (root == null) {
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Info leftInfo = sizeOfLargestBSTinBinaryTree(root.left);
        Info rightInfo = sizeOfLargestBSTinBinaryTree(root.right);

        final int size = leftInfo.size + rightInfo.size + 1;
        final int min = Math.min(root.data, Math.min(leftInfo.min, rightInfo.min));
        final int max = Math.max(root.data, Math.max(leftInfo.min, rightInfo.min));

        if (root.data <= leftInfo.max || root.data >= rightInfo.min) {
            return new Info(false, size, min, max);
        }

        if (leftInfo.isBST && rightInfo.isBST) {
            maxBST = Math.max(maxBST, size);
            return new Info(true, size, min, max);
        }

        return new Info(false, size, min, max);
    }
    public static Node mergeTwoBSTs (final Node root1, final Node root2) {

        ArrayList<Integer> inOrder1 = new ArrayList<>();
        ArrayList<Integer> inOrder2 = new ArrayList<>();
        ArrayList<Integer> inOrder = new ArrayList<>();

        getInOrder(root1, inOrder1);
        getInOrder(root2, inOrder2);

        int left = 0, right = 0;

        while (left < inOrder1.size() && right < inOrder2.size()) {
            if (inOrder1.get(left) > inOrder2.get(right)) {
                inOrder.add(inOrder2.get(right));
                right++;
            } else {
                inOrder.add(inOrder1.get(left));
                left++;
            }
        }

        while (left < inOrder1.size()) {
            inOrder.add(inOrder1.get(left));
            left++;
        }

        while (right < inOrder2.size()) {
            inOrder.add(inOrder2.get(right));
            right++;
        }

        Node root = createBST(0, inOrder.size(), inOrder);
        return root;
    }
    public static void main(String[] args) {
        int values[] = {5, 1, 3, 4, 2, 7};

        Node root = null;
        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }

        inOrder(root);
    }
}
