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



	<div class="container">
		<div class="col-md-12">
			<table class="table table-striped custab">
				<legend>Liste des topos</legend>
				<thead>
					<tr>
						<th>Nom Topo</th>
						<th>Fichier</th>
						<th></th>
						<th>Site</th>
						<th></th>
						<th>Action</th>
					</tr>
				</thead>
				<c:forEach var="topo" items="${ topos }">
					<tr>
						<td><c:out value="${ topo.nomTopo }" /></td>
						<td><c:out value="${ topo.fichier }" /></td>
						<td><c:forEach var="site" items="${ sites }">
								<c:set var="idSiteTopo" scope="session" value="${ topo.idSite }" />
								<c:set var="idSiteSite" scope="session" value="${site.id}" />

								<c:if test="${idSiteTopo == idSiteSite}">

									<td><c:out value="${ site.nomSite }" /> -- <c:out
											value="${ site.pays }" /></td>
								</c:if>
							</c:forEach>


							<form action="detailsTopo" method="post">
								<td><input id="id_topo" name="id_topo" type="hidden"
									value="<c:out value="${ topo.idTopo }" />"> <input
									type="submit" value="détails" class="btn btn-info btn-xs"></td>
							</form> <c:choose>
								<c:when test="${sessionUtilisateur.role == 1}">
									<c:choose>
										<c:when test="${topo.disponible}">
											<form action="reserverTopo" method="post">
												<td><input id="id_topo" name="id_topo" type="hidden"
													value="<c:out value="${ topo.idTopo }" />"> <input
													id="nom_topo" name="nom_topo" type="hidden"
													value="<c:out value="${ topo.nomTopo }" />"> <input
													id="dispo" name="dispo" type="hidden"
													value="<c:out value="${ topo.disponible }" />"> <input
													id="id_site" name="id_site" type="hidden"
													value="<c:out value="${ topo.idSite }" />"> <input
													type="submit" value="réserver" class="btn btn-info btn-xs"></td>
											</form>
										</c:when>

										<c:otherwise>
											<td>pas disponible</td>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<td><a href="betaConn">Connectez-vous pour réserver</a></td>
								</c:otherwise>
							</c:choose>
					</tr>
				</c:forEach>
			</table>

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
	box-shadow: 3px 3px 2px #ccc;
}

.custab:hover {
	box-shadow: 3px 3px 0px transparent;
	transition: 0.5s;
}
</style>





	<%@ include file="pages/footer.jsp"%>