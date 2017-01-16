<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- Importação do Cabecalho -->
<t:cabecalho>
<!-- Estilização da TAG select -->
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Seção Principal -->
<div class="row" role="main">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
	<div class='row'>
		<form action="${linkTo[UsuarioController].alterarPerfil(null, null, null) }"
			method="post" id="form">
	
			<!-- Seção de Pessoa -->
			<!-- Nome e Sobrenome -->
			<t:centralizarDiv divCol="10">
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Nome:"
					nome="usuario.pessoa.nome" tipo="text"
					valorPadrao="${usuarioLogado.usuario.pessoa.nome }" id="nome"></t:input>
	
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Sobrenome:"
					nome="usuario.pessoa.sobrenome" tipo="text"
					valorPadrao="${usuarioLogado.usuario.pessoa.sobrenome }" id="sobrenome"></t:input>
			</t:centralizarDiv>
			<!-- Email e Data de Nascimento -->
			<t:centralizarDiv divCol="10">
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Email:"
					nome="usuario.pessoa.email" tipo="email"
					valorPadrao="${usuarioLogado.usuario.pessoa.email }" id="email"></t:input>
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Data de Nascimento:"
					nome="datanascimento" tipo="date"
					valorPadrao="${usuarioLogado.usuario.pessoa.datanascimento}" id="dataNascimento"></t:input>
			</t:centralizarDiv>
			<!-- Seção de Usuário -->
			<!-- Login de Usuário e Grupo -->
			<t:centralizarDiv divCol="10">
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Login:"
					nome="usuario.login" tipo="text" valorPadrao="${usuarioLogado.usuario.login }"
					id="login" editavel="1"></t:input>
			</t:centralizarDiv>
			<!-- Senha e Confirmar Senha -->
			<t:centralizarDiv divCol="10">
				<!-- <input name="teste" style="display: block;"/> -->
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Senha:"
					nome="usuario.senha" tipo="password" valorPadrao="${usuario.senha }"
					id="senha"></t:input>
				<t:input valorPadrao="${usuario.senha }" divCol="col-xs-12 col-sm-6"
					nomeLabel="Confirmar Senha" nome="" tipo="password"
					id="confirmarSenha"></t:input>
			</t:centralizarDiv>
			<!--     Adicionar Telefones -->
			<t:centralizarDiv divCol="10">
				<div class="col-sm-6">
					<label>Telefones: </label> <a href="" href="#" id="btn_telefone"
						class="fa fa-plus-square-o fa-1x botao_confirmar"
						style="color: green;" onclick="return adicionar_telefone();"></a>
				</div>
	
			</t:centralizarDiv>
	
			<!-- 
	 <div class="col-xs-12 col-sm-4">
	 <a href=""
	       href="#" id="btn_telefone"
	       class="fa fa-phone-square fa-3x botao_confirmar" 
	       style="color: green;" onclick="return adicionar_telefone();" >
	</a>
	 </div>
	  -->
			<div class="col-sm-12" id="telefones"></div>
			<div class="col-xs-12 text-center">
			<button class="btn btn-success">Enviar</button>
			</div>
		</form>
	</div>
</div>

<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>

<!-- Importação do Script de Telefone -->
<c:import url="../scripts/AdicionarTelefoneScript.jsp"></c:import>

<!-- Validação Jquery -->
<script type="text/javascript" >
	$().ready(function(){
		$("#form").validate(function(){});
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
<script type="text/javascript">
var telefones;
var criarTelefones = function(){
	$.ajax({  
	    type:"post",  
	    url: "${linkTo[UsuarioController].listarTelefones() }", 
	    data: $("#form").serialize(),
	    dataType: "json",  // Isso diz que você espera um JSON do servidor
	    beforeSend: function(xhr, settings){},  
	    success: function(data, textStatus, xhr){
				telefones = data;
				chamarTelefones();
	    	
	    },  // a variavel data vai ser o seu retorno do servidor, que no caso é um JSON
	    error: function(xhr, textStatus, errorThrown){ 
	    	tratarErroAjax(xhr, textStatus, errorThrown);
	    }
	});
}
criarTelefones();
var chamarTelefones = function(){
	$(telefones).each(function(i, telefone){
		adicionar_telefones(telefone.ddd, telefone.numero, telefone.operadora);
	});
};

</script>
</t:rodape>