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

	<c:if test="${siteFini == true}">
		<p>
			Site
			<c:out value="${ site.nomSite}" />
			ajouté avec succès
		</p>
		<form action="sites" method="post">
			<input type="submit" value="Voir Liste des sites">
		</form>
	</c:if>



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
				<c:forEach var="site" items="${ sites }">
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