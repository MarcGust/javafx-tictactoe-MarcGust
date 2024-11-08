package javafxtictactoemarcgust;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos; // Import for alignment
import javafx.geometry.Insets; // Import for padding

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxtictactoemarcgust/view.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();

        Label selectionLabel = new Label("Välj motståndare:");
        Button vsPlayerButton = new Button("Spelare mot Spelare");
        Button vsComputerButton = new Button("Spelare mot Dator");

        vsPlayerButton.setOnAction(event -> {
            controller.setOpponentType(false);
            primaryStage.setScene(new Scene(root, 400, 450));
            controller.initialize();
        });

        vsComputerButton.setOnAction(event -> {
            controller.setOpponentType(true);
            primaryStage.setScene(new Scene(root, 400, 450));
            controller.initialize();
        });

        VBox mainMenu = new VBox(15, selectionLabel, vsPlayerButton, vsComputerButton);
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setPadding(new Insets(20));

        Scene menuScene = new Scene(mainMenu, 400, 450);

        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
