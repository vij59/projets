<%@ page pageEncoding="UTF-8"%>
<jsp:useBean id="ourDate" class="java.util.Date" />
<jsp:setProperty name="ourDate" property="date" value="${ourDate.date}" />
<fmt:formatDate value="${ourDate}" pattern="dd-MM-yyyy" />

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
			<div class="container">
				<div class="col-md-12">
					<table class="table table-striped custab">
						<legend>Liste des réservations</legend>

						<thead>
							<tr>
								<th>Nom Topo</th>
								<th>Date Emprunt</th>
								<th>Date Retour</th>
								<th></th>
								<th></th>
							</tr>
						</thead>

						<c:forEach var="reservation" items="${ reservations }">
							<c:choose>
								<c:when
									test="${sessionUtilisateur.id == reservation.idUtilisateur}">

									<c:forEach var="topo" items="${ topos }">
										<c:choose>

											<c:when test="${topo.idTopo == reservation.idTopo}">


												<tr>
													<td><c:out value="${ topo.nomTopo }" /></td>
													<td><c:out value="${ reservation.dateEmprunt }" /></td>
													<td><c:out value="${ reservation.dateRetour }" /></td>
													<c:choose>
														<c:when
															test="${reservation.dateRetour > ourDate && topo.disponible == false }">

															<td>
																<form action="mesReservations" method="post">
																	<input type="text" value="${topo.idTopo }" id="idTopo"
																		name="idTopo" hidden> <input type="submit"
																		value="Rendre le topo" class="btn btn-warning">
															</td>
															</form>
														</c:when>
														<c:otherwise>
															<td></td>
														</c:otherwise>
													</c:choose>

													<td>
														<form action="mesReservations" method="post" id="form1">
															<input type="hidden" value="1" id="formVal"
																name="formVal"> <input type="hidden"
																value="${topo.idTopo }" id="idTopoBis" name="idTopoBis">
															<input type="submit" value="Laisser un avis"
																class="btn btn-info">
													</td>
													</form>

												</tr>

											</c:when>
										</c:choose>
									</c:forEach>


								</c:when>
							</c:choose>
						</c:forEach>
					</table>
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