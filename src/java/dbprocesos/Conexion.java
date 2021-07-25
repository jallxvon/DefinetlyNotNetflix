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
    private String username = "root";
    private String password = "admin";
    private String hostname = "localhost";
    private String port = "3306";
    private String database = "definetlynotnetflixdb";
    private String classname = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://"+hostname+":"+port+"/"+database;
    private Connection con;
    
    public Conexion(){
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
    }
    public Connection getConnection(){
        return con;
    }
}
