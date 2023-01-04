package com.example.supplychainpro;

import java.sql.Statement;

public  class addmember {

//    public static int addAmember(String email_i,String pass,String firstN,String lastN){
//        String query= String.format("INSERT INTO customerinfo (password,first_name,last_name) VALUES (pass,firstN,lastN)");
//
//        database_connection db = new database_connection();
//
//        int statement= db.executeUpdateQuery(query);
//       return statement;
//    }
//

    public static int addAmember(String emailad,String pass,String firstN,String lastN,String mobilenum){
        database_connection databaseConnection= new database_connection();
        String query=String.format("INSERT INTO customerinfo(email,password,first_name,last_name,mobile) VALUES ('%s','%s','%s','%s','%s')",emailad,pass,firstN,lastN,mobilenum);

//        try{
//            databaseConnection.executeUpdateQuery(query);
//            return true;
//        }catch(Exception e){
//            e.printStackTrace();
//            return false;
//        }

        Statement statement= databaseConnection.getStatement();
        try{
            return statement.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;

    }

}
