<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>
     <c:choose>
      
  	  <c:when test="${ !empty form.resultat }"><p><c:out value="Bonjour ${ form.resultat }" /></p></c:when>
  	  
      <c:otherwise>
        <form method="post" action="creerUtilisateur">
            <label for="nom">nom : </label>
            <input type="text" name="nom" id="nom" />
            
             <label for="prenom">prenom : </label>
            <input type="text" name="prenom" id="prenom" />
            
             <label for="mail">mail : </label>
            <input type="text" name="mail" id="mail" />
            
                  <label for="mdp">mdp : </label>
            <input type="text" name="mdp" id="mdp" />
            
            <input type="submit" />
        </form>
         </c:otherwise>
     </c:choose> 
     <ul>
	    <c:forEach var="utilisateur" items="${ utilisateurs }">
	    	<li>Hello <c:out value="${ utilisateur.mail }" /></li>
	    </c:forEach>
	</ul>    
  	
</body>
</html>