<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importação do Cabecalho -->
<t:cabecalho>

	<!-- Estilização da TAG select -->
	<style>
</style>
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>
<div class="col-lg-12">

	<div class="row" role="main">
		<h1 class="page-header" style="margin-top: 0px;">
			Cadastro <small>Turno</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[HomeController].index()}">Página
					Inicial</a></li>
			<li class="active">Turno
			<a href="" onclick="return cadastroTurno();"><i class="fa fa-plus"></i></a> 
			</li>
			
		</ol>
		
		<div class="col-xs-12">
			<div class='panel panel-default' id='panel_paginacao_tabela'>
				<div class='panel-body'>
					<div class='table-responsive espaco_cima'>
						<table id='tabelaMensagens'
							class='table table-striped table-hover table-bordered'>
							<thead class='thead-inverse' style='background: #1e282c;'>
								<tr>
									<th class='my-table-label'>Entrada</th>
									<th class='my-table-label'>Saída</th>
									<th class='my-table-label'>Período</th>
									<th class='my-table-label'>Membros</th>
									<th class='my-table-label'>Ações</th>
								</tr>
							</thead>
							<tbody id='corpoTabelaTurno'>
							<tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<c:import url="/WEB-INF/jsp/mensagem_modal/modal_excluir.jsp" />
<c:import url="/WEB-INF/jsp/turno_modal/modal_form.jsp" />
<c:import url="/WEB-INF/jsp/turno_modal/modal_addUsuario.jsp" />

<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
<script type="text/javascript">
	var verificarErroEAtualizar = function(data){
		if (data.mensagem.indexOf("Erro") !== -1) {
    		formularErro(data.mensagem);
		}
    	preencherTabela();
	}
</script>
<script type="text/javascript">
	var cadastroTurno = function(){
		$("#novoTurno").modal('show');
		return false;
	}
	var cadastrarTurno = function(){
		if($("#form").valid())
		{
			$.ajax({  
			    type:"post",  
			    url: "${linkTo[TurnoController].postar() }", 
			    data: $("#form").serialize(),
			    dataType: "json",  // Isso diz que você espera um JSON do servidor
			    beforeSend: function(xhr, settings){},  
			    success: function(data, textStatus, xhr){
			    	verificarErroEAtualizar(data);
			    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
			    error: function(xhr, textStatus, errorThrown){ 
			    	tratarErroAjax(xhr, textStatus, errorThrown);
				 }
			});
		}else{
			formularErro("Formulário Inválido!");
		}
		return false;
	}
	
</script>

<!-- Validação com Jquery -->
<script type="text/javascript">
$().ready(function(){
	$("#form").validate(function(){});
	$("#horaInicio").rules("add",{
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
	$("#horaFim").rules("add",{
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
});
</script>

<script type="text/javascript">
var turnos;
var listarTurnos = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[TurnoController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	turnos = data;
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
var retornarComZero = function(valor){
	if (valor < 10) {
		return "0"+valor;
	}else{
		return valor;
	}
};
var montarHorario = function(hora){
	return retornarComZero(hora.hour)+":"+retornarComZero(hora.minute);
}
var retornarTurno = function(turno){
	switch(turno) {
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
var preencherTabela = function(){
	$("#corpoTabelaTurno").find('tr').remove().end();
	$.when(listarTurnos()).done(function(){
		$(turnos).each(function(i,turno){
			$.when(listarUsuariosTurnos(turno.id)).done(function(){
				var entrada = montarHorario(turno.horaInicio);
				var saida = montarHorario(turno.horaFim);
				var excluir = "<a href='' onclick='return excluirTurno(\""+i
				+"\")'><i class='fa fa-trash'></i></a>";
				var addMembros = "<a href='' onclick='return adicionarUsuarioTurno(\""+turno.id+"\"	);'>"+
				"<i class='fa fa-plus'></i></a>"; 
				var textUsuarios = "";
				$(usuariosDoTurno).each(function(i, usrTur){
					textUsuarios += usrTur.usuario.pessoa.nome+" "
					+usrTur.usuario.pessoa.sobrenome+" / ";
				});
				$("#corpoTabelaTurno").append("<tr><td class='my-table-label-body'>"+
				entrada+"</td><td class='my-table-label-body'>"+
				saida+"</td><td class='my-table-label-body'>"+
				retornarTurno(turno.periodo)+"</td><td class='my-table-label-body'>"+
				addMembros+textUsuarios+"</td><td class='my-table-label-body'>"+
				excluir+
				"</td></tr>");
			})
			
		});
		
	});
}
$(preencherTabela());
</script>
<script type="text/javascript">
var excluirTurnoSelecionado = function(id){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[TurnoController].excluir() }", 
	    data: {"id" : id},
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	verificarErroEAtualizar(data);
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
var excluirTurno = function(indice){
	excluirTurnoSelecionado(turnos[indice].id);
	return false;
}
</script>
<script type="text/javascript">
	var adicionarUsuarioTurno = function(idTurno){
		$("#selecionarUsuarioTurnoTurno").val(idTurno);
		$("#novoUsuarioTurno").modal('show');
		return false;
	};
	var cadastrarUsuarioTurno = function(){
		if($("#formTurno").valid())
		{
			$.ajax({  
			    type:"post",  
			    url: "${linkTo[TurnoController].cadastrarUsuarioTurno() }", 
			    data: $("#formTurno").serialize(),
			    dataType: "json",  // Isso diz que você espera um JSON do servidor
			    beforeSend: function(xhr, settings){},  
			    success: function(data, textStatus, xhr){
			    	verificarErroEAtualizarUsuarioTurno(data);
			    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
			    error: function(xhr, textStatus, errorThrown){ 
			    	tratarErroAjax(xhr, textStatus, errorThrown);
				 }
			});
			preencherTabela();
		}else{
			formularErro("Formulário Inválido!");
		}
		return false;
	}
	var verificarErroEAtualizarUsuarioTurno = function(data){
		if (data.mensagem.indexOf("Erro") !== -1) {
    		formularErro(data.mensagem);
		}
	}
</script>
<script type="text/javascript">
var usuariosDoTurno;
var listarUsuariosTurnos = function(idTurno){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[TurnoController].listarUsuarioTurnoPorTurno() }", 
	    data: {"idTurno" : idTurno },
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	usuariosDoTurno = data;
	    	//criarPermissoes();
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});
};
</script>
<script type="text/javascript">
var usuarios;
var listarUsuarios = function(){
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[UsuarioController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	usuarios = data;
	    	adicionarUsuariosLista();
	    	//criarPermissoes();
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});
};
var adicionarUsuariosLista = function(){
	$("#selecionarUsuarioTurnoUsuario").find('option')
    .remove()
    .end();
	$(usuarios).each(function(i,usuario){
		$("<option />", {value: usuario.login, text: usuario.pessoa.nome 
		+ " "+ usuario.pessoa.sobrenome}).appendTo($("#selecionarUsuarioTurnoUsuario"));
	});
}
$(listarUsuarios());
</script>
</t:rodape>