<%-- 
    Document   : VueMain
    Created on : 7 nov. 2018, 13:38:08
    Author     : Diego
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Saisie d'un tableau de remise </title>
    </head>
    <body>

        <form method='GET'>
            Code : <input name="code" size="1" maxlength="1" pattern="[A-Z]{1}" title="Une lettre en MAJUSCULES"><br/>
            Taux : <input name="taux" type="number" step="0.01" min="0.0" max="100" size="5"><br/>
            <input  type="hidden" name="action" value="ADD">

            <input type="submit" value="Ajouter">
        </form>

        <table border=2>
            <tr> <th>Id</th> <th>Name</th> <th>Supprimer</th></tr>

            <c:forEach items="${remises}" var="element"> 
                <tr> 
                    <td>${element.code}</td> 
                    <td> <fmt:formatNumber minIntegerDigits="2" minFractionDigits="2" maxFractionDigits="2" value="${element.taux}"/> % </td> 
                    <td><a href="?action=DELETE&code=${element.code}" >delete</a></td>
                    
                </tr>
            </c:forEach>
        </table>
        <div><h4>${erreur}</h4></div>


    </body>
</html>