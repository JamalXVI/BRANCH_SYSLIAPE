<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importação do Cabecalho -->
<t:cabecalho>
	<!-- jQuery UI-->
	<link href="<c:url value="/assets/css/jquery-ui.css"/>"
		rel="stylesheet">
	<!-- Estilização da TAG select -->
	<style>
</style>
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>
<div class="col-lg-12">

	<div class="row" role="main">
		<h1 class="page-header" style="margin-top: 0px;">
			Visualizar <small>Ordem de Serviço</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[HomeController].index()}">Página
					Inicial</a></li>
			<li><a href="${linkTo[OrdemServicoController].listarAtivas()}">Listar OS</a></li>
			<li class="active">Visualizar</li>
		</ol>
		<div class="col-xs-12">
			<div class="col-xs-12 text-center">
			<label for="descricao">Descrição</label>
				<textarea id="descricao" rows="5" class="form-control" readonly="readonly">
				 ${ordem.descricao }
				</textarea>
			</div>
			<div class="col-xs-12 text-center">
				<label for="justificativa">Justificativa de Repasse (Caso Tenha)</label>
				<textarea id="justificativa" rows="5" class="form-control" readonly="readonly">
				${ordem.justificativa }
				</textarea>
			</div>
			<t:input divCol="col-xs-12" 
			id="pessoaRepasse" nome="" nomeLabel="Repassado Por" valorPadrao="${ordem.login }"
			 tipo="text" editavel="1">
			</t:input>
			  
			 </div>
		</div>
	</div>

</div>
<t:rodape>
</t:rodape>