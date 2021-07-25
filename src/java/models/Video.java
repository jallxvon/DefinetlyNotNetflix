/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dbprocesos.Conexion;
import java.sql.*;

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
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            // The column count starts from 1
            for (int i = 1; i <= columnCount; i++ ) {
              String name = rsmd.getColumnName(i);
              System.out.println(name);
              // Do stuff with name
            }
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            
            if (size == 0) {
                return null;
            }
            rs.next();
            video = new Video();
            video.id = rs.getInt("idvideos");
            video.titulo = rs.getString("titulo");
            video.descripcion = rs.getString("descripcion");
            video.link = rs.getString("link");
            video.categoria = rs.getString("categoria");
        } catch (SQLException se){
            System.err.println(se.getMessage());
        }
        return video;
    }
}
