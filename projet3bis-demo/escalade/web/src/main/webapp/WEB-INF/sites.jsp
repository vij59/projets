<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sites</title>
</head>
<body>
  <c:choose>
      
  	  <c:when test="${ !empty form.resultat }"><p><c:out value="Bonjour ${ form.resultat }" /></p></c:when>
  	  
      <c:otherwise>
        <form method="post" action="sites">
            <label for="nom_site">nom du site : </label>
            <input type="text" name="nom_site" id="nom_site" />
            
             <label for="pays">pays : </label>
            <input type="text" name="pays" id="pays" />
            
             <label for="region">region : </label>
            <input type="text" name="region" id="region" />
            
                  <label for="code_postal">code postal : </label>
            <input type="text" name="code_postal" id="code_postal" />
            
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

</body>
</html>