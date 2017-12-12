<%@ include file ="pages/header.jsp"  %>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Accueil</title>
		<link href="../css/background.css" rel="stylesheet" type="text/css">
	</head>

	<body>
		<%@ include file ="pages/navbar.jsp"  %>
		<br/><br/><br/>
		
		
		<div class="jumbotron">
				<h2>
					Bienvenue sur le site des fondus de la grimpette
				</h2>
				<p>
					Rejoignez notre communauté et partagez vos parcours d'escalade
				</p>
				<p>
					<a class="btn btn-primary btn-large" href="/web/creerUtilisateur">S'inscrire</a>
				</p>
				<p>
					<a class="btn btn-primary btn-large" href="/web/connexion">Se connecter</a>
				</p>
			</div>
						
			  <%@ include file ="menu.jsp"  %> 
		
		    <c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
			<p>Bonjour, voici la liste des utilisateurs</p>
		    <ul>
			    <c:forEach var="utilisateur" items="${ utilisateurs }">
			    	<li><c:out value="${ utilisateur.prenom }" /> <c:out value="${ utilisateur.nom }" /></li>
			    </c:forEach>
			</ul>    
		
		
 <%@ include file ="pages/footer.jsp"  %>