package com.example.supplychainpro;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

//import static jdk.internal.org.jline.utils.Colors.s;

public class login {

    private static byte[] getShA(String input){
        try{
            MessageDigest messageDigest= MessageDigest.getInstance("Sha-256");
            return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;


    }

    private static String getEncryptedPassword(String password){
      try {
          BigInteger number = new BigInteger(1, getShA(password));
          StringBuilder hexString= new StringBuilder(number.toString(16));
          return hexString.toString();
      }catch(Exception e){
          e.printStackTrace();
      }
      return null;
    }
public static boolean customerlogin (String email,String password){
 String query = String.format("SELECT * FROM customerinfo WHERE email= '%s' AND password= '%s' ",email,password);
 try{
     database_connection dbcon= new database_connection();
     ResultSet rs = dbcon.getQueryTable(query);
     if(rs!= null && rs.next()){
         return true;
     }
 }catch(Exception e){
     e.printStackTrace();
    }
 return false;
}

//    public static void main(String[] args) {
//        login Login= new login();
//        System.out.println(login.customerlogin("isapurkar1998@gmail.com", "1234@abc"));
//    }

    public static void main(String[] args) {
        login Login = new login();
        System.out.println(login.getEncryptedPassword("1234@abc"));
    }
}
