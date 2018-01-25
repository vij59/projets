<%@ page pageEncoding="UTF-8"%>
<%@ include file="pages/header.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Connexion</title>

</head>
<body>

	<%@ include file="pages/navbar.jsp"%>
	<br />
	<br />
	<br />

	<script>
		function stripspaces(input) {
			input.value = input.value.replace(/\s/gi, "");
			return true;
		}
	</script>

	<c:choose>
		<c:when test="${sessionUtilisateur.role != 1}">

			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="well well-sm">
							<form class="form-horizontal" method="post" action="betaConn">
								<fieldset>
									<legend class="text-center header">Connexion</legend>


									<div class="form-group">
										<span class="col-md-1 col-md-offset-2 text-center"><i
											class="fa fa-envelope-o bigicon"></i></span>
										<div class="col-md-6">
											<input id="mail" name="mail" required
												value="<c:out value="${utilisateur.mail}"/>" type="email"
												placeholder="Email Address" class="form-control"
												onkeydown="javascript:stripspaces(this)"> <span
												class="erreur">${errorMail}</span>
										</div>
									</div>

									<div class="form-group">
										<span class="col-md-1 col-md-offset-2 text-center"><i
											class="fa fa-phone-square bigicon"></i></span>
										<div class="col-md-6">
											<input type="password" name="mdp" id="mdp" required
												placeholder="Mot de passe" class="form-control"
												onkeydown="javascript:stripspaces(this)"> <span
												class="erreur">${ errorMdp }</span>
										</div>
									</div>
									<center>
										<p>
											Pas encore inscrit ? <a href="/web/creerUtilisateur">Créez
												votre espace</a>
										</p>
										<br />
									</center>

									<div class="form-group">
										<div class="col-md-12 text-center">
											<button type="submit" class="btn btn-primary btn-lg">Valider</button>
										</div>
									</div>

								</fieldset>
							</form>
						</div>
					</div>
				</div>
			</div>


			<style>
.header {
	color: #36A0FF;
	font-size: 27px;
	padding: 10px;
}

.bigicon {
	font-size: 35px;
	color: #36A0FF;
}
</style>

		</c:when>
		<c:otherwise>

			<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>


			<%-- Vérification de la présence d'un objet utilisateur en session --%>
			<c:if test="${!empty sessionScope.sessionUtilisateur}">
				<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
				<p class="succes">Vous êtes connecté(e) avec l'adresse :
					${sessionScope.sessionUtilisateur.mail}</p>
			</c:if>
			</fieldset>
			</form>

		</c:otherwise>

	</c:choose>





</body>
<%@ include file="pages/footer.jsp"%>
</html>