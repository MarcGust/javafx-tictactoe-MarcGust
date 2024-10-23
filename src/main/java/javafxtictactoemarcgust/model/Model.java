package javafxtictactoemarcgust.model;

public class Model {
    private final String[][] board;
    private boolean playerXTurn;
    private int movesCount;

    public Model() {
        board = new String[3][3];
        resetGame();
    }

    public void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = " ";
            }
        }
        playerXTurn = true;
        movesCount = 0;
    }

    public boolean makeAMove(int xAxis, int yAxis) {
        if (board[xAxis][yAxis].isEmpty()) {
            board[xAxis][yAxis] = playerXTurn ? "X" : "O";
            movesCount++;
            playerXTurn = !playerXTurn;
            return true;
        }
        return false;
    }

    public String currentBoard(int xAxis, int yAxis) {
        return board[xAxis][yAxis];
    }

    public String checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (!board[i][0].isEmpty() && board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2])) {
                return board[i][0];
            }
            if (!board[0][i].isEmpty() && board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i])) {
                return board[0][i];
            }
            if (!board[0][0].isEmpty() && board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
                return board[0][0];
            }
            if (!board[0][2].isEmpty() && board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0])) {
                return board[0][2];
            }
            if (movesCount == 9) {
                return "Draw";
            }
        }
        return null;
    }
}
