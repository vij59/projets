<%@ page pageEncoding="UTF-8"%>
<%@ include file="pages/header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>Topos</title>
</head>
<body>

	<%@ include file="pages/navbar.jsp"%>
	<br />
	<br />
	<br />

<c:choose>
			<c:when test="${sessionUtilisateur.role == 1}">
<p>Bonjour Monsieur <c:out value="${ sessionUtilisateur.nom }" /></p>
<p>Bonjour Monsieur <c:out value="${ sessionUtilisateur.id }" /></p>

	<form action="reserverTopo" method ="post">
	
	<input type="text" value ="<c:out value="${ topo.nomTopo }" />" name="nom_topo" id="nom_topo" hidden> <c:out value="${ topo.nomTopo }" />
	<input type="text" value ="<c:out value="${ topo.fichier }" />" name="fichier" id="fichier" hidden> <c:out value="${ topo.fichier }" />
	<input type="text" value ="<c:out value="${ topo.idSite }" />" name="id_site" id="id_site" hidden> 
	<input type="text" value ="<c:out value="${ topo.idTopo }" />" name="id_topo" id="id_topo" hidden> 
	<input type="text" value ="<c:out value="${ topo.disponible }" />" name="dispo" id="dispo" hidden> <c:out value="${ topo.disponible }" />
	
	
	<input type="text" hidden value="1" id="fini" name="fini">
	<input type="submit" value ="valider" >
	</form>

<c:forEach var="reservation" items="${ reservations }">
<br/><ul>
	<li>le topo numéro : <c:out value="${ reservation.idTopo }" />
	l'utilisateur numéro : <c:out value="${ reservation.idUtilisateur }" />
	date emprunt :<c:out value="${ reservation.dateEmprunt }" /> </li>
<br/></ul>
</c:forEach>
	
	
		</c:when>
		<c:otherwise>
		<a href="betaConn">Connectez-vous d'abord</a>
		</c:otherwise>
</c:choose>


	<%@ include file="pages/footer.jsp"%>