package com.example.javafxtictactoemarcgust.model;

public class Model {
    private final String[][] board;
    private boolean playerXTurn;

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
    }

    public boolean makeAMove(int xAxis, int yAxis) {
        if (board[xAxis][yAxis].isEmpty()) {
            board[xAxis][yAxis] = playerXTurn ? "X" : "O";
            playerXTurn = !playerXTurn;
            return true;
        }
        return false;
    }
    public String[][] getBoard() {
        return board;
    }
}
