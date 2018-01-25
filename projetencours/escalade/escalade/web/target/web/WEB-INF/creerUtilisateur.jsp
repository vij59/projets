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
	<script>
		function stripspaces(input) {
			input.value = input.value.replace(/\s/gi, "");
			return true;
		}
	</script>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="well well-sm">
					<form class="form-horizontal" method="post"
						action="creerUtilisateur">
						<fieldset>
							<legend class="text-center header">Inscrivez-vous</legend>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-user bigicon"></i></span>
								<div class="col-md-6">
									<input type="text" name="nom" id="nom" required
										placeholder="Nom" class="form-control"
										onkeydown="javascript:stripspaces(this)"> <span
										class="erreur" style="color: red">${errorNom}</span>
								</div>
							</div>
							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-user bigicon"></i></span>
								<div class="col-md-6">
									<input type="text" name="prenom" id="prenom" required
										placeholder="Prénom" class="form-control"
										onkeydown="javascript:stripspaces(this)"> <span
										class="erreur" style="color: red">${errorPrenom}</span>
								</div>
							</div>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-envelope-o bigicon"></i></span>
								<div class="col-md-6">
									<input type="email" name="mail" id="mail" required
										placeholder="Email" class="form-control"
										onkeydown="javascript:stripspaces(this)"> <span
										class="erreur" style="color: red">${errorMail}</span>
								</div>
							</div>

							<div class="form-group">
								<span class="col-md-1 col-md-offset-2 text-center"><i
									class="fa fa-phone-square bigicon"></i></span>
								<div class="col-md-6">
									<input type="password" name="mdp" id="mdp" required
										placeholder="Mot de passe" class="form-control"
										onkeydown="javascript:stripspaces(this)"> <span
										class="erreur" style="color: red">${errorMdp}</span>
								</div>
							</div>

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
	<%@ include file="pages/footer.jsp"%>