<%@ page pageEncoding="UTF-8"%>
<%@ include file="pages/header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>Voies</title>
</head>
<body>

	<%@ include file="pages/navbar.jsp"%>
	<br />
	<br />
	<br />

	<style>
.header {
	color: #36A0FF;
	font-size: 27px;
	padding: 10px;
}

.bigicon {
	font-size: 35px;
	color: #36A0FF;
}
</style>
	<script>
		function stripspaces(input) {
			input.value = input.value.replace(/\s/gi, "");
			return true;
		}
	</script>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="well well-sm">
					<form class="form-horizontal" method="post" action="ajouterVoie">
						<fieldset>
							<legend class="text-center header">
								Site
								<c:out value="${ site.nomSite }" />
								--- Secteur
								<c:out value="${site.secteurs[numSecteur].nom}" />

							</legend>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-envelope-o bigicon"></i></span>
								<div class="col-md-6">
									<label for="nom_voie">Nom voie : </label> <input type="text"
										name="nom_voie" id="nom_voie" required
										placeholder="Nom de la voie" class="form-control"
										onkeydown="javascript:stripspaces(this)" /> <span
										class="erreur" style="color: red">${errorNom}</span>
								</div>
							</div>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-envelope-o bigicon"></i></span>
								<div class="col-md-6">
									<label for="nom_voie">Cotation : </label> <input type="text"
										name="cotation" id="cotation" required
										placeholder="Cotation de la voie" class="form-control"
										onkeydown="javascript:stripspaces(this)" /> <span
										class="erreur" style="color: red">${errorCotation}</span>
								</div>

							</div>

							<div class="form-group">
								<div class="col-md-12 text-center">
									<button type="submit" class="btn btn-primary"
										value="ajouter la voie">Ajouter Voie</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>


	<c:if test="${ affichage == 1}">

		<div class="container">
			<div class="col-md-12">
				<table class="table table-striped custab">
					<legend class="text-center header">
						Secteur
						<c:out value="${site.secteurs[numSecteur].nom}" />
					</legend>
					<thead>
						<tr>
							<th>Voie n°</th>
							<th>Nom voie</th>
							<th>Cotation</th>
							<th class="text-center">Action</th>
						</tr>
					</thead>
					<c:forEach var="voie" items="${ voies }">
						<tr>
							<td><c:out value="${ voie.id + 1}" /></td>
							<td><c:out value="${ voie.nom }" /></td>
							<td><c:out value="${ voie.cotation }" /></td>

							<td class="text-center"><a class='btn btn-info btn-xs'
								href="#"><span class="glyphicon glyphicon-edit"></span> Edit</a>
								<a href="#" class="btn btn-danger btn-xs"><span
									class="glyphicon glyphicon-remove"></span> Del</a></td>
						</tr>
					</c:forEach>
				</table>
				<div class="col-md-12 text-center">

					<br /> <br />
					<c:choose>
						<c:when test="${ secteursFinis == 0 }">
							<form method="post" action="ajouterVoie">
								<input type="text" hidden value="1" id="fini" name="fini">
								<input type="submit" value="passer au secteur suivant"
									class="btn btn-primary">
							</form>
						</c:when>

						<c:otherwise>
							<form method="post" action="ajouterVoie">
								<input type="text" hidden value="1" id="fini" name="fini">
								<input type="submit" value="passer aux longueurs"
									class="btn btn-primary">
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>

		<style>
.custab {
	border: 1px solid #ccc;
	padding: 5px;
	margin: 3% 0;
	box-shadow: 3px 3px 2px #ccc;
	transition: 0.5s;
}

.custab:hover {
	box-shadow: 3px 3px 0px transparent;
	transition: 0.5s;
}
</style>


	</c:if>


	<%@ include file="pages/footer.jsp"%>