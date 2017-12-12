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
      
        <form method="post" action="ajouterSecteur">
        
            <label for="nom_secteur">nom du secteur : </label>
            <input type="text" name="nom_secteur" id="nom_secteur" /><br/>
            
             <label for="cotation">cotation : </label>
            <input type="text" name="cotation" id="cotation" /><br/>
            
             <label for="nb_voies">nombre de voies : </label>
            <input type="text" name="nb_voies" id="nb_voies" /><br/>
           
            <input type="submit" />
        </form>
         </c:otherwise>
     </c:choose> 
     
     <c:forEach var="site" items="${ sites }" >  
     <c:out value="${ site.nomSite }" />
     
       <p>Liste des secteurs</p>
  
	 <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>
							Nom secteur
						</th>
						<th>
							Cotation secteur
						</th>
						<th>
							Nombre de voies
						</th>
						<th>
							secteur id
						</th>
					</tr>
				</thead>
				<c:forEach var="secteur" items="${ secteurs }"  >
				
				
				<tbody>
					<tr>
						<td>
						
							<c:out value="${ secteur.nom }" />
						</td>
						<td>
							<c:out value="${ secteur.cotation }" />
						</td>
						<td>
						<c:out value="${ secteur.nbVoies }" />
						</td>
						<td>
						<c:out value="${ secteur.id }" />
						</td>
					</tr>
				</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

<c:forEach begin="1" end="${site.nbSecteurs}" var="i" step="1" varStatus="vs">
value = <c:out value="${i}"/>
  nom = <c:out value="${ secteur.nom}"/> : 
  cotation = <c:out value="${secteur.cotation}"/> : 
  nbVoies = <c:out value="${ secteur.nbVoies }" />
  <c:if test="${vs.first}">
     : Premier element
  </c:if>
  <c:if test="${vs.last}">
     : Dernier element
  </c:if>
  <br>
</c:forEach>
</c:forEach>
 	 
 <%@ include file ="pages/footer.jsp"  %>