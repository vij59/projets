<%@ include file ="pages/header.jsp"  %>
<html>
<head>
<meta charset="utf-8" />
<title>Longueurs</title>
</head>
<body>

<%@ include file ="pages/navbar.jsp"  %>
<br/><br/><br/>
     
     <c:forEach var="site" items="${ sites }" >  
    <h2>Site : <c:out value="${ site.nomSite }" /> </h2>
     </c:forEach>
     <c:forEach var="secteur" items="${ sec }" >  
    <h3>secteur : <c:out value="${ secteur.nom }" /> </h3>
      
     <c:forEach var="voie" items="${ voies }" >  
    <h4>voie : <c:out value="${ voie.nom }" /> </h4>
     
      <c:choose>
      
  	  <c:when test="${ !empty form.resultat }"><p><c:out value="Bonjour ${ form.resultat }" /></p></c:when>
  	  
      <c:otherwise>
      
        <form method="post" action="ajouterLongueur">
        
            <label for="nom_longueur">nom longueur : </label>
            <input type="text" name="nom_longueur" id="nom_longueur" required />
            
             <label for="cotation">cotation : </label>
            <input type="text" name="cotation" id="cotation" required/><br/>
           
            <input type="submit" />
      </form>
         </c:otherwise>
     </c:choose> 
     
       <p>Liste des longueurs</p>
  
	 <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>
							longueur id
						</th>
						<th>
							Nom longueur
						</th>
						<th>
							Cotation longueur
						</th>
					</tr>
				</thead>
				<c:forEach var="longueur" items="${ longueurs }"  >
				<tbody>
					<tr>
						<td>
						<c:out value="${ longueur.id }" />
						</td>
						<td>
						<c:out value="${ longueur.nom }" />
						</td>
						<td>
						<c:out value="${ longueur.cotation }" />
						</td>
					</tr>
				</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</c:forEach>
</c:forEach>
<form method="post" action="ajouterLongueur">
<input type="text" hidden value="1" id="fini" name="fini">
<input type="submit" value="passer à la voie suivante">
</form>
 	 
 <%@ include file ="pages/footer.jsp"  %>