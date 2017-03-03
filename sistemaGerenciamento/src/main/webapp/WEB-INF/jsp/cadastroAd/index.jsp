<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importação do Cabecalho -->
<t:cabecalho>
	<!-- Estilização da TAG select -->
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>
<link href="<c:url value='/assets/css/select2.min.css' />"
		rel="stylesheet">
<!-- Seção Principal -->
<div class="row" role="main">
	<div class="col-xs-4">
		<div class="input-group">
			<span class="input-group-addon"><i class="fa fa-search"
			aria-hidden="true"></i></span> <input type="text" class="form-control"
			aria-label="Pesquisar Usuário" id="pesquisarUsuarios" placeholder="Pesquisar Usuário">
		</div>
	</div>
	<div class="col-xs-5" style="margin-top:10px;">
	<label>Tipo de Pesquisa:</label>
	<label class="radio-inline" id="tipoDePesquisa">
		  <input type="radio" name="inlineRadioOptions" id="rdAluno" value="0"
		  checked="checked"> Aluno
		</label>
		<label class="radio-inline">
		  <input type="radio" name="inlineRadioOptions" id="rdProfessor" value="1"> Professor
		</label>
		<label class="radio-inline">
		  <input type="radio" name="inlineRadioOptions" id="rdMonitor" value="2"> Monitor
		</label>
	</div>
	<div class="col-xs-3">
		<a href="" onclick="return pesquisarUsr();" class='btn btn-primary' id="pesquisar">Pesquisar</a>
	</div>
	<div class="col-xs-12">
		<div class='table-responsive espaco_cima'>
			<table id='tabelaUsuarios'
				class='table table-striped table-hover table-bordered'>
				<thead class='thead-inverse' style='background: #1e282c;'>
					<tr>
						<th class='my-table-label'>Código</th>
						<th class='my-table-label'>Nome</th>
						<th class='my-table-label'>Ações</th>
					</tr>
				</thead>
				<tbody id='corpotabelaUsuarios'>
				<tbody>
			</table>
		</div>
	</div>
</div>
<c:import url="/WEB-INF/jsp/cadastroAd_modal/cadastro.jsp" />
<c:import url="/WEB-INF/jsp/cadastroAd_modal/senha.jsp" />
<c:import url="/WEB-INF/jsp/cadastroAd_modal/modal_excluir.jsp" />
<c:import url="/WEB-INF/jsp/cadastroAd_modal/modal_sucesso.jsp" />
	<!-- Scripts Específicos e Importação do Rodapé -->
	<t:rodape>
	
<!-- Importação do Javascript de Autocompletar Select-->
<script src="<c:url value='/assets/js/select2.full.min.js' /> "></script>
		<script type="text/javascript">
			$(document).ready(function() {
				 $(".select_auto_completar").select2();
				 validarJquery();
				$.when(listarCursos()).done(function() {
					$("#selecionarCurso").find('option').remove().end();
					$(cursos).each(function(indice_curso, curso){
						$("#selecionarCurso").append("<option value='"+curso.Ou+"'>"+
						curso.Descricao+"</option>");
					})
				});
			});
		</script>
		<script type="text/javascript">
			var cursos;
			var usuarios;
			var listarCursos = function() {
				return $.ajax({
					type:"get",  
				    url: "http://svra-aplicacao/servidor/api/Cursos/", 
				    dataType: "json",  // Isso diz que você espera um JSON do servidor
				    beforeSend: function(xhr, settings){},  
					success : function(data, textStatus, xhr) {
						cursos = data;

					}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
					error : function(xhr, textStatus, errorThrown) {
						tratarErroAjax(xhr, textStatus, errorThrown);
					}
				});
			}
			var fazerPesquisas = function(){
				var enviar = {"codigo" : $("#pesquisarUsuarios").val(),
				"tipo" : retornarTipoUsuario()};
				return $.ajax({
					type : "post",
					url : "http://svra-aplicacao/servidor/api/BuscaUsuario/",
					dataType : "json", // Isso diz que você espera um JSON do servidor
					data: enviar,
					beforeSend : function(xhr, settings) {
					},
					success : function(data, textStatus, xhr) {
						usuarios = data;

					}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
					error : function(xhr, textStatus, errorThrown) {
						tratarErroAjax(xhr, textStatus, errorThrown);
					}
				});
			}
			var retornarTipoUsuario = function(){
				switch($('input[type=radio]:checked').val()) {
			    case "1":
			        return "Professores";
			        break;
			    case "2":
			    	return "Monitores";
			        break;
			    default:
			    	return "";
					break;
			}
			}
		</script>
		<script type="text/javascript">
			var criarTextoAcao = function(funcao, icone, codigo)
			{
				return "<a href='' style='margin-left:10px;' onclick='return "+
				funcao+"(\""+codigo+"\")'><i class='fa-2x "+icone+"'></i></a>";
			}
			var pesquisarUsr = function(){
				$.when(fazerPesquisas()).done(function(){
					$("#corpotabelaUsuarios").find('tr').remove().end();
					if (usuarios.length > 0) {
						$(usuarios).each(function(indice_usuario, usuario){
							var acoes = criarTextoAcao("resetar", 'fa fa-key', usuario.Codigo)+
							criarTextoAcao("deletar", 'fa fa-trash', usuario.Codigo)+
							criarTextoAcao("verificar", 'fa fa-asterisk', usuario.Codigo);
							// +criarTextoAcao("verificar", 'fa fa-check-square', usuario.Codigo)
							$("#corpotabelaUsuarios").append('<tr><td>'+usuario.Codigo+"</td>"+
							"<td style='text-transform: capitalize;'>"+
							usuario.Nome+"</td><td>"+acoes+
							"</td></tr>");
						});
					}else{
						$("#corpotabelaUsuarios").append("<tr><td>"
						+"</td><td class='text-center'><a href=''"+
						" class='btn btn-success' onclick='return cadastroUsuario()'>Cadastrar</a>"+
						"</td><td></td></tr>");
					}
				});
				return false;
			}
		</script>
		<script type="text/javascript">
			var deletar = function(){
				$("#excluirUsuarioCodigo").val($("#pesquisarUsuarios").val());
				$("#novoExcluirUsuario").modal('show');
				return false;
			}
			var verificar = function(){
				return false;
			}
			$("#excluirUsuarioSim").on("click", function(){
				$.when(deletarUsuario()).done(function(){
					limparCampos();
					pesquisarUsr();
				});
			});
			var verificar = function(){
				fazerVerificacao();
				return false;
			}
			var fazerVerificacao = function(){
				colocarTipoUsuario();
				var enviar = {"codigo" : $("#excluirUsuarioCodigo").val(),
						"tipo" : $("#tipoUsr").val()};
				return $.ajax({
					type : "post",
					url : "http://svra-aplicacao/servidor/api/VerificaUsuario/",
					dataType : "json", // Isso diz que você espera um JSON do servidor
					data: enviar,
					beforeSend : function(xhr, settings) {
					},
					success : function(data, textStatus, xhr) {
						$("#novaMensagemSucesso").modal('show');
					}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
					error : function(xhr, textStatus, errorThrown) {
						tratarErroAjax(xhr, textStatus, errorThrown);
					}
				});
			}
			var deletarUsuario = function(){
				colocarTipoUsuario();
				var enviar = {"codigo" : $("#excluirUsuarioCodigo").val(),
						"tipo" : $("#tipoUsr").val()};
				return $.ajax({
					type : "post",
					url : "http://svra-aplicacao/servidor/api/Deletar/",
					dataType : "json", // Isso diz que você espera um JSON do servidor
					data: enviar,
					beforeSend : function(xhr, settings) {
					},
					success : function(data, textStatus, xhr) {
					}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
					error : function(xhr, textStatus, errorThrown) {
						tratarErroAjax(xhr, textStatus, errorThrown);
					}
				});
			}
			var resetar = function(codigo){
				colocarTipoUsuario();
				$("#codRes").val(codigo);
				$("#tipoRes").val($("#tipoUsr").val());
				$("#novaSenha").modal('show');
				return false;
			}
			var cadastroUsuario = function(){
				colocarTipoUsuario();
				$("#codUsr").val($("#pesquisarUsuarios").val());
				$("#novoUsuario").modal('show');
				return false;
			}
			var colocarTipoUsuario = function(){
		        $("#divSelectCurso").hide();
				switch($('input[type=radio]:checked').val()) {
				    case "1":
				        $("#tipoUsr").val("Professores");
				        break;
				    case "2":
				    	$("#tipoUsr").val("Monitores");
				        break;
				    default:
				    	$("#tipoUsr").val("");
				    	$("#divSelectCurso").show();
						break;
				}
			}
			var cadastrarUsuario = function(){
				if($("#form").valid())
				{
					$.when(enviarDados()).done(function(){
						limparCampos();
						pesquisarUsr();
					});
				}else{
					formularErro("Formulário Inválido!");
				}
			}
			var cadastrarSenha = function(){
					if($("#formSenha").valid())
					{
						$.when(enviarSenha()).done(function(){
							limparCampos();
							pesquisarUsr();
						});
					}else{
						formularErro("Formulário Inválido!");
					}
			}
			var enviarSenha = function(){
				var enviar = $("#formSenha").serialize();
						return $.ajax({
							type : "post",
							url : "http://svra-aplicacao/servidor/api/ResetarSenha/",
							dataType : "json", // Isso diz que você espera um JSON do servidor
							data: enviar,
							beforeSend : function(xhr, settings) {
							},
							success : function(data, textStatus, xhr) {
							}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
							error : function(xhr, textStatus, errorThrown) {
								tratarErroAjax(xhr, textStatus, errorThrown);
							}
						});
			}
			var enviarDados = function(){
				var enviar = $("#form").serialize();
						return $.ajax({
							type : "post",
							url : "http://svra-aplicacao/servidor/api/Cadastro/",
							dataType : "json", // Isso diz que você espera um JSON do servidor
							data: enviar,
							beforeSend : function(xhr, settings) {
							},
							success : function(data, textStatus, xhr) {

							}, // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
							error : function(xhr, textStatus, errorThrown) {
								tratarErroAjax(xhr, textStatus, errorThrown);
							}
						});
			}
		</script>
		<script type="text/javascript">
			$('#pesquisarUsuarios').bind("enterKey",function(e){
				pesquisarUsr();
			});
			$('#pesquisarUsuarios').keyup(function(e){
			    if(e.keyCode == 13)
			    {
			        $(this).trigger("enterKey");
			    }
			});
			var limparCampos = function(){
				$('#form')[0].reset();
				$('#formSenha')[0].reset();
				
			}
			var validarJquery = function(){
				$("#form").validate(function(){});
				$("#formSenha").validate(function(){});
				//Validação de Usuário
				$("#nomeUsr").rules("add",{
					required: true,
					  minlength: 2,
					  messages: {
					    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
					    minlength: jQuery.validator.format
					    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
					 }}
				);
				$("#sobUsr").rules("add",{
					required: true,
					  minlength: 2,
					  messages: {
					    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
					    minlength: jQuery.validator.format
					    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
					  }}
					);
				$("#codUsr").rules("add",{
					required: true,
					  minlength: 4,
					  messages: {
					    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
					    minlength: jQuery.validator.format
					    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
					  }}
					);
				$("#senUsr").rules("add",{
					required: true,
					  minlength: 5,
					  messages: {
					    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
					    minlength: jQuery.validator.format
					    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
					  }}
					);
				$("#confUsr").rules("add",{
					required: true,
					  minlength: 5,
					  equalTo: "#senUsr",
					  messages: {
						  required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
						  equalTo: iniMensagemAlerta+"As Senhas devem ser iguais!"+fimMensagemAlerta,
						  minlength: jQuery.validator.format
						  (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
					  
					 }}
				);	
				$("#senRes").rules("add",{
					required: true,
					  minlength: 5,
					  messages: {
					    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
					    minlength: jQuery.validator.format
					    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
					  }}
					);
				$("#confRes").rules("add",{
					required: true,
					  minlength: 5,
					  equalTo: "#senRes",
					  messages: {
						  required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
						  equalTo: iniMensagemAlerta+"As Senhas devem ser iguais!"+fimMensagemAlerta,
						  minlength: jQuery.validator.format
						  (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
					  
					 }}
				);
			};
		</script>
	</t:rodape>