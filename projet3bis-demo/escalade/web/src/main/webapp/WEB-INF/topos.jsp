<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Topos</title>
</head>
<body>
  
     <ul>
     <p>Liste des Topos</p>
	    <c:forEach var="topo" items="${ topos }">
	    	<li><a href="<c:out value="${ topo.fichier }" />"><c:out value="${ topo.fichier }" /></a></li>
	    </c:forEach>
	</ul>   

</body>
</html>