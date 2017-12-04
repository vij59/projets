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
        <form method="post" action="connexion">
            <label for="mail">mail : </label>
            <input type="text" name="mail" id="mail" /><br/>
            
            <label for="mdp">mdp : </label>
            <input type="mdp" name="mdp" id="mdp" />
            
         
            
            <input type="submit" />
        </form>
         </c:otherwise>
     </c:choose> 
     <ul>
	    <c:forEach var="utilisateur" items="${ utilisateurs }">
	    	<li>Hello <c:out value="${ utilisateur.nom }" /></li>
	    </c:forEach>
	</ul>    
  	
</body>
</html>