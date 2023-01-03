
package com.example.supplychainpro;
import java.sql.*;
public class database_connection{
    private static final String database_ur="jdbc:mysql://localhost:3306/supply_chain_management";
    private static final String username="root";
    private static final String password="9096";

    public Statement getStatement(){
    Statement statement = null;
    Connection conn;
    try{
        conn= DriverManager.getConnection(database_ur,username,password);
        statement=conn.createStatement();

    }catch(Exception e){
        e.printStackTrace();
    }
    return statement;
    }

    public ResultSet getQueryTable(String query){
        Statement statement=getStatement();
        try{
            return statement.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int executeUpdateQuery(String query){
        Statement statement=getStatement();
        try{
            return statement.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

//    public static void main(String[] args) {
//        database_connection databaseConnection= new database_connection();
//        ResultSet rs= databaseConnection.getQueryTable("select email, password FROM customerinfo");
//        try{
//            while(rs.next()){
//                System.out.println(rs.getString("email") + " " + rs.getString("password"));
//
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }


}
