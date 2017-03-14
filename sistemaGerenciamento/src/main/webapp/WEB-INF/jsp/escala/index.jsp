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
			
		</ol>
		<div class="col-xs-2">
				<label>Filtro de Mês:</label> 
			<select class="form-control select_auto_completar" style="width: 100%;"
			id="selectFiltrarHora">
				<option value="0">Janeiro</option>
				<option value="1">Fevereiro</option>
				<option value="2">Março</option>
				<option value="3">Abril</option>
				<option value="4">Maio</option>
				<option value="5">Junho</option>
				<option value="6">Julho</option>
				<option value="7">Agosto</option>
				<option value="8">Setembro</option>
				<option value="9">Outubro</option>
				<option value="10">Novembro</option>
				<option value="11">Dezembro</option>
			</select>
			</div>
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
			$("#selectFiltrarHora").val(new Date().getMonth()).trigger("change");
			carregando();
			$.when(coletarUsuarios()).done(function(){
				atualizarListaUsuario();
				atualizarAutoCompletar();
				regrasDoJquery();
				iniciarEscalas();
				carregar();
			});
		});
		var iniciarEscalas = function(){
			
			$.when(coletarEscalas()).done(function(){
				usuariosEscalas = [];
				escalas.sort(function(a,b){
					var x = new Date(montarDataVal(a.dataIni));
					var y = new Date(montarDataVal(b.dataIni));
					return (x < y ? -1 : x > y ? 1 : 0);
				});
				var textos = [];
				acrescentarEscalas(textos);
					
				
			});
		}
		 $("#selectFiltrarHora").on("change", function(){
			 if (escalas) {
				 var textos = []
				 acrescentarEscalas(textos);
			}
		 });
		var acrescentarEscalas = function(textos){
			var numeroSelecionados = 0;
			$(escalas).each(function(indice_escala, escala){
				var mesAtual = new Date(montarDataVal(escala.dataIni)).getMonth();
				
				if (mesAtual == $("#selectFiltrarHora").val()) {
					numeroSelecionados++;
				}
			});
			$(escalas).each(function(indice_escala, escala){
				var mesAtual = new Date(montarDataVal(escala.dataIni)).getMonth();
				
				if (mesAtual == $("#selectFiltrarHora").val()) {
					
					$.when(coletarUsuariosEscala(escala.id, indice_escala)).done(function(){
						$(usuariosEscalas[indice_escala]).each(function(indice_usr_escala,usr_escala){
							$(usuarios).each(function(indice_usuario, usuario){
								if (usr_escala.loginUsr == usuario.login) {
									if (!fotos_perfil[usuario.idPes]) {
										$.when(guardarFotoPerfil(usuario)).done(function(){
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
											texto += texto_usuario(texto, usuario);
											texto += "</td><td>";
											texto += criarTextoAcao("removerEscala",
													"fa fa-trash", indice_escala);
											texto += criarTextoAcao("editarEscala",
												"fa fa-pencil", indice_escala)+"</td>";
											textos[indice_escala] = texto;
											acrescentarTextos(textos, numeroSelecionados);	
										})
									}else{
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
										texto += texto_usuario(texto, usuario);
										texto += "</td><td>";
										texto += criarTextoAcao("removerEscala",
												"fa fa-trash", indice_escala);
										texto += criarTextoAcao("editarEscala",
											"fa fa-pencil", indice_escala)+"</td>";
										textos[indice_escala] = texto;
										acrescentarTextos(textos, numeroSelecionados);	
									}
									
									
								};
							});
						});
						
						
					});
				}
				
				
			});
			if (numeroSelecionados == 0) {
				acrescentarTextos(textos, numeroSelecionados);	
			}
		}
		var acrescentarTextos = function(textos, numeroSelecionados){
			$("#corpoTabelaMensagens").find("tr").remove().end();
			if (textos.length >= numeroSelecionados) {
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
		var montarDataVal = function(dataEsc)
		{
			return adicionarZero(dataEsc.year)+"-"+adicionarZero(dataEsc.month)+
			"-"+adicionarZero(dataEsc.day);
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
			return "<a href='' onclick='return "+funcao+"("+indice_usuario+")' style='margin-left:5px;'>"
			+"<i class='"+icone+" fa-2x'></i></a>";
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
				  horaMaiorQue: "#entrada",
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
			$("#idEscalaEnv").val("0");
			$("#novaEscala").modal('show');
			return false;
		}
		var editarEscala = function(indice){
			limparValoresModal();
			var escala = escalas[indice];
			if (escala.dataFim.day == escala.dataIni.day &&
				escala.dataFim.month == escala.dataIni.month &&
				escala.dataFim.year == escala.dataIni.year) {
				$("#diaInicio").val(montarDataVal(escala.dataIni));
			}else{
				$("#diaInicio").val(montarDataVal(escala.dataIni));
				$("#diaFim").val(montarDataVal(escala.dataFim));
					
			}
			$("#entrada").val(montarHora(escala.horaInicio));
			$("#saida").val(montarHora(escala.horaFim));
			$.when(coletarUsuariosEscala(escala.id, indice)).done(function(){
				$(usuariosEscalas[indice]).each(function(indice_usr_escala,usr_escala){
					$(usuarios).each(function(indice_usuario, usuario){
						if (usr_escala.loginUsr == usuario.login) {
							adicionarUsuarioTabelaModal(usuario, indice_usuario);
							
						};
					});
				});
			});
			$("#idEscalaEnv").val(escala.id);
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
			adicionarUsuarioTabelaModal(usuario, indice_usuario);
			return false;
		}
		var adicionarUsuarioTabelaModal = function(usuario, indice_usuario){
			$("#corpoTabelaUsuarios").append("<tr id='trUsuarios"+usuario.login+"'><td>"
					+usuario.pessoa.nome+" "+usuario.pessoa.sobrenome
					+"</td><td>"+criarTextoAcao("removerUsuarioTabela",
			"fa fa-trash", indice_usuario)+"</td></tr>");
			$("#usuariosLogin").append("<input type='hidden'"+
			" name='escalaEnv.usuarios["+indice_usuario+"]' value='"+usuario.login+"' />");
		}
		var removerUsuarioTabela = function(indice_usuario){
			var usuario = usuarios[indice_usuario];
			$("#usuariosLogin").find(":input[value='"+usuario.login+"']").remove().end();
			$("#trUsuarios"+usuario.login).remove().end();
			return false;
		}
		var cadastrarEscala = function(){
			$.when(enviarEscala()).done(function(){
				limparValoresModal();
			});
			
			return false;
		};
		var limparValoresModal = function(){
			$("#saida").val("");
			$("#entrada").val("");
			$("#corpoTabelaUsuarios").find('tr').remove().end();
			$("#usuariosLogin").find('input').remove().end();
			$("#idEscalaEnv").val("0");
		}
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
							}else{
								mensagemSucesso();
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
	<script type="text/javascript">
	fotos_perfil = [];
	var guardarFotoPerfil = function(usuario){
		return $.ajax({
			type : "post",
			url : urlFoto + usuario.idPes,
			processData : false
		}).success(function(b64data) {
			fotos_perfil[usuario.idPes] = b64data;
			
		});
	}
	var texto_usuario = function(texto, usuario){
		return "<img style='width:6%;margin-right:10px;' class='img-circle' src='data:image/png;base64," + fotos_perfil[usuario.idPes]+"'></img>"
		+usuario.pessoa.nome+" "+usuario.pessoa.sobrenome+" / ";
	}
	</script>
</t:rodape>	