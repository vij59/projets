<%@ include file ="pages/header.jsp"  %>
<html>
<head>
<meta charset="utf-8" />
<title>Voies</title>
</head>
<body>

<%@ include file ="pages/navbar.jsp"  %>
<br/><br/><br/>
     
     <c:forEach var="site" items="${ sites }" >  
    <h2>Site : <c:out value="${ site.nomSite }" /> </h2>
     </c:forEach>
     
     <c:forEach var="secteur" items="${ sect }" >  
    <h3>secteur : <c:out value="${ secteur.nom }" /> </h3>
     
      <c:choose>
      
  	  <c:when test="${ !empty form.resultat }"><p><c:out value="Bonjour ${ form.resultat }" /></p></c:when>
  	  
      <c:otherwise>
      
        <form method="post" action="ajouterVoie">
        
            <label for="nom_voie">nom voie : </label>
            <input type="text" name="nom_voie" id="nom_voie" required/>
            
             <label for="cotation">cotation : </label>
            <input type="text" name="cotation" id="cotation" required/><br/>
           
            <input type="submit" />
      </form>
         </c:otherwise>
     </c:choose> 
     
       <p>Liste des voies</p>
  
	 <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>
							voie id
						</th>
						<th>
							Nom voie
						</th>
						<th>
							Cotation voie
						</th>
					</tr>
				</thead>
				<c:forEach var="voie" items="${ voies }"  >
				<tbody>
					<tr>
						<td>
						<c:out value="${ voie.id }" />
						</td>
						<td>
						<c:out value="${ voie.nom }" />
						</td>
						<td>
						<c:out value="${ voie.cotation }" />
						</td>
					</tr>
				</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
</c:forEach>
<form method="post" action="ajouterVoie">
<input type="text" hidden value="1" id="fini" name="fini">
<input type="submit" value="passer au secteur suivant">
</form>
 	 
 <%@ include file ="pages/footer.jsp"  %>