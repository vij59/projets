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
Commentez le topo 
<c:forEach var="topo" items="${ topos }">
<c:if test="${top == topo.idTopo }">
<c:out value="${ topo.nomTopo }" />
</c:if>
</c:forEach>


<form action ="commentaireTopo" method="post" id="usrform">

<textarea rows="4" cols="50" name="comment" form="usrform" placeholder="">
</textarea>
<input type="submit" value="Valider le commentaire">
</form>
<br/>



<table border="2px solid black">
<th>Utilisateur</th>
<th>Commentaire</th>
<th>Date</th>

<c:forEach var="commentaire" items="${ commentaires }">
<c:if test="${top == commentaire.idTopo }">
<c:forEach var="utilisateur" items="${ utilisateurs }">

<c:if test="${ utilisateur.id == commentaire.idUtilisateur }">
<tr border="2px solid black">
	<td><c:out value="${ utilisateur.nom }" /></td>
	<td><c:out value="${ commentaire.commentaire }" /></td> 
	<td><c:out value="${ commentaire.date }" /></td> 
</tr>
</c:if>

</c:forEach>
</c:if>
</c:forEach>
</table>

									
	

	<%@ include file="pages/footer.jsp"%>