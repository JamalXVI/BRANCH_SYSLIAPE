<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geral nomeLabel="Cadastro de Usuário para Turno" nome="UsuarioTurno" genero="o">
	<div class='row'>
		<form action="${linkTo[TurnoController].cadastrarUsuarioTurno(null, null) }"
			method="post" id="formTurno">
			<div class="col-xs-12">
			<div class="col-xs-12">
			<label>Selecione um Usuário:</label> <select
				class="form-control select_auto_completar" style="width: 100%;"
				id="selecionarUsuarioTurnoUsuario" name="login">
	
				</select>
			</div>
			<input type="hidden" name="idTurno" id="selecionarUsuarioTurnoTurno" />
		</div>
		</form>
	</div>
</t:modal_geral>