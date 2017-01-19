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
			Visualizar <small>Ordem de Serviço</small>
		</h1>
		<div class="col-xs-12">
			<div class='panel panel-default' id='panel_paginacao_tabela'>
				<div class='panel-body'>
					<div class='table-responsive espaco_cima'>
						<table id='tabelaMensagens'
							class='table table-striped table-hover table-bordered'>
							<thead class='thead-inverse' style='background: #1e282c;'>
								<tr>
									<th class='my-table-label'>Tipo de Ordem</th>
									<th class='my-table-label'>Prazo</th>
									<th class='my-table-label'>Destinada Para</th>
									<th class='my-table-label'>Ações</th>
								</tr>
							</thead>
							<tbody id='corpoTabelaOrdemServicos'>
							<tbody>
						</table>
					</div>
					<div class='panel-footer' id='rodapeTabelaPaginacao'></div>
				</div>
			</div>
		</div>
		
	</div>
</div>
<c:import url="/WEB-INF/jsp/ordemServico_modal/modal_ver.jsp" />
<c:import url="/WEB-INF/jsp/ordemServico_modal/modal_excluir.jsp" />
<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
<script type="text/javascript">
 var ordems;
 var ordemsSalas = [];
 var ordemsComputador = [];
 var subOrdems = [];
 var subOrdemsTurno = [];
 var subOrdemsUsuario = [];
 var turnos;
 var salas;
 var usuarios;
 var listarOrdem = function()
 {
	 return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].listar() }", 
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	ordems = data;
		    	//criarPermissoes();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
 }
 var listarTurnos = function()
 {
	 return $.ajax({  
		    type:"post",  
		    url: "${linkTo[TurnoController].listar() }", 
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	turnos = data;
		    	//criarPermissoes();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
 }
 var listarUsuarios = function()
 {
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
 var listarSalas = function()
 {
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
 var listarOrdemSala = function(id, indice){
	 var enviar = {"idOrs" : id};
	 return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].listarSala() }",
		    data: enviar,
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	ordemsSalas[indice] = data;
		    	//criarPermissoes();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
 }
 var listarOrdemComputador = function(id, indice){
	 var enviar = {"idOrs" : id};
	 return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].listarComputador() }",
		    data: enviar,
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	ordemsComputador[indice] = data;
		    	//criarPermissoes();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
 }
 var listarSubOrdem = function(id, indice){
	 var enviar = {"idOrs" : id};
	 return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].listarSubOrdem() }",
		    data: enviar,
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	subOrdems[indice] = data;
		    	//criarPermissoes();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
 }
 var listarSubOrdemTurno = function(id, indice){
	 var enviar = {"idSor" : id};
	 return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].listarTurno() }",
		    data: enviar,
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	subOrdemsTurno[indice] = data;
		    	//criarPermissoes();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
 }
 var listarSubOrdemUsuario = function(id, indice){
	 var enviar = {"idSor" : id};
	 return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].listarUsuario() }",
		    data: enviar,
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	subOrdemsUsuario[indice] = data;
		    	//criarPermissoes();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
 }
 var atualizarValoresTabela = function(){
	 $.when(listarOrdem()).done(function(){
			$(ordems).each(function(indice_ordem, ordem){
				$.when(listarOrdemSala(ordem.id, indice_ordem),
				listarOrdemComputador(ordem.id, indice_ordem), listarSubOrdem(ordem.id, indice_ordem))
				.done(function(){
					atualizarValoresSubOrdem(indice_ordem);
				});
			});
			prepararParaAtualizarTabela();
		 });
	 
 }
 var atualizarValoresSubOrdem = function(indice_ordem){
	 var indice = -1;
	 var indice_id = -1;
	 $(subOrdems[indice_ordem]).each(function(indice_sub_ordem, sub_ordem){
		if (sub_ordem.id > indice_id) {
			indice = indice_sub_ordem;
			indice_id =  sub_ordem.id;
		}
     });
	if (indice != -1) {
		$.when(listarSubOrdemTurno(indice_id, indice_ordem),
				listarSubOrdemUsuario(indice_id, indice_ordem))
		.done(function(){
			return;
		});
	}
 }
 var prepararParaAtualizarTabela = function(){
	 $.when(listarSalas(), listarTurnos(), listarUsuarios()).done(function(){
		 atualizarTabela();
	 });
 };
 var atualizarTabela = function(){
	 $("#corpoTabelaOrdemServicos").find('tr').remove().end();
	 $(ordems).each(function(indice_ordem, ordem){
		var texto = "<tr><td>";
		$(salas).each(function(indice_sala, sala){
			if (ordemsSalas[indice_ordem].length > 0) {
				if (ordemsSalas[indice_ordem][0].idSala == sala.id) {
					texto += "Sala: "+sala.nome+"</td><td>";
				}
			}else{
				if (ordemsComputador[indice_ordem][0].idSala == sala.id) {
					texto += "Computadora da Sala: "+sala.nome
					+" Nº:"+ordemsComputador[indice_ordem][0].numeroPc+"</td><td>";
				}
			}
		});
		texto += subOrdems[indice_ordem][0].dataParaSerExecutada.toLocaleString()+"</td><td>";
		if (subOrdemsTurno[indice_ordem].length > 0) {
			$(turnos).each(function(indice_turno, turno){
				if (turno.id == subOrdemsTurno[indice_ordem][0].idTur) {
					texto += "Turno: "+retornarTurno(turno.periodo)+"</td><td>";
				}
			});
		}else{
			$(usuarios).each(function(indice_usuario, usuario){
				if (usuario.login == subOrdemsUsuario[indice_ordem][0].loginUsr) {
					texto += "Usuário: "+usuario.pessoa.nome+" "+usuario.pessoa.sobrenome+"</td><td>";
				}
			});
		}
		var diaHj = new Date();
		var diaOr = new Date(subOrdems[indice_ordem][0].dataParaSerExecutada);
		texto += criarTextoAcao("finalizarOrdem", "fa fa-check", indice_ordem);
		if(verificarDataRepasse(diaHj, diaOr))
		{
			texto += criarTextoAcao("repassarOrdem", "fa fa-reply", indice_ordem);
		}
		texto += criarTextoAcao("excluirOrdem", "fa fa-trash", indice_ordem);
		texto += criarTextoAcao("detalhesOrdem", "fa fa-search", indice_ordem);
		texto += "</td></tr>";
		$("#corpoTabelaOrdemServicos").append(texto);
	 });
 }
 $(document).ready(function(){
	 atualizarValoresTabela();
 });
</script>
<script type="text/javascript">
var retornarTurno = function(turno) {
	switch (turno) {
	case 1:
		return "Manhã";
		break;
	case 2:
		return "Tarde";
		break;
	case 3:
		return "Noite";
		break;

	}
}
var criarTextoAcao = function(funcao, icone, indice_ordem)
{
	return "<a href='' onclick='return "+funcao+"("+indice_ordem+")'><i class='"+icone+"'></i></a>";
};
var verificarDataRepasse = function(dataHj, dataOr){
	if(dataHj.getYear() > dataOr.getYear() || 
	   dataHj.getYear() == dataOr.getYear() && dataHj.getMonth() > dataOr.getMonth() ||
	   dataHj.getYear() == dataOr.getYear() && dataHj.getMonth() == dataOr.getMonth()
	   && dataHj.getDay() > dataOr.getDay() ||
	   dataHj.getYear() == dataOr.getYear() && dataHj.getMonth() == dataOr.getMonth()
	   && dataHj.getDay() == dataOr.getDay() && dataHj.getHours() >= dataOr.getHours())
	{
		return true;
	}
	return false;
};
var detalhesOrdem = function(indice_ordem)
{
	var ordem = ordems[indice_ordem];
	var subOrdem = subOrdems[indice_ordem][0];
	visualizarOrdem(subOrdem.id);
	$(usuarios).each(function(indice_usuario, usuario){
		if (usuario.login == subOrdem.loginUsr) {
			$("#pessoaRepasse").val(usuario.pessoa.nome+" "+usuario.pessoa.sobrenome);
		}
	});
	if (ordem.descricao) {
		$("#descricao").val(ordem.descricao);
	}
	if (subOrdem.justificativa) {
		$("#justificativa").val(subOrdem.justificativa);
	}
	$("#novoDetalhesOrdem").modal('show');
	return false;
}
var visualizarOrdem = function(id)
{
	var enviar = {"idSor" : id};

	debugger;
	 return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].visualizar() }",
		    data: enviar,
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	
		    },
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
}
var excluirOrdem = function(indice_ordem){
	$("#excluirOrdemId").val(ordems[indice_ordem].id)
	$("#novoExcluirOrdem").modal('show');
	return false;
}
$("#excluirOrdemSim").on('click', function(){
	var enviar = {"idOrs" : $("#excluirOrdemId").val()};
	 return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].excluir() }",
		    data: enviar,
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	atualizarValoresTabela();
		    	//criarPermissoes();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
});
</script>
</t:rodape>