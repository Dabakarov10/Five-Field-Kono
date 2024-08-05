public class FFV_game {
    int movesCounter = 0;
    public char[][] board;
    private int turn = 1; // 1 == w , 0 == b

    public FFV_game() {
        board = new char[5][5];
        /*אתחול חיילים לבנים בלוח*/
        for (int i = 0; i < 5; i++)
            board[0][i] = 'w';
        board[1][0] = 'w';
        board[1][4] = 'w';
        /*אתחול חיילים שחורים בלוח*/
        for (int i = 0; i < 5; i++)
            board[4][i] = 'b';
        board[3][0] = 'b';
        board[3][4] = 'b';
    }


    public void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < board.length; i++)
            System.out.print(i + " ");
        System.out.println();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (j == 0)
                    System.out.print(i + " ");

                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("movesCounter: " + movesCounter);
    }

    public boolean MakeMove(int from_i, int from_j, int to_i, int to_j) {
        try {
            if (is_my_turn(from_i, from_j)) {
                if (board[to_i][to_j] != 0)
                    return false;
                else if (Check_slant(from_i, from_j, to_i, to_j) == true) {
                    board[to_i][to_j] = board[from_i][from_j];
                    board[from_i][from_j] = 0;
                    movesCounter++;
                    turn++;
                    if (Victory())
                        System.out.println("!!!!win!!!!");
                    return true;
                }
            } else {
                System.out.println("is not your turn");
            }
        } catch (Exception e) {
            System.out.print("ERROR__MakeMove()");
        }
        return false;
    }

    private boolean Check_slant(int from_i, int from_j, int to_i, int to_j) {
        try {
            if (from_i == to_i - 1 && from_j == to_j - 1) return true;
            if (from_i == to_i + 1 && from_j == to_j + 1) return true;
            if (from_i == to_i - 1 && from_j == to_j + 1) return true;
            if (from_i == to_i + 1 && from_j == to_j - 1) return true;
            return false;
        } catch (Exception e) {
            System.out.print("ERROR__Check_slant()");
        }
        return false;
    }

    boolean Victory() {
        boolean win_W = true, win_B = true;
        try {
            for (int i = 0; i < 5; i++)
                if (board[0][i] != 'b')
                    win_B = false;
            if ((board[1][0] != 'b') || (board[1][4] != 'b'))
                win_B = false;

            for (int i = 0; i < 5; i++)
                if (board[4][i] != 'w')
                    win_W = false;
            if ((board[3][0] != 'w') || (board[3][4] != 'w'))
                win_W = false;
        } catch (Exception e) {
            System.out.print("ERROR__Victory()");
        }
        if (win_W || win_B)
            return true;
        return false;
    }

    public void cheatBWin() {
        for (int i = 0; i < 5; i++)
            board[0][i] = 'b';
        board[1][0] = 'b';
        board[2][3] = 'b';
        board[1][4] = 0;
    }

    public void whose_turn() {
        // 1 == w , 0 == b
        if (turn % 2 == 1)
            System.out.println("white turn");
        else
            System.out.println("black turn");
    }

    private boolean is_my_turn(int from_i, int from_j) {
        // 1 == w , 0 == b
        if (turn % 2 == 1 && board[from_i][from_j] == 'w')
            return true;
        if (turn % 2 == 0 && board[from_i][from_j] == 'b')
            return true;
        else return false;
    }
}
