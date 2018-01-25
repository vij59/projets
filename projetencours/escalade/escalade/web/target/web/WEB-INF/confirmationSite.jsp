<%@ include file="pages/header.jsp"%>
<%@ page pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8" />
<title>Sites</title>

</head>
<body>

	<%@ include file="pages/navbar.jsp"%>
	<br />
	<br />
	<br />

	<c:if test="${siteFini == true}">
		<p>
			Site
			<c:out value="${ site.nomSite}" />
			ajouté avec succès
		</p>
		<form action="sites" method="post">
			<input type="submit" value="Voir Liste des sites"
				class='btn btn-info'>
		</form>
	</c:if>




	<%@ include file="pages/footer.jsp"%>