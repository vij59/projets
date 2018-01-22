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
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="well well-sm">
							<form class="form-horizontal" method="post" action="ajouterTopo">
								<fieldset>
									<legend class="text-center header">Topo</legend>

									<div class="form-group">
										<span class="col-md-1 col-md-offset-2 text-center"><i
											class="fa fa-envelope-o bigicon"></i></span>
										<div class="col-md-6">
											<label for="fichier">Correspond au site : </label> <br /> <select
												id="idSite" name="idSite" class="selectpicker">
												<c:forEach var="site" items="${ sites }">
													<option data-tokens="${site.id}"
														value="<c:out value="${site.id}"/>">
														<td><c:out value="${ site.nomSite }" /> / <c:out
																value="${ site.pays }" /> / <c:out
																value="${ site.region }" />
													</option>
												</c:forEach>
											</select> Nom / Pays / Région
										</div>
									</div>



									<div class="form-group">
										<span class="col-md-1 col-md-offset-2 text-center"><i
											class="fa fa-envelope-o bigicon"></i></span>
										<div class="col-md-6">
											<label for="nom_topo">Nom : </label> <input type="text"
												name="nom_topo" id="nom_topo" required
												placeholder="Nom du topo" class="form-control"
												onkeydown="javascript:stripspaces(this)" /> <span
												class="erreur" style="color: red">${errorNom}</span>

										</div>
									</div>

									<div class="form-group">
										<span class="col-md-1 col-md-offset-2 text-center"><i
											class="fa fa-envelope-o bigicon"></i></span>
										<div class="col-md-6">
											<label for="fichier">Fichier : </label> <input type="file"
												name="fichier" id="fichier" required value="Ajouter fichier"
												onkeydown="javascript:stripspaces(this)" />

										</div>
									</div>
									<br />

									<div class="form-group">
										<div class="col-md-12 text-center">
											<button type="submit" class="btn btn-primary">Ajouter
												Topo</button>
										</div>
									</div>
								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<center>
				<a href="betaConn" class="btn btn-primary" style="margin-top: 10%">Connectez-vous
					d'abord</a>
			</center>
		</c:otherwise>

	</c:choose>
	<link
		href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">
	<%@ include file="pages/footer.jsp"%>