<%@ page pageEncoding="UTF-8" %>
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


	<p>Liste des sites</p>

	<p>The length of the secteurs is : ${fn:length(site.secteurs)}</p>


	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<thead>
						<tr>
							<th>Nom site</th>
							<th>Noms secteurs</th>
							<th>Noms voies</th>
							<th>Noms longueurs</th>
						</tr>
					</thead>
				</table>
				<ol>

					<h1>
						Site :
						<c:out value="${ site.nomSite }" />


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
											<c:out value="${ site.secteurs[i].voies[j].nom}" /> (<c:out value="${ site.secteurs[i].voies[j].cotation}" />)
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
															value="${ site.secteurs[i].voies[j].longueurs[l].nom}" />&nbsp;(<c:out
															value="${ site.secteurs[i].voies[j].longueurs[l].cotation}" />)
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
	

	<%@ include file="pages/footer.jsp"%>