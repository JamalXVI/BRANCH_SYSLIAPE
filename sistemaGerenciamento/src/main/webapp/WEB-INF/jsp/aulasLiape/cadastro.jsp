<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importação do Cabecalho -->
<t:cabecalho>
	<!-- Estilização da TAG select -->
	<link href="<c:url value='/assets/css/cupertino/jquery-ui-1.7.2.custom.css' />"
	rel="stylesheet">
	<link href="<c:url value='/assets/css/select2.min.css' />" rel="stylesheet">
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Seção Principal -->
<div class="row col-xs-12" role="main">
	<div class="container-fluid">
		<h1 id="tituloForm">Painel de Informação</h1>
	</div>
		<form action="${linkTo[AulasLiapeController].postar() }" method="post" id="form">
			<input id="idAula" name="aula.id" type="hidden" value="0" />
			<div class="col-lg-2 col-md-4 col-xs-6" id="divSelectSalas" >
			<label>Salas:</label> 
				<select class="form-control select_auto_completar" style="width: 100%;"
					id="selecionarSala" name="aula.sala">
				</select>
			</div>
			<div class="col-lg-2 col-md-4 col-xs-6" id="divSelectFlag" >
			<label>Status:</label> 
				<select class="form-control select_auto_completar" style="width: 100%;"
					id="selecionarFlag" name="aula.status">
					<option value="0">Aula Normal</option>
					<option value="1">Sala Livre</option>
					<option value="2">Manutenção</option>
				</select>
			</div>
			<t:input divCol="col-lg-3 col-md-4 col-xs-6"
			 id="descricao" nome="aula.descricao" nomeLabel="Descrição" valorPadrao="" 
			 tipo="text" placeholder="Nome Professor"></t:input>
			 <t:input divCol="col-lg-2 col-md-4 col-xs-6" id="entrada"
				nome="horaInicio" nomeLabel="Início:" valorPadrao=""
				tipo="text" classes="formHora"></t:input>
			<t:input divCol="col-lg-2 col-md-4 col-xs-6" id="saida"
				nome="horaFim" nomeLabel="Termino:" valorPadrao=""
				tipo="text" classes="formHora"></t:input>
			<div class="col-lg-1 col-md-4 col-xs-6 text-center">
				<button class="btn btn-primary" style="margin-top:25px;" type="submit">Cadastrar</button>
			</div>
		</form>
		<div class="col-lg-2 col-md-4 col-xs-6">
			<label>Filtro de Salas:</label> 
			<select class="form-control select_auto_completar" style="width: 100%;"
			id="selectFiltrarSalas">
			</select>
		</div>
		<div class="col-lg-2 col-md-4 col-xs-6">
			<label>Filtro de Turno:</label> 
			<select class="form-control select_auto_completar" style="width: 100%;"
			id="selectFiltrarHora">
				<option value="-1">Nenhum</option>
				<option value="0">Manhã</option>
				<option value="1">Tarde</option>
				<option value="2">Noite</option>
			</select>
		</div>
	<div class="col-xs-12">
		<div class='table-responsive espaco_cima'>
			<table id='TabelaAulas'
				class='table table-striped table-hover table-bordered'>
				<thead class='thead-inverse' style='background: #1e282c;'>
					<tr>
						<th class='my-table-label'>Sala</th>
						<th class='my-table-label'>Descrição</th>
						<th class='my-table-label'>Início</th>
						<th class='my-table-label'>Fim</th>
						<th class='my-table-label'>Status</th>
						<th class='my-table-label'>Ações</th>
					</tr>
				</thead>
				<tbody id='corpoTabelaAulas'>
				<tbody>
						
			</table>
		</div>
	</div>
	<div class="col-xs-12">
		<a href="" onclick="return renovando();" class="btn btn-primary">Renovar</a>
		<a href="" onclick="return nova();" class="btn btn-primary">Nova</a>
	</div>
</div>
<c:import url="/WEB-INF/jsp/aulasLiape_modal/modal_excluir.jsp" />
<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
	
	<!-- Importação do Javascript de  Criação de Eventos da Agenda de Reservas-->
	<script src="<c:url value='/assets/js/jquery-ui-1.7.2.custom.min.js' /> "></script>
	<!-- Importação do Javascript de Autocompletar Select-->
	<script src="<c:url value='/assets/js/select2.full.min.js' /> "></script>
	
	
	<script type="text/javascript">
		$(document).on("ready", function(){
			$.when(listarSalas()).done(function(){
				renovarLista();
				preencherSelectSala();
				$(".select_auto_completar").select2();
			})
			
		});
		var preencherSelectSala = function(){
			$("#selecionarSala").find("option").remove().end();
			$("#selectFiltrarSalas").find("option").remove().end();
			$("#selectFiltrarSalas").append("<option value=''>Todas</option>");
			$(salas).each(function(indice_sala, sala){
				$("#selecionarSala").append("<option value='"+sala.nome+"'>"+sala.nome+"</option>");
				$("#selectFiltrarSalas").append("<option value='"+sala.nome+"'>"+sala.nome+"</option>");
			})
		}
		$("#selectFiltrarSalas").on("change", function(){
			preencherTabela();
		})
		$("#selectFiltrarHora").on("change", function(){
			preencherTabela();
		})
		var renovarLista = function(){
			$.when(listar()).done(function(){
				preencherTabela();
			});
		}
		var preencherTabela = function(){
			$("#corpoTabelaAulas").find('tr').remove().end();
			$(aulasLiape).each(function(indice_sala, sala){
				if (sala.ativo 
					&& sala.sala.indexOf($("#selectFiltrarSalas").val()) != -1
					&& filtrarPeriodo(sala)) {
					var texto = "<tr><td>";
					texto += sala.sala+"</td><td>";
					texto += sala.descricao+"</td><td>";
					texto += retornarHora(sala.horaInicio)+"</td><td>";
					texto += retornarHora(sala.horaFim)+"</td><td>";
					texto += retornarFlag(sala.status)+"</td><td>";
					texto += criarTextoAcao("editar", "fa fa-pencil", indice_sala)
					+criarTextoAcao("remover", "fa fa-trash", sala.id)+"</td></tr>";
					$("#corpoTabelaAulas").append(texto);
				}
			});
		};
		var filtrarPeriodo = function(sala){
			var valor = $("#selectFiltrarHora").val();
			if (valor != -1) {
				var dataCi = new Date();
				var dataAi = new Date();
				var dataCf = new Date();
				var dataAf = new Date();
				dataAi.setHours(sala.horaInicio.hour);
				dataAi.setMinutes(sala.horaInicio.minute);
				dataAf.setHours(sala.horaFim.hour);
				dataAf.setMinutes(sala.horaFim.minute);
				switch (valor) {
				case "1":
					dataCi.setHours("12");
					dataCi.setMinutes("30");
					dataCf.setHours("18");
					dataCf.setMinutes("00");
					break;
				case "2":
					dataCi.setHours("18");
					dataCi.setMinutes("00");
					dataCf.setHours("23");
					dataCf.setMinutes("55");
					break;

				default:
					dataCi.setHours("06");
					dataCi.setMinutes("00");
					dataCf.setHours("12");
					dataCf.setMinutes("30");
					break;
				}
				if (dataCf.getTime() > dataAf.getTime() && dataAi.getTime() >= dataCi.getTime() ||
						dataCf.getTime() > dataAf.getTime() &&
					(dataAf.getTime() - dataAi.getTime()) > (dataCf.getTime() - dataCi.getTime()) ||
					dataAi.getTime() >= dataCi.getTime() &&
					(dataAf.getTime() - dataAi.getTime()) > (dataCf.getTime() - dataCi.getTime())) {
					return true;
				}else{
					return false;
				}
			}
			return true;

		}
		var retornarHora = function(hora)
		{
			return retornarComZero(hora.hour)+":"+retornarComZero(hora.minute);
		}
		var retornarFlag = function(flag){
			switch (flag) {
			case 1:
				return "Sala Livre";
				break;
			case 2:
				return "Manutenção";
				break;
			default:
				return "Aula Normal";
				break;
			}
		}
		var alterarTipoForm = function(){
			var id = $("#idAula").val();
			if (id > 0) {
				$("#tituloForm").html("Editar Aula");
			}else{
				$("#tituloForm").html("Inserir Aula");
			}
		}
		var retornarComZero = function(valor) {
			if (valor < 10) {
				return "0" + valor;
			} else {
				return valor;
			}
		}
		var criarTextoAcao = function(funcao, icone, id) {
			return "<a href='' style='margin-left:10px;' onclick='return "
					+ funcao + "(\"" + id
					+ "\")'><i class='fa-2x "+icone+"'></i></a>";
		}
		$("#selecionarFlag").on("change", function(){
			if ($(this).val() == "1") {
				$("#descricao").val("Sala Livre");
				$("#descricao").attr('readonly', true);
			}else if ($(this).val() == "2"){
				$("#descricao").val("Manutenção");
				$("#descricao").attr('readonly', true);
			}else{
				//$("#descricao").val("");
				$("#descricao").attr('readonly', false);
				
			}
		});
	</script>
	<script type="text/javascript">
		var aulasLiape;
		var salas;
		var editar = function(indice_sala){
			sala = aulasLiape[indice_sala];
			$("#idAula").val(sala.id);
			$("#entrada").val(retornarHora(sala.horaInicio));
			$("#saida").val(retornarHora(sala.horaFim));
			$("#descricao").val(sala.descricao);
			$(salas).each(function(i,e){
				if (e.nome === sala.sala) {
					$("#selecionarSala").val(e.nome).trigger("change");
				}
			});
			$("#selecionarFlag").val(sala.status).trigger("change");
			alterarTipoForm();
			return false;
		}
		var nova = function(){
			$("#idAula").val(0);
			$("#entrada").val("");
			$("#saida").val("");
			$("#descricao").val("");
			$("#selecionarFlag").val(0);
			alterarTipoForm();
			return false;
		}
		var renovando = function() {
			$("#excluirSala").val(0);
			$("#mensagemAviso").html("Tem certeza que deseja renovar?");
			$("#novoExcluirSala").modal("show");
			return false;
		}
		var listar = function() {
			return $.ajax({
				type : "post",
				url : "${linkTo[AulasLiapeController].lista()}",
				dataType : "json", // Isso diz que você espera um JSON do servidor
				beforeSend : function(xhr, settings) {
				},
				success : function(data, textStatus, xhr) {
					aulasLiape = data;
				}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
				error : function(xhr, textStatus, errorThrown) {
					tratarErroAjax(xhr, textStatus, errorThrown);
				}
			});
		}
		var renovar = function() {
			return $.ajax({
				type : "post",
				url : "${linkTo[AulasLiapeController].renovar()}",
				dataType : "json", // Isso diz que você espera um JSON do servidor
				beforeSend : function(xhr, settings) {
				},
				success : function(data, textStatus, xhr) {
					mensagemSucesso();
					aulasLiape = data;
					preencherTabela();
				}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
				error : function(xhr, textStatus, errorThrown) {
					tratarErroAjax(xhr, textStatus, errorThrown);
				}
			});
		}
		var remover = function(id){
			$("#excluirSala").val(id);
			$("#mensagemAviso").html("Tem certeza que deseja excluir?");
			$("#novoExcluirSala").modal("show");
			return false;
		}
		$("#excluirSalaSim").on("click", function(){
			var id = $("#excluirSala").val();
			if (id > 0) {
				removendo(id);
			}else if (id == 0)
			{
				renovar();
			}
		});
		var removendo = function(id){
			return $.ajax({
				type : "post",
				url : "${linkTo[AulasLiapeController].remover()}",
				data: {"id" : id},
				dataType : "json", // Isso diz que você espera um JSON do servidor
				beforeSend : function(xhr, settings) {
				},
				success : function(data, textStatus, xhr) {
					mensagemSucesso();
					renovarLista();
				}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
				error : function(xhr, textStatus, errorThrown) {
					tratarErroAjax(xhr, textStatus, errorThrown);
				}
			});
		}
		var listarSalas = function() {
			return $.ajax({
				type : "post",
				url : "${linkTo[SalaController].listar()}",
				dataType : "json", // Isso diz que você espera um JSON do servidor
				beforeSend : function(xhr, settings) {
				},
				success : function(data, textStatus, xhr) {
					salas = data;
				}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
				error : function(xhr, textStatus, errorThrown) {
					tratarErroAjax(xhr, textStatus, errorThrown);
				}
			});
		}
	</script>
	<script type="text/javascript">
		$("#form").validate(function(){});
		$("#entrada").rules("add",{
			required: true,
			  minlength: 5,
			  maxlength: 5,
			  hora: true,
			  messages: {
			    required: iniMensagemAlerta+"Campo obrigatório"+fimMensagemAlerta,
			    minlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por favor insira os {0} caracteres."+fimMensagemAlerta),
			    maxlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por favor insira os {0} caracteres."+fimMensagemAlerta)
			  }}
			);
		$("#saida").rules("add",{
			required: true,
			  minlength: 5,
			  maxlength: 5,
			  hora: true,
			  horaMaiorQue: "#entrada",
			  messages: {
			    required: iniMensagemAlerta+"Campo obrigatório"+fimMensagemAlerta,
			    minlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por favor insira os {0} caracteres."+fimMensagemAlerta),
			    maxlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por favor insira os {0} caracteres."+fimMensagemAlerta)
			  }}
			);
		
	</script>
</t:rodape>