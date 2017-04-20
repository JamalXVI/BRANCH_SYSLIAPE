<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- Importação do Cabecalho -->
<t:cabecalho>
<!-- Estilização da TAG select -->
<style>
.cor_exporadico{
	background-color: #E1BEE7 !important;
}
</style>
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Seção Principal -->
<div class="row" role="main">
<div class="col-lg-8">
<div class="panel panel-default">
	<div class="panel-heading">
		Próximas Aulas <i class="fa fa-clock-o" aria-hidden="true"></i>
	</div>
	<div class="panel-body">
<!-- 		<div class="list-group" id=> -->
<!-- 		</div> -->
		<table class='table table-striped table-bordered table-hover'>
			<thead>
				<th>Hora:</th>
				<th>Sala:</th>
				<th>Professor:</th>
			</thead>
			<tbody id="proximaAulas">
			</tbody>
		</table>
	</div>
</div>
</div>
<div class="col-lg-4">
<div class="panel panel-default">
	<div class="panel-heading">
		<i class="fa fa-bell fa-fw"></i> Notificações
	</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<div class="list-group" id='listaNotificacoes'>
		</div>
		<div id="carregarnot">
		</div>
	</div>
	<!-- /.panel-body -->
</div>
</div>
<div class="col-lg-8">
<div class="panel panel-default">
	<div class="panel-heading">
		Aniversáriantes do Mês <i class="fa fa-birthday-cake" aria-hidden="true"></i>
	</div>
	<div class="panel-body">
<!-- 		<div class="list-group" id=> -->
<!-- 		</div> -->
		<table class='table table-striped table-bordered table-hover'>
			<thead>
				<th>Nome:</th>
				<th>Aniversário:</th>
			</thead>
			<tbody id="aniversariantes">
			</tbody>
		</table>
	</div>
</div>
</div>
</div>

<form action="${linkTo[MuralController].visualizar()}" method="POST" id="verMural">
	<input type='hidden' name='idMur' id='idMurEnviar' />
</form>
<form action="${linkTo[OrdemServicoController].visualizar() }" method="post" id="formVisualizar">
			<input type="hidden" name="idSor" id="visualizarOrdemId" />
</form>
<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
<script type="text/javascript">
var mensagens;
var ordens;
var murais;
var novasMensagens = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[MensagemController].listarNovasMensagens() }", 
//	    data: $("#formReserva").serialize(),
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
				
	    	mensagens = data;
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});		
}
var ordemUsuario = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[OrdemServicoController].listarParaUsuarioAtivo() }", 
//	    data: $("#formReserva").serialize(),
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
		    	ordens = data;
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});
	
}
var novoMurais = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[MuralController].listarMensagensNovas() }", 
//	    data: $("#formReserva").serialize(),
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
				
	    	murais = data;
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});		
}
var adicionarNotificacoes = function(){
	agora = new Date();
	$("#listaNotificacoes").find("a").remove().end();
	carregando();
	notificacaoMensagem(agora);
	notificacaoOrdem(agora);
	notificacaoMural(agora);
	carregar();
}
var notificacaoMural = function(agora){
	$.when(novoMurais()).done(function(){
		$(murais).each(function(indice_mural, mural){
			var texto = adicionarTextoDiferenca(mural.data, agora);
			$("#listaNotificacoes").append("<a href='' onclick='return visualizarMural(\""
					+indice_mural+"\")' class='list-group-item'> <i"+
					" class='fa fa-envelope fa-fw'></i> Mural de "+mural.turnoRemetente
					+"<span"+
					" class='pull-right text-muted small'><em>"+texto+"</em> </span>"+
				"</a>");
		});
	});
}
var notificacaoOrdem = function(agora){
	$.when(ordemUsuario()).done(function(){
		$(ordens).each(function(indice_ordem, ordem){
			var texto = adicionarTextoDiferenca(ordem.dataParaSerExecutada, agora);
			var link = "${linkTo[OrdemServicoController].listarAtivas()}";
			$("#listaNotificacoes").append("<a href='' onclick='return visualizarOrdem(\""+ordem.id+"\")' "
					+" class='list-group-item'> <i class='fa fa-file-text fa-fw'></i>Ordem de Serviço <span"+
					" class='pull-right text-muted small'><em>"+texto+"</em> </span>"+
				"</a>");
		});
	});
}
var visualizarOrdem = function(idSor) {
	$("#visualizarOrdemId").val(idSor);
	$("#formVisualizar").submit();
	return false;
};
var adicionarTextoDiferenca = function(dataEsc, agora){
	dia = dataEsc.split("T")[0];
	hora = dataEsc.split("T")[1].split("-")[0];
	dataMensangem = new Date(dia+" "+hora);
	diffMs = (agora -dataMensangem);
	var diffDays = Math.round(diffMs / 86400000); // days
	var diffHrs = Math.round((diffMs % 86400000) / 3600000); // hours
	var diffMins = Math.round(((diffMs % 86400000) % 3600000) / 60000); // minutes
	var texto ="";
	if(diffDays == 0 && diffHrs == 0 &&diffMins <= 1)
	{
		texto = "Agora";
	}
	else if (diffDays == 0 && diffHrs == 0 && diffMins <= 59) {
		texto = diffMins+"minutos atrás";
	}
	else if(diffDays == 0 && diffHrs < 24){
		texto = hora;
	}else{
		texto = dia+" "+hora;
	}
	return texto;
};
var notificacaoMensagem = function(agora){
	$.when(novasMensagens()).done(function(){
		$(mensagens).each(function(i,mensagem){
			var texto = adicionarTextoDiferenca(mensagem.data, agora);
			$("#listaNotificacoes").append("<a href='' onclick='return visualizarMensagem(\""
					+i+"\")' class='list-group-item'> <i"+
					" class='fa fa-comment fa-fw'></i> Mensagem de "+mensagem.usuarioRemetente
					+"<span"+
					" class='pull-right text-muted small'><em>"+texto+"</em> </span>"+
				"</a>");
			
		});
	});
}
var aniversariantes;
var listar_aniversariantes = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[UsuarioController].aniversario() }", 
//	    data: $("#formReserva").serialize(),
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
				
	    	aniversariantes = data;
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});		
}
var preencher_aniversariantes = function(){
	$.when(listar_aniversariantes()).done(function(){
		$("#aniversariantes").find("tr").remove().end();
		$(aniversariantes).each(function(indice_aniversariantes, aniversariante){
			var texto = "<tr><td>"+aniversariante.pessoa.nome+" "+aniversariante.pessoa.sobrenome;
			texto += "</td><td>"+retornarComZero(aniversariante.pessoa.datanascimento.day)+"/"+
			retornarComZero(aniversariante.pessoa.datanascimento.month);
			texto += "</td></tr>";
			$("#aniversariantes").append(texto);
		});
	});
}
$(document).ready(function(){
	preencher_aniversariantes();
	adicionarProximas();
	adicionarNotificacoes();
});
</script>
<script type="text/javascript">
var todasReservas;
var professores;
var salas;
var buscarReservas = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[ReservasController].listarProximas() }", 
//	    data: $("#formReserva").serialize(),
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	todasReservas = data;
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});		
}
var buscarProfessores = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[ProfessorController].listar() }", 
//	    data: $("#formReserva").serialize(),
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	professores = {};
	    	$(data).each(function(i,e){
	    		professores[e.codigo] = e;
	    	})
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});		
}
var buscarSalas = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[SalaController].listar() }", 
//	    data: $("#formReserva").serialize(),
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	salas = {};
	    	$(data).each(function(i,e){
	    		salas[e.id] = e;
	    	})
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});		
}
var retornarComZero = function(minuto)
{
	if (minuto < 10) {
		return "0"+minuto;
	}
	return minuto;
}
var adicionarSemestrais = function(reserva){
	$(reserva.semestrais).each(function(indice, semestral){
		icone = "";
		horaIni = retornarComZero(semestral.horaInicio.hour)+":"
		+retornarComZero(semestral.horaInicio.minute);
		horaFim = retornarComZero(semestral.horaFim.hour)+":"
		+retornarComZero(semestral.horaFim.minute);
		tSala = salas[semestral.idSal].nome;
		tProfessor = professores[reserva.reserva.codigoPro].pessoa.nome + " "+
		professores[reserva.reserva.codigoPro].pessoa.sobrenome;
		texto = "<tr class=''>"+'<td>'+icone+horaIni+" - "+horaFim+"</td>"+
		'<td>'+tSala+'</td>'+'<td>'+tProfessor+'</td>'+
		"</tr>";
		$("#proximaAulas").append(texto);
		
	});
}
var adicionarExporadicos = function(reserva){
	$(reserva.exporadicos).each(function(indice, exporadico){
		icone = "";
		horaIni = retornarComZero(exporadico.horaInicio.hour)+":"
		+retornarComZero(exporadico.horaInicio.minute);
		horaFim = retornarComZero(exporadico.horaFim.hour)+":"
		+retornarComZero(exporadico.horaFim.minute);
		tSala = salas[exporadico.idSal].nome;
		tProfessor = professores[reserva.reserva.codigoPro].pessoa.nome + " "+
		professores[reserva.reserva.codigoPro].pessoa.sobrenome;
		texto = "<tr class='cor_exporadico'>"+'<td>'+icone+horaIni+" - "+horaFim+"</td>"+
		'<td>'+tSala+'</td>'+'<td>'+tProfessor+'</td>'+
		"</tr>";
		$("#proximaAulas").append(texto);
	});
}
var adicionarProximas = function(){
// 	$("#proximaAulas")
	carregando();
	$.when(buscarReservas(), buscarProfessores(), buscarSalas()).done(function(){
		$("#proximaAulas").find('tr').remove().end();
		$(todasReservas).each(function(indiceReserva, reserva){
			if(reserva.semestrais.length > 0)
			{
				adicionarSemestrais(reserva);
			}
			if (reserva.exporadicos.length > 0) {
				
				adicionarExporadicos(reserva);
			}
		});
		carregar();
	});
}

window.setInterval(function(){
	adicionarProximas();
	adicionarNotificacoes();
}, 60000);

</script>
<script type="text/javascript">
		var visualizarMensagem = function(indice){
			var mensagem = mensagens[indice];
			$("#idRecEnviar").val(mensagem.idRec);
			$("#verMensagem").submit();
			return false;
		};
		var visualizarMural = function(indice){
			var mural = murais[indice];
			$("#idMurEnviar").val(mural.idMur);
			$("#verMural").submit();
			return false;
		};
</script>
</t:rodape>