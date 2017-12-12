<%@ include file ="pages/header.jsp"  %>
<html>
<head>
<meta charset="utf-8" />
<title>Sites</title>
</head>
<body>

<%@ include file ="pages/navbar.jsp"  %>
<br/><br/><br/>
  <c:choose>
      
  	  <c:when test="${ !empty form.resultat }"><p><c:out value="Bonjour ${ form.resultat }" /></p></c:when>
  	  
      <c:otherwise>
        <form method="post" action="ajouterSite">
            <label for="nom_site">nom du site : </label>
            <input type="text" name="nom_site" id="nom_site" /><br/>
            
             <label for="pays">pays : </label>
            <input type="text" name="pays" id="pays" /><br/>
            
             <label for="region">region : </label>
            <input type="text" name="region" id="region" /><br/>
            
                  <label for="code_postal">code postal : </label>
            <input type="text" name="code_postal" id="code_postal" /><br/>
            
               <label for="nb_secteurs">Nombre de Secteurs : </label>
            <input type="number" name="nb_secteurs" id="nb_secteurs" /><br/>
            <input type="submit" />
        </form>
         </c:otherwise>
     </c:choose> 
     
    
	  <p>Liste des sites</p>
  
	 <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>
							Nom site
						</th>
						<th>
							Pays site
						</th>
						<th>
							Region site
						</th>
						<th>
							Nombre Secteurs
						</th>
					</tr>
				</thead>
				<c:forEach var="site" items="${ sites }" >  
				<tbody>
					<tr>
						<td>
						<%-- <c:out value="${ status.last }" />  varStatus="status" --%>
							<c:out value="${ site.nomSite }" />
						</td>
						<td>
							<c:out value="${ site.pays }" />
						</td>
						<td>
							<c:out value="${ site.region }" />
						</td>
						<td>
							<c:out value="${ site.nbSecteurs }" />
						</td>
						<td>
							<a href="ajouterSecteur">Ajouter Secteur</a>
						</td>
					</tr>
				</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</body>
</html>
      <link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
 <%@ include file ="pages/footer.jsp"  %>