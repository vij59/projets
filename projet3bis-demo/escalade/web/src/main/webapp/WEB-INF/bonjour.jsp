<%@ include file ="pages/header.jsp"  %>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Accueil</title>
		<link href="../css/background.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<%@ include file ="pages/navbar.jsp"  %>
		<br/><br/><br/>
		
		<div class="jumbotron">
						
			  <%@ include file ="menu.jsp"  %> 
		
		    <c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
			<p>Bonjour, voici la liste des utilisateurs</p>
		    <ul>
			    <c:forEach var="utilisateur" items="${ utilisateurs }">
			    	<li><c:out value="${ utilisateur.prenom }" /> <c:out value="${ utilisateur.nom }" /></li>
			    </c:forEach>
			</ul>    
		</div>
		
 <%@ include file ="pages/footer.jsp"  %>