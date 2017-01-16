<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geral nomeLabel="Cadastro de Turno" nome="Turno" genero="o">
	<div class='row'>
		<form action="${linkTo[TurnoController].postar(null, null, null) }"
			method="post" id="form">
			<div class="col-xs-12">
			<div class="col-xs-12 col-md-6">
			<label>Selecione um Per�odo:</label> <select
				class="form-control select_auto_completar" style="width: 100%;"
				id="selecionarPeriodo" name="turno.periodo">
				<option value="0">---Selecione um Per�odo---</option>
				<option value="1">Manh�</option>
				<option value="2">Tarde</option>
				<option value="3">Noite</option>
			</select>
		</div>
		<t:input divCol="col-xs-12 col-md-6 text-center" id="horaInicio" nome="horaInicio" 
		nomeLabel="Hor�rio de Entrada:" valorPadrao="" tipo="text" classes="formHora"></t:input>
		<t:input divCol="col-xs-12 col-md-6 text-center" id="horaFim" nome="horaFim" 
		nomeLabel="Hor�rio de Sa�da:" valorPadrao="" tipo="text" classes="formHora"></t:input>
			</div>
		</form>
	</div>
</t:modal_geral>