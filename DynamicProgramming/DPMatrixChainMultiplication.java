package DynamicProgramming;

public class DPMatrixChainMultiplication {
    public static int matrixChainMultiplication (final int[] arr) {
        final int size = arr.length;
        int[][] dp = new int[size][size];

        // Initialisation -- Base-case

        for (int i = 0; i < size; i++) {
            dp[i][i] = 0;
        }

        // Bottom-Up
        for (int len = 2; len <= size-1; len++) {
            for (int i = 1; i <= size - len; i++) {
                final int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k <= j-1; k++) {
                    final int cost1 = dp[i][k];
                    final int cost2 = dp[k+1][j];
                    final int cost3 = arr[i-1] * arr[k] * arr[j];

                    dp[i][j] = Math.min (dp[i][j], cost1 + cost2 + cost3);
                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return dp[1][size-1];
    }
    public static void main(String[] args) {
        final int[] arr = {1, 2, 3, 4, 3};

        System.out.println(matrixChainMultiplication(arr));
    }
}
