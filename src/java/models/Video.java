/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dbprocesos.Conexion;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author janel
 */
public class Video extends Conexion {
    public int id;
    public String titulo;
    public String descripcion;
    public String link;
    public String categoria;
    
    public static Video find(int id){
        Video video = null;
        try {
            ResultSet rs = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeQuery("select * from Videos where idvideos = "+ id + ";");
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            
            if (size == 0) {
                return null;
            }
            rs.next();
            
            video = parse_from_rs(rs);
        } catch (SQLException se){
            System.err.println(se.getMessage());
        }
        return video;
    }
    
    public static ArrayList<Video> where(String condition) {
        ArrayList<Video> videos = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            ResultSet.CONCUR_UPDATABLE).executeQuery("select * from videos where " + condition + ";");
            
            while(rs.next()){
                Video rs_video = parse_from_rs(rs);
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
    
    private static Video parse_from_rs(ResultSet rs) {
        Video video = null;
        try {
            video = new Video();
            video.id = rs.getInt("idvideos");
            video.titulo = rs.getString("titulo");
            video.descripcion = rs.getString("descripcion");
            video.link = rs.getString("link");
            video.categoria = rs.getString("categoria");
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        return video;
    } 
}
