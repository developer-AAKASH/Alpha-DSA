public class RecursionAssignment {
    public static void findAllAccurenceR (final int currentIndex, final int[] data, final int value) {
        if (currentIndex == data.length) {
            return;
        }

        if (data[currentIndex] == value) {
            System.out.println(currentIndex);
        }

        findAllAccurenceR (currentIndex + 1, data, value);
    }

    public static String numberInWords (final int number) {
        switch (number) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 3:
                return "three";
            case 4:
                return "four";
            case 5:
                return "five";
            case 6:
                return "six";
            case 7:
                return "seven";
            case 8:
                return "eight";
            case 9:
                return "nine";
            case 0:
                return "zero";
            default:
                return "";
        }
    }

    public static void printNumbersInWords (final int number, StringBuilder answer) {
        if (number < 10) {
            String currentNumberInString = numberInWords (number);
            answer.append (" " + currentNumberInString);

            return;
        }
        final int currentNumber = number % 10;
        final int nextNumber = number / 10;

        printNumbersInWords (nextNumber, answer);
        String currentNumberInString = numberInWords(currentNumber);
        answer.append(" " + currentNumberInString);
    }
    public static void findAllAccurence (final int[] data, final int value) {
        findAllAccurenceR (0, data, value);
    }
    public static void main(String[] args) {
//        int[] data = {1, 2, 3, 4, 3, 2, 1};
//
//        findAllAccurence (data, 2);
        StringBuilder answer = new StringBuilder("");
        printNumbersInWords (2019, answer);
        System.out.println (answer);
    }
}
