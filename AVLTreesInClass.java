public class AVLTreesInClass {
    static class Node {
        int data, height;
        Node left, right;

        Node (final int data) {
            this.data = data;
            height = 1;
        }
    }

    public static Node root;

    public static int height (final Node root) {
        if (root == null) {
            return 0;
        }

        return root.height;
    }

    static int max (final int a, final int b) {
        return (a > b) ? a : b;
    }

    // Left rotate...
    public static Node leftRotate (final Node x) {
        Node y = x.right;
        Node t2 = y.left;

        // Perform rotation...
        y.left = x;
        x.right = t2;

        // Update Heights...
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // return new root
        return y;
    }
    // Right Rotate subtree rooted with y
    public static Node rightRotate (final Node y) {
        Node x = y.left;
        Node t2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = t2;

        // Update heights...
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root.
        return x;
    }
    public static int getBalance (final Node root) {
        if (root == null) {
            return 0;
        }

        return height(root.left) - height(root.right);
    }
    public static Node insert (final Node root, final int key) {
        if (root == null) {
            return new Node (key);
        }

        if (key < root.data) {
            root.left = insert(root.left, key);
        } else if (key > root.data) {
            root.right = insert(root.right, key);
        } else {
            return root; // Duplicates are not allowed...
        }

        // Updating height of root...
        root.height = 1 + Math.max(height(root.left), height(root.right));

        // Get root's balance factor...
        final int balanceFactor = getBalance (root);

        // Left Left case...
        if (balanceFactor > 1 && key < root.left.data) {
            return rightRotate(root);
        }
        // Right Right case...
        else if (balanceFactor < -1 && key > root.right.data) {
            return leftRotate (root);
        }

        // Left Right case...
        else if (balanceFactor > 1 && key > root.left.data ) {
            // first will do left rotate so our left will be updated...
            root.left = leftRotate (root.left);
            // and than will do right rotate so our root will be updated...
            return rightRotate(root);
        }

        // Right Left case
        if (balanceFactor < -1 && key < root.right.data) {
            root.right = rightRotate(root.right);
            return leftRotate (root);
        }

        return root; // AVL is balanced...
    }
}
