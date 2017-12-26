<%@ page pageEncoding="UTF-8" %>
<%@ include file="pages/header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>Secteurs</title>
</head>
<body>

	<%@ include file="pages/navbar.jsp"%>
	<br />
	<br />
	<br />
	<c:choose>

		<c:when test="${ !empty form.resultat }">
			<p>
				<c:out value="Bonjour ${ form.resultat }" />
			</p>
		</c:when>

		<c:otherwise>

			<form method="post" action="ajouterSecteur">

				<label for="nom_secteur">nom du secteur : </label> <input
					type="text" name="nom_secteur" id="nom_secteur" required /><br /> <input
					type="submit" />
			</form>
		</c:otherwise>
	</c:choose>


	<c:out value="${ site.nomSite }" />

	<p>Liste des secteurs</p>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<thead>
						<tr>
							<th>secteur n°</th>
							<th>Nom secteur</th>
						</tr>
					</thead>
					<c:forEach var="secteur" items="${ secteurs }">
						<tbody>
							<tr>
								<td><c:out value="${ secteur.id + 1}" /></td>
								<td><c:out value="${ secteur.nom }" /></td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<a href="ajouterVoie">Ajouter Voies</a>

	<td><%@ include file="pages/footer.jsp"%>