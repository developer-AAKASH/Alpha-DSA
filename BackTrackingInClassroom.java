public class BackTrackingInClassroom {
    public static void permutations (final String str, final String answer) {
        final int length = str.length();
        if (length == 0) {
            System.out.println(answer);
            return;
        }

        // Enumaration type of problem.
        for (int i = 0; i < length; i++) {
            final char currentChar = str.charAt(i);
            final String futureString = str.substring(0, i) + str.substring(i+1, length);
            permutations (futureString, answer + currentChar);
        }
    }
    public static boolean isQueenThere (final int row, final int col, final Character[][] board) {
        if (board[row][col] == 'Q') {
            return true;
        }

        return false;
    }
    public static boolean isValidCell (final int row, final int col, final Character[][] board) {
        final int rowN = board.length, colN = board[0].length;
        // Checking its within board Boundries
        if (row >= rowN || col >= colN || board[row][col] == 'Q') {
            return false;
        }

        // Check Horizontally -- Right
        for (int c = col + 1; c < colN; c++) {
            if (board[row][c] == 'Q') {
                return false;
            }
        }

        // Check Horizontally -- Left
        for (int c = col - 1; c >= 0; c--) {
            if (board[row][c] == 'Q') {
                return false;
            }
        }

        // Check Vertically -- Up
        for (int r = row - 1; r >= 0; r--) {
            if (board[r][col] == 'Q') {
                return false;
            }
        }

        // Check Vertically -- Down
        for (int r = row + 1; r < rowN; r++) {
            if (board[r][col] == 'Q') {
                return false;
            }
        }

        // Check Left Diagonal -- Up
        for (int r = row-1, c = col-1; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Check Left Diagonal -- Down
        for (int r = row + 1, c = col - 1; r < rowN && c >= 0; r++, c--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Check Right Diagonal -- Up
        for (int r = row + 1, c = col + 1; r < rowN && c < colN; r++, c++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Check Right Diagonal -- Down
        for (int r = row - 1, c = col + 1; r >= 0 && c < colN; r--, c++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true;
    }
    public static void nQueenHelper (final int currentIndex, final int n, Character[][] board) {
        if (currentIndex == n) {
            printBoard (board);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValidCell(currentIndex, col, board)) {
                board[currentIndex][col] = 'Q';
                nQueenHelper(currentIndex + 1, n, board);
                board[currentIndex][col] = '-';
            }
        }
    }
    public static void printBoard (final Character[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void nQueens (final int n) {
        Character[][] board = new Character[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                board[row][col] = '-';
            }
        }

        nQueenHelper (0, n, board);
    }
    public static void main(String[] args) {
//        permutations("abc", "");
        nQueens(5);
    }
}
