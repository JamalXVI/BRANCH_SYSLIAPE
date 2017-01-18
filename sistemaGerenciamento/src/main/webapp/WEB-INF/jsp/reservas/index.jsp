<!-- Importação JSTLs -->
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importação do Cabecalho -->
<t:cabecalho>
	<!-- Estilização da TAG select -->

	<!-- Estilização da Agenda de Reservas -->

	<link
		href="<c:url value='/assets/css/cupertino/jquery-ui-1.7.2.custom.css' />"
		rel="stylesheet">

	<link href="<c:url value='/assets/css/ger.css' />" rel="stylesheet">

	<link href="<c:url value='/assets/css/select2.min.css' />"
		rel="stylesheet">
	<style>
/* override bootstrap 3 class to remove scrollbar from modal backdrop
   when not necessary */
.modal {
	overflow-y: auto;
}
.upperCase{
text-transform:uppercase;
}
/* custom class to override .modal-open */
.modal-noscrollbar {
	margin-right: 0 !important;
}

#tabelaGrade>tbody>tr>td {
	height: 12px;
	padding: 0px;
	border-top: 0px;
}
.dataCalendario{
	background-color: #CFD8DC;
	font-size:20px;
}
.caixa {
	width: 95%;
	height: 19px;
	position: absolute;
	background-color: #ddd;
	margin-top: 0px;
	-webkit-box-shadow: 3px 4px 11px -1px rgba(0, 0, 0, 0.51);
	-moz-box-shadow: 3px 4px 11px -1px rgba(0, 0, 0, 0.51);
	box-shadow: 3px 4px 11px -1px rgba(0, 0, 0, 0.51);
}

.colorido,  #tabelaGrade thead th:nth-of-type(2n) {
	background: #ffffc8;
}

.caixaMaior {
	position: relative;
	width: 100%;
}
#minuto {
	background-color: #f7f7f7;
}
.cor_verde {
	color: #8BC34A;
}

.cor_verde:hover {
	color: #64B5F6;
}
.btn-cinza{
	
    background-color: #CFD8DC;
    color: black;
    border-color: black;
	
}
.modal_espacado{
	margin-top: 400px;
}
.cancelar_reserva{
	width: 15px;
}
@media ( min-width : 768px) {
	.cancelar_reserva{
		width: 30px;
	}
}
#formReserva > div{
float:right;
}
</style>

</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Seção Principal -->
<div class="right_col" role="main">
	<div class="geral_form" style="width: 100%">
		<div id="esq">
			<div id="mensagemErro">
			
			</div>
			<img src="<c:url value='/assets/img/fechar.png' />" alt="Fechar" style="display:none"
			id="fecharBase" />
			
			<t:centralizarDiv divCol="12" divColmd="6">

			
				<div class="col-xs-6">
					<label for="calendarioReserva">Data:</label>
					<input type="datetime" value="${agora }" max="2050-01-01" min="2001-01-01" name="calendarioReserva"
					id="calendarioReserva" class="dataCalendario form-control text-center" />
				</div>
				<div class="col-xs-6">
<!-- 					<label>Outras:</label> -->
<!-- 					<a class="btn btn-info" href="" onclick="return mostrarOpcoes();"><i class="fa fa-cog" aria-hidden="true"></i>Opções</a> -->
					<label>Selecione uma Opção:</label> 
					<select
					class="form-control select_auto_completar" style="width: 100%;"
					id="opcaoExtra" name="">
					<option value="">---Selecione uma Opção---</option>
					<option class="permissaoSala" value="aparecerModalSala">Cadastro de Sala</option>
					<option class="permissaoDisciplina" value="aparecerModalDisciplina">Cadastro de Desciplina</option>
					<option class="permissaoCurso" value="aparecerModalCurso">Cadastro de Curso</option>
					<option class="" value="aparecerModalSlctAnoSemestre">Ano/Semestre</option>
					<option class="permissao" value="aparecerModalReserva">Cadastro de Reserva</option>
					<option class="" value="verExporadicasMesModal">Ver Exporádicas do Mês</option>
					
					</select>
				</div>
			</t:centralizarDiv>
			<div>
				<table class="table table-striped table-hover table-bordered"
					id="tabelaGrade">
					<thead id="tabelaGradeThead">
						<tr>
							<th>HH</th>
							<th id="minuto" style="background-color: #f7f7f7;">MM</th>
	
						</tr>
					</thead>
	
					<c:forEach var="i" begin="6" end="22">
						<tr id="linha_Aula_00_${i }">
							<td rowspan="2" class="hora"><c:if test="${i < 10 }">
				0${i}
			</c:if> <c:if test="${i > 9 }">
				${i}
			</c:if></td>
							<td class="min00 minSize">00</td>
	
	
						</tr>
						<tr id="linha_Aula_30_${i }">
							<td class="min30 minSize" onclick="">30</td>
	
						</tr>
					</c:forEach>
	
				</table>
			</div>
			<!-- Importar Modals -->
			<c:import url="/WEB-INF/jsp/reservas_modal/modal_ano_semestre.jsp" />
			<c:import url="/WEB-INF/jsp/reservas_modal/modal_sala.jsp" />
			<c:import url="/WEB-INF/jsp/reservas_modal/modal_disciplina.jsp" />
			<c:import url="/WEB-INF/jsp/reservas_modal/modal_curso.jsp" />
			<c:import
				url="/WEB-INF/jsp/reservas_modal/modal_select_ano_semestre.jsp" />
			<c:import url="/WEB-INF/jsp/reservas_modal/modal_reserva.jsp" />
			<c:import url="/WEB-INF/jsp/reservas_modal/modal_info_reserva.jsp" />
<%-- 			<c:import url="/WEB-INF/jsp/reservas_modal/modal_opcoes.jsp" /> --%>
			<c:import url="/WEB-INF/jsp/reservas_modal/modal_deletar_reserva_exp.jsp" />
			<c:import url="/WEB-INF/jsp/reservas_modal/modal_deletar_reserva_sem.jsp" />
			<c:import url="/WEB-INF/jsp/reservas_modal/modal_deletar_ano_semestre.jsp" />
			<c:import url="/WEB-INF/jsp/reservas_modal/modal_ver_exporadicas_mes.jsp" />
		</div>

		<div style="clear: both"></div>
	</div>

</div>

<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>

<!-- Importação do Javascript de  Criação de Eventos da Agenda de Reservas-->
<script src="<c:url value='/assets/js/jquery-ui-1.7.2.custom.min.js' /> "></script>

<!-- Importação do Javascript de Autocompletar Select-->
<script src="<c:url value='/assets/js/select2.full.min.js' /> "></script>
<script type="text/javascript" >

var retornarMinuto = function(minuto)
{
	if (minuto < 10) {
		return "0"+minuto;
	}
	return minuto;
}
</script>
<!-- Script de Execução de Cadastro de Sala -->
<script type="text/javascript">
//Aparecer modal de Sala
var aparecerModalSala = function()
{
	$('#novaSala').modal('toggle');
	return false;
};
//Enviar Formulário de Ajax de Cadastramento de Sala
var cadastrarSala = function(){
	if($("#formSala").valid())
	{
		$.ajax({  
		    type:"post",  
		    url: "${linkTo[SalaController].postar() }", 
		    data: $("#formSala").serialize(),
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	popularSala(data);
		    	//popularSelectAnoSemestre(data);
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){ 
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});		
	}else{
		formularErro("Formulário Inválido!");
	}
	
};
</script>
<!-- Cadastro de Disciplina -->
<script type="text/javascript">
var aparecerModalDisciplina = function()
{
	$("#novaDisciplina").modal("toggle");
	return false;
};
//POPULAR DISCIPLINA
var listarDisciplinaAjax = function(){
	
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[DisciplinaController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	popularDisciplina(data);
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
var hashDisciplina = {};
//Renovar Lista de Disciplinas no Form de Reserva
function popularDisciplina(disciplinas){
	$(disciplinas).each(function(i,e){
		hashDisciplina[e.codigo] = e;
	});
	$("#selecionarDisciplina")
    .find('option')
    .remove()
    .end();
	$(disciplinas).each(function(indice, disciplina){
		$("<option />", {value: disciplina.codigo, text: disciplina.codigo + " - "+ disciplina.nome}).appendTo($("#selecionarDisciplina"));
	});
	
};
listarDisciplinaAjax();
//Ajax de Cadastramento de Disciplina
var cadastrarDisciplina = function(){
	if($("#formDisciplina").valid())
	{
		$.ajax({  
		    type:"post",  
		    url: "${linkTo[DisciplinaController].postar() }", 
		    data: $("#formDisciplina").serialize(),
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	popularDisciplina(data);
		    	//popularSala(data);
		    	//popularSelectAnoSemestre(data);
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){ 
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});		
	}else{
		formularErro("Formulário Inválido!");
	}
	
};
</script>
<!-- Popular Professores -->
<script type="text/javascript">
//POPULAR PROFESSOR
var listarProfessorAjax = function(){
	
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[ProfessorController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	popularProfessor(data);
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
var hashProfessores = {};
//Renovar Lista de Professores no Form de Reserva
function popularProfessor(professores){
	$(professores).each(function(i,e){
		hashProfessores[e.codigo] = e;
	});
	$("#selecionarProfessor")
    .find('option')
    .remove()
    .end();
	$(professores).each(function(indice, professor){
		$("<option />", {value: professor.codigo, text: professor.codigo + " - "+ professor.pessoa.nome+
			" "+professor.pessoa.sobrenome }).appendTo($("#selecionarProfessor"));
	});
	
};
listarProfessorAjax();

</script>


<!-- Cadastro de Curso -->
<script type="text/javascript">
var aparecerModalCurso = function()
{
	$("#novoCurso").modal("toggle");
	return false;
};
//POPULAR CURSO
var listarCursoAjax = function(){
	
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[CursoController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	popularCurso(data);
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
hashCurso = {};
//Renovar Lista de Cursos no Form de Reserva
function popularCurso(cursos){
	$("#selecionarCursoReserva")
    .find('option')
    .remove()
    .end();
	$(cursos).each(function(indice, curso){
		hashCurso[curso.codigo] = curso;
		$("<option />", {value: curso.codigo, text: curso.nome}).appendTo($("#selecionarCursoReserva"));
	});
	
};
listarCursoAjax();
//Ajax de Cadastramento de Curso
var cadastrarCurso = function(){
	if($("#formCurso").valid())
	{
		$.ajax({  
		    type:"post",  
		    url: "${linkTo[CursoController].postar() }", 
		    data: $("#formCurso").serialize(),
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	popularCurso(data);
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){ 
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});		
	}else{
		formularErro("Formulário Inválido!");
	}
	
};
</script>
<!-- Aparecer Select de Ano Semestre -->
<script type="text/javascript">
var aparecerModalSlctAnoSemestre = function()
{
	$("#novoSlctAnoSemestre").modal("toggle");
	return false;
};
</script>
<!-- Script de Execução de Reserva -->
<script type="text/javascript">
var aparecerModalReserva = function(){
	$('#novaReserva').modal('toggle');
	$("#selecionarDiaSemanaSemestral").val(diaSemana).trigger("change");
	return false;
}

</script>

<!-- Script de Execução de Cadastro de AnoSemestre -->
<script type="text/javascript">
//Verificar Ano Semestre de Acordo com a Data
$("#selecionarAnoSemestre").on("change", function(){
	if(dataValida())
	{
		cadastrarSlctAnoSemestre();
	}else{
		
		formularErro("Data Inválida! Por Favor Insira uma data Válida!");
		restaurarDataInicial();
	}
	
})
var anoSemestreSelecionado;
var anoSemestres;
var primeiraVezAlterarCalendario = false;
//Alterar quando o Ano/Semestre for Selecionado, alterar data do calendário
var alterarAnoSemestreSelecionado = function(){
	$("#anoReserva").val(anoSemestreSelecionado.ano);
	$("#semestreReserva").val(anoSemestreSelecionado.semestre);
	$("#calendarioReserva").attr({"max": anoSemestreSelecionado.DataFim.year
			+"-"+retornarMinuto(anoSemestreSelecionado.DataFim.month)+
			"-"+retornarMinuto(anoSemestreSelecionado.DataFim.day),
			"min": anoSemestreSelecionado.DataIni.year
			+"-"+retornarMinuto(anoSemestreSelecionado.DataIni.month)+
			"-"+retornarMinuto(anoSemestreSelecionado.DataIni.day)
			});
	if (primeiraVezAlterarCalendario) {
		restaurarDataInicial();
	}else{
		primeiraVezAlterarCalendario = true
	}
	
}
//Restaurar a Data Inicial do Ano/Semestre caso haja Erro
var restaurarDataInicial =  function(){
	$("#calendarioReserva").val(anoSemestreSelecionado.DataIni.year
			+"-"+retornarMinuto(anoSemestreSelecionado.DataIni.month)+
			"-"+retornarMinuto(anoSemestreSelecionado.DataIni.day));
}
//Mostrar Modal de Ano Semestre
var aparecerModalAnoSemestre = function()
{
	$('#novoAnoSemestre').modal('toggle');
	return false;
};
//Cadastrar Ano/Semestre
var cadastrarAnoSemestre = function(){
	if($("#formAnoSemestre").valid())
	{
		$.ajax({  
		    type:"post",  
		    url: "${linkTo[AnoSemestreController].postar() }", 
		    data: $("#formAnoSemestre").serialize(),
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	popularSelectAnoSemestre(data);
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){ 
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});		
	}else{
		formularErro("Formulário Inválido!");
	}
	
};

//POPULAR AnoSemestre
var listarAnoSemestreAjax = function(){
	
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[AnoSemestreController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	popularSelectAnoSemestre(data);
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
listarAnoSemestreAjax();
//Script de População de Todos os Anos/Semestres na Lista de Ano/Semestre
var popularSelectAnoSemestre = function(anoSemestre)
{
	anoSemestres = anoSemestre;
	$("#selecionarAnoSemestre")
	    .find('option')
	    .remove()
	    .end();
	$("#selecionarAnoSemestre").append('<option value="">---Selecione um Ano/Semestre---</option>');
	$(anoSemestre).each(function(indice, elemento){
		if (!anoSemestreSelecionado && indice == 0) {
			anoSemestreSelecionado = elemento;
			alterarAnoSemestreSelecionado();
		}
		var sem =  elemento.semestre+1
		//$("#selecionarGrupo").append("<option value='"+elemento.id+"'>"+elemento.nome+"</option>")
		$("<option />", {value: indice, text: elemento.ano+"/"+sem+"º Semestre"}).appendTo($("#selecionarAnoSemestre"));
	});
}
//Mudar Ano/Semestre Selecionado
var cadastrarSlctAnoSemestre = function(){
	anoSemestreSelecionado = anoSemestres[$("#selecionarAnoSemestre").val()];
	alterarAnoSemestreSelecionado();
}
/*
 * EXCLUSÃO DE ANO SEMESTRE
 */
 //REQUISIÇÃO DE EXCLUSÃO AJAX
var removerAnoSemestre = function(ano,semestre){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[AnoSemestreController].remover() }", 
	    data: {"ano" : ano, "semestre" :  semestre},
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});		
}
//APARECER MODAL DE EXCLUSÃO E GUARDAR ANO/SEMESTRE A EXCLUIR
var excluirAnoSemestre = function(){
	var ans = anoSemestres[$("#selecionarAnoSemestre").val()];
	 $("#deletarAnoSemestreAno").val(ans.ano);
	 $("#deletarAnoSemestreSemestre").val(ans.semestre);
	 $("#novoDeletarAnoSemestre").modal('show');
	return false;
}
//CONFIRMAR EXCLUSÃO
$("#excluirAnoSemestreSim").on("click", function(){
	var ano = $("#deletarAnoSemestreAno").val();
	var semestre = $("#deletarAnoSemestreSemestre").val();
	$.when(removerAnoSemestre(ano,semestre)).done(function(){
		popularSelectAnoSemestre(data);	
	});
	
})
/*
 * FIM DA EXCLUSÃO
 */
//Validar Input Calendário
function dataValida(){ 
      var inputDate = new Date($("#calendarioReserva").val());
      return validarData(inputDate);
}
//Script de Verificação se a Data é Válida
var validarData = function(inputDate){
	if (inputDate == " " || inputDate == "Invalid Date"){
        return false;
    } else {
  	  if (validarComAnoSemestre(inputDate)) {
            return true;
			
		  }else{
			  return false;
		  }
    }
}
//Script de Verificação se a Data está dentro do Ano/Semestre
var validarComAnoSemestre = function(inputDate){
	if (inputDate.getFullYear() >= anoSemestreSelecionado.DataIni.year &&
			inputDate.getFullYear() <= anoSemestreSelecionado.DataFim.year) {
		if ((inputDate.getMonth() > anoSemestreSelecionado.DataIni.month-1 &&
			inputDate.getMonth() < anoSemestreSelecionado.DataFim.month-1) ||
			(inputDate.getMonth() == anoSemestreSelecionado.DataIni.month-1 && 
			inputDate.getDate() >= anoSemestreSelecionado.DataIni.day-1) ||
			(inputDate.getMonth() == anoSemestreSelecionado.DataFim.month-1 && 
			inputDate.getDate() <= anoSemestreSelecionado.DataFim.day-1)) {
				return true;
		}else{
			return false;
		}
	}else{
		return false;
	}
}
</script>
<!-- Script de Alteração do Tipo de Reserva -->
<script type="text/javascript">
	$("#selecionarTipoReserva").on("change", function(){
		atualizarTipoReserva();
	});
	//Atualizar o Formulário de Reserva de Acordo com o Tipo de Reserva
	var atualizarTipoReserva = function(){
		$("#divTipoReserva").find("div").remove().end();
		if($("#selecionarTipoReserva").val() == "semestral")
		{
			semestral();
		}else if($("#selecionarTipoReserva").val() == "exporadica")
		{
			exporadica();
		};
	};
	$().ready(function(){
		atualizarTipoReserva();
	});
	//Mostrar dados de Reserva Semestral
	var semestral = function(){
		$("#divTipoReserva").append("<div class='col-xs-12 col-md-6'><label>Selecione o dia da Semana:</label>"+
				"<select class='form-control select_auto_completar' style='width: 100%;'"+
					"id='selecionarDiaSemanaSemestral' name='tipoReserva.diaSemana'>"+
						"<option value=''>---Selecione um Dia da Semana---</option>"+
						"<option value='0'>Segunda</option>"+
						"<option value='1'>Terça</option>"+
						"<option value='2'>Quarta</option>"+
						"<option value='3'>Quinta</option>"+
						"<option value='4'>Sexta</option>"+
						"<option value='5'>Sábado</option>"+
						"<option value='6'>Domingo</option>"+
					"</select></div><div class='col-xs-12 col-md-6'><label>Hora de Início:</label>"+
					"<input type='text' class='form-control horaReserva' value='"+horaInicio+"' name='tipoReserva.horaInicio' /></div>"+
					"<div class='col-xs-12 col-md-6'><label>Hora de Fim:</label>"+
					"<input type='text' class='form-control horaReserva' value='' name='tipoReserva.horaFim' /></div>"+
					"<div class='col-xs-12 col-md-6'><label>Observação:</label>"+
					"<input type='text' class='form-control' value='' name='tipoReserva.observacao' /></div>");
	};
	//Mostrar dados de Reserva Exporádica
	var exporadica = function(){
		$("#divTipoReserva").append("<div class='col-xs-12 col-md-6'><label>Data Marcada:</label>"+
				"<input id='dataEscolhidaExporadicaForm'type='date' value='"+dataEscolhida+"' "+
				"class='form-control' name='tipoReserva.dataMarcada' />"+
				"</div><div class='col-xs-12 col-md-6'><label>Hora de Início:</label>"+
					"<input type='text' class='form-control horaReserva' value='"+horaInicio+"' name='tipoReserva.horaInicio' /></div>"+
					"<div class='col-xs-12 col-md-6'><label>Hora de Fim:</label>"+
					"<input type='text' class='form-control horaReserva' value='' name='tipoReserva.horaFim' /></div>"+
					"<div class='col-xs-12 col-md-6'><label>Observação:</label>"+
					"<input type='text' class='form-control' value='' name='tipoReserva.observacao' /></div>");
	};
</script>
<!-- Popular Reservas -->
<script type="text/javascript">
//Retornar a Data do Dia escolhido na Reserva
var retornarDiaSemana = function(){
	return new Date($("#calendarioReserva").val()).getDay();
}
//Semestral
var reservasSemestral;
var retornarReservasSemestral = function(dia){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[ReservasController].listarSemestral() }", 
	    data: {"diaSemana" : dia,
	    	   "ano" : anoSemestreSelecionado.ano,
	    	   "semestre" : anoSemestreSelecionado.semestre},
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	reservasSemestral = data;
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
//Exporádica
var reservasExporadica;
var retornarReservasExporadica = function(dia){
	
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[ReservasController].listarExporadica() }", 
	    data: {"data" : dia},
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	reservasExporadica = data;
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
</script>
<!-- Script de Listagem de Salas -->
<script type="text/javascript">
//POPULAR SALAS	
var listarSalasAjax = function(){
	
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[SalaController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	popularSala(data);
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
listarSalasAjax();
//Escrever Observação na Reserva na caixa Caso tenha uma
var escreverObservacao = function(observacao)
{
	if (observacao) {
		return " - "+observacao; 
	}
	return "";
}
//Script para Ajustar o tamanho da fonte da caixa, de acordo com o tamanho dela
var ajustarTamanhoFonte = function(idCaixaTexto){
	if ($("#"+idCaixaTexto)[0].scrollHeight >  $('#'+idCaixaTexto).innerHeight() ||
			$("#"+idCaixaTexto)[0].scrollWidth >  $('#'+idCaixaTexto).innerWidth()) {
			
			var tamanhoFonte = parseInt($("#"+idCaixaTexto).css("font-size").replace("px",""));
			tamanhoFonte--;
			$("#"+idCaixaTexto).css({"font-size": tamanhoFonte});
			ajustarTamanhoFonte(idCaixaTexto);
			return;
	}
}
var todasDaReserva = null;
//Retornar Todos os Dias da Mesma Reserva Via Ajax
var retornarTodasDaReserva = function(idRes){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[ReservasController].listarTodasdaReserva() }", 
	    data: {"idRes" : idRes},
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	todasDaReserva = data;
	    	
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
var idReservas = [];
var contagemIdReservas = 0;
var inativarHorarios = false;
var inativarVisualizarHorario = false;
//Desativar a Função de Clicar para Cadastrar Reserva na Tabela quando ja houver uma cadastrada.
var desativarClickHorarios = function(idRes){
	if (inativarVisualizarHorario == false) {
		inativarHorarios = true;
		$.when(retornarTodasDaReserva(idRes)).done(function(){
			preencherInfoReserva();
		})
	}else{
		inativarHorarios = true;
		inativarVisualizarHorario = false;
	}
	
};
//Script para Cancelar Reserva Semestral
var cancelarRerserva = function(idRes)
{
	cadastrarDeletarReserva(idRes);
	return false;
}
//Script para Cancelar Reserva Exporádica
var cancelarRerservaExp = function(idRes, dataMarcada, horaIni, horaFim)
{
	cadastrarDeletarReservaExp(idRes, dataMarcada, horaIni, horaFim);
	return false;
}
//Preencher Detalhes da Reserva quando clicado na Reserva, mostrar pelo modal
var preencherInfoReserva = function(){
	$("#reservaLerDisciplina").val(hashDisciplina[todasDaReserva.reserva.codigoDis].nome);
	$("#reservaLerCurso").val(hashCurso[todasDaReserva.reserva.codigoCur].nome);
	$("#reservaLerProfessor").val(hashProfessores[todasDaReserva.reserva.codigoPro].pessoa.nome);
	$("#reservaLerTurma").val(todasDaReserva.reserva.turma);
	$("#reservaLerTodasTBody").find('tr').remove().end();
	$(todasDaReserva.semestrais).each(function(i,s){
		$("#reservaLerTodasTBody").append("<tr><td>Semestral</td><td>"+retornarDiaSemanaString(s.diaSemana)
				+"</td><td>"+retornarMinuto(s.horaInicio.hour)+":"+
				retornarMinuto(s.horaInicio.minute)+"-"+
				retornarMinuto(s.horaFim.hour)+":"+
				retornarMinuto(s.horaFim.minute)+"</td><td>"+hashSalas[s.idSal].nome+"</td></tr>");
	});
	$(todasDaReserva.exporadicos).each(function(i,s){
		$("#reservaLerTodasTBody").append("<tr><td>Exporádico</td><td>"+
				retornarMinuto(s.data.day)+"/"+retornarMinuto(s.data.month)+"/"
				+s.data.year+"</td><td>"+retornarMinuto(s.horaInicio.hour)+":"+
				retornarMinuto(s.horaInicio.minute)+"-"+
				retornarMinuto(s.horaFim.hour)+":"+
				retornarMinuto(s.horaFim.minute)+"</td><td>"+hashSalas[s.idSal].nome+"</td></tr>");
	});
	$("#novaInfoReserva").modal('toggle');
	
};
//Retornar o Dia da Semana de Acordo com um valor inteiro
var retornarDiaSemanaString = function(dia)
{
	diaSemana = "";
	switch(dia) {
    case 0:
        diaSemana = "Segunda";
        break;
    case 1:
        diaSemana = "Terça";
        break;
    case 2:
        diaSemana = "Quarta";
        break;
    case 3:
        diaSemana = "Quinta";
        break;
    case 4:
        diaSemana = "Sexta";
        break;
    case 5:
        diaSemana = "Sábado";
        break;
    case 6:
        diaSemana = "Domingo";
        break;
	}
	return diaSemana;
};
hashSalas = {};
//Verificar Se existe Reserva para a Sala Escolhida no Dia Determinado
var verificarSeTemReserva = function(sala, hashReserva){
	var retornar = false;
	for (var i = 6; i < 23; i++) {
		$(hashReserva[i]).each(function(indiceReservaSemestral, elementoReserva){
			if (elementoReserva.idSal == sala.id) {
				retornar = true; 	
			}
		});
	}
	return retornar;
}
//Script de Populamento das Salas
var popularSala = function(data)
{
	//Esperar Requisições Ajax das Salas Terminarem para poder Preencher a Tabela
	$.when(retornarReservasSemestral(retornarDiaSemana()), retornarReservasExporadica($("#calendarioReserva").val())).done(function(){
	//Remover Reservas Anteriores
	$("#tabelaGrade").find(".salaDeAula").remove().end();
	//Remover Salas do Form da Reserva
	$("#selecionarSala")
    .find('option')
    .remove()
    .end();
	//HashTable de Reservas para facilitar o processamento da página;
	var hashReserva = {}
	$(reservasSemestral).each(function(indice, elementoReserva){
		if (!hashReserva[elementoReserva.horaInicio.hour]) {
			hashReserva[elementoReserva.horaInicio.hour] = [];
		}
		elementoReserva.exporadico = false;
		hashReserva[elementoReserva.horaInicio.hour].push(elementoReserva); 
	});
	$(reservasExporadica).each(function(indice, elementoReserva){
		if (!hashReserva[elementoReserva.horaInicio.hour]) {
			hashReserva[elementoReserva.horaInicio.hour] = [];
		}
		elementoReserva.exporadico = true;
		hashReserva[elementoReserva.horaInicio.hour].push(elementoReserva); 
	});
	
$("#selecionarSala").append('<option value="">---Selecione uma Sala---</option>');
var realIndice = 0;
	$(data).each(function(indiceSala,sala){
		hashSalas[sala.id] = sala;
		
		$("<option />", {value: sala.id, text: sala.nome}).appendTo($("#selecionarSala"));
		//Verificar se a Sala tem Aulas, Caso Sim, inserir na Tabela
		if (verificarSeTemReserva(sala, hashReserva)) {
			$("#tabelaGrade > thead > tr").append("<th class='salaDeAula'>"+sala.nome+"</th>");
			preencherSala(indiceSala, sala, hashReserva, realIndice);
			realIndice++;
		}
		
	});
	formartarCaixas();
});
}
//POPULAR SALAS COM LINHAS E RESERVAS
var preencherSala = function(indiceSala, sala, hashReserva, realIndice){
	//Do Horário das 06horas às 23 horas.
	for (var i = 6; i < 23; i++) {
		//Colorir linha Sim e Linha Não
		var colorido = ( (realIndice) % 2 == 1 ? "colorido" : "");
		//Adicionar Linha na Tabela da Sala
		$("#linha_Aula_00_"+i).append("<td id='td_Aula_00_"+i+"_"+sala.id+"_"+sala.nome+"' class='"+colorido+" salaDeAula'></td>");
		$("#linha_Aula_30_"+i).append("<td id='td_Aula_30_"+i+"_"+sala.id+"_"+sala.nome+"'class='"+colorido+" salaDeAula'></td>");
		$(hashReserva[i]).each(function(indiceReservaSemestral, elementoReserva){
			
			if (elementoReserva.idSal == sala.id) {
				//Cálculo da Altura da Caixa
				var diffHora = elementoReserva.horaFim.hour -
				elementoReserva.horaInicio.hour;
				var diffMin = elementoReserva.horaFim.minute -
				elementoReserva.horaInicio.minute;
				//Cálculo da Altura da Caixa por Coluna 19 = Altura da Linha da Tabela
				var calcAltura = 19*2*diffHora + (19*diffMin/30);
				var espaco = 19*elementoReserva.horaInicio.minute/30;
				var posicaoTd = "00";
				if (elementoReserva.horaInicio.minute >= 30) {
					//calcAltura += 19;
					espaco -= 19;
					posicaoTd = "30";
				}
				cor = "";
				var adTexto = "";
				var horaIni = retornarMinuto(elementoReserva.horaInicio.hour)+":"+
				retornarMinuto(elementoReserva.horaInicio.minute);
				var horaFim = retornarMinuto(elementoReserva.horaFim.hour)+":"+
				retornarMinuto(elementoReserva.horaFim.minute);
				/*
					Verificar Se a Reserva é exporádica, caso positivo
					inserir a imagem de deletar com a referência de reserva exporádica.
					Se contrário com a Reserva Semestral
				*/
				if (elementoReserva.exporadico) {
					var dataMarcadaExporadico = elementoReserva.data.year+"-"+
					retornarMinuto(elementoReserva.data.month)+"-"+retornarMinuto(elementoReserva.data.day);
					cor = "background-color: #E1BEE7;"
					adTexto = "<a href='' onclick='return excluirModalReservaExp("+elementoReserva.reserva.id+
						",\""+dataMarcadaExporadico+"\",\""+horaIni+"\",\""+horaFim+"\");'>"+
						"<img src='"+$("#fecharBase").attr("src")
					+"' class='cancelar_reserva pull-right'/></a>"
				}else{
					adTexto = "<a href='' onclick='return excluirModalReservaSem("+elementoReserva.id+
							");'><img src='"+$("#fecharBase").attr("src")
					+"' class='cancelar_reserva pull-right'/></a>"
				}
				//overflow: hidden;
				//Inserir Caixa da Reserva
				$("#td_Aula_"+posicaoTd+"_"+i+"_"+sala.id+"_"+sala.nome).append("<div class='caixaMaior'>"+	
				"<div class='caixa' id='reservaId"+contagemIdReservas+"' style='height:"+calcAltura+"px;top:"+espaco+"px;"+cor
				+"' onclick='desativarClickHorarios("+elementoReserva.reserva.id+");'>"
				+adTexto+"Hor:"
				+horaIni+"-"+
				horaFim+
			    "<br/>Prof:"+
				hashProfessores[elementoReserva.reserva.codigoPro].pessoa.nome+
				"<br/>Dis:"+
				hashDisciplina[elementoReserva.reserva.codigoDis].nome
				+escreverObservacao(elementoReserva.observacao)+
				"</div>"+
				"</div>");
				idReservas.push("reservaId"+contagemIdReservas);
				contagemIdReservas++;
			}
		});
		$("#td_Aula_00_"+i+"_"+sala.id+"_"+sala.nome).click(function(){
			var regexp = new RegExp("_"); 
			var pedacos = $(this).attr('id').split(regexp);
			mostrarReserva(""+pedacos[3],pedacos[2],pedacos[4],pedacos[5]+"");
		});
		$("#td_Aula_30_"+i+"_"+sala.id+"_"+sala.nome).click(function(){
			var regexp = new RegExp("_"); 
			var pedacos = $(this).attr('id').split(regexp);
			mostrarReserva(""+pedacos[3],pedacos[2],pedacos[4],pedacos[5]+"");
		});
		
	}

};
//Ajustar as Caixas de Reservas de Acordo com o Tamanho da Tela
var formartarCaixas = function(){
	
	$(".caixa").each(function(i,e){
		$(e).css('font-size','20px');
		ajustarTamanhoFonte($(e).attr('id'));
		});
}
$( window ).resize(function() {
	formartarCaixas();
});

</script>

<!-- Máscaras -->
<script type="text/javascript">
$('.ano').mask('0000');
$('#codigoDisciplinaForm').mask("ZZZZZ", {placeholder: "_____", translation: {
      'Z': {
        pattern: /[A-Z,a-z,0-9]/, optional: false
      }}});
$('#codigoCursoForm').mask("ZZ", {placeholder: "__", translation: {
          'Z': {
            pattern: /[A-Z,a-z,0-9]/, optional: false
          }}});
$('.horaReserva').mask('H0:M0', {placeholder: "__:__", translation: {'H': {
    pattern: /[0-2]/, optional: false
}, 'M': {
    pattern: /[0-6]/, optional: false
}}});
$("#reservaTurma").mask("Z", {placeholder: "_", translation: {
      'Z': {
        pattern: /[A-Z,a-z]/, optional: false
      }}});
</script>
<!-- Script de Jquery Validate - Validação dos Modais e Forms -->
<script type="text/javascript">
$().ready(function(){
	$("#formAnoSemestre").validate(function(){});
	$("#formSala").validate(function(){});
	$("#formCurso").validate(function(){});
	$("#formDisciplina").validate(function(){});
	$("#formReserva").validate(function(){});
	$("#nomeSalaForm").rules("add",{
		required: true,
		  minlength: 2,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
		    minlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
		  }}
		);
	$("#dataAnoAnoSemestreForm").rules("add",{
		required: true,
		  minlength: 4,
		  maxlength: 4,
		  messages: {
		    required: iniMensagemAlerta+"Campo obrigatório"+fimMensagemAlerta,
		    minlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por favor insira os {0} caracteres."+fimMensagemAlerta),
		    maxlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por favor insira os {0} caracteres."+fimMensagemAlerta)
		  }}
		);

	$("#nomeDisciplinaForm").rules("add",{
		required: true,
		  minlength: 2,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
		    minlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
		  }}
		);	
	$("#codigoDisciplinaForm").rules("add",{
		required: true,
		  maxlength: 5,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
		    maxlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por Favor insira até {0} caracteres."+fimMensagemAlerta)
		  }}
		);
	$("#dataIniAnoSemestreForm").rules("add",{
		required: true,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
		  }}
		);
	$("#dataFimAnoSemestreForm").rules("add",{
		required: true,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
		  }}
		);
	$("#codigoCursoForm").rules("add",{
		required: true,
		  maxlength: 2,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
		    maxlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por Favor insira até {0} caracteres."+fimMensagemAlerta)
		  }
	});
	$("#nomeCursoForm").rules("add",{
		required: true,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta
		  }
	});
	$("#reservaTurma").rules("add",{
		required: true,
		  messages: {
		    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta
		  }
	});
});

</script>
<!-- Evento de acionar Reserva quando clicado no horário desejado -->
<script type="text/javascript">
var horaInicio = "";
var dataEscolhida = "";
var diaSemana = "";
//Mostrar a Reserva Quando Clicado na Tabela de Reservas
function mostrarReserva(hora, minuto,  salaId, sala)
{
	if (temPermissao) {
		if (!inativarHorarios) {
			if (hora < 10) {
				hora = "0"+hora;
			}
			horaInicio = hora+":"+minuto;
			$("#selecionarSala").val(salaId).trigger("change");
			$("#selecionarTipoReserva").val("").trigger("change");
			dataEscolhida = $("#calendarioReserva").val();
			diaSemana = retornarDiaSemana();
			
			aparecerModalReserva();
		}else{
			inativarHorarios = false;
		}
	}
	
}
//Validar data Caso o Cadastro Seja Exporádico
var validarSeExporadico = function(){
	if ($("#selecionarTipoReserva").val() == "exporadica") {
		if (validarData(new Date($("#dataEscolhidaExporadicaForm").val()))) {
			return true;
		}else{
			return false;
		}
	} else {
		return true;
	};
}
//Enviar Formulário de Cadastro de Sala
var cadastrarReserva = function(){
	if($("#formReserva").valid() && validarSeExporadico())
	{
		$.ajax({  
		    type:"post",  
		    url: "${linkTo[ReservasController].postar() }", 
		    data: $("#formReserva").serialize(),
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	if (data.mensagem.indexOf("Erro") !== -1) {
		    		formularErro(data.mensagem);
				}
		    	listarSalasAjax();
//	 	    	popularSelectAnoSemestre(data);
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){ 
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});		
	}else{
		formularErro("Formulário Inválido!");
	}
	
};
</script>
<!-- Script de Select com Pesquisa -->
<script type="text/javascript">
			$(document).ready(function() {
			  $(".select_auto_completar").select2();
			});
</script>

<!-- Atualizar Salas -->
<script type="text/javascript">
	$("#calendarioReserva").on("change", function(){
		if(dataValida())
		{
			$.when(listarSalasAjax()).done(function(){
				formartarCaixas();
			});
		}else{
			formularErro("Data Inválida! Por Favor Insira uma data Válida!");
			restaurarDataInicial();
		}
	});
</script>

<!-- Deletar Reserva -->
<script type="text/javascript">
//Deletar Reserva Semestral
var cadastrarDeletarReserva = function(idResSem){
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[ReservasController].deletarSem() }", 
	    data: {"idResSem" : idResSem},
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	if (data.mensagem.indexOf("Erro") !== -1) {
	    		formularErro(data.mensagem);
			}
	    	listarSalasAjax();
// 	    	popularSelectAnoSemestre(data);
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});	
	
};
//Deletar Reserva Exporádica
var cadastrarDeletarReservaExp = function(idResExp, dataMaraca, horaIni, horaFim){
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[ReservasController].deletarExp() }", 
	    data: {"idResExp" : idResExp, "dataMarcada" : dataMaraca, "horaIni" : horaIni, "horaFim" : horaFim},
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	if (data.mensagem.indexOf("Erro") !== -1) {
	    		formularErro(data.mensagem);
			}
	    	listarSalasAjax();
// 	    	popularSelectAnoSemestre(data);
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});	
	
};

</script>

<!-- Acionar Modal de Mostrar Opções de Acordo com as Permissões // Antigo -->
<!-- <script type="text/javascript"> -->
<!-- //  var mostrarOpcoes = function(){ -->
<!-- // 	 $("#opcoesGerais").modal('show'); -->
<!-- // 	 return false; -->
<!-- //  } -->
<!-- </script> -->
<!-- Verificar Permissões -->
<script type="text/javascript">
var temPermissao = false;
var temPermissaoCurso = false;
var temPermissaoDisciplina = false;
var temPermissaoAnoSemestre = false;
var temPermissaoSala = false;

var verificarPermissao = function(){
	$(permissoesUsuario).each(function(i,permi){
		  if(permi== 4 || permi == 1){
			  temPermissao = true;
		  }
		  if(permi == 6 || permi == 1){
			  temPermissaoCurso = true;
		  }
		  if(permi == 8 || permi == 1){
			  temPermissaoDisciplina = true;
		  }
		  if(permi == 7 || permi == 1){
			  temPermissaoAnoSemestre = true;
		  }
		  if(permi == 9 || permi == 1){
			  temPermissaoSala = true;
		  }
	});
	if (!temPermissao) {
		$(".permissao").remove().end();
	}
	if (!temPermissaoAnoSemestre) {
		$(".permissaoAnoSemestre").remove().end();
	}
	if (!temPermissaoCurso) {
		$(".permissaoCurso").remove().end();
	}
	if (!temPermissaoDisciplina) {
		$(".permissaoDisciplina").remove().end();
	}
	if (!temPermissaoSala) {
		$(".permissaoSala").remove().end();
	}
}
$(verificarPermissao());

</script>
<!-- Modais de Deletar -->
<script type="text/javascript">
	//Reservas Semestrais
	var excluirModalReservaSem = function(idRes){
		inativarVisualizarHorario = true;
		$("#deletarIdReservaSem").val(idRes);
		$("#novaDeletarReservaSem").modal('show');
		return false;
	}
	$("#excluirReservaSemSim").on("click", function(){
		cancelarRerserva($("#deletarIdReservaSem").val());
	});
	//Reservas Exporádicas
	var excluirModalReservaExp = function(idRes, data, horaIni, horaFim){
		inativarVisualizarHorario = true;
		$("#deletarIdReservaExp").val(idRes);
		$("#deletarDataReservaExp").val(data);
		$("#deletarHoraIniReservaExp").val(horaIni);
		$("#deletarHoraFimReservaExp").val(horaFim);
		$("#novaDeletarReservaExp").modal('show');
		return false;
	}
	$("#excluirReservaExpSim").on("click", function(){
		cancelarRerservaExp($("#deletarIdReservaExp").val(),
				$("#deletarDataReservaExp").val(), $("#deletarHoraIniReservaExp").val(),
				$("#deletarHoraFimReservaExp").val());
	});
</script>
<!-- Ver Reservas Exporádicas do Mês -->
<script type="text/javascript">
var verExporadicasMesModal = function(){
	$("#novaExporadicaMes").modal('show');
	return false;
}
var reservasExporadicasMes;
$("#mesVerExporadicas").on("change", function(){
	atualizarReservasExporadicasMes();
})
var atualizarReservasExporadicasMes = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[ReservasController].listarMes() }", 
	    data: {"data" : $("#mesVerExporadicas").val(), "ano" : anoSemestreSelecionado.ano,
	    	"semestre" : anoSemestreSelecionado.semestre},
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	reservasExporadicasMes = data;
	    	atualizarTabelaExporadicasMes();
	    	
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
var atualizarTabelaExporadicasMes = function(){
	$(reservasExporadicasMes).each(function(i,s){
		$("#reservaLerTodasTBodyExpMes").append("<tr><td>"+
				retornarMinuto(s.data.day)+"/"+retornarMinuto(s.data.month)+"/"
				+s.data.year+"</td><td>"+retornarMinuto(s.horaInicio.hour)+":"+
				retornarMinuto(s.horaInicio.minute)+"-"+
				retornarMinuto(s.horaFim.hour)+":"+
				retornarMinuto(s.horaFim.minute)+"</td><td>"+hashSalas[s.idSal].nome+"</td></tr>")
	});
}
</script>
<script type="text/javascript">
$("#opcaoExtra").on("change", function(){
	$(eval($(this).val()+"()"));
});
	</script>
</t:rodape>