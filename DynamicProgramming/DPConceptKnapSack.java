package DynamicProgramming;

public class DPConceptKnapSack {
    public static int knapSack01Recursion (final int currentIndex, final int ans, final int maxWeightAllowed, final int[] values, final int[] weight) {
        final int size = values.length;
        if (currentIndex > size || maxWeightAllowed <= 0) {
            return 0;
        }

//        if (maxWeightAllowed <= 0) {
//            return 0;
//        }

        // we are moving from 0 to N but we can go from N to 0 also...
//        if (maxWeightAllowed <= 0 || currentIndex == 0) {
//            return 0;
//        }

        if (currentIndex == size) {
            return ans;
        }

        // Include...
        int include = 0;
        if (weight[currentIndex] <= maxWeightAllowed) {
            include = knapSack01Recursion (currentIndex + 1, ans + values[currentIndex], maxWeightAllowed - weight[currentIndex], values, weight);
        }

        return Math.max(include, knapSack01Recursion (currentIndex + 1, ans, maxWeightAllowed, values, weight));
    }
    public static int knapSack01AdvRecusrion (final int currentIndex, final int answer, final int maxWeightAllowed, final int[] values, final int[] weight, int[][] dp) {
        if (maxWeightAllowed <= 0 || currentIndex <= 0) {
            return 0;
        }

        if (dp[currentIndex][maxWeightAllowed] != -1) {
            return dp[currentIndex][maxWeightAllowed];
        }

        int include = 0;
        // Include...
        if (weight[currentIndex] <= maxWeightAllowed) {
            include = knapSack01AdvRecusrion (currentIndex - 1, answer + values[currentIndex], maxWeightAllowed - weight[currentIndex], values, weight, dp);
        }

        final int ans = Math.max (include, knapSack01AdvRecusrion (currentIndex - 1, answer, maxWeightAllowed, values, weight, dp));
        dp[currentIndex][maxWeightAllowed] = ans;

        return ans;
    }
    public static void knapSack_0_1 () {
        final int[] values = {15, 14, 10, 45, 30};
        final int[] weight = {2, 5, 1, 3, 4};
        final int maxWeightAllowed = 7;

        System.out.println(knapSack01Recursion (0, values.length, maxWeightAllowed, values, weight));
    }

    public static void knapSack_0_1_Memo () {
        final int[] values = {15, 14, 10, 45, 30};
        final int[] weight = {2, 5, 1, 3, 4};
        final int maxWeightAllowed = 7;
        int[][] dp = new int[values.length + 1][maxWeightAllowed + 1];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println(knapSack01AdvRecusrion(values.length - 1, 0, maxWeightAllowed, values, weight, dp));
    }
    public static void main(String[] args) {
//        knapSack_0_1();
        knapSack_0_1_Memo();
    }
}
