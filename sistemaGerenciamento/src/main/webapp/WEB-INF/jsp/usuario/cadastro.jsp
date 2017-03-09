<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- Importação do Cabecalho -->
<t:cabecalho>
<!-- Estilização da TAG select -->
<style>
body {
    overflow-x: hidden !important;
}
.container {
    max-width: 100% !important;
    overflow-x: hidden !important;
}
.imagemFotoUsuario{
	width: 90px;
	height: auto;
}
</style>
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>
<div class="col-lg-12">
		<h1 class="page-header" style="margin-top: 0px;">
			Controle <small>Usuário</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[HomeController].index()}">Página Inicial</a></li>
			<li class="active">Usuário</li>
		</ol>
	</div>
</div>
<div class="row" role="main">
	<t:centralizarDiv divCol="12" divColmd="10">
		<div id="mensagemErro">
			
		</div>
	</t:centralizarDiv>
	<t:centralizarDiv divCol="12" divColmd="10">
<!-- 		<label for="pesquisarUsr">Pesquisar Usuário</label> -->
		<div class="col-xs-6 col-md-4">
			<input class="form-control" placeholder="Filtrar" id="pesquisarUsr" name="pesquisarUsr" />
		</div>
		
		<div class="col-xs-3 col-md-2">
			<a href="" href="#" id="btnInserirUsuario"
				class="fa fa-plus-square fa-2x botao_confirmar cor_verde permissao"
				onclick="return novoUsuarioForm();"></a>
		</div>
	</t:centralizarDiv>
	<div class="container">
	<div id="listaUsuarios" class="col-xs-12"></div>
	
	</div>
	<c:import url="/WEB-INF/jsp/usuario_modal/modal_form.jsp" />

	<c:import url="/WEB-INF/jsp/usuario_modal/modal_grupo.jsp" />
	
	<c:import url="/WEB-INF/jsp/usuario_modal/modal_excluir.jsp" />
	
	<c:import url="/WEB-INF/jsp/usuario_modal/modal_ver.jsp" />

</div>


<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>

<!-- Scripts Modal de Criar Grupo -->
<script type="text/javascript">
	//Aparecer Modal de Grupos
	var aparecer_modal = function()
	{
		$('#novoGrupo').modal('toggle');
		return false;
	}
	var num_permissao = 0;
	//Listar todas Permissões em Formato de CheckBox no Modal de Grupos
	var criarPermissoes = function()
	{
		$(permissoes).each(function(indice, permissao){
			$("#permissoes").append("<label class='checkbox-inline'>"+
					"<input type='checkbox' value='"+permissao.id+"'>"+permissao.nome+"</label>")
		});
	};
	//Envio do Formulário de Cadastro de Grupo
	var cadastrarGrupo = function()
	{
		$(".checkbox-inline").each(function(indice, elemento){
			if ($(this).find(":checkbox").prop('checked')) {
				$("#restoPermissao").append("<input type='hidden' name='permissoes["+num_permissao+"].id"+
						"' value='"+$(this).find(":checkbox").val()+"' />");
				num_permissao++;
				
			}
			
		});
		
		$.ajax({  
		    type:"post",  
		    url: "${linkTo[GrupoController].postar() }", 
		    data: $("#formGrupo").serialize(),
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	popularSelectGrupo(data);
		    	$("#restoPermissao").html("");
		    	mensagemSucesso();
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){ 
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});
		
	};
</script>
<!-- Script de Grupo -->
<script type="text/javascript">
	//Criar Options no Select do Grupo
	var popularSelectGrupo = function(grupo)
	{
		$("#selecionarGrupo")
		    .find('option')
		    .remove()
		    .end();
		$("#selecionarGrupo").append('<option value="">---Selecione um Grupo---</option>');
		$(grupo).each(function(indice, elemento){
			//$("#selecionarGrupo").append("<option value='"+elemento.id+"'>"+elemento.nome+"</option>")
			$("<option />", {value: elemento.id, text: elemento.nome}).appendTo($("#selecionarGrupo"));
		});
	}
</script>

<!-- Requisições Ajax de Permissões e Grupos-->
<script type="text/javascript">
var listarGrupoAjax = function(){
	
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[GrupoController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	popularSelectGrupo(data);
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
		 }
	});
}
// Listar todos os Grupos e Permissões via Ajax
listarGrupoAjax();
var permissoes;
$.ajax({  
    type:"post",  
    url: "${linkTo[PermissaoController].listar() }", 
    dataType: "json",  // Isso diz que você espera um JSON do servidor
    beforeSend: function(xhr, settings){},  
    success: function(data, textStatus, xhr){
    	permissoes = data;
    	criarPermissoes();
    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
    error: function(xhr, textStatus, errorThrown){
    	tratarErroAjax(xhr, textStatus, errorThrown);
    }
});

</script>
<!-- Alteração de Usuário -->
<script type="text/javascript">
var fotos_perfil = {};
//Listar Usuários quando todas as fotos de perfis forem buscadas
var colocarImagensFotosPerfis = function(){
	$(usuarios).each(function(i,u){
		$.when(guardarFotoPerfil(u)).done(function(){
			fazerListaUsuarios();
		});
	});
}
//Associar Foto de Perfil com o Usuário
var guardarFotoPerfil = function(usuario){
	return $.ajax({
		type : "post",
		url : urlFoto + usuario.idPes,
		processData : false
	}).success(function(b64data) {
		fotos_perfil[usuario.idPes] = b64data;
		
	});
}
var usuarios;
//Listar Todos os Usuários e fazer Requisição Ajax da Lista
var listarUsuarios = function(){
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[UsuarioController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	usuarios = data;
	    	colocarImagensFotosPerfis();
	    	//criarPermissoes();
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});
};

//Refazer a Lista de Usuários na Tabela de Pesquisa
var fazerListaUsuarios = function(){
	//Remover Tabela de Lista de Usuários anterior
	$("#listaUsuarios").find(".table-responsive").remove().end();
	//Cabeçalho da Tabela de Usuários
	$("#listaUsuarios").append("<div class='table-responsive espaco_cima'><table id='tabelaListaUsuarios' "+
			"class='table table-striped table-hover table-bordered'><thead class='thead-inverse'"+
			"style='background: #1e282c;'>"+
			"<tr><th class='my-table-label'>Foto</th>"+
			"<th class='my-table-label'>Nome</th><th class='my-table-label'>Email</th>"+
			"<th class='my-table-label'>Telefone</th><th class='my-table-label'>Grupo</th>"+
			"<th class='my-table-label'>Ações</th></tr>"+
// 			'my-table-label'
			"</thead><tbody id='corpoTabelaListaUsuarios'><tbody></table></div>");
	//Chamar todos os usuários válidos
	$(usuarios).each(function(i, usuario){
		//Verificar Filtro da barra de Pesquisa
		if (usuario.pessoa.nome.toLowerCase().indexOf(filtroUsr) >= 0
				|| usuario.pessoa.sobrenome.toLowerCase().indexOf(filtroUsr) >= 0 ||
				usuario.grupo.nome.toLowerCase().indexOf(filtroUsr) >= 0) {
			var telefone = "--- Não Possuí ---";
			if (usuario.pessoa.telefones.length > 0) {
				var tel = usuario.pessoa.telefones[0];
				telefone = "("+tel.ddd+")"+tel.numero+" - Op:"+tel.operadora;	
			};
			var acoesUsuarios = "";
			//Verificar se o Usuário tem a permissão para editar, se tiver acrescentar ações
			if (temPermissao) {
				var acoesUsuarios = criarTextoAcao("editarUsuario", "fa-pencil", i)+
				criarTextoAcao("excluirUsuario", "fa-trash", usuario.login)+
				criarTextoAcao("verUsuario", "fa-search", i);
			}
			//Inserir linha na tabela com as informações do usuário
			$("#corpoTabelaListaUsuarios").append("<tr><td><img id='fotoPerfilUsuario"
					+usuario.idPes+"' class='imagemFotoUsuario'"+
					"></img></td><td class='my-table-label-body'>"
					+usuario.pessoa.nome+" "+usuario.pessoa.sobrenome+"</td><td class='my-table-label-body'>"+
					""+usuario.pessoa.email 
// 					'my-table-label-body'
					+"</td><td class='my-table-label-body'>"+telefone
					+"</td><td class='my-table-label-body'>"+usuario.grupo.nome
					+"</td><td style='display:none;' class='usuarioSis'>"+usuario.login
					+"</td><td class='my-table-label-body'>"+acoesUsuarios
					+"</td></tr>");
			posicionarImagemFotoPerfil(usuario);
			
			
		}
		
	});
}
var criarTextoAcao = function(funcao, icone, id) {
	return "<a href='' style='margin-left:10px;' onclick='return "
			+ funcao + "(\"" + id
			+ "\")'><i class='fa fa-2x "+icone+"'></i></a>";
}
//Posicionar Imagem de Perfil na Tabela na <tr> apropriada.
var posicionarImagemFotoPerfil = function(usuario){
	$("#fotoPerfilUsuario"+usuario.idPes)
	.attr("src", "data:image/png;base64," + fotos_perfil[usuario.idPes]);
}
//Função que Acrescenta 0 caso o número seja menor que 10
var retornarComZero = function(valor){
	return (valor < 10 ? "0"+valor : valor);
}
//Chamar Modal para Edição da Usuário
var editarUsuario = function(indice){
	usuario = usuarios[indice];
	$("#formIdPessoa").val(usuario.idPes);
	$("#nome").val(usuario.pessoa.nome);
	$("#sobrenome").val(usuario.pessoa.sobrenome);
	$("#email").val(usuario.pessoa.email);
	var dtn = usuario.pessoa.datanascimento;
	$("#dataNascimento").val(dtn.year+"-"+retornarComZero(dtn.month)+"-"+retornarComZero(dtn.day));
	$("#login").val(usuario.login);
	$("#login").attr("readonly","readonly");
	$("#selecionarGrupo").val(usuario.grupo.id);
	$("#senha").val("654321");
	$("#confirmarSenha").val("654321");
	$("#senha").hide();
	$("#confirmarSenha").hide();
	$("#labelsenha").hide();
	$("#labelconfirmarSenha").hide();
	removerTelefones();
	$(usuario.pessoa.telefones).each(function(i, telefone){
		adicionar_telefones(telefone.ddd, telefone.numero, telefone.operadora);
	});
	$("#novoUsuario").modal('show');
	return false;
};
//Chamar o Modal Pedindo Confirmamento de Exclusão
var excluirUsuario = function(id){
	$("#excluirUsuarioId").val(id);
	$("#novoExcluirUsuario").modal('show');
	return false;
}
//Função para se confirmado excluir usuário fazer a requisição para exclui-lo
var clicarExluirUsuario = function(){
	var loginUsuario = $("#excluirUsuarioId").val();
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[UsuarioController].excluir()}", 
	    data: {"loginUsuario" : loginUsuario},
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	$("#mensagemErro").fadeIn(0);
	    	//limparFormularioErro();
	    	if (data.mensagem.indexOf("Erro") != -1) {
	    		formularErro(data.mensagem);
			}else{
				mensagemSucesso();
				$(".usuarioSis").each(function(){
		    		   if($(this).html() == $("#excluirUsuarioId").val())
		    		   {
		    		      $(this).parent().remove().end();
		    		   }
		    	});
			}
// 	    	$('table').find()
	    	//criarPermissoes();
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});	
};
//Confirmar Excluir Usuário
$("#excluirUsuarioSim").on("click", function(){
	clicarExluirUsuario();
});
//Chamar Listar Usuário
listarUsuarios();
</script>
<!-- Validação Jquery -->
<script type="text/javascript" >
	$().ready(function(){
		$("#form").validate(function(){});
		$("#formGrupo").validate(function(){});
		//Validação de Grupos
		$("#nomeGrupo").rules("add",{
			required: true,
			  minlength: 5,
			  messages: {
			    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
			    minlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
			  }}
			);
		//Validação de Usuário
		$("#nome").rules("add",{
			required: true,
			  minlength: 2,
			  messages: {
			    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
			    minlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
			  }}
			);

		$("#sobrenome").rules("add",{
			required: true,
			  minlength: 2,
			  messages: {
			    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
			    minlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
			  }}
			);
		$("#dataNascimento").rules("add",{
			required: true,
			  messages: {
			    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta
			  }}
			);
		$("#login").rules("add",{
			required: true,
			  minlength: 5,
			  messages: {
			    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
			    minlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
			  }}
			);
		
		$("#senha").rules("add",{
			required: true,
			  minlength: 5,
			  messages: {
			    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
			    minlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
			  }}
			);
		$("#email").rules("add",{
			required: true,
			email: true,
			messages: {
			    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
			    email:iniMensagemAlerta+"Este não é um E-mail Válido"+fimMensagemAlerta
		}});
		$("#confirmarSenha").rules("add",{
			required: true,
			  minlength: 5,
			  equalTo: "#senha",
			  messages: {
				  required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
				  equalTo: iniMensagemAlerta+"As Senhas devem ser iguais!"+fimMensagemAlerta,
				  minlength: jQuery.validator.format
				    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta)
			  
			  }}
			);	
	});
</script>
<!-- Novo Usuário -->
<script type="text/javascript" >
 
	var novoUsuarioForm = function() {
		$("#formIdPessoa").val("");
		$("#nome").val("");
		$("#sobrenome").val("");
		$("#email").val("");
		$("#dataNascimento").val("2001-01-01");
		$("#login").val("");
		$("#login").removeAttr("readonly");
		$("#selecionarGrupo").val("");
		$("#senha").val("");
		$("#confirmarSenha").val("");
		$("#senha").show();
		$("#confirmarSenha").show();
		$("#labelsenha").show();
		$("#labelconfirmarSenha").show();
		removerTelefones();
		$("#novoUsuario").modal('show');
		
		return false;
	};
</script>
<!-- Ver Usuário -->
<script type="text/javascript">
var verUsuario = function(indice) {
	usuario = usuarios[indice];
	$("#verTelefones").find("div").remove().end();
	$("#nomePessoaVer").val(usuario.pessoa.nome);
	$("#sobrenomePessoaVer").val(usuario.pessoa.sobrenome);
	$("#emailPessoaVer").val(usuario.pessoa.email);
	var dtn = usuario.pessoa.datanascimento;
	$("#dataNascimentoVer").val(retornarComZero(dtn.day)+"/"+retornarComZero(dtn.month)+"/"+dtn.year);
	$("#grupoVer").val(usuario.grupo.nome);
	$("#loginVer").val(usuario.login);
	$(usuario.pessoa.telefones).each(function(i, telefone){
		$("#verTelefones").append("<div><label>Telefone "+i+"</label><input type='text'"+
				" class='form-control' "
				+"readonly='readonly' value='("+
				telefone.ddd+")"+telefone.numero+" - Operadora: "+telefone.operadora+"'/></div>");
	});
	$("#novoVerUsuario").modal('show');
	return false;
};
</script>
<!-- Formulação da Barra de Pesquisa -->
<script type="text/javascript">
	var filtroUsr = "";
	$("#pesquisarUsr").on("change", function(){
		filtrar();
	});
	$("#pesquisarUsr").on("type", function(){
		filtrar();
	});
	$("#pesquisarUsr").on("tap", function(){
		filtrar();
	});
	$("#pesquisarUsr").on("keyup", function(){
		filtrar();
	});
	$("#pesquisarUsr").on("keydown", function(){
		filtrar();
	});
	var filtrar = function(){
		
		filtroUsr = $("#pesquisarUsr").val().toLowerCase();
		fazerListaUsuarios();
	}
</script>
<!-- Cadastro de Usuario -->
<script type="text/javascript">
var cadastrarUsuario = function(){
	if($("#form").valid())
	{
		$.ajax({  
		    type:"post",  
		    url: "${linkTo[UsuarioController].postar() }", 
		    data: $("#form").serialize(),
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	if (data.mensagem.indexOf("Erro") != -1) {
		    		formularErro(data.mensagem);
				}else{
					mensagemSucesso();
					listarUsuarios();
				}
		    	
		    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
		    error: function(xhr, textStatus, errorThrown){ 
		    	tratarErroAjax(xhr, textStatus, errorThrown);
		    }
		});		
	}else{
		formularErro("Formulário Inválido!");
	}
	return false;
};
</script>
<!-- Editar Grupo -->
<script type="text/javascript">
var editarGrupo = function(){
	var nome = $("#selecionarGrupo").find('option:selected').html();
	var idGrp = $("#selecionarGrupo").val();
	$("#nomeGrupo").val(nome);
	$("#formGrupoId").val(idGrp);
	return aparecer_modal();
}
</script>
<!-- Verificar Permissões -->
<script type="text/javascript">
var temPermissao = false;
var temPermissaoGrupo = false;
var verificarPermissao = function(){
	$(permissoesUsuario).each(function(i,permi){
		  if(permi== 2 || permi == 1){
			  temPermissao = true;
		  }
		  if(permi == 3 || permi == 1){
			  temPermissaoGrupo = true;
		  }
	});
	if (!temPermissao) {
		$(".permissao").remove().end();
	}
	if (!temPermissaoGrupo) {
		$(".permissaoGrupo").remove().end();
	}
}
$(verificarPermissao());

</script>
<!-- Importação do Script de Telefone -->
<c:import url="../scripts/AdicionarTelefoneScript.jsp"></c:import>


</t:rodape>