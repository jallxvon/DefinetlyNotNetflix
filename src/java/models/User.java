/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import dbprocesos.Conexion;
import static dbprocesos.Conexion.getConnection;
import java.sql.*;
/**
 *
 * @author janel
 */
public class User {
    public int id;
    public String username;
    public String password;
    public String email;
    
    public static User find(int id){
        User user = null;
        try {
            ResultSet rs = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeQuery("select * from Users where idusers = "+ id + ";");
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            
            if (size == 0) {
                return null;
            }
            rs.next();
            
            user = parse_from_rs(rs);
        } catch (SQLException se){
            System.err.println(se.getMessage());
        }
        return user;
    }
    
    private static User parse_from_rs(ResultSet rs) {
        User user = null;
        try {
            user = new User();
            user.id = rs.getInt("idusers");
            user.username = rs.getString("username");
            user.password = rs.getString("password");
            user.email = rs.getString("email");
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return user;
    }
}
