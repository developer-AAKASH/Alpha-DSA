import java.util.*;
public class StackInClassroom {
    public static void pushAtTheBottomOfStack (Stack<Integer> stk, final int data) {
        if (stk.isEmpty()) {
            stk.push(data);
            return;
        }

        final int currentElement = stk.peek();
        stk.pop();
        pushAtTheBottomOfStack(stk, data);
        stk.push(currentElement);
    }

    public static String reverseAString (final String str) {
        StringBuilder reverserString = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }

        while (!stack.isEmpty()) {
            reverserString.append(stack.pop().toString());
        }

        return reverserString.toString();
    }

    public static void reverseAStack (Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        final int currentElement = stack.pop();
        reverseAStack(stack);
        pushAtTheBottomOfStack(stack, currentElement);
    }

    public static void printStack (final Stack<Integer> stack) {
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void stockSpan (final int[] stocks, int[] span) {
        Stack<Integer> stack = new Stack<>();

        span[0] = 1;
        stack.push(0);

        for (int i = 1; i < stocks.length; i++) {
            final int currentPrice = stocks[i];

            while (!stack.isEmpty() && currentPrice > stocks[stack.peek()]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                span[i] = i + 1;
            } else {
                final int previousHigh = stack.peek();
                span[i] = i = previousHigh;
            }

            stack.push(i);
        }
    }
    public static void stockSpanBF (final int[] stocks) {
//        span = currentIndex - previousHigh
    }

    public static void nextGreaterElement (final int[] nums) {
        int[] nge = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i] ) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                nge[i] = -1;
            } else {
                nge[i] = nums[stack.peek()];
            }

            stack.push(i);
        }

        System.out.println();
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nge[i] + " ");
        }
        System.out.println();
    }

    public static void nextGreaterElementBF (final int[] nums) {

    }

    public static boolean isValidParentheses (final String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            final Character currentChar = str.charAt(i);
            if (currentChar == '{' || currentChar == '[' || currentChar == '(') {
                stack.push(currentChar);
            } else if (currentChar == '}' && stack.peek() == '{' && !stack.isEmpty()) {
                stack.pop();
            } else if (currentChar == ']' && stack.peek() == '[' && !stack.isEmpty()) {
                stack.pop();
            } else if (currentChar == ')' && stack.peek() == '(' && !stack.isEmpty()) {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.isEmpty();
    }

    public static boolean isDuplicateParentheses (final String str) {
        final int strLen = str.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < strLen; i++) {
            final char currentChar = str.charAt(i);

            if (currentChar == ')' || currentChar == '}' || currentChar == ']') {
                final char currentOpen = currentChar == ')' ? '(' :
                        currentChar == '}' ? '{' : '[';
                int count = 0;
                while (!stack.isEmpty() && stack.peek() != currentOpen) {
                    count++;
                    stack.pop();
                }

                if (count < 1){
                    return true;
                }

                stack.pop();

            } else {
                stack.push(currentChar);
            }
        }

        return false;
    }

    public static int maxAreaInHistogram (final int[] heights) {
        int[] nsl = new int[heights.length];
        int[] nsr = new int[heights.length];
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                nsl[i] = -1;
            } else {
                nsl[i] = stack.peek();
            }

            stack.push(i);
        }

        stack = new Stack<>();
        for (int i = heights.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                nsr[i] = heights.length;
            } else {
                nsr[i] = stack.peek();
            }

            stack.push(i);
        }

//        Calculating Area...
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            int width = nsr[i] - nsl[i] - 1;
            int currentArea = height * width;

            maxArea = Math.max(currentArea, maxArea);
        }

        return maxArea;
    }
    public static void main(String[] args) {
        System.out.println(reverseAString("Aakash"));

        final int[] stocks = {100, 80, 60, 70, 60, 85, 100};

        System.out.println(isValidParentheses("({[]}"));
        System.out.println(isDuplicateParentheses("(((a+b)+(c+d)))"));

        final int[] heights = {2, 1, 5, 6, 2, 3};
    }
}
