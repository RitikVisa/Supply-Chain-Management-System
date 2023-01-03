package com.example.supplychainpro;

public class order {
    public static boolean placeOrder(String customeremail,product Product){
    database_connection databaseConnection= new database_connection();
    String query=String.format("INSERT INTO orders(cus_id,product_id) VALUES ((SELECT cid FROM customerinfo WHERE email= %s),%s)",customeremail,Product.getId());
    int rowCount=0;
    try{
    rowCount = databaseConnection.executeUpdateQuery(query);
    }catch(Exception e){
        e.printStackTrace();
    }
        return (rowCount!=0);
    }
}
