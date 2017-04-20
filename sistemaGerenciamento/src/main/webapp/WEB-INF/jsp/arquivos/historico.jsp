<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importação do Cabecalho -->
<t:cabecalho>
	<!-- Estilização da TAG select -->
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Seção Principal -->
<div class="row col-xs-12" role="main">
	<h1 class="page-header" style="margin-top: 0px;">
		Histórico <small>Arquivos</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="${linkTo[HomeController].index()}">Página
				Inicial</a></li>
		<li><a href="${linkTo[ArquivosController].index()}">Arquivos</a></li>
		<li class="active">Histórico</a>
		</li>
	</ol>
	<div class="col-xs-12">
		<table id='tabelaListaProfessores' class='table table-striped table-hover table-bordered'>
			<thead class='thead-inverse' style='background: #1e282c;'>
				<tr>
					<th class='my-table-label'>Tipo Movimentação</th>
					<th class='my-table-label'>Data</th>
					<th class='my-table-label'>Usuário</th>
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
<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
	
</t:rodape>