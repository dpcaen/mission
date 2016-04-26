<%-- 
    Document   : erreur
    Created on : 12 janv. 2016, 08:47:44
    Author     : dp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <image src="images/logo.png" />
       <h3> 
           Désolé, une erreur est survenue. Veuillez contacter votre correspondant informatique   
           <P><H6>
                <%= exception %>
           </h6>
        </h3>   
    </body>
</html>
