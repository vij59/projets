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
        <form method="post" action="sites">
            <label for="nom_site">nom du site : </label>
            <input type="text" name="nom_site" id="nom_site" /><br/>
            
             <label for="pays">pays : </label>
            <input type="text" name="pays" id="pays" /><br/>
            
             <label for="region">region : </label>
            <input type="text" name="region" id="region" /><br/>
            
                  <label for="code_postal">code postal : </label>
            <input type="text" name="code_postal" id="code_postal" /><br/>
            
            <input type="submit" />
        </form>
         </c:otherwise>
     </c:choose> 
     <ul>
     <p>Liste des sites</p>
	    <c:forEach var="site" items="${ sites }">
	    	<li> <c:out value="${ site.nomSite }" /></li>
	    </c:forEach>
	</ul>   
 <%@ include file ="pages/footer.jsp"  %>