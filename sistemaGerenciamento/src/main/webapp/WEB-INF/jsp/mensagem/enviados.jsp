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
			Itens <small>Enviados</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[HomeController].index()}">Página
					Inicial</a></li>
			<li class="active">Enviados</li>
		</ol>
		<div class="col-xs-12">
			<div class='panel panel-default' id='panel_paginacao_tabela'>
				<div class='panel-body'>
					<div class='table-responsive espaco_cima'>
						<table id='tabelaMensagens'
							class='table table-striped table-hover table-bordered'>
							<thead class='thead-inverse' style='background: #1e282c;'>
								<tr>
									<th class='my-table-label'>Data</th>
									<th class='my-table-label'>Título</th>
									<th class='my-table-label'>Remetente</th>
									<th class='my-table-label'>Ações</th>
								</tr>
							</thead>
							<tbody id='corpoTabelaMensagens'>
							<tbody>
						</table>
					</div>
					<div class='panel-footer' id='rodapeTabelaPaginacao'></div>
				</div>
			</div>
		</div>
	</div>

</div>
<c:import url="/WEB-INF/jsp/mensagem_modal/modal_excluir.jsp" />

<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
	<!-- jQuery UI-->
	<script src="<c:url value='/assets/js/jquery-ui.js' />"></script>
	<c:import url="/WEB-INF/jsp/scripts/PaginacaoMensagemComum.jsp" />
	<script type="text/javascript">
		var receberMensagens = function() {
			return $.ajax({
				type : "post",
				url : "${linkTo[MensagemController].caixaEntrada() }",
				dataType : "json", // Isso diz que você espera um JSON do servidor
				beforeSend : function(xhr, settings) {
				},
				success : function(data, textStatus, xhr) {
					mensagens = data;

				}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
				error : function(xhr, textStatus, errorThrown) {
					tratarErroAjax(xhr, textStatus, errorThrown);
				}
			});
		}
		
	</script>
	
<c:import url="/WEB-INF/jsp/scripts/ExcluirMensagemComum.jsp" />
<script type="text/javascript">
	var apagarMensagens = function(idRec) {
		return $.ajax({
			type : "post",
			url : "${linkTo[MensagemController].apagarMensagemEnviada() }",
			data: {"idRec" : idRec},
			dataType : "json", // Isso diz que você espera um JSON do servidor
			beforeSend : function(xhr, settings) {
				
			},
			success : function(data, textStatus, xhr) {
				$("#mensagemErro").fadeIn(0);
		    	if (data.mensagem.indexOf("Erro") != -1) {
		    		formularErro(data.mensagem);
				};
			}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
			error : function(xhr, textStatus, errorThrown) {
				tratarErroAjax(xhr, textStatus, errorThrown);
			}
		});
	}
</script>
</t:rodape>