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
.imagemFotoprofessor{
	width: 90px;
	height: auto;
}
</style>
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>
<div class="col-lg-12">
		<h1 class="page-header" style="margin-top: 0px;">
			Controle <small>Professor</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[HomeController].index()}">Página Inicial</a></li>
			<li class="active">Professor</li>
		</ol>
	</div>
</div>
<div class="row" role="main">
	<t:centralizarDiv divCol="12" divColmd="10">
		<div id="mensagemErro">
			
		</div>
	</t:centralizarDiv>
	<t:centralizarDiv divCol="12" divColmd="10">
<!-- 		<label for="pesquisarProf">Pesquisar Usuário</label> -->
			<div class="col-xs-6 col-md-4">
			<input class="form-control" placeholder="Filtrar" id="pesquisarProf" name="pesquisarProf" />
		</div>
		
		<div class="col-xs-3 col-md-2">
			<a href="" href="#" id="btnInserirProfessor"
				class="fa fa-plus-square fa-2x botao_confirmar cor_verde permissao"
				onclick="return novoProfessorForm();"></a>
		</div>
	</t:centralizarDiv>
	<t:centralizarDiv divCol="12" divColmd="10">
	<div id="listaprofessores" class="col-xs-12"></div>
	
	</t:centralizarDiv>
	<c:import url="/WEB-INF/jsp/professor_modal/modal_form.jsp" />

	<c:import url="/WEB-INF/jsp/professor_modal/modal_grupo.jsp" />
	
	<c:import url="/WEB-INF/jsp/professor_modal/modal_excluir.jsp" />
	
	<c:import url="/WEB-INF/jsp/professor_modal/modal_ver.jsp" />
	

</div>


<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>

<!-- Scripts Modal de Criar Grupo -->
<!-- Alteração de Usuário -->
<script type="text/javascript">
var fotos_perfil = {};
//Quando Carregar todas as imagens de perfil, atualizar a tabela de professores
var colocarImagensFotosPerfis = function(){
	$(professores).each(function(i,u){
		$.when(guardarFotoPerfil(u)).done(function(){
			fazerListaProfessores();
		});
	});
}
//Armazenar foto de perfil de acordo com o Professor Correspondente
var guardarFotoPerfil = function(professor){
	return $.ajax({
		type : "post",
		url : urlFoto + professor.idPes,
		processData : false
	}).success(function(b64data) {
		fotos_perfil[professor.idPes] = b64data;
		
	});
}
var professores;
var paginaAtual = 1;
var paginas = 1;
var conteudoTabelaProf = [];
var numeroElementos = 10;
//Listar Professores via Ajax
var listarProfessores = function(){
	return $.ajax({  
	    type:"post",  
	    url: "${linkTo[ProfessorController].listar() }", 
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	professores = data;
	    	colocarImagensFotosPerfis();
	    	//criarPermissoes();
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});
};

var fazerListaProfessores = function(){
	$("#listaprofessores").find(".panel").remove().end();
	//Adicionar Cabeçalho na Tabela
	$("#listaprofessores").append("<div class='panel panel-default' id='panel_paginacao_tabela'>"+
			"<div class='panel-body'>"+"<div class='table-responsive espaco_cima'>"
			+"<table id='tabelaListaProfessores' "+
			"class='table table-striped table-hover table-bordered'><thead class='thead-inverse'"+
			"style='background: #1e282c;'>"+
			"<tr><th class='my-table-label'>Foto</th>"+
			"<th class='my-table-label'>Nome</th><th class='my-table-label'>Email</th>"+
			"<th class='my-table-label'>Telefone</th>"+
			"<th class='my-table-label'>Ações</th></tr>"+
// 			'my-table-label'
			"</thead><tbody id='corpotabelaListaProfessores'><tbody></table></div>"+
			"<div class='panel-footer' id='rodapeTabelaPaginacao'></div></div></div>");
	//Adicionar Professores na Tabela
	conteudoTabelaProf = [];
	$(professores).each(function(i, professor){
		//Verificar se o Professor está no Filtro
		if (professor.pessoa.nome.toLowerCase().indexOf(filtroUsr) >= 0
				|| professor.pessoa.sobrenome.toLowerCase().indexOf(filtroUsr) >= 0) {
			conteudoTabelaProf.push(professor);
			
			
		}
		
	});
	paginas = Math.ceil(conteudoTabelaProf.length / numeroElementos);
	//"<li><a href=''>1</a></li>"+
	$("#rodapeTabelaPaginacao").append("<ul class='pagination' id='paginacaoTabelaNumero'>"+
			"</ul>");
	criarPaginas();
	mudarPagina(1);
}
var criarPaginas = function(){
	for (var j = 1; j < paginas+1; j++) {
		$("#paginacaoTabelaNumero").append("<li><a href='' onclick='return mudarPagina(\""
				+j+"\")'>"+j+"</a></li>");
	}
}
var mudarPagina = function(k){
	paginaAtual = k;
	$("#corpotabelaListaProfessores").find("tr").remove().end();
	$(conteudoTabelaProf).each(function(j,e){
		var antes = (paginaAtual - 1)*numeroElementos;
		if ( antes <= j) {
			if (j <= paginaAtual*numeroElementos - 1) {
				adicionarProfessorTabela(e,j);	
			}
		}
	});
	return false;
}
var criarTextoAcao = function(funcao, icone, id) {
	return "<a href='' style='margin-left:10px;' onclick='return "
			+ funcao + "(\"" + id
			+ "\")'><i class='fa fa-2x "+icone+"'></i></a>";
}
var adicionarProfessorTabela = function(professor, i){
	var telefone = "--- Não Possuí ---";
	if (professor.pessoa.telefones.length > 0) {
		var tel = professor.pessoa.telefones[0];
		telefone = "("+tel.ddd+")"+tel.numero+" - Op:"+tel.operadora;	
	};
	
	var acoesProfessores = "";
	//Verificar se Tem permissão, caso sim, adicionar ações
	if (temPermissao) {
		acoesProfessores = criarTextoAcao("editarProfessor", "fa fa-pencil", i)+
		criarTextoAcao("excluirProfessor", "fa fa-trash", professor.codigo)
	}
	//Adicionar professor na tabela correta.
	$("#corpotabelaListaProfessores").append("<tr><td><img id='fotoPerfilprofessor"
			+professor.idPes+"' class='imagemFotoprofessor'"+
			"></img></td><td class='my-table-label-body'>"
			+professor.pessoa.nome+" "+professor.pessoa.sobrenome+"</td>"
			+"<td class='my-table-label-body' style='text-transform:none;'>"+professor.pessoa.email 
//				'my-table-label-body'
			+"</td><td class='my-table-label-body'>"+telefone
			+"</td><td style='display:none;' class='professoresis'>"+professor.codigo
			+"</td><td class='my-table-label-body'>"+criarTextoAcao("verProfessor", "fa fa-search", i)
			+acoesProfessores+"</td></tr>");
	posicionarImagemFotoPerfil(professor);
}
//Posicionar foto de Perfil na Tabela de Professores
var posicionarImagemFotoPerfil = function(professor){
	$("#fotoPerfilprofessor"+professor.idPes)
	.attr("src", "data:image/png;base64," + fotos_perfil[professor.idPes]);
}
//Função que retorna com 0 caso o número seja menor que 10
var retornarComZero = function(valor){
	return (valor < 10 ? "0"+valor : valor);
}
//Chamar Modal para Editar Professor
var editarProfessor = function(indice){
	professor = conteudoTabelaProf[indice];
	$("#formIdPessoa").val(professor.idPes);
	$("#nome").val(professor.pessoa.nome);
	$("#sobrenome").val(professor.pessoa.sobrenome);
	$("#email").val(professor.pessoa.email);
	var dtn = professor.pessoa.datanascimento;
	$("#dataNascimento").val(dtn.year+"-"+retornarComZero(dtn.month)+"-"+retornarComZero(dtn.day));
	$("#codigoProfessor").val(professor.codigo);
	removerTelefones();
	$(professor.pessoa.telefones).each(function(i, telefone){
		adicionar_telefones(telefone.ddd, telefone.numero, telefone.operadora);
	});
	
	$("#novoProfessor").modal('show');
	return false;
};
//Modal de Exclusão de Professor
var excluirProfessor = function(id){
	
	$("#excluirProfessorId").val(id);
	$("#novoExcluirProfessor").modal('show');
	return false;
}
//Função de Exclusão de Professores via Ajax
var clicarExluirProfessor = function(){
	var codProfessor = $("#excluirProfessorId").val();
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[ProfessorController].excluir()}", 
	    data: {"codProfessor" : codProfessor},
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
	    	$("#mensagemErro").fadeIn(0);
	    	limparFormularioErro();
	    	if (data.mensagem.indexOf("Erro") !== -1) {
	    		formularErro(data.mensagem);
			}else{
				$(".professoresis").each(function(){
		    		   if($(this).html() == $("#excluirProfessorId").val())
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
//Função de Confirmação de Exclusão de Professor pelo Botão Sim do Modal
$("#excluirProfessorSim").on("click", function(){
	clicarExluirProfessor();
});
$(document).ready(function(){
	carregarProfessor();
})

var carregarProfessor = function(){
	carregando();
	$.when(listarProfessores()).done(function(){
		carregar();
	});
}
</script>
<!-- Validação Jquery -->
<script type="text/javascript" >
	$().ready(function(){
		$("#form").validate(function(){
			
		});
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
		$("#codigoProfessor").rules("add",{
			required: true,
			  minlength: 4,
			  maxlength: 5,
			  messages: {
			    required: iniMensagemAlerta+"Campo Obrigatório"+fimMensagemAlerta,
			    minlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por Favor insira ao menos {0} caracteres."+fimMensagemAlerta),

			    maxlength: jQuery.validator.format
			    (iniMensagemAlerta+"Por Favor insira até {0} caracteres."+fimMensagemAlerta)
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
	});
</script>
<!-- Novo Professor -->
<script type="text/javascript" >
 	//Abrir Modal de Novo Profesor
	var novoProfessorForm = function() {
		$("#formIdPessoa").val("");
		$("#nome").val("");
		$("#sobrenome").val("");
		$("#email").val("email@email.com");
		$("#dataNascimento").val("2001-01-01");
		$("#codigoProfessor").val("");
		$("#codigoProfessor").removeAttr("readonly");
		removerTelefones();
		$("#novoProfessor").modal('show');
		
		return false;
	};
</script>
<!-- Filtrar Pesquisa de Professor - Eventos -->
<script type="text/javascript">
	var filtroUsr = "";
	$("#pesquisarProf").on("change", function(){
		filtrar();
	});
	$("#pesquisarProf").on("type", function(){
		filtrar();
	});
	$("#pesquisarProf").on("tap", function(){
		filtrar();
	});
	$("#pesquisarProf").on("keyup", function(){
		filtrar();
	});
	$("#pesquisarProf").on("keydown", function(){
		filtrar();
	});
	var filtrar = function(){
		
		filtroUsr = $("#pesquisarProf").val().toLowerCase();
		fazerListaProfessores();
	}
</script>
<!-- Cadastro de Professor -->
<script type="text/javascript">
var cadastrarProfessor = function(){
	if($("#form").valid())
	{
		$.ajax({  
		    type:"post",  
		    url: "${linkTo[ProfessorController].postar() }", 
		    data: $("#form").serialize(),
		    dataType: "json",  // Isso diz que você espera um JSON do servidor
		    beforeSend: function(xhr, settings){},  
		    success: function(data, textStatus, xhr){
		    	if (data.mensagem.indexOf("Erro") !== -1) {
		    		formularErro(data.mensagem);
				}else{
					carregarProfessor();
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
<!-- Verificar Permissões do Professor -->
<script type="text/javascript">
var temPermissao = false;
var verificarPermissao = function(){
	$(permissoesUsuario).each(function(i,permi){
		  if(permi== 5 || permi == 1){
			  temPermissao = true;
		  }
	});
	if (!temPermissao) {
		$(".permissao").remove().end();
	}
}
$(verificarPermissao());

</script>
<!-- Ver Professor -->
<script type="text/javascript">
var verProfessor = function(indice) {
	var professor = conteudoTabelaProf[indice];
	$("#verTelefones").find("div").remove().end();
	$("#nomePessoaVer").val(professor.pessoa.nome);
	$("#sobrenomePessoaVer").val(professor.pessoa.sobrenome);
	$("#emailPessoaVer").val(professor.pessoa.email);
	var dtn = professor.pessoa.datanascimento;
	$("#dataNascimentoVer").val(retornarComZero(dtn.day)+"/"+retornarComZero(dtn.month)+"/"+dtn.year);
	$("#codigoVer").val(professor.codigo);
	$(professor.pessoa.telefones).each(function(i, telefone){
		$("#verTelefones").append("<div><label>Telefone "+i+"</label><input type='text'"+
				" class='form-control' "
				+"readonly='readonly' value='("+
				telefone.ddd+")"+telefone.numero+" - Operadora: "+telefone.operadora+"'/></div>");
	});
	$("#novoVerProfessor").modal('show');
	return false;
};
</script>
<!-- Importação do Script de Telefone -->
<c:import url="../scripts/AdicionarTelefoneScript.jsp"></c:import>


</t:rodape>