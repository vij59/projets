<%@ page pageEncoding="UTF-8"%>
<%@ include file="pages/header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>Sites</title>
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
	<c:choose>
		<c:when test="${sessionUtilisateur.role == 1}">
			<c:if test="${ site.id != 1}">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="well well-sm">
								<form class="form-horizontal" method="post" action="ajouterSite">
									<fieldset>
										<legend class="text-center header">Site</legend>

										<div class="form-group">
											<span class="col-md-1 col-md-offset-2 text-center"><i
												class="fa fa-envelope-o bigicon"></i></span>
											<div class="col-md-6">
												<label for="nom_site">Nom du site : </label> <input
													type="text" name="nom_site" id="nom_site" required
													placeholder="Nom du site" class="form-control"
													onkeydown="javascript:stripspaces(this)" /> <span
													class="erreur" style="color: red">${errorNom}</span>

											</div>
										</div>

										<div class="form-group">
											<span class="col-md-1 col-md-offset-2 text-center"><i
												class="fa fa-envelope-o bigicon"></i></span>
											<div class="col-md-6">
												<label for="pays">Pays : </label> <input type="text"
													name="pays" id="pays" required placeholder="Pays du site"
													class="form-control"
													onkeydown="javascript:stripspaces(this)" /> <span
													class="erreur" style="color: red">${errorPays}</span>

											</div>
										</div>

										<div class="form-group">
											<span class="col-md-1 col-md-offset-2 text-center"><i
												class="fa fa-envelope-o bigicon"></i></span>
											<div class="col-md-6">
												<label for="region">Région : </label> <input type="text"
													name="region" id="region" required
													placeholder="Région du site" class="form-control"
													onkeydown="javascript:stripspaces(this)" /> <span
													class="erreur" style="color: red">${errorRegion}</span>

											</div>
										</div>

										<div class="form-group">
											<div class="col-md-12 text-center">
												<button type="submit" class="btn btn-primary">Ajouter</button>
											</div>
										</div>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>



			</c:if>

			<c:if test="${ site.id >0}">

				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<div class="well well-sm">
								<form class="form-horizontal" method="post" action="ajouterSite">
									<fieldset>
										<legend class="text-center header">Site</legend>

										<div class="form-group">
											<span class="col-md-1 col-md-offset-4 text-center"><i
												class="fa fa-envelope-o bigicon"></i></span>
											<div class="col-md-6">
												Nom du site : <b><c:out value="${ site.nomSite }" /></b>

											</div>
										</div>

										<div class="form-group">
											<span class="col-md-1 col-md-offset-4 text-center"><i
												class="fa fa-envelope-o bigicon"></i></span>
											<div class="col-md-6">
												Pays : <b><c:out value="${ site.pays }" /></b>

											</div>
										</div>

										<div class="form-group">
											<span class="col-md-1 col-md-offset-4 text-center"><i
												class="fa fa-envelope-o bigicon"></i></span>
											<div class="col-md-6">
												Région : <b><c:out value="${ site.region }" /></b>

											</div>
										</div>

										<div class="form-group">
											<span class="col-md-1 col-md-offset-4 text-center"><i
												class="fa fa-envelope-o bigicon"></i></span>
											<div class="col-md-6">
												<a href="ajouterSecteur" class="btn btn-primary">Ajouter
													Secteur</a>

											</div>
										</div>



										<div class="form-group">
											<div class="col-md-12 text-center">
												<input type="text" value="1" name="reponse" id="reponse"
													hidden>
												<button type="submit" name="annuler" value="annuler"
													id="annuler" class="btn btn-primary"
													style="background-color: red">Annuler</button>
											</div>
										</div>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
				</div>

			</c:if>
		</c:when>
		<c:otherwise>
			<center>
				<a href="betaConn" class="btn btn-primary" style="margin-top: 10%">Connectez-vous
					d'abord</a>
			</center>
		</c:otherwise>

	</c:choose>

</body>
</html>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<%@ include file="pages/footer.jsp"%>