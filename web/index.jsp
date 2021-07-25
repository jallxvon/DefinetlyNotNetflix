<%-- 
    Document   : index
    Created on : 07/24/2021, 8:01:52 p. m.
    Author     : janel
--%>
<%@ include file="header.jsp"  %>
<%@page import="dbprocesos.Conexion"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Si Dios conmigo</title>
        <%
            Conexion db = new Conexion();
            
            String query = "select COUNT(*) from users;";
            ResultSet rs = db.getConnection().createStatement().executeQuery(query);


            int rowcount = 0;

            while (rs.next()){
                rowcount = rs.getInt(1);
            }
          
        %>
    </head>
    <body>
        <h1>El apache debe encender!</h1>
        <p><%= rowcount %></p>
    </body>
</html>

<%@ include file="footer.jsp"  %>