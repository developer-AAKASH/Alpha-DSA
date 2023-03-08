import java.util.ArrayList;

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
    public static void main(String[] args) {
        int values[] = {5, 1, 3, 4, 2, 7};

        Node root = null;
        for (int i = 0; i < values.length; i++) {
            root = insert(root, values[i]);
        }

        inOrder(root);
    }
}
