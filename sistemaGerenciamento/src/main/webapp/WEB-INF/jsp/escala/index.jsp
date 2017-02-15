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
<div class="col-lg-12">

	<div class="row" role="main">
		<h1 class="page-header" style="margin-top: 0px;">
			Cadastro <small>Escala</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[HomeController].index()}">Página
					Inicial</a></li>
			<li class="active">Escala
			<a href="" onclick="return cadastroEscala();"><i class="fa fa-plus"></i></a> 
			</li>
			
		</ol>\
		
		<div class="col-xs-12">
			<div class='panel panel-default' id='panel_paginacao_tabela'>
				<div class='panel-body'>
					<div class='table-responsive espaco_cima'>
						<table id='tabelaMensagens'
							class='table table-striped table-hover table-bordered'>
							<thead class='thead-inverse' style='background: #1e282c;'>
								<tr>
									<th class='my-table-label'>Data</th>
									<th class='my-table-label'>Entrada</th>
									<th class='my-table-label'>Saída</th>
									<th class='my-table-label'>Usuários</th>
									<th class='my-table-label'>Ações</th>
								</tr>
							</thead>
							<tbody id='corpoTabelaMensagens'>
							<tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</div>
<c:import url="/WEB-INF/jsp/escala_modal/modal_form.jsp" />
<c:import url="/WEB-INF/jsp/escala_modal/modal_excluir.jsp" />
<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
	<!-- Importação do Javascript de  Criação de Eventos da Agenda de Reservas-->
	<script src="<c:url value='/assets/js/jquery-ui-1.7.2.custom.min.js' /> "></script>
	<!-- Importação do Javascript de Autocompletar Select-->
	<script src="<c:url value='/assets/js/select2.full.min.js' /> "></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$.when(coletarUsuarios()).done(function(){
				atualizarListaUsuario();
				atualizarAutoCompletar();
				regrasDoJquery();
				iniciarEscalas();
				
			});
		});
		var iniciarEscalas = function(){
			
			$.when(coletarEscalas()).done(function(){
				usuariosEscalas = [];
				escalas.sort(function(a,b){
					var x = new Date(a.dataIni);
					var y = new Date(b.dataIni);
					return (x < y ? -1 : x > y ? 1 : 0);
				});
				var textos = [];
				acrescentarEscalas(textos);
					
				
			});
		}
		var acrescentarEscalas = function(textos){
			$(escalas).each(function(indice_escala, escala){
				var texto = "<tr><td>";
				if (escala.dataFim.day == escala.dataIni.day &&
					escala.dataFim.month == escala.dataIni.month &&
					escala.dataFim.year == escala.dataIni.year) {
					texto += montarData(escala.dataIni)+"</td><td>";
				}else{
					texto += montarData(escala.dataIni)+" - "
					+montarData(escala.dataFim)+"</td><td>";
					
				}
				texto += montarHora(escala.horaInicio)+"</td><td>";
				texto += montarHora(escala.horaFim)+"</td><td>";
				$.when(coletarUsuariosEscala(escala.id, indice_escala)).done(function(){
					$(usuariosEscalas[indice_escala]).each(function(indice_usr_escala,usr_escala){
						$(usuarios).each(function(indice_usuario, usuario){
							if (usr_escala.loginUsr == usuario.login) {
								texto += usuario.pessoa.nome+" "+usuario.pessoa.sobrenome+" / ";
								
							};
						});
					});
					texto += "</td><td>";
					texto += criarTextoAcao("removerEscala",
							"fa fa-trash", indice_escala)+"</td>";
					textos[indice_escala] = texto;
					acrescentarTextos(textos);
						
				});
			});
		}
		var acrescentarTextos = function(textos){
			$("#corpoTabelaMensagens").find("tr").remove().end();
			if (textos.length >= escalas.length) {
				$(textos).each(function(indice,texto){
					$("#corpoTabelaMensagens").append(texto);
				});
			}
		}
		var removerEscala = function(indice_escala){
			var escala = escalas[indice_escala];
	    	$("#excluirEscalaId").val(escala.id);
	    	$("#novoExcluirEscala").modal('show');
	    	return false;
		};
		$("#excluirEscalaSim").on("click", function(){
			excluirEscala();
		});
		var excluirEscala = function(){
			var enviar = {"idEsc":$("#excluirEscalaId").val()};
			 return $.ajax({  
				    type:"post",  
				    url: "${linkTo[EscalaController].apagar() }",
				    data: enviar,
				    dataType: "json",  // Isso diz que você espera um JSON do servidor
				    beforeSend: function(xhr, settings){},  
				    success: function(data, textStatus, xhr){
				    	if (data.mensagem.indexOf("Erro") !== -1) {
				    		formularErro(data.mensagem);
						}
				    	
				    	iniciarEscalas();
				    },
				    error: function(xhr, textStatus, errorThrown){
				    	tratarErroAjax(xhr, textStatus, errorThrown);
				    }
				});
		}
		var montarHora = function(hora){
			return adicionarZero(hora.hour)+":"+adicionarZero(hora.minute);
		}
		var montarData = function(dataEsc)
		{
			return adicionarZero(dataEsc.day)+"/"+adicionarZero(dataEsc.month)+"/"
			+dataEsc.year;
		}
		var adicionarZero = function(numero)
		{
			if (numero < 10) {
				return "0"+numero;
			}
			return numero;
		}
		var criarTextoAcao = function(funcao, icone, indice_usuario)
		{
			return "<a href='' onclick='return "+funcao+"("+indice_usuario+")'><i class='"+icone+"'></i></a>";
		}
		var regrasDoJquery = function(){
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
				  messages: {
				    required: iniMensagemAlerta+"Campo obrigatório"+fimMensagemAlerta,
				    minlength: jQuery.validator.format
				    (iniMensagemAlerta+"Por favor insira os {0} caracteres."+fimMensagemAlerta),
				    maxlength: jQuery.validator.format
				    (iniMensagemAlerta+"Por favor insira os {0} caracteres."+fimMensagemAlerta)
				  }}
				);
			$("#diaInicio").rules("add",{
				required: true,
				 date: true,
				  messages: {
				    required: iniMensagemAlerta+"Campo obrigatório"+fimMensagemAlerta,
				    date: jQuery.validator.format
				    (iniMensagemAlerta+"Por favor insira uma data válida!"+fimMensagemAlerta),
				   
				  }}
				);
		}
	</script>
	<script type="text/javascript">
		var usuarios;
		var escalas;
		var usuariosEscalas = [];
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
		var coletarEscalas = function(){
			return $.ajax({  
			    type:"post",  
			    url: "${linkTo[EscalaController].listar() }", 
			    dataType: "json",  // Isso diz que você espera um JSON do servidor
			    beforeSend: function(xhr, settings){},  
			    success: function(data, textStatus, xhr){
			    	escalas = data;
			    	//criarPermissoes();
			    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
			    error: function(xhr, textStatus, errorThrown){
			    	tratarErroAjax(xhr, textStatus, errorThrown);
			    }
			});
		}
		
		var coletarUsuariosEscala = function(id, indice){
			enviar = {"id" : id};
			return $.ajax({  
			    type:"post",  
			    url: "${linkTo[EscalaController].listar_usuario() }", 
			    data: enviar,
			    dataType: "json",  // Isso diz que você espera um JSON do servidor
			    beforeSend: function(xhr, settings){},  
			    success: function(data, textStatus, xhr){
			    	usuariosEscalas[indice] = data;
			    	//criarPermissoes();
			    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
			    error: function(xhr, textStatus, errorThrown){
			    	tratarErroAjax(xhr, textStatus, errorThrown);
			    }
			});
		};
	</script>
	<script type="text/javascript">
		var cadastroEscala = function(){
			$("#novaEscala").modal('show');
			return false;
		}
		var atualizarAutoCompletar = function(){
			$(".select_auto_completar").select2();
		}
		var atualizarListaUsuario = function(){
			$("#selecionarUsuario").find('option').remove().end();
			$(usuarios).each(function(indice_usuario,usuario){
				$("#selecionarUsuario").append("<option value='"+indice_usuario+"'>"+usuario.pessoa.nome
				+" "+usuario.pessoa.sobrenome+"</option>");
				
			});
		}
		var addUsuarioEsc = function(){
			var indice_usuario = $("#selecionarUsuario").val();
			var usuario = usuarios[indice_usuario];
			if ($("#usuariosLogin").find(":input[value='"+usuario.login+"']").length > 0) {
				removerUsuarioTabela(indice_usuario);
			}
			$("#corpoTabelaUsuarios").append("<tr id='trUsuarios"+usuario.login+"'><td>"
					+usuario.pessoa.nome+" "+usuario.pessoa.sobrenome
					+"</td><td>"+criarTextoAcao("removerUsuarioTabela",
			"fa fa-trash", indice_usuario)+"</td></tr>");
			$("#usuariosLogin").append("<input type='hidden'"+
			" name='escalaEnv.usuarios["+indice_usuario+"]' value='"+usuario.login+"' />");
			return false;
		}
		var removerUsuarioTabela = function(indice_usuario){
			var usuario = usuarios[indice_usuario];
			$("#usuariosLogin").find(":input[value='"+usuario.login+"']").remove().end();
			$("#trUsuarios"+usuario.login).remove().end();
			return false;
		}
		var cadastrarEscala = function(){
			$.when(enviarEscala()).done(function(){
				$("#saida").val("");
				$("#entrada").val("");
				
			});
			
			return false;
		};
		var enviarEscala = function(){
			if($("#form").valid())
			{
				var enviar = $("#form").serialize();
				 return $.ajax({  
					    type:"post",  
					    url: "${linkTo[EscalaController].postar() }",
					    data: enviar,
					    dataType: "json",  // Isso diz que você espera um JSON do servidor
					    beforeSend: function(xhr, settings){},  
					    success: function(data, textStatus, xhr){
					    	if (data.mensagem.indexOf("Erro") !== -1) {
					    		formularErro(data.mensagem);
							}
					    	iniciarEscalas();
					    },
					    error: function(xhr, textStatus, errorThrown){
					    	tratarErroAjax(xhr, textStatus, errorThrown);
					    }
					});
			}else{
				formularErro("Formulário Inválido!");
			}
		}
	</script>
</t:rodape>	