package DynamicProgramming;

import java.util.Arrays;

public class DPMinArrayJumps {

    public static int minArrayJumpsTab (final int[] nums) {
        final int size = nums.length;
        int[] dp = new int[size];

        Arrays.fill (dp, -1);

        // Base-case
        dp[size-1] = 0;

        for (int i = size - 2; i >= 0; i--) {
            final int steps = nums[i];
            int ans = Integer.MAX_VALUE;

            for (int j = i + 1; j <= i + steps && j < size; j++) {
                if (dp[j] != -1) {
                    ans = Math.min(ans, dp[j] + 1);
                }
            }

            if (ans != Integer.MAX_VALUE) {
                dp[i] = ans;
            }
        }

        return dp[0];
    }
    public static void main(String[] args) {
        final int[] nums = {2, 3, 1, 1, 4};

        System.out.println(minArrayJumpsTab(nums));
    }
}
