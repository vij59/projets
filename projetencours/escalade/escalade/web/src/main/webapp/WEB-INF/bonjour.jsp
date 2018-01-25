<%@ page pageEncoding="UTF-8"%>
<%@ include file="pages/header.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>Accueil</title>

</head>

<body>
	<%@ include file="pages/navbar.jsp"%>
	<br />
	<br />
	<br />
	<br />


	<center>
		<div class="jumbotron">
			<h2>Bienvenue sur le site des fondus de la grimpette</h2>
			<p>Rejoignez notre communauté et partagez vos parcours d'escalade
			</p>
			<c:if test="${sessionUtilisateur.role != 1}">
			
			<p>
				<a class="btn btn-primary btn-large" href="/web/betaConn">Se
					connecter</a>
					<h6>
					Pas encore inscrit ? <a href="/web/creerUtilisateur">Créez
												votre espace</a>
												</h6>
			</p>
			</c:if>
			
			<p>
				<a class="btn btn-success btn-large" href="/web/sites">Nos sites</a>
			
				<a class="btn btn-success btn-large" href="/web/topos">Nos topos</a>
			</p>
		</div>
	</center>

<div class="container-fluid">
	<div class="row">
		<div class="col-md-4">
			<h1 class="page_title">
				ONDRA finit le DAWN WALL
			</h1>
			<p>
				<span>Ce n’était plus qu’une question de temps… Une semaine après avoir quitté le sol, Adam Ondra est venu à bout des 32 longueurs de la grande voie la plus dure de la planète, le « Dawn Wall ».</span>
			</p>
			<p>
				<a class="btn" href="https://planetgrimpe.com/2016/11/boum-adam-ondra-enchaine-32-longueurs-dawn-wall/">Plus de détails »</a>
			</p>
		</div>
		
		<div class="col-md-4">
			<h1 class="entry-title">
			</h1>
			<h1 class="page_title">
				MARGO HAYES grimpe BRIO
			</h1>
			
			<p>
				<span>En septembre dernier, Margo Hayes devenait la première "répétitrice" (comprendre la première femme à enchainer cette voie) de la célèbre voie "Biographie", à Céüse .</span>
			</p>
			
			<p>
				<a class="btn" href="http://kairn.com/video-margo-hayes-grimpe-bio/">Voir la vidéo »</a>
			</p>
		</div>
		<div class="col-md-4">
			<h1 class="page_title">
				Les salles recrutent
			</h1>
			<p>
				<span>Les salles d'escalade de Marseille et Aix en Provence recrutent des passionnés aux postes d'hote d'accueil et d'ouvreurs pour débuter en Février 2018.</span>
			</p>
			<p>
				<a class="btn" href="http://www.grimper.com/news-les-salles-grimper-recrutent">Plus d'informations »</a>
			</p>
		</div>
	</div>
</div>

	<%@ include file="pages/footer.jsp"%>