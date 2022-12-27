package com.example.supplychainpro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static final int width=700,height=600,headerBar=50;
    Pane bodyPane= new Pane();

    private GridPane headerBar(){
        TextField searchText= new TextField();
        Button searchButton= new Button("Search");

        GridPane gridPane = new GridPane();
       // gridPane.setStyle("-fx-background-color: #C0C0C0");
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);

        return gridPane;
    }

    private GridPane loginPage(){
        Label emailLabel= new Label("Email");
        Label passwordLabel= new Label("Password");
        Label trial= new Label("new Label FOR me");

        Button loginButton= new Button("Login");

        TextField emailTextField= new TextField("");
        PasswordField passwordField= new PasswordField();



        GridPane gridPane= new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());

        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(emailLabel,0,0);
        gridPane.add(emailTextField,1,0);
        gridPane.add(passwordLabel,0,1);
        gridPane.add(passwordField,1,1);
        gridPane.add(loginButton,0,2);


        return gridPane;


    }
    private Pane createContent(){


        Pane root= new Pane();
        root.setPrefSize(width,height+headerBar);
        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerBar);
        bodyPane.getChildren().addAll(loginPage());
        root.getChildren().addAll(headerBar(),bodyPane);

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
    //    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Supply Chain Management System Project");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}