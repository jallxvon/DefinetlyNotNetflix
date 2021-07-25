package dbprocesos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author janel
 */
public class Conexion {
    private static String username = "root";
    private static String password = "admin";
    private static String hostname = "localhost";
    private static String port = "3306";
    private static String database = "definetlynotnetflixdb";
    private static String classname = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
    private static Connection con;
    
    public static Connection getConnection(){
        con = null;
        try{
            Class.forName(classname);
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Coneccion exitosa");
        } catch(ClassNotFoundException cnfe){
            System.out.println("Error en la libreria mariadb");
            System.err.println(cnfe.getMessage());
        }
        catch(SQLException se){
            System.out.println("Error al conectarse a la db");
            System.err.println(se.getMessage());
        }
        return con;
    }
}
