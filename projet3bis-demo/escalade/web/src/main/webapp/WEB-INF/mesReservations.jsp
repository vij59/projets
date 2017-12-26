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

<c:forEach var="reservation" items="${ reservations }">
<c:choose>
<c:when test="${sessionUtilisateur.id == reservation.idUtilisateur}">

	<p>Liste de mes réservations</p>

	
				<table class="table">
					<thead>
						<tr>
							<th>Nom Topo</th>
							<th>Date Emprunt</th>
							<th>Date Retour</th>
							<th></th>
						</tr>
					</thead>

					<c:forEach var="topo" items="${ topos }">
<c:choose>
<c:when test="${topo.idTopo == reservation.idTopo}">
						<tbody>
							<tr>
								<td><c:out value="${ topo.nomTopo }" /></td>
								<td><c:out value="${ reservation.dateEmprunt }" /></td>
								<td><c:out value="${ reservation.dateRetour }" /> </td>
							</tr>
						</tbody>
</c:when>
</c:choose>
				</c:forEach>
				</table>
				
</c:when>
</c:choose>
</c:forEach>
	
</c:when>
<c:otherwise>
											<td><a href="betaConn">Connectez-vous pour réserver</a></td>
										</c:otherwise>

	</c:choose>

	<%@ include file="pages/footer.jsp"%>