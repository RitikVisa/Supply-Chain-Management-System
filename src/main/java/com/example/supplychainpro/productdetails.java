package com.example.supplychainpro;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class productdetails {

    public static TableView <product> productTable;

    public Pane getAllProducts (){
        TableColumn id= new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name= new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price= new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//        ObservableList<product> data = FXCollections.observableArrayList();
//        data.add(new product(1,"amul corneto",35.00));
//        data.add(new product(1,"amul cassata",55.00));

        ObservableList<product> products= product.getAllproducts();
        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(supplychain.width,supplychain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane= new Pane();
        tablePane.setMinSize(supplychain.width,supplychain.height);
        tablePane.getChildren().addAll(productTable);
        return tablePane;


    }

    public static Pane getProductsByName(String productname){
        TableColumn id= new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn name= new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn price= new TableColumn("Price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

//        ObservableList<product> data = FXCollections.observableArrayList();
//        data.add(new product(1,"amul corneto",35.00));
//        data.add(new product(1,"amul cassata",55.00));

        ObservableList<product> products= product.getproductsByname(productname);
        productTable = new TableView<>();
        productTable.setItems(products);
        productTable.getColumns().addAll(id,name,price);
        productTable.setMinSize(supplychain.width,supplychain.height);
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Pane tablePane= new Pane();
        tablePane.setMinSize(supplychain.width,supplychain.height);
        tablePane.getChildren().addAll(productTable);
        return tablePane;


    }



    public static product getselectedproduct(){
        try{
          product  selectproduct= productTable.getSelectionModel().getSelectedItem();
          return selectproduct;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
