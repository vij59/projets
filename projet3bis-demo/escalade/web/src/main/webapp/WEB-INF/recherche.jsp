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


	<form method="post" action="recherche">
		<fieldset>
			<legend>Recherche</legend>
			<p>Rechercher un site selon plusieurs critères</p>

			<label for="pays">Pays </span></label> 
		
			<select id="pays" name="pays"  >
				<option value="">Tous les pays</option>
				<c:forEach var="site" items="${ sites }">
				<option value="${ site.pays }"><c:out value="${ site.pays }" /></option>
				</c:forEach>
			</select> 
			<br />
		
			 <label
				for="region">Région</span></label>
				
				
			<select id="region" name="region"  >
				<option value="">Toutes les régions</option>
				<c:forEach var="site" items="${ sites }">
				<option value="${ site.region }"><c:out value="${ site.region }" /></option>
				</c:forEach>
			</select> 
			<br />
			
				
				<label for="voie">Cotation Voie </span></label>
				<input type="text" id="cotationVoie" name="cotationVoie"
				 size="20"
				maxlength="60" /> <br />
				
				<label for="longueur">Cotation Longueur </span></label>
				<input type="text" id="cotationLongueur" name="cotationLongueur"
				 size="20"
				maxlength="60" />
			<input type="submit" />


		</fieldset>
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
							<th></th>
						</tr>
					</thead>
					<c:forEach var="site" items="${ siteRecherché }">
						<tbody>
							<tr>
								<td><c:out value="${ site.nomSite }" /></td>
								<td><c:out value="${ site.pays }" /></td>
								<td><c:out value="${ site.region }" /></td>
								<td>
									<form method ="post" action="detailsSite">
									<input type ="text" id="id" name="id" value="<c:out value="${ site.id }" />" hidden>
									<input type="submit" >
									</form>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<%@ include file="pages/footer.jsp"%>