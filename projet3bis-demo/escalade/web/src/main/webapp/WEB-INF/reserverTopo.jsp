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
			<p>
				Bonjour Monsieur
				<c:out value="${ sessionUtilisateur.nom }" />
				,

			</p>


			<form action="reserverTopo" method="post">

				<input type="text" value="<c:out value="${ topo.nomTopo }" />"
					name="nom_topo" id="nom_topo" hidden> <input type="text"
					value="<c:out value="${ topo.fichier }" />" name="fichier"
					id="fichier" hidden> <input type="text"
					value="<c:out value="${ topo.idSite }" />" name="id_site"
					id="id_site" hidden> <input type="text"
					value="<c:out value="${ topo.idTopo }" />" name="id_topo"
					id="id_topo" hidden> <input type="text"
					value="<c:out value="${ topo.disponible }" />" name="dispo"
					id="dispo" hidden>


				<c:choose>
					<c:when test="${ topo.disponible == true}">
						<p>
							vous souhaitez réserver le topo
							<c:out value="${ topo.nomTopo }" />
							? <input type="text" hidden value="1" id="fini" name="fini">
							<button type="submit" value="Oui" class="btn btn-success">Oui</button>
							<a href="topos" class="btn btn-danger">Non</a>
			</form>

			</p>
		</c:when>
		<c:otherwise>
			<a href="topos" class="btn btn-info">Liste des topos</a>
		</c:otherwise>
	</c:choose>




	</c:when>
	<c:otherwise>
		<a href="betaConn">Connectez-vous d'abord</a>
	</c:otherwise>
	</c:choose>


	<%@ include file="pages/footer.jsp"%>