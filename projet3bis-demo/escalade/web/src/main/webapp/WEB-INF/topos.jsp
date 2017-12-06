<%@ include file ="pages/header.jsp"  %>
<html>
<head>
<meta charset="utf-8" />
<title>Topos</title>
</head>
<body>

<%@ include file ="pages/navbar.jsp"  %>
<br/><br/><br/>
  
     <ul>
     <p>Liste des Topos</p>
	    <c:forEach var="topo" items="${ topos }">
	    	<li><a href="<c:out value="${ topo.fichier }" />"><c:out value="${ topo.fichier }" /></a></li>
	    </c:forEach>
	</ul>   

 <%@ include file ="pages/footer.jsp"  %>