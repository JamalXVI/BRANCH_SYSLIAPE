<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geralLg nomeLabel="Cadastro de Usuário" nome="Usuario" genero="o">
	<div class="row">
		<div class="col-xs-12">
		<form id="form">
			<input type='hidden' id="tipoUsr" name="usuario.tipoUsuario" value=""/>
			<t:input divCol="col-xs-12 col-md-6 col-lg-4"
		 	id="codUsr" nome="usuario.Codigo" nomeLabel="Código:" valorPadrao="" tipo="text"></t:input>
			<t:input divCol="col-xs-12 col-md-6 col-lg-4"
		 	id="nomeUsr" nome="usuario.Nome" nomeLabel="Nome:" valorPadrao="" tipo="text"></t:input>
			<t:input divCol="col-xs-12 col-md-6 col-lg-4"
		 	id="sobUsr" nome="usuario.SobreNome" nomeLabel="Sobrenome:" valorPadrao="" tipo="text">
		 	</t:input>
		 	<div class="col-xs-12 col-md-6 col-lg-6" id="divSelectCurso" >
			<label>Curso:</label> <select
				class="form-control select_auto_completar" style="width: 100%;"
				id="selecionarCurso" name="usuario.Curso">
			</select>
			</div>
			<t:input divCol="col-xs-12 col-md-3"
		 	id="senUsr" nome="usuario.Senha" nomeLabel="Senha:" valorPadrao="" tipo="password"></t:input>
			<t:input divCol="col-xs-12 col-md-3"
		 	id="confUsr" nome="" nomeLabel="Confirmar Senha:"
		 	 valorPadrao="" tipo="password"></t:input>
		</form>
		</div>
	</div>
</t:modal_geralLg>
