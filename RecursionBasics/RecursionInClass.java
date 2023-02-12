package RecursionBasics;

public class RecursionInClass {
    public static void printIncreasing (final int limit) {
        if (limit == 0) {
            return;
        }

        printIncreasing (limit - 1);
        System.out.println(limit);
    }

    public static void printDecreasing (final int limit) {
        if (limit == 0) {
            return;
        }

        System.out.println(limit);
        printDecreasing (limit - 1);
    }

    public static int factorial (final int n) {
        if (n == 1) {
            return 1;
        }

        return n * factorial (n - 1);
    }

    public static int sumOfNNaturalNumbers (final int n) {
        if (n == 1) {
            return n;
        }

        return n + sumOfNNaturalNumbers (n - 1);
    }

    public static int fibonacci (final int n) {
        if (n == 1 || n == 0) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }

        return fibonacci (n - 1) + fibonacci (n - 2);
    }

    public static boolean isSorted (final int currentIndex, final int[] data) {
        if (currentIndex + 1 == data.length - 1) {
            return !(data[currentIndex] > data[currentIndex + 1]);
        }

        if (data[currentIndex] > data[currentIndex + 1]) {
            return false;
        }

        return isSorted (currentIndex + 1, data);
    }

    public static int firstAccurance (final int currentIndex, final int value, final int[] data) {
        if (currentIndex == data.length) {
            return -1;
        }

        if (data[currentIndex] == value) {
            return currentIndex;
        }

        return firstAccurance (currentIndex + 1, value, data);
    }

    public static int lastAccurance (final int currentIndex, final int value, final int[] data) {
        if (currentIndex == data.length) {
            return -1;
        }

        if (data[currentIndex] == value) {
            final int isValueExistFurther = lastAccurance (currentIndex + 1, value, data);
            if ( isValueExistFurther >= 0) {
                return isValueExistFurther;
            }

            return currentIndex;
        }

        return lastAccurance (currentIndex + 1, value, data);
    }

    public static void allAccurance (final int currentIndex, final int value, final int[] data) {
        if (currentIndex == data.length) {
            return;
        }

        if (data[currentIndex] == value) {
            System.out.println(currentIndex);
        }

        allAccurance (currentIndex + 1, value, data);
    }

    public static int power (final int num, final int power) {
        if (power == 0) {
            return 1;
        }

        return num * power (num, power - 1);
    }

    public static int powerOptimized (final int num, final int power) {
        if (power == 0) {
            return 1;
        }

        final int powerBy2 = powerOptimized (num, power / 2);

        if (power % 2 == 0) {
            return powerBy2 * powerBy2;
        }

        return num * powerBy2 * powerBy2;
    }

    public static int tilingProblem (final int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

//        n - 1 -> Putting tile Vertically
//        n - 2 -> Putting tile Horizontally
        return tilingProblem (n - 1) + tilingProblem (n - 2);
    }

    public static void removeDuplicates (final int currentIndex, final String str, int[] map, StringBuilder answer) {
        if (currentIndex == str.length()) {
            System.out.println(answer);
            return;
        }

        if (map[str.charAt(currentIndex) - 'a'] != 1) {
            map[str.charAt(currentIndex) - 'a'] = 1;
            answer.append(str.charAt(currentIndex));
        }

        removeDuplicates (currentIndex + 1, str, map, answer);
    }

    public static int friendsPairing (final int friends) {
        if (friends <= 2) {
            return friends;
        }

//        At every step, we have 2 choices,
//        Being alone...
        final int alone = friendsPairing (friends - 1);
//        Be with friends
//        And for being with friends, there will be friends - 1 choices as we can't include himself as a choice so friends - 1
        final int withFriends = (friends - 1) * friendsPairing (friends - 2);

        return alone + withFriends;
//        in short form
//        return friendsPairing (friends - 1) + (friends - 1 * friendsPairing (friends - 2));
    }

    public static void binaryString (final int currentIndex, final int n, final int lastChar, StringBuilder answer) {
        if (currentIndex == n) {
            System.out.println (answer);
            return;
        }

        if (lastChar != 1) {
            binaryString (currentIndex + 1, n, 1, new StringBuilder(answer).append(1));
        }

        binaryString (currentIndex + 1, n, 0, new StringBuilder(answer).append(0));
    }

    public static void binaryString (final int size, final int lastChar, StringBuilder answer) {
        if (size == 0) {
            System.out.println (answer);
            return;
        }

        if (lastChar != 1) {
            binaryString (size - 1, 1, new StringBuilder(answer).append(1));
        }

        binaryString (size - 1, 0, new StringBuilder(answer).append(0));
    }
    public static void main(String[] args) {
//        printIncreasing (5);
//        printDecreasing (5);
//        System.out.println(factorial (5));
//        System.out.println (sumOfNNaturalNumbers(5));
//        System.out.println(fibonacci(5));

//        int[] data = {1, 2, 3, 4, 5};
//        int[] data = {1, 2, 1, 3, 4, 5};
//        int[] data = {5, 4, 3, 2, 1};

//        System.out.println (isSorted (0, data));

//        int[] data = {1, 2, 1, 3, 4, 5};
//        System.out.println(firstAccurance(0, 1, data));
//        System.out.println(lastAccurance(0, 1, data));
//        allAccurance(0, 1, data);

//        System.out.println(power (5, 5));
//        System.out.println(powerOptimized (5, 5));

//        int[] map = new int[26];
//        removeDuplicates (0, "aakash", map, new StringBuilder(""));

        binaryString(0, 3, 0, new StringBuilder());
        System.out.println();
        binaryString(3, 0, new StringBuilder());
    }
}
