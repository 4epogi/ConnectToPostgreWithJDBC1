package com.chepogi;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Properties prop = new Properties();

        System.out.println(System.getProperty("user.dir"));
       Path p = Paths.get("resources\\db.properties");
       System.out.println(p);
        try{
            try (InputStream fis = Files.newInputStream(p)) {
                     prop.load(fis);
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(prop.getProperty("jdbc.driver"));
            Class.forName(prop.getProperty("jdbc.driver"));
            //DriverManager.registerDriver(new org.sqlite.JDBC());
            Connection con = DriverManager.getConnection(prop.getProperty("jdbc.url"));
            System.out.println("connected");
            Statement stm = con.createStatement();
            ResultSet rezult = stm.executeQuery("SELECT id, username FROM users");
            while(rezult.next()){
                System.out.println(rezult.getInt("id"));
                System.out.println(rezult.getString(2));
            }
            rezult.close();
            stm.close();
            con.close();
        } catch (ClassNotFoundException e){
            System.out.println(e.getStackTrace());
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println( "Hello World!" );
    }
}
