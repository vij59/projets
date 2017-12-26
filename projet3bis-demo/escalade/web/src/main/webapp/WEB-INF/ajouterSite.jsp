<%@ page pageEncoding="UTF-8" %>
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
	
	<c:choose>
			<c:when test="${sessionUtilisateur.role == 1}">
	
	
			<form method="post" action="ajouterSite">
				<label for="nom_site">nom du site : </label> 
				<input type="text" name="nom_site" id="nom_site" required /><br /> 
				<label for="pays">pays : </label> 
				<input type="text" name="pays" id="pays" required /><br /> 
				<label for="region">region : </label> 
				<input type="text" name="region" id="region" required /><br /> 
				<input type="submit" />
			</form>
	


	<p>Liste des sites</p>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<thead>
						<tr>
							<th>Nom site</th>
							<th>Pays site</th>
							<th>Region site</th>

						</tr>
					</thead>

					<tbody>
						<tr>
							<td>
								<%-- <c:out value="${ status.last }" />  varStatus="status" --%>
								<c:out value="${ site.nomSite }" />
							</td>
							<td><c:out value="${ site.pays }" /></td>
							<td><c:out value="${ site.region }" /></td>

							<td><a href="ajouterSecteur">Ajouter Secteur</a></td>
						</tr>
					</tbody>

				</table>
			</div>
		</div>
	</div>
	 </c:when>
	  <c:otherwise>
          <a href="betaConn">Connectez-vous d'abord</a>
           </c:otherwise>
        
        </c:choose>
                
</body>
</html>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<%@ include file="pages/footer.jsp"%>