package javafxtictactoemarcgust;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Controller {
    @FXML
    private GridPane gridPane;
    @FXML
    private Label statusLabel;
    @FXML
    private Button restartButton;

    @FXML
    private Button button00, button01, button02, button10, button11, button12, button20, button21, button22;

    private Model model;
    private boolean isVsAI;
    private Random random;

    public void initialize() {
        model = new Model();
        random = new Random();

        // Set up button actions
        setButtonAction(button00, 0, 0);
        setButtonAction(button01, 0, 1);
        setButtonAction(button02, 0, 2);
        setButtonAction(button10, 1, 0);
        setButtonAction(button11, 1, 1);
        setButtonAction(button12, 1, 2);
        setButtonAction(button20, 2, 0);
        setButtonAction(button21, 2, 1);
        setButtonAction(button22, 2, 2);

        restartButton.setOnAction(event -> handleRestart());
    }

    public void setOpponentType(boolean vsAI) {
        this.isVsAI = vsAI;
    }

    private void setButtonAction(Button button, int row, int col) {
        button.setOnAction(event -> handleButtonPress(row, col, button));
    }

    private void handleButtonPress(int row, int col, Button button) {
        if (model.makeMove(row, col)) {
            updateBoard();
            if (model.isGameOver()) {
                statusLabel.setText("Player " + model.getCurrentPlayer() + " wins!");
            } else if (isVsAI && model.getCurrentPlayer() == 'O') {
                aiMove();
            } else {
                statusLabel.setText("Player " + model.getCurrentPlayer() + "'s turn");
            }
        }
    }

    private void aiMove() {
        Platform.runLater(() -> {
            int row, col;
            do {
                row = random.nextInt(3);
                col = random.nextInt(3);
            } while (!model.makeMove(row, col));

            updateBoard();
            if (model.isGameOver()) {
                statusLabel.setText("Player " + model.getCurrentPlayer() + " wins!");
            } else {
                statusLabel.setText("Player " + model.getCurrentPlayer() + "'s turn");
            }
        });
    }

    private void updateBoard() {
        button00.setText(String.valueOf(model.getCell(0, 0)));
        button01.setText(String.valueOf(model.getCell(0, 1)));
        button02.setText(String.valueOf(model.getCell(0, 2)));
        button10.setText(String.valueOf(model.getCell(1, 0)));
        button11.setText(String.valueOf(model.getCell(1, 1)));
        button12.setText(String.valueOf(model.getCell(1, 2)));
        button20.setText(String.valueOf(model.getCell(2, 0)));
        button21.setText(String.valueOf(model.getCell(2, 1)));
        button22.setText(String.valueOf(model.getCell(2, 2)));
    }

    private void handleRestart() {
        model.resetBoard();
        updateBoard();
        statusLabel.setText("Player X's turn");
    }
}
