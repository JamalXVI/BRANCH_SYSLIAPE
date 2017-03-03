<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- Importação do Cabecalho -->
<t:cabecalho>
<!-- Estilização da TAG select -->
<link href="<c:url value='/assets/css/cupertino/jquery-ui-1.7.2.custom.css' />"
rel="stylesheet">
<link href="<c:url value='/assets/css/select2.min.css' />" rel="stylesheet">
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>
<div class="col-lg-12 container-fluid">
	<div class="row" role="main">
		<h1 class="page-header" style="margin-top: 0px;">
			Nova <small>Ordem de Serviço</small>
		</h1>
		<form action="${linkTo[OrdemServicoController].enviar(null) }"
			method="post" id="formOrdemServico">
			<div class="col-xs-12">
				<div class="col-xs-6 col-md-6 col-lg-3">
						<label for="calendarioReserva">Data para ser Executada:</label>
						<input type="date"  max="2050-01-01" min="2001-01-01" name="ordem.dataExecutada"
						id="dataExecutada" class="form-control text-center"  />
				</div>
				<t:input divCol="col-xs-6 col-md-6 col-lg-3 text-center" id="dataExecutadaHora"
				 nome="ordem.dataExecutadaHora" nomeLabel="Hora para Ser Executada:" valorPadrao=""
				  tipo="text" classes="formHora"></t:input>
				<div class="col-xs-6 col-md-6 col-lg-3">
					<label for="selecionarAlvo">Ordem Destinada para:</label>
					<select class="form-control" id="selecionarAlvo"
								name="ordem.destinada">
								<option value="1">Turno</option>
								<option value="0">Usuário</option>
					</select>
				</div>
				<div class="col-xs-6 col-md-6 col-lg-3" id="tipoAlvo">
				</div>
			</div>
			<div class="col-xs-12">
				<div class="col-xs-6 col-md-6 col-lg-3">
					<label for="selecionarTipoOrdem">Tipo de Ordem:</label>
					<select class="form-control" id="selecionarTipoOrdem"
								name="ordem.tipo">
								<option value="1">Computador</option>
								<option value="0">Sala</option>
					</select>
					
					
				</div>
				<div class="col-xs-6 col-md-6 col-lg-3" id="tipo">
				</div>
				<div class="col-xs-6 col-md-6 col-lg-3" id="numeroComputador">
				</div>
			</div>
			
			<div class="col-xs-12">
				<label>Descrição da Ordem:</label>
				<textarea rows="5" class="form-control" name="ordem.descricao" id="descricaoOrdem"></textarea>
			</div>
			<t:centralizarDiv divCol="6" divColmd="4">
				<button type="submit" class="btn btn-lg btn-success"
					id="enviarMensagem">Enviar</button>
			</t:centralizarDiv>
		</form>
		
	</div>
</div>

<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
<!-- Importação do Javascript de  Criação de Eventos da Agenda de Reservas-->
<script src="<c:url value='/assets/js/jquery-ui-1.7.2.custom.min.js' /> "></script>
<!-- Importação do Javascript de Autocompletar Select-->
<script src="<c:url value='/assets/js/select2.full.min.js' /> "></script>
<script type="text/javascript">
	//Regras do Jquery Validator
	var regrasDoJquery = function(){
		$("#formOrdemServico").validate(function(){});
		$("#dataExecutadaHora").rules("add",{
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
		$("#descricaoOrdem").rules("add",{
			required: true,
			  minlength: 5,
			  maxlength: 1000,
			  messages: {
			    required: iniMensagemAlerta+"Campo obrigatório"+fimMensagemAlerta,
			    minlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por favor no mínimo {0} caracteres."+fimMensagemAlerta),
			    maxlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por favor insira até {0} caracteres."+fimMensagemAlerta)
			  }}
			);
		$("#dataExecutada").rules("add",{
			required: true,
			  messages: {
			    required: iniMensagemAlerta+"Campo obrigatório"+fimMensagemAlerta,
			  }}
			);
		
	}
</script>
<script type="text/javascript">
var retornarMinuto = function(minuto)
{
	if (minuto < 10) {
		return "0"+minuto;
	}
	return minuto;
}
$(document).ready( function() {
	var dataEsc = new Date();
	$("#dataExecutada").val(dataEsc.getFullYear()
			+"-"+retornarMinuto(dataEsc.getMonth()+1)+
			"-"+retornarMinuto(dataEsc.getDate()));
	$.when(coletarUsuarios()).done(function(){
		verficarDestino();
	});
	$.when(coletarComputadores(), coletarSalas()).done(function(){
		verificarDestinoAlvo();
	});
	regrasDoJquery();
});
	
</script>
<script type="text/javascript">
	var usuarios;
	
	var adicionarTurnos = function(){
		$("#tipoAlvo").find("div").remove().end();
		$("#tipoAlvo").append("<div><label>Selecione um Turno:</label>"+
		"<select class=\"form-control\" id=\"idAlvo\" name=\"ordem.idDestino\">"
		+"<option value='1'>Manhã</option> "
		+"<option value='2'>Tarde</option>"
		+"<option value='3'>Noite</option>"
		+"</div>");
		
	}
	var adicionarUsuario = function(){
			$("#tipoAlvo").find("div").remove().end();
			$.when(criarSelectUsuario()).done(function(){
				$(usuarios).each(function(indice_usuario, usuario){
					$("#idAlvo").append("<option value='"+usuario.login+ "'>"
					+usuario.pessoa.nome+" "+usuario.pessoa.sobrenome+"</option>")
				});
				$(".select_auto_completar").select2();
			});
			
	}
	var criarSelectUsuario = function(){
		return $("#tipoAlvo").append("<div><label>Selecione um Usuário:</label>"+
		"<select class=\"form-control select_auto_completar \" id=\"idAlvo\" name=\"ordem.idDestino\">"+
		"</div>");
	}
	var coletarUsuarios = function(){
		return $.ajax({  
		    type:"post",  
		    url: "${linkTo[UsuarioController].listar() }", 
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	usuarios = data;
		    	//criarPermissoes();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
	}
	var verficarDestino = function(){
		if($("#selecionarAlvo").val() == "0"){
			adicionarUsuario();
		}else{
			adicionarTurnos();
		}
	}
	$("#selecionarAlvo").on("change", function(){
		verficarDestino();
	});
</script>
<script type="text/javascript">
var computadres;
var salas;
var coletarComputadores = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[ComputadorController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	computadores = data;
	    	//criarPermissoes();
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});
}
var coletarSalas = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[SalaController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	salas = data;
	    	//criarPermissoes();
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});
}
var criarSelectTipo = function(tipo){
	return $("#tipo").append("<div><label>Selecione "+tipo+":</label>"+
	"<select class=\"form-control select_auto_completar \" id=\"idTipoAlvo\" name=\"ordem.idTipo\">"+
	"</div>");
}
var verificarDestinoAlvo = function(){
	if($("#selecionarTipoOrdem").val() == "0"){
		adicionarSala();
	}else{
		adicionarComputador();
	}
}
var excluirItensAnteriores = function(){
	$.when(excluirComputador()).done(function(){
		return $("#tipo").find("div").remove().end();
	});
	
}
var excluirComputador = function(){
	return $("#numeroComputador").find("div").remove().end();
}
var adicionarSala = function(){
	excluirItensAnteriores();
	$.when(criarSelectTipo("uma Sala")).done(function(){
		$(salas).each(function(indice_sala, sala){
		$("#idTipoAlvo").append("<option value='"+sala.id+ "'>"
		+sala.nome+"</option>")
	});
	$(".select_auto_completar").select2();
});		
}
var adicionarComputador = function(){
	excluirItensAnteriores();
	$.when(criarSelectTipo("uma Sala")).done(function(){
		$(salas).each(function(indice_sala, sala){
		$("#idTipoAlvo").append("<option value='"+sala.id+ "'>"
		+sala.nome+"</option>");
		});
	$("#idTipoAlvo").on("change", function(){
		reiniciarComputadores();
	});
	reiniciarComputadores();
});
}
var reiniciarComputadores = function(){
	$("#numeroComputador").find("div").remove().end();
	mudarValorDaSala();
	$(".select_auto_completar").select2();
};
var mudarValorDaSala = function(){
	$("#numeroComputador").append("<div id='salaComputador'><label>Selecione um Computador: </label>"+
	"<select class=\"form-control select_auto_completar \" id=\"idComputador\" name=\"ordem.idComputador\">"+
	"</div>");
	$(computadores).each(function(indice_computador, computador){
		if(computador.idSal == $("#idTipoAlvo").val())
		{
			$("#idComputador").append("<option value='"+computador.numeroPc+"'>"
					+computador.numeroPc+"</option>")	
		}
	});
}
$("#selecionarTipoOrdem").on("change", function(){
	verificarDestinoAlvo();
});

</script>
<c:if test="${editandoOrdem == true }">
<script type="text/javascript">
	var adicionarZero = function(numero)
	{
		if (numero < 10) {
			return "0"+numero;
		}
		return numero;
	}
	var idEditar;
	var editarOrdem;
	$(document).ready(function(){
		idEditar = "${editarId}";
		$.when(coletarEditar()).done(function(){
			if (editarOrdem.ehSala) {
				$("#selecionarTipoOrdem").val("0").trigger("change");
				adicionarSala();
				$("#idTipoAlvo").val(editarOrdem.idSala).trigger("change");
			}else{
				$("#selecionarTipoOrdem").val("1").trigger("change");
				adicionarComputador();
				$("#idTipoAlvo").val(editarOrdem.idSala).trigger("change");
				$("#idComputador").val(editarOrdem.idComputador).trigger("change");
			}
			if (editarOrdem.ehUsuario) {
				$("#selecionarAlvo").val("0").trigger("change");
				adicionarUsuario();
			}else{
				$("#selecionarAlvo").val("1").trigger("change");
				adicionarTurnos();
			}
			$("#tipoAlvo").val(editarOrdem.alvo).trigger("change");
			var dataEsc = new Date(editarOrdem.dataParaSerExecutada);
			$("#dataExecutada").val(dataEsc.getFullYear()
					+"-"+adicionarZero(dataEsc.getMonth()+1)+
					"-"+adicionarZero(dataEsc.getDate()));
			$("#dataExecutadaHora").val(adicionarZero(dataEsc.getHours())+":"
			+adicionarZero(dataEsc.getMinutes()));
			$("#descricaoOrdem").val(editarOrdem.descricao);
		});
		
	});
	var coletarEditar = function(){
		var dataEnviar = {"idOrs" : idEditar}
		return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].editandoOrdem() }", 
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    data: dataEnviar,
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	editarOrdem = data;
		    	//criarPermissoes();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
	}
</script>
</c:if>
</t:rodape>