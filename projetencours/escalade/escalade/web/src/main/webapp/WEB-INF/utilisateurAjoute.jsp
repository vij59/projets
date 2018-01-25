<%@ page pageEncoding="UTF-8"%>
<%@ include file="pages/header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>Créer utilisateur</title>
</head>
<body>

	<%@ include file="pages/navbar.jsp"%>
	<br />
	<br />
	<br />

<p>Utilisateur ajouté avec l'adresse <c:out value="${nouvelUtilisateur}"/></p>
	<p>
		<a class="btn btn-primary btn-large" href="/web/betaConn">Se
			connecter</a>
	</p>
	
<%@ include file="pages/footer.jsp"%>