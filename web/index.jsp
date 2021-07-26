<%-- 
    Document   : index
    Created on : 07/24/2021, 8:01:52 p. m.
    Author     : janel
--%>
<%@ include file="header.jsp"  %>
<%@page import="dbprocesos.Conexion"%>
<%@page import="models.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Si Dios conmigo</title>
        <%
            ArrayList<Video> videos = Video.where("categoria = 'serie'");
            Video fav_video = Video.find(7);
            User current_user = User.find_by_credentials("Akatsuki507","123456789");
            ArrayList<Video> favorites = current_user.getFavorites();
            //current_user.create_favorito(fav_video);
            
            User new_user = new User();
            new_user.email = "jane@gmail.com";
            new_user.username = "Trapito12";
            new_user.password = "12345";
            new_user.save();
        %>
    </head>
    <body>
        <h1>El apache debe encender!</h1>
        <h2><%= current_user.username %></h2>
        <ul>
            <% for (Video video : videos) { %>
                <li><%= video.id %> - <%= video.titulo %></li>
            <% } %>
        </ul>
        
        <h2>Favorites of <%= current_user.username %></h2>
        <ul>
            <% for (Video video : favorites) { %>
                <li><%= video.id %> - <%= video.titulo %></li>
            <% } %>
        </ul>
    </body>
</html>

<%@ include file="footer.jsp"  %>