<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geralLg nomeLabel="Cadastro de Usu�rio" nome="Usuario" genero="o">
	<div class="row">
		<div class="col-xs-12">
		<form id="form">
			<input type='hidden' id="tipoUsr" name="tipo" value=""/>
			<t:input divCol="col-xs-12 col-md-6 col-lg-4"
		 	id="codUsr" nome="codigo" nomeLabel="C�digo:" valorPadrao="" tipo="text"></t:input>
			<t:input divCol="col-xs-12 col-md-6 col-lg-4"
		 	id="nomeUsr" nome="nome" nomeLabel="Nome:" valorPadrao="" tipo="text"></t:input>
			<t:input divCol="col-xs-12 col-md-6 col-lg-4"
		 	id="sobUsr" nome="sobrenome" nomeLabel="Sobrenome:" valorPadrao="" tipo="text">
		 	</t:input>
		 	<div class="col-xs-12 col-md-6 col-lg-6" id="divSelectCurso" >
			<label>Curso:</label> <select
				class="form-control select_auto_completar" style="width: 100%;"
				id="selecionarCurso" name="curso">
			</select>
			</div>
			<t:input divCol="col-xs-12 col-md-3"
		 	id="senUsr" nome="senha" nomeLabel="Senha:" valorPadrao="" tipo="password"></t:input>
			<t:input divCol="col-xs-12 col-md-3"
		 	id="confUsr" nome="" nomeLabel="Confirmar Senha:"
		 	 valorPadrao="" tipo="password"></t:input>
		</form>
		</div>
	</div>
</t:modal_geralLg>
