<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geralLg nomeLabel="Usuario" nome="Usuario" genero="o">
	<div class='row'>
		<form action="${linkTo[UsuarioController].postar(null, null, null) }"
			method="post" id="form">
	
			<input type='hidden' id="formIdPessoa" name="usuario.idPes"/>
			<!-- Seção de Pessoa -->
			<!-- Nome e Sobrenome -->
			<t:centralizarDiv divCol="10">
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Nome:"
					nome="usuario.pessoa.nome" tipo="text"
					valorPadrao="${usuario.pessoa.nome }" id="nome"></t:input>
	
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Sobrenome:"
					nome="usuario.pessoa.sobrenome" tipo="text"
					valorPadrao="${usuario.pessoa.sobrenome }" id="sobrenome"></t:input>
			</t:centralizarDiv>
			<!-- Email e Data de Nascimento -->
			<t:centralizarDiv divCol="10">
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Email:"
					nome="usuario.pessoa.email" tipo="email"
					valorPadrao="${usuario.pessoa.email }" id="email"></t:input>
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Data de Nascimento:"
					nome="datanascimento" tipo="date"
					valorPadrao="${usuario.pessoa.datanascimento}" id="dataNascimento"></t:input>
			</t:centralizarDiv>
			<!-- Seção de Usuário -->
			<!-- Login de Usuário e Grupo -->
			<t:centralizarDiv divCol="10">
				<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Login:"
					nome="usuario.login" tipo="text" valorPadrao="${usuario.login }"
					id="login"></t:input>
				<!-- Seção de Grupo -->
				<div class="col-xs-12 col-sm-6">
					<div>
						<label>Grupos <a href="" href="#" id="btnAddGrupo"
							class="fa fa-plus-square-o fa-1x botao_confirmar permissaoGrupo"
							style="color: green;" onclick="return aparecer_modal();"></a>
							<a href="" href="#" id="btnEditGrupo"
							class="fa fa-pencil fa-1x botao_confirmar permissaoGrupo"
							style="color: red;" onclick="return editarGrupo();"></a>:
						</label>
					</div>
					<div id="divGrupo">
						<select class="form-control" id="selecionarGrupo"
							name="usuario.idGrupo">
							<option value="">---Selecione um Grupo---</option>
						</select>
					</div>
					<div class="col-xs-3"></div>
				</div>
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
			
<%-- 			<t:centralizarDiv divCol="10"> --%>
<!-- 				<div class="col-sm-3"> -->
<!-- 					<input type="submit" value="Cadastrar" class="btn btn-primary" -->
<!-- 						style="display: block; width: 100%;" id="" /> -->
<!-- 				</div> -->
<%-- 			</t:centralizarDiv> --%>
		</form>
	</div>
</t:modal_geralLg>