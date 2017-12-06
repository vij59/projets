<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="form.css" />
    </head>
    <body>
        <form method="post" action="betaConn">
            <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via ce formulaire.</p>

                <label for="mail">Adresse email <span class="requis">*</span></label>
                <input type="text" id="mail" name="mail" value="<c:out value="${utilisateur.mail}"/>" size="20" maxlength="60" />
          		 <span class="erreur">${errorMail}</span><br/>
                

                <label for="mdp">Mot de passe <span class="requis">*</span></label>
                <input type="text" id="mdp" name="mdp" value="" size="20" maxlength="20" />
               <span class="erreur">${ errorMdp }</span>

				<br/>
                <input type="submit"  />
                
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
                
                <%-- Vérification de la présence d'un objet utilisateur en session --%>
                <c:if test="${!empty sessionScope.sessionUtilisateur}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.mail}</p>
                </c:if>
            </fieldset>
        </form>
        
         <ul>
	    <c:forEach var="utilisateur" items="${ utilisateurs }">
	    	<li>Hello <c:out value="${ utilisateur.mail }" /></li>
	    </c:forEach>
	</ul>  
        
    </body>
</html>