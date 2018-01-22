
<c:choose>
	<c:when test="${sessionUtilisateur.role == 1}">
Commentez le site 


<c:out value="${ site.nomSite }" />




		<form action="commentaireSite" method="post" id="usrform">
			<input type="text" value="${ site.id }" hidden id="idSite"
				name="idSite">
			<textarea rows="4" cols="50" name="comment" form="usrform"
				placeholder="">
</textarea>
			<input type="submit" value="Valider le commentaire">
		</form>
		<br />
	</c:when>
	<c:otherwise>
		<a href="betaConn">Connectez-vous d'abord pour commenter le site :</a>
	</c:otherwise>
</c:choose>



<table border="2px solid black">
	<th>Utilisateur</th>
	<th>Commentaire</th>
	<th>Date</th>

	<c:forEach var="commentaire" items="${ commentaires }">
		<c:if test="${site.id == commentaire.idSite}">
			<c:forEach var="utilisateur" items="${ utilisateurs }">

				<c:if test="${ utilisateur.id == commentaire.idUtilisateur }">
					<tr border="2px solid black">
						<td><c:out value="${ utilisateur.nom }" /></td>
						<td><c:out value="${ commentaire.commentaire }" /></td>
						<td><c:out value="${ commentaire.date }" /></td>
					</tr>
				</c:if>

			</c:forEach>
		</c:if>
	</c:forEach>
</table>
