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
			Visualizar <small>Mensagem no Mural</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[HomeController].index()}">Página
					Inicial</a></li>
			<li><a href="${linkTo[MuralController].entrada()}">Entrada</a></li>
			<li class="active">Mural</li>
		</ol>
		<div class="col-xs-12">
			<t:input divCol="col-xs-12"
			 id="mensagemDe" nome="mensagem.de" nomeLabel="De:" valorPadrao="${recado.turnoRemetente }"
			  tipo="text" classes="" editavel="1"></t:input>
			  <t:input divCol="col-xs-12"
			 id="mensagemTitulo" nome="mensagem.titulo" nomeLabel="Título:" 
			 valorPadrao="${recado.titulo }" tipo="text" classes="" editavel="1"></t:input>
			 <div class="col-xs-12">
			 <label for="mensagem.texto">Texto:</label>
			 <textarea rows="5" cols=""
			  name="mensagem.mensagem" class="form-control" readonly="readonly">${recado.descricao }</textarea>
			  
			 </div>
		</div>
	</div>

</div>
<t:rodape>
</t:rodape>