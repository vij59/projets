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

	<form action="ajouterTopo" method="post">
	<p>Correspond au site :</p>
		<select id="idSite" name="idSite">
			<c:forEach var="site" items="${ sites }">
				<option value="<c:out value="${site.id}"/>">
					<td><c:out value="${ site.nomSite }" /> / <c:out
							value="${ site.pays }" /> / <c:out value="${ site.region }" />
				</option>
			</c:forEach>
		</select> <br /><br /> Nom du topo : <input type="text" name="nom_topo"
			id="nom_topo"><br /><br /><input type="file"
			name="fichier" id="fichier" value="Ajouter fichier"><br /> <input type="submit" value ="Ajouter topo">
	</form>
	 </c:when>
          <c:otherwise>
          <a href="betaConn">Connectez-vous d'abord</a>
           </c:otherwise>
        
        </c:choose>
	

	<%@ include file="pages/footer.jsp"%>