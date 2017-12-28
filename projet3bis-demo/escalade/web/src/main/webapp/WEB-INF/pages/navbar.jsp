<%@ page pageEncoding="UTF-8" %>
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active">
							<a href="/web/">Accueil</a>
						</li>
						
						<li class="dropdown">
							 <a href="/web/sites" class="dropdown-toggle" data-toggle="dropdown">Sites<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="/web/sites">Liste des sites</a>
								</li>
								<li>
									<a href="/web/ajouterSite">Ajouter un site</a>
								</li>
								<li>
									<a href="#">Modifier un site</a>
								</li>
							</ul>
						</li>
						
						<li class="dropdown">
							 <a href="/web/topos" class="dropdown-toggle" data-toggle="dropdown">Topos<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="/web/topos">Liste des topos</a>
								</li>
								<li>
									<a href="/web/ajouterTopo">Ajouter un topo</a>
								</li>
								<li>
									<a href="/web/mesReservations">Mes réservations</a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							 <a href="/web/connexion" class="dropdown-toggle" data-toggle="dropdown">Connexion<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="/web/betaConn">Se connecter</a>
								</li>
								<li>
									<a href="/web/creerUtilisateur">S'inscrire</a>
								</li>
							</ul>
						</li>
					</ul>
					
					
					<ul class="nav navbar-nav navbar-right">
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" />
						</div> 
						<button type="submit" class="btn btn-default">
							Submit
						</button>
					</form>
						<li>
							<a href="/web/recherche">Recherche</a>
						</li>
						
						<c:if test="${sessionUtilisateur.role == 1}">
						<li>
        <form method="post" action="deconnexion">
        	<input type="submit" value="Deconnecter" style="margin: 0;
    padding: 0;
    line-height: 100%; /* Si ça fonctionne pas mets 0 */
    border: 0;
    margin-top: 22%;
    background: none; /* ou transparent je sais plus... */
    font: /* Définis le même font qu'un lien traditionnel */;
    color: /* La même couleur... */;
    text-decoration: underline; /* Normalement */"/>
        </form>
         </li>
        </c:if>
       
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<c:if test="${sessionUtilisateur.role == 1}">
						<li>
        <form method="post" action="deconnexion">
        	<input type="submit" value="Deconnecter" style="margin: 0;
    padding: 0;
    line-height: 100%; /* Si ça fonctionne pas mets 0 */
    border: 0;
    background: none; /* ou transparent je sais plus... */
    font: /* Définis le même font qu'un lien traditionnel */;
    color: /* La même couleur... */;
    text-decoration: underline; /* Normalement */"/>
        </form>
         </li>
        </c:if>
								</li>
								<li>
									<a href="#">Another action</a>
								</li>
								<li>
									<a href="#">Something else here</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a href="#">Separated link</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				
			</nav>