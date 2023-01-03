package com.example.supplychainpro;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class product {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public int getId() {
        return id.get();
    }


    public String getName() {
        return name.get();
    }



    public double getPrice() {
        return price.get();
    }

    public static ObservableList<product> getAllproducts(){
        database_connection databaseConnection = new database_connection();
        ObservableList<product> productList= FXCollections.observableArrayList();
        String selectProducts = "select * from product";
        try{
            ResultSet rs= databaseConnection.getQueryTable(selectProducts);
            while(rs.next()){
                productList.add(
                        new product(
                                rs.getInt("productid"),
                                rs.getString("name"),
                                rs.getDouble("price")
                        )
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    public static ObservableList<product> getproductsByname(String pname){
        database_connection databaseConnection = new database_connection();
        ObservableList<product> productList= FXCollections.observableArrayList();
        String selectProducts = String.format("SELECT * FROM product WHERE lower(name) like '%%%s%%'",pname.toLowerCase());
        try{
            ResultSet rs= databaseConnection.getQueryTable(selectProducts);
            while(rs.next()){
                productList.add(
                        new product(
                                rs.getInt("productid"),
                                rs.getString("name"),
                                rs.getDouble("price")
                        )
                );
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return productList;
    }



    public product (int id,String name,Double price){
    this.id = new SimpleIntegerProperty(id);
    this.name = new SimpleStringProperty(name);
    this.price = new SimpleDoubleProperty(price);
    }

}
