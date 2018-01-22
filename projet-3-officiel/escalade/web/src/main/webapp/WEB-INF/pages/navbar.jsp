<%@ page pageEncoding="UTF-8"%>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-default navbar-fixed-top" role="navigation">

				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="/web/">Accueil</a></li>

						<li class="dropdown"><a href="/web/sites"
							class="dropdown-toggle" data-toggle="dropdown">Sites<strong
								class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="/web/sites">Liste des sites</a></li>
								<li><a href="/web/ajouterSite">Ajouter un site</a></li>

							</ul></li>

						<li class="dropdown"><a href="/web/topos"
							class="dropdown-toggle" data-toggle="dropdown">Topos<strong
								class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="/web/topos">Liste des topos</a></li>
								<li><a href="/web/ajouterTopo">Ajouter un topo</a></li>
								<li><a href="/web/mesReservations">Mes réservations</a></li>
							</ul></li>
						<c:if test="${sessionUtilisateur.role != 1}">
							<li class="dropdown"><a href="/web/connexion"
								class="dropdown-toggle" data-toggle="dropdown">Connexion<strong
									class="caret"></strong></a>
								<ul class="dropdown-menu">
									<li><a href="/web/betaConn">Se connecter</a></li>
									<li><a href="/web/creerUtilisateur">S'inscrire</a></li>
								</ul></li>
						</c:if>
					</ul>


					<ul class="nav navbar-nav navbar-right" style="padding-right: 20px">

						<li><a href="/web/recherche"><span
								class="glyphicon glyphicon-search"></span> Recherche </a></li>

						<c:if test="${sessionUtilisateur.role == 1}">
							<li>
								<form method="post" action="deconnexion">
									<b><input type="submit" value="Déconnecter"
										style="text-decoration: none; margin: 0; padding: 0; line-height: 100%; /* Si ça fonctionne pas mets 0 */ border: 0; margin-top: 15%; margin-right: 1.5em; margin-left: 1em; background: none; /* ou transparent je sais plus... */ font: /* Définis le même font qu'un lien traditionnel */; color: blue /* La même couleur... */;" />
									</b>
								</form>
							</li>
						</c:if>


					</ul>
				</div>

			</nav>