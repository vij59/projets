<%@ page pageEncoding="UTF-8"%>
<%@ include file="pages/header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>Connexion</title>
</head>
<body>
<html>
<head>
<meta charset="utf-8" />
<title>Connexion</title>
<link type="text/css" rel="stylesheet" href="form.css" />
</head>
<body>
	<%@ include file="pages/navbar.jsp"%>
	<br />
	<br />
	<br />

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<form role="form" method="post" action="connexion">
					<div class="form-group">

						<label for="nom">Adresse email <span class="requis">*</span></label>
						<input type="email" id="email" name="email"
							value="<c:out value="${utilisateur.mail}"/>" size="20"
							maxlength="60" /> <span class="erreur">${form.erreurs['mail']}</span>
					</div>

					<div class="form-group">

						<label for="motdepasse">Mot de passe <span class="requis">*</span></label>
						<input type="password" id="motdepasse" name="motdepasse" value=""
							size="20" maxlength="20" /> <span class="erreur">${form.erreurs['motdepasse']}</span>
					</div>

					<div class="form-group">

						<label for="exampleInputFile"> File input </label> <input
							type="file" id="exampleInputFile" />
						<p class="help-block">Example block-level help text here.</p>
					</div>

					<button type="submit" value="Connexion" class="btn btn-default">
						Submit</button>

				</form>
				<p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>

				<%-- Vérification de la présence d'un objet utilisateur en session --%>
				<c:if test="${!empty sessionScope.sessionUtilisateur}">
					<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
					<p class="succes">Vous êtes connecté(e) avec l'adresse :
						${sessionScope.sessionUtilisateur.mail}</p>
				</c:if>
			</div>
		</div>
	</div>


	<%@ include file="pages/footer.jsp"%>