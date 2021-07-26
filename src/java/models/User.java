/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import dbprocesos.Conexion;
import static dbprocesos.Conexion.getConnection;
import java.sql.*;
import java.util.ArrayList;
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
    
    public static User find_by_credentials(String email, String password){
        User user = null;
        try {
            ResultSet rs = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeQuery(String.format("select * from Users where username = '%s' && password = '%s';", email, password));
            
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            
            if (size == 0) {
                return null;
            }
            rs.next();
            
            user = parse_from_rs(rs);
            System.out.println("Usuario '" + user.username + "' encontrado");
        } catch (SQLException se){
            System.err.println(se.getMessage());
        }
        return user;
    }
    
    public boolean create_favorito(Video video){
        boolean created = false;
        try {
            getConnection()
                .createStatement()
                .executeUpdate(String.format("INSERT INTO favorites (idvideo, iduser) values ('%s', '%s');", video.id, this.id));
            created = true;
            System.out.println("Favorito creado");
        } catch (SQLException se){
            System.err.println("ERROR AL CREAR FAVORITO");
            System.err.println(se.getMessage());
        }
        return created;
    }
    
    public ArrayList<Video> getFavorites() {
        ArrayList<Video> videos = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeQuery(String.format("SELECT videos.* FROM videos INNER JOIN favorites ON videos.idvideos = favorites.idvideo WHERE iduser = %s;", this.id));
            
            while(rs.next()){
                Video rs_video = Video.parse_from_rs(rs);
                videos.add(rs_video);
                if (rs_video != null ){
                    System.out.println(rs_video.titulo);
                } else {
                    System.out.println("rs_video es nulo, no se encontraron videos");
                }
            }
            System.out.println("Size videos: "+ videos.size());
            
        } catch (SQLException se){
            System.err.println(se.getMessage());
        }
        return videos;
    }
    
    public boolean save() {
        boolean saved = false;
         if (this.id == 0){
            try {
                getConnection()
                    .createStatement()
                    .executeUpdate(String.format("INSERT INTO Users (username, password, email) VALUES ('%s','%s','%s');", this.username, this.password, this.email));
           
                saved = true;
            } catch (SQLException se){
                System.err.println("ON INSERT USER ERROR");
                System.err.println(se.getMessage());
            }
         } else {
             try {
                getConnection()
                    .createStatement()
                    .executeUpdate(String.format("UPDATE Users SET username = '%s', password = '%s', email = '%s' WHERE idvideos = %s;", this.username, this.password, this.email, this.id));
           
                saved = true;
            } catch (SQLException se){
                System.err.println("ON UPDATE USER ERROR");
                System.err.println(se.getMessage());
            }
         }
        
        return saved;
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
