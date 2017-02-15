<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geral nomeLabel="Resetar Senha" nome="Senha" genero="a">
	<div class="row">
		<div class="col-xs-12">
		<form id="formSenha">
			<input type="hidden" name="codigo" id="codRes" />
			<t:input divCol="col-xs-6"
		 	id="senRes" nome="senha" nomeLabel="Senha:" valorPadrao="" tipo="password"></t:input>
			<t:input divCol="col-xs-6"
		 	id="confRes" nome="" nomeLabel="Confirmar Senha:"
		 	 valorPadrao="" tipo="password"></t:input>
		</form>
		</div>
	</div>
</t:modal_geral>
