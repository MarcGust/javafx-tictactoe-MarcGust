module com.example.javafxtictactoemarcgust {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxtictactoemarcgust to javafx.fxml;
    exports com.example.javafxtictactoemarcgust;
    exports com.example.javafxtictactoemarcgust.controller;
    opens com.example.javafxtictactoemarcgust.controller to javafx.fxml;
}
