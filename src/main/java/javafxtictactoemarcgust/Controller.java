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
    private Label scoreLabelX;
    @FXML
    private Label scoreLabelO;
    @FXML
    private Label scoreLabelDraws;
    @FXML
    private Button restartButton;

    @FXML
    private Button button00, button01, button02, button10, button11, button12, button20, button21, button22;

    private Model model;
    private boolean isVsComputer;
    private Random random;

    public void initialize() {
        model = new Model();
        random = new Random();

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
        updateScoreDisplay();
    }

    public void setOpponentType(boolean vsComputer) {
        this.isVsComputer = vsComputer;
    }

    private void setButtonAction(Button button, int xAxis, int yAxis) {
        button.setOnAction(event -> handleButtonClick(xAxis, yAxis, button));
    }

    private void updateScoreDisplay() {
        scoreLabelX.setText("Spelare X: " + model.getPlayerXWins() + " vinster");
        scoreLabelO.setText("Spelare O: " + model.getPlayerOWins() + " vinster");
        scoreLabelDraws.setText("Oavgjort: " + model.getDraws() + " gånger");
    }

    private void handleButtonClick(int xAxis, int yAxis, Button button) {
        if (model.makeAMove(xAxis, yAxis)) {
            updateBoard();

            if (model.isGameOver()) {
                if (model.checkWin()) {
                    statusLabel.setText("Spelare " + model.getCurrentPlayer() + " vann!");
                } else if (model.isDraw()) {
                    statusLabel.setText("Oavgjort!");
                }
                updateScoreDisplay();
            } else if (isVsComputer && model.getCurrentPlayer() == 'O') {
                computerMove();
            } else {
                statusLabel.setText("Spelare " + model.getCurrentPlayer() + " tur");
            }
        }
    }

    private void computerMove() {
        Platform.runLater(() -> {
            int xAxis, yAxis;
            do {
                xAxis = random.nextInt(3);
                yAxis = random.nextInt(3);
            } while (!model.makeAMove(xAxis, yAxis));

            updateBoard();

            if (model.isGameOver()) {
                if (model.checkWin()) {
                    statusLabel.setText("Spelare " + model.getCurrentPlayer() + " vann!");
                } else if (model.isDraw()) {
                    statusLabel.setText("Oavgjort!");
                }
                updateScoreDisplay();
            } else {
                statusLabel.setText("Spelare " + model.getCurrentPlayer() + " tur");
            }
        });
    }

    private void updateBoard() {
        button00.setText(String.valueOf(model.getBoardSquare(0, 0)));
        button01.setText(String.valueOf(model.getBoardSquare(0, 1)));
        button02.setText(String.valueOf(model.getBoardSquare(0, 2)));
        button10.setText(String.valueOf(model.getBoardSquare(1, 0)));
        button11.setText(String.valueOf(model.getBoardSquare(1, 1)));
        button12.setText(String.valueOf(model.getBoardSquare(1, 2)));
        button20.setText(String.valueOf(model.getBoardSquare(2, 0)));
        button21.setText(String.valueOf(model.getBoardSquare(2, 1)));
        button22.setText(String.valueOf(model.getBoardSquare(2, 2)));
    }

    private void handleRestart() {
        model.resetBoard();
        updateBoard();
        statusLabel.setText("Spelare X tur");
    }
}
