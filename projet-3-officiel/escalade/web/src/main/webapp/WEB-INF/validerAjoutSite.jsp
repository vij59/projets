<%@ page pageEncoding="UTF-8"%>
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
	<c:choose>

		<c:when test="${ !empty form.resultat }">
			<p>
				<c:out value="Bonjour ${ form.resultat }" />
			</p>
		</c:when>

		<c:otherwise>
			<form method="post" action="ajouterSite">
				<label for="nom_site">nom du site : </label> <input type="text"
					name="nom_site" id="nom_site" /><br /> <label for="pays">pays
					: </label> <input type="text" name="pays" id="pays" /><br /> <label
					for="region">region : </label> <input type="text" name="region"
					id="region" /><br /> <label for="code_postal">code postal
					: </label> <input type="text" name="code_postal" id="code_postal" /><br />

				<input type="submit" />
			</form>
		</c:otherwise>
	</c:choose>


	<style type="text/css">
.entry:not (:first-of-type ) {
	margin-top: 10px;
}

.glyphicon {
	font-size: 12px;
}
</style>
	<script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
        window.alert = function(){};
        var defaultCSS = document.getElementById('bootstrap-css');
        function changeCSS(css){
            if(css) $('head > link').filter(':first').replaceWith('<link rel="stylesheet" href="'+ css +'" type="text/css" />'); 
            else $('head > link').filter(':first').replaceWith(defaultCSS); 
        }
        $( document ).ready(function() {
          var iframe_height = parseInt($('html').height()); 
          window.parent.postMessage( iframe_height, 'https://bootsnipp.com');
        });
    </script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="control-group" id="fields">
				<label class="control-label" for="field1">Nice Multiple Form
					Fields</label>
				<div class="controls">
					<form role="form" autocomplete="off">
						<div class="entry input-group col-xs-3">
							<input class="form-control" name="fields[]" type="text"
								placeholder="Type something" /> <span class="input-group-btn">
								<button class="btn btn-success btn-add" type="button">
									<span class="glyphicon glyphicon-plus"></span>
								</button>
							</span>
						</div>
					</form>
					<br> <small>Press <span
						class="glyphicon glyphicon-plus gs"></span> to add another form
						field :)
					</small>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function()
{
    $(document).on('click', '.btn-add', function(e)
    {
        e.preventDefault();

        var controlForm = $('.controls form:first'),
            currentEntry = $(this).parents('.entry:first'),
            newEntry = $(currentEntry.clone()).appendTo(controlForm);

        newEntry.find('input').val('');
        controlForm.find('.entry:not(:last) .btn-add')
            .removeClass('btn-add').addClass('btn-remove')
            .removeClass('btn-success').addClass('btn-danger')
            .html('<span class="glyphicon glyphicon-minus"></span>');
    }).on('click', '.btn-remove', function(e)
    {
		$(this).parents('.entry:first').remove();

		e.preventDefault();
		return false;
	});
});

	</script>
	<p>Liste des sites</p>

	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<thead>
						<tr>
							<th>Nom site</th>
							<th>Pays site</th>
							<th>Region site</th>
						</tr>
					</thead>
					<c:forEach var="site" items="${ sites }" varStatus="status">
						<tbody>
							<tr>
								<td><c:out value="${ status.last }" /> <c:out
										value="${ site.nomSite }" /></td>
								<td><c:out value="${ site.pays }" /></td>
								<td><c:out value="${ site.region }" /></td>
								<td>
									<button href="ajouterSecteur.jsp">Ajouter Secteur</button>
								</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<%@ include file="pages/footer.jsp"%>