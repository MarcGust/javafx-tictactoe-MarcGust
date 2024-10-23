module com.example.javafxtictactoemarcgust {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens javafxtictactoemarcgust to javafx.fxml;
    exports javafxtictactoemarcgust;
    exports javafxtictactoemarcgust.controller;
    opens javafxtictactoemarcgust.controller to javafx.fxml;
}
