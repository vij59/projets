<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>

	  <%@ include file ="menu.jsp"  %> 
    <%--  <%@ include file ="connexion.jsp"  %> --%>
   
 
   <body>
    <c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
	<p>Bonjour, voici la liste des utilisateurs</p>
    <ul>
	    <c:forEach var="utilisateur" items="${ utilisateurs }">
	    	<li><c:out value="${ utilisateur.prenom }" /> <c:out value="${ utilisateur.nom }" /></li>
	    </c:forEach>
	</ul>    
</body>
</html>