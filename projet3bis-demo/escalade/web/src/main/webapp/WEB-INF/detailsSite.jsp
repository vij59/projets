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



	<c:set var="site" scope="page" value="${siteDetail}" />


	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 ">

				<ol>

					<h1>
						Site :
						<c:out value="${ site.nomSite }" />
					</h1>

					<ol>
						<c:forEach begin="0" end="${fn:length(site.secteurs)-1}" var="i"
							step="1">
							<h2 style="color: red">
								&nbsp;Secteur
								<c:out value="${i+1}" />
								:&nbsp;
								<c:out value="${ site.secteurs[i].nom}" />
							</h2>


							<ol>
								<c:forEach begin="0"
									end="${fn:length(site.secteurs[i].voies)-1}" var="j" step="1">
									<h3 style="color: green">
										&nbsp;&nbsp;Voie
										<c:out value="${j+1}" />
										:&nbsp;
										<c:out value="${ site.secteurs[i].voies[j].nom}" />
										(
										<c:out value="${ site.secteurs[i].voies[j].cotation}" />
										)
									</h3>


									<ol>
										<c:if test="${not empty site.secteurs[i].voies[j].longueurs}">

											<c:forEach begin="0"
												end="${fn:length(site.secteurs[i].voies[j].longueurs)-1}"
												var="l" step="1">

												<h4 style="color: blue">
													&nbsp;&nbsp;&nbsp;Longueur
													<c:out value="${l+1}" />
													:&nbsp;
													<c:out
														value="${ site.secteurs[i].voies[j].longueurs[l].nom}" />
													&nbsp;(
													<c:out
														value="${ site.secteurs[i].voies[j].longueurs[l].cotation}" />
													)
												</h4>

											</c:forEach>
										</c:if>
									</ol>
								</c:forEach>
							</ol>
						</c:forEach>
					</ol>
				</ol>

				</tr>

			</div>
			<div class="col-md-5 ">
				<c:choose>
					<c:when test="${sessionUtilisateur.role == 1}">
						<legend> Commentaires </legend>


						<c:forEach var="commentaire" items="${ commentaires }">
							<c:if test="${site.id == commentaire.idSite}">
								<c:forEach var="utilisateur" items="${ utilisateurs }">

									<c:if test="${ utilisateur.id == commentaire.idUtilisateur }">
										<div class="comments-list">
											<div class="media">
										<c:if test="${ utilisateur.id == sessionUtilisateur.id }">
											<form action="SupprimerCommentaire" method="post" id="usrform">
												<p class="pull-right">
													<small><c:out value="${ commentaire.date }" /></small>
													<br/>
													<input type="text" id="id" name="id"
									value="<c:out value="${ site.id }" />" hidden> 
									
													<input type="text" value="${ commentaire.id }" hidden name="idCommentaire" id="idCommentaire">
														<small><button type="submit" class="btn btn-default btn-xs ">Supprimer</button></small>
														
												</p>
												</form>
										</c:if>
												<div class="media-body">

													<h4 class="media-heading user_name">
														<c:out value="${ utilisateur.nom }" />
													</h4>
													<c:out value="${ commentaire.commentaire }" />

													<p>
													
													
													
													</p>
												</div>
											</div>
										</div>
									</c:if>

								</c:forEach>
							</c:if>
						</c:forEach>
						<center>
							<form action="detailsSite" method="post" id="usrform"
								name="usrform">
								<input type="text" value="${ site.id }" hidden id="id" name="id">
								<textarea rows="4" cols="50" name="comment" form="usrform"
									placeholder="Entrez votre commentaire ici" required>
</textarea>
								<input type="submit" value="Valider commentaire"
									name="valider_commentaire" id="valider_commentaire"
									class='btn btn-info'>
							</form>
						</center>
						<br />
					</c:when>
					<c:otherwise>
						<a href="betaConn">Connectez-vous d'abord pour commenter le
							site :</a>
					</c:otherwise>
				</c:choose>
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