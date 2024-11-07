package javafxtictactoemarcgust;

public class Model {
    private char[][] board;
    private char currentPlayer;
    private boolean gameOver;
    private int playerXWins;
    private int playerOWins;
    private int draws;

    public Model() {
        board = new char[3][3];
        currentPlayer = 'X';
        gameOver = false;
        resetBoard();

        playerXWins = 0;
        playerOWins = 0;
        draws = 0;
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
        gameOver = false;
    }

    public boolean makeAMove(int xAxis, int yAxis) {
        if (board[xAxis][yAxis] == ' ' && !gameOver) {
            board[xAxis][yAxis] = currentPlayer;
            if (checkWin()) {
                gameOver = true;
                if (currentPlayer == 'X') {
                    playerXWins++;
                } else {
                    playerOWins++;
                }
            } else if (isBoardFull()) {
                gameOver = true;
                draws++;
            } else {
                switchPlayer();
            }
            return true;
        }
        return false;
    }

    public int getPlayerXWins() {
        return playerXWins;
    }

    public int getPlayerOWins() {
        return playerOWins;
    }

    public int getDraws() {
        return draws;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char getBoardSquare(int xAxis, int yAxis) {
        return board[xAxis][yAxis];
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return true;
            }
            if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return true;
            }
        }
        if (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return true;
        }
        if (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return true;
        }
        return false;
    }

    public boolean isDraw() {
        return isBoardFull() && !checkWin();
    }
}
