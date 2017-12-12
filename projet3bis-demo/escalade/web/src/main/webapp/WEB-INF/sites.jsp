<%@ include file ="pages/header.jsp"  %>
<html>
<head>
<meta charset="utf-8" />
<title>Sites</title>
</head>
<body>

<%@ include file ="pages/navbar.jsp"  %>
<br/><br/><br/>
 
     
     <p>Liste des sites</p>
  
	 <div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<thead>
					<tr>
						<th>
							Nom site
						</th>
						<th>
							Pays site
						</th>
						<th>
							Region site
						</th>
					</tr>
				</thead>
				<c:forEach var="site" items="${ sites }">
				<tbody>
					<tr>
						<td>
						<c:out value="${ site.id }" />
							<c:out value="${ site.nomSite }" />
						</td>
						<td>
							<c:out value="${ site.pays }" />
						</td>
						<td>
							<c:out value="${ site.region }" />
						</td>
					</tr>
				</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
	 
 <%@ include file ="pages/footer.jsp"  %>