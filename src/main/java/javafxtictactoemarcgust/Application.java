package javafxtictactoemarcgust;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/javafxtictactoemarcgust/view.fxml"));
        Parent root = loader.load();

        // Get the controller from the FXML loader
        Controller controller = loader.getController();

        // Set up UI components like buttons for choosing opponent types
        Button vsPlayerButton = new Button("Play vs Player");
        Button vsAIButton = new Button("Play vs AI");
        Label selectionLabel = new Label("Choose your opponent:");

        // Actions for the buttons to set the opponent type
        vsPlayerButton.setOnAction(event -> {
            controller.setOpponentType(false); // Player vs Player
            primaryStage.setScene(new Scene(root, 400, 450)); // Switch to game scene
            controller.initialize(); // Re-initialize the controller
        });

        vsAIButton.setOnAction(event -> {
            controller.setOpponentType(true); // Player vs AI
            primaryStage.setScene(new Scene(root, 400, 450)); // Switch to game scene
            controller.initialize(); // Re-initialize the controller
        });

        // Creating a VBox layout for the main menu
        VBox mainMenu = new VBox(10, selectionLabel, vsPlayerButton, vsAIButton);
        Scene menuScene = new Scene(mainMenu, 300, 200);

        primaryStage.setTitle("Tic Tac Toe"); // Set window title
        primaryStage.setScene(menuScene); // Set initial scene
        primaryStage.show(); // Display the window
    }

    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }
}
