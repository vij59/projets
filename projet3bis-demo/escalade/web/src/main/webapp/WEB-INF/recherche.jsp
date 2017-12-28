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
	
	

<script>

var paysEtRegion = {};

var tab =[];
tab.push("bite");
<c:forEach var="pays" items="${ pays}">
paysEtRegion['${ pays }'] = ['x'];
</c:forEach>

function changerListeRegion() {
    var listePays = document.getElementById("pays");
    var listeRegion = document.getElementById("region");
    var selPays = listePays.options[listePays.selectedIndex].value;
    while (listeRegion.options.length) {
    	listeRegion.remove(0);
    }
    
    var toutes = new Option("TOUTES REGIONS");
    listeRegion.options.add(toutes);
    <c:forEach var="site" items="${ sites }">
    if(selPays === '${site.pays}') {
    var pays = paysEtRegion[selPays];
    
    if (pays) {
        var i;
        for (i = 0; i < pays.length; i++) {
        	
            var region = new Option("${ site.region }");
            listeRegion.options.add(region); 	
        }   
    }
    }
    </c:forEach>
} 
</script>

	
	
	<form method="post" action="recherche">
		<fieldset>
			<legend>Recherche</legend>
			<p>Rechercher un site selon plusieurs critères</p>

			<label for="pays">Pays </span></label> 
		
		
		<select id="pays" name="pays" onchange="changerListeRegion()"> 
  <option value="">TOUS LES PAYS</option>
  <c:forEach var="pays" items="${ pays }">
  <option value="${pays }">${pays }</option> 
  </c:forEach>
</select> 
<br />
<label for="region">Region </span></label>
<select id="region" name="region"><option value="">Toutes les régions</option></select> 
		
		
			
			<br />
			
				
				<label for="voie">Cotation Voie </span></label>
				<input type="text" id="cotationVoie" name="cotationVoie"
				 size="20"
				maxlength="60" /> <br />
				
				<label for="longueur">Cotation Longueur </span></label>
				<input type="text" id="cotationLongueur" name="cotationLongueur"
				 size="20"
				maxlength="60" />
				
				
			<input type="submit" value="RECHERCHER"/>


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
									<input type="submit" value="Détails">
									</form>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

	<%@ include file="pages/footer.jsp"%>