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
			Listar <small>Ordem de Serviço</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[HomeController].index()}">Página
					Inicial</a></li>
			<li class="active">Listar OS</li>
		</ol>
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
		<form action="${linkTo[OrdemServicoController].visualizar() }" method="post" id="formVisualizar">
			<input type="hidden" name="idSor" id="visualizarOrdemId" />
		</form>
	</div>
</div>
<c:import url="/WEB-INF/jsp/ordemServico_modal/modal_ver.jsp" />
<c:import url="/WEB-INF/jsp/ordemServico_modal/modal_excluir.jsp" />
<c:import url="/WEB-INF/jsp/ordemServico_modal/modal_redirecionar.jsp" />
<c:import url="/WEB-INF/jsp/ordemServico_modal/modal_finalizar.jsp" />
<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
<!-- Importação do Javascript de  Criação de Eventos da Agenda de Reservas-->
<script src="<c:url value='/assets/js/jquery-ui-1.7.2.custom.min.js' /> "></script>
<!-- Importação do Javascript de Autocompletar Select-->
<script src="<c:url value='/assets/js/select2.full.min.js' /> "></script>
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
	return $.when(listarOrdem()).done(function(){
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
		var texto = "<tr><td>"+"<a href='' onclick='return detalhesOrdem(\""+indice_ordem+"\")'>";
		$(salas).each(function(indice_sala, sala){
			if (ordemsSalas[indice_ordem].length > 0) {
				if (ordemsSalas[indice_ordem][0].idSala == sala.id) {
					texto += "Sala: "+sala.nome+"</a></td><td>";
				}
			}else{
				if (ordemsComputador[indice_ordem][0].idSala == sala.id) {
					texto += "Computadora da Sala: "+sala.nome
					+" Nº:"+ordemsComputador[indice_ordem][0].numeroPc+"</a></td><td>";
				}
			}
		});
		var dataEsc = new Date(subOrdems[indice_ordem][0].dataParaSerExecutada);
		var paraUsuario = false;
		texto += adicionarZero(dataEsc.getDate())+"/"+adicionarZero(dataEsc.getMonth()+1)+
		"/"+adicionarZero(dataEsc.getFullYear())+" "+
		adicionarZero(dataEsc.getHours())+":"+adicionarZero(dataEsc.getMinutes())+"</td><td>";
		if (subOrdemsTurno[indice_ordem].length > 0) {
			$(turnos).each(function(indice_turno, turno){
				if (turno.id == subOrdemsTurno[indice_ordem][0].idTur) {
					texto += "Turno: "+retornarTurno(turno.periodo)+"</td><td>";
				}
			});
		}else{
			$(usuarios).each(function(indice_usuario, usuario){
				if (usuario.login == subOrdemsUsuario[indice_ordem][0].loginUsr) {
					paraUsuario = true;
					texto += "Usuário:"+usuario.pessoa.nome+" "+usuario.pessoa.sobrenome+"</a></td><td>";
				}
			});
		}
		var diaHj = new Date();
		var diaOr = new Date(subOrdems[indice_ordem][0].dataParaSerExecutada);
		if (temPermissaoCadastro) {
			texto += criarTextoAcao("finalizarOrdem", "fa fa-check", indice_ordem);
		}else if (paraUsuario){
			if(subOrdemsUsuario[indice_ordem][0].loginUsr == "${usuarioLogado.usuario.login}"){
				texto += criarTextoAcao("finalizarOrdemUsr", "fa fa-check", indice_ordem);
			}
		}
		if(verificarDataRepasse(diaHj, diaOr))
		{
			if (temPermissao) {
				texto += criarTextoAcao("repassarOrdem", "fa fa-reply", indice_ordem);
			}else if (paraUsuario){
				if(subOrdemsUsuario[indice_ordem][0].loginUsr == "${usuarioLogado.usuario.login}"){
					texto += criarTextoAcao("repassarOrdemUsr", "fa fa-reply", indice_ordem);
				}
			}
			
		}
		if (temPermissaoExcluir) {
			texto += criarTextoAcao("excluirOrdem", "fa fa-trash", indice_ordem);
			texto += criarTextoAcao("editarOrdem", "fa fa-pencil", indice_ordem);
		}
		
		texto += criarTextoAcao("detalhesOrdem", "fa fa-search", indice_ordem);
		texto += "</td></tr>";
		$("#corpoTabelaOrdemServicos").append(texto);
	 });
 }

 $(document).ready(function(){
	 carregando();
	 $.when(atualizarValoresTabela()).done(function(){
		 verificarPermissao();
		 regrasRepassar();
		 mudarDataExecutada();
		 verficarDestino();
		 carregar();
	 });
	 
	 
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
	return "<a href='' onclick='return "+funcao+"("+indice_ordem+")'><i class='fa-2x "+icone+"' style='padding-right:5px;'></i></a>";
}
var verificarDataRepasse = function(dataHj, dataOr){
	if(dataHj.getYear() > dataOr.getYear() || 
	   dataHj.getYear() == dataOr.getYear() && dataHj.getMonth() > dataOr.getMonth() ||
	   dataHj.getYear() == dataOr.getYear() && dataHj.getMonth() == dataOr.getMonth()
	   && dataHj.getDate() > dataOr.getDate() ||
	   dataHj.getYear() == dataOr.getYear() && dataHj.getMonth() == dataOr.getMonth()
	   && dataHj.getDate() == dataOr.getDate() && dataHj.getHours() >= dataOr.getHours())
	{
		return true;
	}
	return false;
};
var editarOrdem = function(indice_ordem){
	var ordem = ordems[indice_ordem];
	 $.redirectPost("${linkTo[OrdemServicoController].editarOrdem() }",
				{"idOrs" : ordem.id});
	 return false;
}
$.extend(
{
    redirectPost: function(location, args)
    {
        var form = $('<form></form>');
        form.attr("method", "post");
        form.attr("action", location);

        $.each( args, function( key, value ) {
            var field = $('<input></input>');

            field.attr("type", "hidden");
            field.attr("name", key);
            field.attr("value", value);

            form.append(field);
        });
        $(form).appendTo('body').submit();
    }
});
var detalhesOrdem = function(indice_ordem)
{
	var subOrdem = subOrdems[indice_ordem][0];
	visualizarOrdem(subOrdem.id);
	return false;;
}

var visualizarOrdem = function(idSor) {
	$("#visualizarOrdemId").val(idSor);
	$("#formVisualizar").submit();
	return false;
};
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
var finalizarOrdem = function(indice_ordem){
	fazerFinalizacaoOrdem(indice_ordem);
	return false;
}
var finalizarOrdemUsr = function(indice_ordem){
	redirecionada = true;
	fazerFinalizacaoOrdem(indice_ordem);
	return false;
}
var fazerFinalizacaoOrdem = function(indice_ordem){
	$("#finalizarSubOrdemId").val(subOrdems[indice_ordem][0].id)
	$("#novoFinalizarOrdem").modal('show');
}
$("#finalizarOrdemSim").on('click', function(){
	var enviar = {"idSor" : $("#finalizarSubOrdemId").val()};
	if (!redirecionada) {
		return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].finalizar() }",
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
	}else{
		redirecionada = false;
		return $.ajax({  
		    type:"post",  
		    url: "${linkTo[OrdemServicoController].finalizarUsuario() }",
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
	}
	 
});
</script>
<script type="text/javascript">
var adicionarZero = function(numero)
{
	if (numero < 10) {
		return "0"+numero;
	}
	return numero;
}
var mudarDataExecutada = function(){
	var dataEsc = new Date();
	$("#dataExecutada").val(dataEsc.getFullYear()
			+"-"+adicionarZero(dataEsc.getMonth()+1)+
			"-"+adicionarZero(dataEsc.getDate()));
}
var adicionarTurnos = function(){
	$("#tipoAlvo").find("div").remove().end();
	$("#tipoAlvo").append("<div><label>Selecione um Turno:</label>"+
	"<select class=\"form-control\" id=\"idAlvo\" name=\"redirecionada.idDestino\">"
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
	"<select class=\"form-control select_auto_completar \" id=\"idAlvo\" name=\"redirecionada.idDestino\">"+
	"</div>");
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
var redirecionada = false;
var cadastrarRepasseOrdem = function(){
	enviarMensagemRedirecionar();
	return false;
}
var enviarMensagemRedirecionar = function(){
	 if ($("#formRepasse").valid()) {
		 var enviar = $("#formRepasse").serialize();
		 if (!redirecionada) {
			 return $.ajax({  
				    type:"post",  
				    url: "${linkTo[OrdemServicoController].redirecionar() }",
				    data: enviar,
				    dataType: "json",  // Isso diz que você espera um JSON do servidor
				    beforeSend: function(xhr, settings){},  
				    success: function(data, textStatus, xhr){
				    	if (data.mensagem.indexOf("Erro") !== -1) {
				    		formularErro(data.mensagem);
						}else{
							atualizarValoresTabela();
						}
				    	
				    },
				    error: function(xhr, textStatus, errorThrown){
				    	tratarErroAjax(xhr, textStatus, errorThrown);
				    }
				});
		}else{
			redirecionada = false;
			return $.ajax({  
			    type:"post",  
			    url: "${linkTo[OrdemServicoController].redirecionarOrdemUsuario() }",
			    data: enviar,
			    dataType: "json",  // Isso diz que você espera um JSON do servidor
			    beforeSend: function(xhr, settings){},  
			    success: function(data, textStatus, xhr){
			    	if (data.mensagem.indexOf("Erro") !== -1) {
			    		formularErro(data.mensagem);
					}else{
						atualizarValoresTabela();
					}
			    	
			    },
			    error: function(xhr, textStatus, errorThrown){
			    	tratarErroAjax(xhr, textStatus, errorThrown);
			    }
			});
		}
	}else{
		formularErro("Formulário Inválido!");
	}
}
var repassarOrdem = function(indice_ordem)
{
	repassandoOrdem(indice_ordem);
	return false;
}
var repassarOrdemUsr = function(indice_ordem){
	redirecionada = true;
	repassandoOrdem(indice_ordem);
	return false;
}
var repassandoOrdem = function(indice_ordem){
	var ordem = ordems[indice_ordem];
	var subOrdem = subOrdems[indice_ordem][0];
	$("#idOrdemRedirecionada").val(ordem.id);
	$("#idSubOrsAntiga").val(subOrdem.id);
	$("#novoRepasseOrdem").modal('show');
}

</script>
<script type="text/javascript">
var regrasRepassar = function(){
	$("#formRepasse").validate(function(){});
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
	$("#justificativaRedirecionada").rules("add",{
		required: true,
		  minlength: 5,
		  maxlength: 1000,
		  messages: {
		    required: iniMensagemAlerta+"Campo obrigatório"+fimMensagemAlerta,
		    minlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por favor insira no mínimo {0} caracteres."+fimMensagemAlerta),
		    maxlength: jQuery.validator.format
		    (iniMensagemAlerta+"Por favor insira até {0} caracteres."+fimMensagemAlerta)
		  }}
	);
}
</script>
<script type="text/javascript">
var temPermissao = false;
var temPermissaoExcluir = false;
var temPermissaoCadastro = false;
var verificarPermissao = function(){
	
	$(permissoesUsuario).each(function(i,permi){
		if(permi== 18 || permi == 1){
			  temPermissao = true;
		  }
		if(permi== 19 || permi == 1){
			temPermissaoExcluir = true;
		  }
		if(permi== 17 || permi == 1){
			temPermissaoCadastro = true;
		  }
	});
}
</script>
</t:rodape>