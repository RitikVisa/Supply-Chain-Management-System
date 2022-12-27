module com.example.supplychainpro {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.supplychainpro to javafx.fxml;
    exports com.example.supplychainpro;
}