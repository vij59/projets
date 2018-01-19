<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="pages/header.jsp"%>
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


	<c:set var="top" scope="page" value="${top}" />
	<div class="col-lg-6 col-md-7 col-xs-10">
		<form action="detailsSite" method="post">
			<h1>
				"
				<c:out value="${ topoNom }" />
				" topo du site


				<button type="submit" id="id" name="id" type="hidden"
					value="<c:out value="${ idSite }" />" class='btn btn-info  btn-lg'>
					<c:out value="${ nomSite }" />
				</button>
		</form>
		</h1>

		<legend> Avis </legend>


		<c:forEach var="commentaire" items="${ commentaires }">
			<c:if test="${top == commentaire.idTopo }">
				<c:forEach var="utilisateur" items="${ utilisateurs }">

					<c:if test="${ utilisateur.id == commentaire.idUtilisateur }">


						<div class="comments-list">
							<div class="media">
								<c:if test="${ utilisateur.id == sessionUtilisateur.id }">
									<form action="SupprimerCommentaireTopo" method="post"
										id="usrform">
										<p class="pull-right">
											<small><c:out value="${ commentaire.date }" /></small> <br />
											<input type="text" id="cache" name="cache" value="2" hidden>
											<input type="text" id="nomSite" name="nomSite"
												value="${ nomSite }" hidden> <input type="text"
												id="idTopo" name="idTopo" value="${ top }" hidden> <input
												type="text" value="${ commentaire.id }" hidden
												name="idCommentaire" id="idCommentaire"> <small><button
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

		<br />

	</div>
	<div class="col-lg-6 col-md-5 col-xs-2">
		<h1>
			<h2>
				<a href="topos" class='btn btn-warning '> Voir disponibilités</a>
			</h2>
		</h1>
	</div>

	</div>
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