module com.example.javafxtictactoemarcgust {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    // Open the main package for JavaFX FXML processing
    opens javafxtictactoemarcgust to javafx.fxml;

    // Export the main package
    exports javafxtictactoemarcgust;
}
