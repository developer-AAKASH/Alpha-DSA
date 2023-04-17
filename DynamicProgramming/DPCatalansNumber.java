package DynamicProgramming;

import java.util.Arrays;

public class DPCatalansNumber {
    public static int catalanRecursion (final int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int nthCatalan = 0;
        for (int i = 0; i <= n-1; i++) {
            nthCatalan += catalanRecursion (i) * catalanRecursion(n-i-1);
        }

        return nthCatalan;
    }
    public static int catalanRecursionMemo (final int n, int[] dp) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int nthCatalan = 0;
        for (int i = 0; i <= n-1; i++) {
            nthCatalan += catalanRecursionMemo (i, dp) * catalanRecursionMemo(n-i-1, dp);
        }

        dp[n] = nthCatalan;

        return nthCatalan;
    }
    public static void catalanTabulation (final int n) {
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }

        System.out.println(dp[n]);
    }
    public static void main (String[] args) {
        System.out.println(catalanRecursion(4));
        final int n = 4;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        System.out.println(catalanRecursionMemo(n, dp));

        catalanTabulation(4);
    }
}
