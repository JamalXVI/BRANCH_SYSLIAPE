<!-- Importa��o JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Importa��o das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importa��o do Cabecalho -->
<t:cabecalho>
	<!-- Estiliza��o da TAG select -->
</t:cabecalho>
<!-- Importa��o do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Se��o Principal -->
<div class="row col-xs-12" role="main">
	<h1 class="page-header" style="margin-top: 0px;">
		Hist�rico <small>Arquivos</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="${linkTo[HomeController].index()}">P�gina
				Inicial</a></li>
		<li><a href="${linkTo[ArquivosController].index()}">Arquivos</a></li>
		<li class="active">Hist�rico</a>
		</li>
	</ol>
	<div class="col-xs-12">
		<table id='tabelaListaProfessores' class='table table-striped table-hover table-bordered'>
			<thead class='thead-inverse' style='background: #1e282c;'>
				<tr>
					<th class='my-table-label'>Tipo Movimenta��o</th>
					<th class='my-table-label'>Data</th>
					<th class='my-table-label'>Usu�rio</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${movimentacoes }" var="movimento">
					<tr>
						<td>${movimento.movimentacao }</td>
						<td><fmt:formatDate type="both" 
						  pattern="dd/MM/yyyy HH:mm"  value="${movimento.data}" /></td>
						<td>${movimento.login }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!-- Scripts Espec�ficos e Importa��o do Rodap� -->
<t:rodape>
	
</t:rodape>