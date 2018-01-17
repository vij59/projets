<%@ page pageEncoding="UTF-8"%>
<%@ include file="pages/header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>Topos</title>
</head>
<body>

	<%@ include file="pages/navbar.jsp"%>
	<br />
	<br />
	<br /> Laissez un avis au topo
	<c:forEach var="topo" items="${ topos }">
		<c:if test="${top == topo.idTopo }">
			<c:out value="${ topo.nomTopo }" />
		</c:if>
	</c:forEach>


	<form action="CommentaireTopo" method="post" id="usrform">

		<textarea rows="4" cols="50" name="comment" form="usrform"
			placeholder="" required>
</textarea>
		<button type="submit" value="Valider le commentaire"
			class='btn btn-warning '>Valider</button>
	</form>
	<br />

	<div class="col-lg-6 col-md-7 col-xs-10">
		<c:set var="top" scope="page" value="${top}" />
		<c:forEach var="commentaire" items="${ commentaires }">
			<c:if test="${top == commentaire.idTopo }">
				<c:forEach var="utilisateur" items="${ utilisateurs }">

					<c:if test="${ utilisateur.id == commentaire.idUtilisateur }">


						<div class="comments-list">
							<div class="media">
								<c:if test="${ utilisateur.id == sessionUtilisateur.id }">
									<form action="SupprimerCommentaireTopo" method="post">
										<p class="pull-right">
											<small><c:out value="${ commentaire.date }" /></small> <br />
											<input type="text" id="cache" name="cache" value="1" hidden>
											<input type="text" id="idTopo" name="idTopo" value="${ top }"
												hidden> <input type="text"
												value="${ commentaire.id }" hidden name="idCommentaire"
												id="idCommentaire"> <small><button
													type="submit" class="btn btn-default btn-xs ">Supprimer</button></small>

										</p>
									</form>
								</c:if>
								<div class="media-body">

									<h4 class="media-heading user_name">
										<c:out value="${ utilisateur.nom }" />
									</h4>
									<c:out value="${ commentaire.commentaire }" />

								</div>
							</div>
						</div>
					</c:if>

				</c:forEach>
			</c:if>
		</c:forEach>
	</div>
	<style>
.user_name {
	font-size: 14px;
	font-weight: bold;
}

.comments-list .media {
	border-bottom: 1px dotted #ccc;
}
</style>



	<%@ include file="pages/footer.jsp"%>