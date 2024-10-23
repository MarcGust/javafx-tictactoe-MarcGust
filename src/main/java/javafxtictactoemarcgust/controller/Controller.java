package javafxtictactoemarcgust.controller;

import javafxtictactoemarcgust.model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Controller {
    @FXML
    private GridPane gridPane;
    @FXML
    private Button button00, button01, button02, button10, button11, button12, button20, button21, button22;
    @FXML
    private Button resetButton;

    private Model model;

    public Controller() {
        this.model = new Model();
    }

    private void settingTheBoard() {
        setupBoardSquare(button00, 0, 0);
        setupBoardSquare(button01, 0, 1);
        setupBoardSquare(button02, 0, 2);
        setupBoardSquare(button10, 1, 0);
        setupBoardSquare(button11, 1, 1);
        setupBoardSquare(button12, 1, 2);
        setupBoardSquare(button20, 2, 0);
        setupBoardSquare(button21, 2, 1);
        setupBoardSquare(button22, 2, 2);

        resetButton.setOnAction(e -> model.resetGame());
    }

    private void setupBoardSquare(Button button, int xAxis, int yAxis) {
        button.setOnAction(e -> clickButton(xAxis, yAxis, button));
    }

    private void clickButton(int xAxis, int yAxis, Button button) {
        if (model.makeAMove(xAxis, yAxis)) {
            button.setText(model.currentBoard(xAxis, yAxis));
            String winner = model.checkWinner();
            if (winner != null) {
                gameResult(winner.equals("Draw") ? "Oavgjort!" : winner + " vann!");
            }
        }
    }

    private void gameResult(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        resetGame();
    }

    private void resetGame() {
        model.resetGame();
    }
}
