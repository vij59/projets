<%@ page pageEncoding="UTF-8"%>
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
					<form class="form-horizontal" method="post" action="ajouterSecteur">
						<fieldset>
							<legend class="text-center header">
								Secteurs du site
								<c:out value="${ site.nomSite }" />
							</legend>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-envelope-o bigicon"></i></span>
								<div class="col-md-6">
									<label for="nom_secteur">Nom du secteur : </label> <input
										type="text" name="nom_secteur" id="nom_secteur" required
										placeholder="Nom du secteur" class="form-control"
										onkeydown="javascript:stripspaces(this)" /> <span
										class="erreur" style="color: red">${errorNom}</span>

								</div>
							</div>

							<div class="form-group">
								<div class="col-md-12 text-center">
									<button type="submit" class="btn btn-primary"
										value="ajouter secteur">Ajouter secteur</button>
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
					<thead>
						<tr>
							<th>N°</th>
							<th>Nom secteur</th>
							<th></th>
							<th class="text-center">Action</th>
						</tr>
					</thead>
					<c:forEach var="secteur" items="${ secteurs }">
						<tr>
							<td><c:out value="${ secteur.id + 1}" /></td>
							<td><c:out value="${ secteur.nom }" /></td>
							<td></td>
							<form method="post" action="SupprimerSecteur">
								<td class="text-center"><a class='btn btn-info btn-xs'
									href="#"><span class="glyphicon glyphicon-edit"></span>
										Edit</a> <a href="#" class="btn btn-danger btn-xs"><span
										class="glyphicon glyphicon-remove"></span> Del</a></td>
							</form>
						</tr>
					</c:forEach>
				</table>
				<div class="col-md-12 text-center">
					<a href="ajouterVoie" class="btn btn-primary">Ajouter Voies</a> <br />
					<br />
					<form method="post" action="ajouterSecteur">
						<input type="text" value="1" name="reponse" id="reponse" hidden>
						<input type="submit" name="annuler" value="vider les secteurs"
							id="annuler" class="btn btn-primary"
							style="background-color: red" />
					</form>
				</div>
			</div>
		</div>



	</c:if>
	<style>
.custab {
	border: 1px solid #ccc;
	padding: 5px;
	margin: 3% 0;
	box-shadow: 3px 3px 2px #ccc;
	transition: 0.5s;
	box-shadow: 3px 3px 2px #ccc;
}

.custab:hover {
	box-shadow: 3px 3px 0px transparent;
	transition: 0.5s;
}

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



	<%@ include file="pages/footer.jsp"%>