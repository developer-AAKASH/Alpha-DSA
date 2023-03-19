import com.sun.jdi.request.StepRequest;

public class TriesInClass {
    static class Node {
        Node[] childrens = new Node[26];
        boolean isWord = false;

        Node () {
            for (int i = 0; i < 26; i++) {
                childrens[i] = null;
            }
        }
    }

    public static Node root = new Node();

    // Insert -> O(L)
    public static void insert (final String word) {
        Node iterator = root;

        for (int level = 0; level < word.length(); level++) {
            final int index = word.charAt(level) - 'a';

            if (iterator.childrens[index] == null) {
                iterator.childrens[index] = new Node();
            }

            iterator = iterator.childrens[index];
        }

        iterator.isWord = true;
    }
    public static boolean search (final String word) {
        Node iterator = root;

        for (int level = 0; level < word.length(); level++) {
            final int index = word.charAt(level) - 'a';
            if (iterator.childrens[index] == null) {
                return false;
            }

            iterator = iterator.childrens[index];
        }

        return iterator.isWord;
    }
    public static boolean wordBreak (final String key) {
        if (key.length() == 0) {
            return true;
        }

        // expanation for i = 1 to i <= lenth...
        for (int i = 1; i <= key.length(); i++) {
            if (search(key.substring(0, i)) && wordBreak(key.substring(i))) {
                return true;
            }
        }

        return false;
    }
    public static void wordBreakProblem () {
        String words[] = {"i", "like", "sam", "samsung", "mobile", "ice"};

        for (int i = 0; i < words.length; i++) {
            insert(words[i]);
        }

        final String key = "ilikesamsung";

        System.out.println(wordBreak(key));
    }
    static class NodeP {
        NodeP[] childrens = new NodeP[26];
        boolean isWord = false;
        int frequency;

        public NodeP () {
            for (int i = 0; i < childrens.length; i++) {
                childrens[i] = null;
            }

            frequency = 1;
        }
    }

    public static NodeP rootP = new NodeP();
    public static void insertP (final String word) {
        NodeP iterator = rootP;

        for (int i = 0; i < word.length(); i++) {
            final int index = word.charAt(i) - 'a';

            if (iterator.childrens[index] == null) {
                iterator.childrens[index] = new NodeP();
            } else {
                iterator.childrens[index].frequency++;
            }

            iterator = iterator.childrens[index];
        }

        iterator.isWord = true;
    }
    public static void findPrefix (final NodeP root, final String answer) {
        if (root == null) {
            return;
        }

        if (root.frequency == 1) {
            System.out.println(answer);

            return;
        }

        for (int i = 0; i < 26; i++) { // 26 -> root.childrens.length
            if (root.childrens[i] != null) {
                findPrefix(root.childrens[i], answer + (char)(i + 'a'));
            }
        }
    }
    public static void prefixProblem () {
        final String arr[] = {"zebra", "dog", "duck", "dove"};

        for (int i = 0; i < arr.length; i++) {
            insertP(arr[i]);
        }

        rootP.frequency = -1;

        findPrefix(rootP, "");
    }
    public static boolean isStartWith (final String prefix) {
        Node iterator = root;

        for (int i = 0; i < prefix.length(); i++) {
            final int index = prefix.charAt(i) - 'a';

            if (iterator.childrens[index] == null) {
                return false;
            }

            iterator = iterator.childrens[index];
        }

        return true;
    }
    public static void startsWithProblem () {
        final String words[] = {"apple", "app", "mango", "man", "woman"};

        for (int i = 0; i < words.length; i++) {
            insert(words[i]);
        }

        final String prefix = "app";
        final String prefix2 = "moon";

        System.out.println(isStartWith(prefix));
        System.out.println(isStartWith(prefix2));
    }
    public static int uniqueCount (final Node root) {
        if (root == null) {
            return 0;
        }
        int myChildCount = 0;

        for (int i = 0; i < root.childrens.length; i++) {
            if (root.childrens[i] != null) {
                myChildCount += uniqueCount(root.childrens[i]);
            }
        }

        return myChildCount + 1;
    }
    public static void countUniqueSubStrings () {
//        final String str = "ababa";
        final String str = "apple";

        for (int i = 0; i < str.length(); i++) {
            final String suffix = str.substring(i);
//            insert(str.substring(i, str.length()));
            insert(suffix);
        }

        System.out.println(uniqueCount(root));
    }
//    public static boolean longestWordWithAllPrefixesRMy (final Node root, final String answer) {
//        if (root == null) {
//            System.out.println(answer);
//
//            return true;
//        }
//
//        for (int i = 0; i < root.childrens.length; i++) {
//            if (root.childrens[i] != null && root.childrens[i].isWord) {
//                final String current = answer + (char)(i + 'a');
//
//                return longestWordWithAllPrefixesR(root.childrens[i], answer);
//            }
//        }
//
//        return false;
//    }

    public static String answer = "";
    public static void longestWordWithAllPrefixesR (final Node root, final StringBuilder current) {
        if (root == null) {
            return;
        }

        /*
            if we start loop in reverse order, we will get apply answer in place of apple basically larger answer
            in place of smaller as apple is smaller string than apply !!
        */
        for (int i = 0; i < root.childrens.length; i++) {
            if (root.childrens[i] != null && root.childrens[i].isWord == true) {
                final char ch = (char)( i + 'a');
                current.append(ch);

                if (current.length() > answer.length()) {
                    answer = current.toString();
                }

                longestWordWithAllPrefixesR(root.childrens[i], current);

                // backtrack
                current.deleteCharAt(current.length() - 1);
            }
        }
    }
    public static void longestWordWithAllPrefixes () {
//        final String words[] = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        final String words[] = {"a", "banana", "app", "appl", "ap", "apply"};

        for (String word : words) {
            insert(word);
        }

        longestWordWithAllPrefixesR(root, new StringBuilder());

        System.out.println(answer);
    }
    public static void main(String[] args) {
//        String words[] = {"the", "a", "there", "their", "any", "thee"};
//
//        for (int i = 0; i < words.length; i++) {
//            insert(words[i]);
//        }
//
//        System.out.println(search("thee"));
//        System.out.println(search("thor"));

//        wordBreakProblem();

//        prefixProblem();

//        startsWithProblem();

//        countUniqueSubStrings();
        longestWordWithAllPrefixes();
    }
}
