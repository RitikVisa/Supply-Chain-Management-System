package com.example.supplychainpro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.paint.Color;

public class supplychain extends Application {









    public static final int width=700,height=500,headerBar=50;
    Pane bodyPane= new Pane();

  //  bodyPane.setStyle("-fx-background-color: black;");

    login Login= new login();
    productdetails Productdetails= new productdetails();


    Label customerEmailLabel = null;
    String customerEmail= null;
    Button signout= new Button("Sign-Out");

    Button addtocart= new Button("Add To Cart");
    Label messageLabel= new Label("");
    Button buyNow= new Button("Buy Now");

    Label welcomemsg= new Label("Welcome To the Store");



    private GridPane headerBar(){
        TextField searchText = new TextField();
        Button searchButton= new Button("Search");
        Button loginButton= new Button("Login");
        loginButton.setStyle("-fx-background-color: #00cc00");
        signout.setVisible(false);

        signout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                signout.setVisible(false);
                customerEmailLabel.setText("Welcome user");

                loginButton.setVisible(true);
                buyNow.setVisible(false);
                messageLabel.setVisible(false);
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(Welcomepage());
               // welcomemsg.setVisible(true);


            }
        });



        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
                loginButton.setVisible(false);
//                customerEmailLabel.setText("Welcome : " + customerEmail);
               // welcomemsg.setVisible(true);

            }
        });

        customerEmailLabel = new Label("Welcome User");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productname = searchText.getText();
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productdetails.getProductsByName(productname));
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #ff9933");
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(searchText,0,0);
        gridPane.add(searchButton,1,0);
        gridPane.add(loginButton,2,0);
        gridPane.add(customerEmailLabel,3,0);
        gridPane.add(signout,4,0);

        return gridPane;
    }

    private GridPane footerBar(){




        buyNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                product selectedproduct= productdetails.getselectedproduct();
                if(order.placeOrder(customerEmail,selectedproduct)){
                    messageLabel.setText("ordered");
                }else{
                    messageLabel.setText("order cancelleddd");
                }

            }
        });


        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #C0C0C0");
        gridPane.setVgap(5);
        gridPane.setHgap(20);
        gridPane.setMinSize(bodyPane.getMinWidth(),headerBar-10);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setTranslateY(headerBar+height+5);

        gridPane.add(addtocart,0,0);
        gridPane.add(buyNow,1,0);
        gridPane.add(messageLabel,2,0);






        return gridPane;
    }

    private GridPane loginPage(){
        Label emailLabel= new Label("Email");
        Label passwordLabel= new Label("Password");
        //Label trial= new Label("new Label FOR me");
        Label messageLabel = new Label("Not Logged In...");
        buyNow.setVisible(false);
        addtocart.setVisible(false);




        TextField emailTextField= new TextField("");
        PasswordField passwordField= new PasswordField();

        Button loginButton= new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {



                String email= emailTextField.getText();
              //  customerEmail=emailTextField.getText();

                String password= passwordField.getText();
             //   messageLabel.setText(email + " $$ " + password);
                if(login.customerlogin(email,password)){
                    buyNow.setVisible(true);
                    addtocart.setVisible(true);
                    bodyPane.getChildren().addAll(Productdetails.getAllProducts());

                    messageLabel.setText("Login Successful");
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productdetails.productTable);
                    customerEmailLabel.setText("Welcome : " + email);
                    signout.setVisible(true);

                }else{
                    messageLabel.setText("Login Failed");
                }


            }
        });

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
        gridPane.add(messageLabel,1,2);


        return gridPane;


    }

    public GridPane Welcomepage(){

 GridPane gridPane= new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setMinSize(bodyPane.getMinWidth(),bodyPane.getMinHeight());

        gridPane.setAlignment(Pos.CENTER);
       // welcomemsg.setVisible(true);
        buyNow.setVisible(false);
        addtocart.setVisible(false);
        gridPane.getChildren().add(welcomemsg);

        return gridPane;


    }


    private Pane createContent(){




        Pane root= new Pane();
        root.setStyle("-fx-background-color: #ffcc99;");
        root.setPrefSize(width,height+ 2*headerBar);
        bodyPane.setMinSize(width,height);
        bodyPane.setTranslateY(headerBar);

        bodyPane.getChildren().add(Welcomepage());



        root.getChildren().addAll(headerBar(),bodyPane,footerBar());

           // bodyPane.getChildren().addAll(Productdetails.getAllProducts());



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

//    public static void main(String[] args) {
//
//        launch();
//    }
}