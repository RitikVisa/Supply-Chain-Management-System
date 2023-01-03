module com.example.supplychainpro {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.supplychainpro to javafx.fxml;
    exports com.example.supplychainpro;
}