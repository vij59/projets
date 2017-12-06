<%@ include file ="pages/header.jsp"  %>
<html>
<head>
<meta charset="utf-8" />
<title>Créer utilisateur</title>
</head>
<body>

<%@ include file ="pages/navbar.jsp"  %>
     <c:choose>
      
  	  <c:when test="${ !empty form.resultat }"><p><c:out value="Bonjour ${ form.resultat }" /></p></c:when>
  	  
      <c:otherwise>
        <form method="post" action="creerUtilisateur">
            <label for="nom">nom : </label>
            <input type="text" name="nom" id="nom" /><br/>
            
             <label for="prenom">prenom : </label>
            <input type="text" name="prenom" id="prenom" /><br/>
            
             <label for="mail">mail : </label>
            <input type="text" name="mail" id="mail" /><br/>
            
                  <label for="mdp">mdp : </label>
            <input type="text" name="mdp" id="mdp" /><br/>
            
            <input type="submit" />
        </form>
         </c:otherwise>
     </c:choose> 
     <ul>
	    <c:forEach var="utilisateur" items="${ utilisateurs }">
	    	<li>Hello <c:out value="${ utilisateur.mail }" /></li>
	    </c:forEach>
	</ul>    
  <%@ include file ="pages/footer.jsp"  %>
