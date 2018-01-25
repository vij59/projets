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



	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">

				<ol>

					<h1>
						Site :
						<c:out value="${ site.nomSite }" />
						-- Pays :
						<c:out value="${ site.pays }" />
						-- Région :
						<c:out value="${ site.region }" />

						<ol>
							<c:forEach begin="0" end="${fn:length(site.secteurs)-1}" var="i"
								step="1">
								<h2>
									Secteur
									<c:out value="${i+1}" />
									:&nbsp;
									<c:out value="${ site.secteurs[i].nom}" />
								</h2>


								<ol>
									<c:forEach begin="0"
										end="${fn:length(site.secteurs[i].voies)-1}" var="j" step="1">
										<h3>
											Voie
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

													<h4>
														Longueur
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
		</div>
	</div>
	<br />

	<form action="recapSite" method="post">
		<button type="submit" value="valider ce site" class='btn btn-info'>
			<span class="glyphicon glyphicon-edit"></span>Confirmer
	</form>

	<form action="AnnulerSite" method="post">
		<button type="submit" value="Annuler" class="btn btn-danger ">
			<span class="glyphicon glyphicon-remove"></span>Annuler
		</button>
	</form>

	<style>
form {
	/* Float both forms to the left */
	float: left;
	/* borders added for visibility. Just remove them */
	margin-left: 15%;
	padding-right: 2%;
}

form#updateForm {
	clear: right;
	/* with some space to the left of the second form */
	margin-right: 20px;
}
/* Give an id to the <p> which follows the second form and clear: both */
p#submit {
	clear: both;
}
</style>

	<%@ include file="pages/footer.jsp"%>