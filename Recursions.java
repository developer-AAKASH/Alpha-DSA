public class Recursions {
    public static void printIncreasing (final int current) {
        if (current < 1) {
            return;
        }

        printIncreasing( current - 1);
        System.out.println(current);
    }

    public static void printDecreasing (final int current) {
        if (current < 1) {
            return;
        }

        System.out.println(current);
        printDecreasing(current - 1);
    }

    public static void printIncreasingDecreasing(final int current) {
        if (current < 1) {
            return;
        }

        System.out.println(current);
        printIncreasingDecreasing(current - 1);
        System.out.println(current);
    }

    public static void printZigZag (final int current) {
        if (current < 1) {
            return;
        }
        System.out.print(current);
        printZigZag(current-1);
        System.out.print(current);
        printZigZag(current-1);
        System.out.print(current);
    }

    public static int firstAccurence (final int currentIndex, final int searchVal, final int[] data) {

        if (currentIndex == data.length) {
            return -1;
        }

        if (data[currentIndex] == searchVal) {
            return currentIndex;
        }

        return firstAccurence (currentIndex + 1, searchVal, data);
    }
    public static int lastAccurence (final int currentIndex, final int searchVal, final int[] data) {
        if (currentIndex == data.length) {
            return -1;
        }

        if (data[currentIndex] == searchVal) {
            int lastAcc = lastAccurence(currentIndex + 1, searchVal, data);

            if (lastAcc >= 0) {
                return lastAcc;
            }
            return currentIndex;
        }

        return lastAccurence(currentIndex + 1, searchVal, data);
    }
    public static int tilingProblem (final int availableLength) {
        if (availableLength < 0) {
            return 0;
        }

        if (availableLength == 0 || availableLength == 1) {
            return 1;
        }

        return tilingProblem (availableLength - 2) + tilingProblem (availableLength - 1);
    }
    public static void binaryString (final int currentIndex, final int size, final int lastChar, StringBuilder answer) {
        if (currentIndex == size) {
            System.out.println(answer);
            return;
        }

        binaryString( currentIndex + 1, size, 0, new StringBuilder(answer).append('0'));
        if (lastChar != 1) {
            binaryString(currentIndex + 1, size, 1, new StringBuilder(answer).append('1'));
        }
    }
    public static StringBuilder removeDuplicates (final int currentIndex, String str, int[] map, StringBuilder answer) {
        if (currentIndex == str.length()) {
            return answer;
        }

        final char ch = str.charAt(currentIndex);
        if (map[ch - 'a'] != 1) {
            map[ch - 'a'] = 1;
            answer.append(ch);
        }

        return removeDuplicates( currentIndex + 1, str, map, answer);
    }
    public static void main(String[] args) {
//        printIncreasing(5);
//        printDecreasing(5);
//        printIncreasingDecreasing(5);
//        printZigZag(3);

//        int[] data = {1, 2, 3, 4, 3, 2, 1};
//
//        System.out.println(firstAccurence(0, 5, data));
//        System.out.println(lastAccurence(0, 5, data));

//        System.out.println(tilingProblem(4));

//        int[] map = new int[26];
//        System.out.println(removeDuplicates(0, "aakash", map, new StringBuilder()));
        binaryString(0, 4, -1, new StringBuilder());
    }
}