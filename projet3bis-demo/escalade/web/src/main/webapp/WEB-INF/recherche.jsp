<%@ include file="pages/header.jsp"%>
<%@ page pageEncoding="UTF-8"%>
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

	<script>
		var paysEtRegion = {};

		var tab = [];
		tab.push("bite");
		<c:forEach var="pays" items="${ pays}">
		paysEtRegion['${ pays }'] = [ 'x' ];
		</c:forEach>

		function changerListeRegion() {
			var listePays = document.getElementById("pays");
			var listeRegion = new Set();
			listeRegion = document.getElementById("region");
			var selPays = listePays.options[listePays.selectedIndex].value;
			while (listeRegion.options.length) {
				listeRegion.remove(0);
			}

			var toutes = new Option("TOUTES REGIONS");
			listeRegion.options.add(toutes);
			<c:forEach var="site" items="${ site }">
			if (selPays === '${site.pays}') {
				var pays = paysEtRegion[selPays];

				if (pays) {
					var i;
					var k;
					var x = 0;
					for (i = 0; i < pays.length; i++) {

						var region = new Option("${ site.region }");
						listeRegion.options.add(region);

						/*
						var k=0;
						var mot= "";
						for(k=0; k<=30; k++) {
							mot = mot + listeRegion.options.value[k];
						}
						alert(mot);
						 */
					}
				}
			}
			</c:forEach>
		}
	</script>


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="well well-sm">
					<form class="form-horizontal" method="post" action="recherche">
						<fieldset>
							<legend class="text-center header">Rechercher un site</legend>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-envelope-o bigicon"></i></span>
								<div class="col-md-6">
									<label for="pays">Pays </span></label> <br /> <select id="pays"
										name="pays" onchange="changerListeRegion()">
										<option value="">TOUS LES PAYS</option>
										<c:forEach var="pays" items="${ pays }">
											<option value="${pays }">${pays }</option>
										</c:forEach>
									</select>

								</div>
							</div>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-envelope-o bigicon"></i></span>
								<div class="col-md-6">
									<label for="region">Region </span></label><br /> <select id="region"
										name="region"><option value="">TOUTES LES
											REGIONS</option></select>

								</div>
							</div>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-envelope-o bigicon"></i></span>
								<div class="col-md-6">
									<label for="voie">Cotation Voie </span></label> <input type="text"
										id="cotationVoie" name="cotationVoie" class="form-control"
										onkeydown="javascript:stripspaces(this)" /> <span
										class="erreur" style="color: red">${errorRegion}</span>

								</div>
							</div>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-envelope-o bigicon"></i></span>
								<div class="col-md-6">
									<label for="longueur">Cotation Longueur </span></label> <input
										type="text" id="cotationLongueur" name="cotationLongueur"
										class="form-control" onkeydown="javascript:stripspaces(this)" />
									<span class="erreur" style="color: red">${errorRegion}</span>

								</div>
							</div>



							<div class="form-group">
								<div class="col-md-12 text-center">
									<button type="submit" class="btn btn-primary">Rechercher</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${ affiche == 1}">
		<div class="container">
			<div class="col-md-12">
				<table class="table table-striped custab">
					<legend>Liste des sites</legend>
					<thead>
						<tr>
							<th>Nom site</th>
							<th>Pays site</th>
							<th>Region site</th>

							<th>Action</th>
						</tr>
					</thead>
					<c:forEach var="site" items="${ siteRecherché }">
						<tr>
							<td><c:out value="${ site.nomSite }" /></td>
							<td><c:out value="${ site.pays }" /></td>
							<td><c:out value="${ site.region }" /></td>
							<td>
								<form method="post" action="detailsSite">
									<input type="text" id="id" name="id"
										value="<c:out value="${ site.id }" />" hidden> <input
										type="submit" value="Détails" class='btn btn-info btn-xs'>
								</form>
							</td>
							
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
		</div>
	</c:if>


	<%@ include file="pages/footer.jsp"%>