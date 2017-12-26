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



	<p>Liste des Topos</p>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<thead>
						<tr>
							<th>Nom Topo</th>
							<th>Fichier</th>
							<th>Site</th>
							<th></th>
						</tr>
					</thead>

					<c:forEach var="topo" items="${ topos }">

						<tbody>
							<tr>
								<td><c:out value="${ topo.nomTopo }" /></td>
								<td><c:out value="${ topo.fichier }" /></td>
								<td><c:out value="${ topo.idSite }" /> <c:forEach
										var="site" items="${ sites }">
										<c:set var="idSiteTopo" scope="session"
											value="${ topo.idSite }" />
										<c:set var="idSiteSite" scope="session" value="${site.id}" />

										<c:if test="${idSiteTopo == idSiteSite}">

											<td><c:out value="${ site.nomSite }" /> -- <c:out
													value="${ site.pays }" /></td>
										</c:if>
									</c:forEach> <c:choose>
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
														type="submit" value="réserver"></td>
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
						</tbody>
					</c:forEach>


				</table>
			</div>
		</div>
	</div>


	<c:forEach var="site" items="${ sites }">
		<p>
			<c:out value="${ site.nomSite }" />
		</p>
	</c:forEach>

	<%@ include file="pages/footer.jsp"%>