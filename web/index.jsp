<%-- 
    Document   : index
    Created on : 07/24/2021, 8:01:52 p. m.
    Author     : janel
--%>
<%@ include file="header.jsp"  %>
<%@page import="dbprocesos.Conexion"%>
<%@page import="models.Video"%>
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
        %>
    </head>
    <body>
        <h1>El apache debe encender!</h1>
        
        <ul>
            <% for (Video video : videos) { %>
                <li><%= video.titulo %></li>
            <% } %>
        </ul>
    </body>
</html>

<%@ include file="footer.jsp"  %>