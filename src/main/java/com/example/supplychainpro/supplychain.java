package com.example.supplychainpro;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


import java.io.IOException;

import javafx.scene.paint.Color;

public class supplychain extends Application {


    public static final int width = 700, height = 500, headerBar = 50;



    Pane bodyPane = new Pane();

    //  bodyPane.setStyle("-fx-background-color: black;");

    login Login = new login();
    productdetails Productdetails = new productdetails();


    Label customerEmailLabel = null;
    String customerEmail = null;
    Button signout = new Button("Sign-Out");

    Button signUp = new Button("Sign UP");
    Label messageLabel = new Label("");
    Button buyNow = new Button("Buy Now");

    Label welcomemsg = new Label("Welcome To the Store");

    Label reg_firstlabel= new Label("First Name");
    Label reg_lastlabel= new Label("Last Name");
    Label reg_emaillabel= new Label("Email");
    Label reg_passwordlabel= new Label("Password");
    Label reg_moblabel= new Label("Mobile Number");

    TextField firstName= new TextField("");
    TextField lastName= new TextField("");
    TextField emailid= new TextField("");
    TextField cuspassword= new TextField("");
    TextField mob= new TextField("");

    Button register = new Button("Register Now");
    Button home= new Button("Home");
    Hyperlink welcomesignup= new Hyperlink("Not a memeber ? Click here to register");
    Hyperlink welcomelogin= new Hyperlink("Already a memeber ? Click here to Login");




    private GridPane headerBar() {
        TextField searchText = new TextField();
        Button searchButton = new Button("Search");
        Button loginButton = new Button("Login");
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

        customerEmailLabel = new Label("");

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String productname = searchText.getText();
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(productdetails.getProductsByName(productname));
            }
        });

        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(Welcomepage());
                signUp.setVisible(true);
            }
        });

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #ff9933");
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setMinSize(bodyPane.getMinWidth(), headerBar - 10);

        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(home,0,0);
        gridPane.add(searchText, 1, 0);
        gridPane.add(searchButton, 2, 0);
        gridPane.add(customerEmailLabel, 3, 0);

        gridPane.add(signout, 5, 0);
        gridPane.add(loginButton, 6, 0);
        gridPane.add(signUp, 7, 0);

        signUp.setStyle("-fx-background-color: #00cc00");
        signUp.setVisible(true);




        return gridPane;
    }

    private GridPane footerBar() {

        buyNow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                product selectedproduct = productdetails.getselectedproduct();
                if (order.placeOrder(customerEmail, selectedproduct)) {
                    messageLabel.setText("ordered");
                } else {
                    messageLabel.setText("order cancelleddd");
                }

            }
        });

        signUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signup());
            }
        });


        GridPane gridPane = new GridPane();
       // gridPane.setStyle("-fx-background-color: #C0C0C0");
        gridPane.setVgap(5);
        gridPane.setHgap(20);
        gridPane.setMinSize(bodyPane.getMinWidth(), headerBar - 10);

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setTranslateY(headerBar + height + 5);


        gridPane.add(buyNow, 0, 0);
        gridPane.add(messageLabel, 1, 0);


        return gridPane;
    }

    private GridPane loginPage() {

        //BUTTONS AND TEXTFIELDS

        Button loginButton = new Button("Login");
        loginButton.setVisible(true);

        TextField emailTextField = new TextField("");
        PasswordField passwordField = new PasswordField();



        Label emailLabel = new Label("Email");
        Label passwordLabel = new Label("Password");
        Label messageLabel = new Label("Please Enter Above Details To Login");
        buyNow.setVisible(false);
        signUp.setVisible(false);


        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


                String email = emailTextField.getText();
                //  customerEmail=emailTextField.getText();

                String password = passwordField.getText();
                //   messageLabel.setText(email + " $$ " + password);
                if (login.customerlogin(email, password)) {
                    buyNow.setVisible(true);
                    signUp.setVisible(true);
                    signUp.setVisible(false);
                    bodyPane.getChildren().addAll(Productdetails.getAllProducts());

                    messageLabel.setText("Login Successful");
                    bodyPane.getChildren().clear();
                    bodyPane.getChildren().add(productdetails.productTable);
                    customerEmailLabel.setText("Welcome : " + email);
                    signout.setVisible(true);

                } else {
                    messageLabel.setText("Login Failed");
                }


            }
        });

        //CREATING GRID PANE ALIGNMENT

        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setAlignment(Pos.CENTER);

        //ADDING BUTTONS TO THE GRIDPANE
        gridPane.add(emailLabel, 0, 0);
        gridPane.add(emailTextField, 1, 0);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 0, 2);
        gridPane.add(messageLabel, 1, 2);


        return gridPane;


    }

    public GridPane signup(){

        Label signupMessage= new Label("");
        Hyperlink loginlink= new Hyperlink("");
        loginlink.setVisited(false);


        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(reg_firstlabel,0,0);
        gridPane.add(reg_lastlabel,0,1);
        gridPane.add(reg_emaillabel,0,2);
        gridPane.add(reg_passwordlabel,0,3);
        gridPane.add(reg_moblabel,0,4);
        gridPane.add(register,0,5);
        gridPane.add(loginlink,1,5);


        gridPane.add(firstName,1,0);
        gridPane.add(lastName,1,1);
        gridPane.add(emailid,1,2);
        gridPane.add(cuspassword,1,3);
        gridPane.add(mob,1,4);

        loginlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });


        register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String firstN= firstName.getText();
                String lastN= lastName.getText();
                String email_i= emailid.getText();
                String pass= cuspassword.getText();
                String mobilenum = mob.getText();

                if(addmember.addAmember(email_i,pass,firstN,lastN,mobilenum)!=0){
                    loginlink.setText("Successfully registered - Go To Login Page..");
                }else{
                    loginlink.setText("You already have an account - Go To Login Page..");
                };

//                bodyPane.getChildren().clear();
//                bodyPane.getChildren().add(Welcomepage());
            }

        });




        return gridPane;

    }

    public GridPane Welcomepage() {

        //CREATING GRID PANE ALIGNMENT
        GridPane gridPane = new GridPane();
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        gridPane.setMinSize(bodyPane.getMinWidth(), bodyPane.getMinHeight());
        gridPane.setAlignment(Pos.CENTER);


        //HIDING FOOTER AND ADDING WELCOME MSG IN LANDING PAGE
        buyNow.setVisible(false);
        signUp.setVisible(false);


        welcomemsg.setStyle("-fx-text-fill: #ff9933");
        welcomemsg.setFont(Font.font("poppins", FontWeight.BOLD, FontPosture.ITALIC, 40));

        welcomesignup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(signup());
            }
        });

        welcomelogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bodyPane.getChildren().clear();
                bodyPane.getChildren().add(loginPage());
            }
        });
        gridPane.add(welcomemsg,0,0);
        gridPane.add(welcomesignup,0,1);
        gridPane.add(welcomelogin,0,2);



        return gridPane;


    }


    private Pane createContent() {


        Pane root = new Pane();
        root.setStyle("-fx-background-color: #ffcc99;");
        root.setPrefSize(width, height + 2 * headerBar);
        bodyPane.setMinSize(width, height);
        bodyPane.setTranslateY(headerBar);
       // Font font= new Font("Courier", Font.BOLD, 20);
        bodyPane.getChildren().add(Welcomepage());


        root.getChildren().addAll(headerBar(), bodyPane, footerBar());

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