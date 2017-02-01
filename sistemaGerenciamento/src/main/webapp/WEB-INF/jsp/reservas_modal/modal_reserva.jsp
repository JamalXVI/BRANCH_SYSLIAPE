<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geralLg nomeLabel="Reserva" nome="Reserva" genero="a">
	<form action="${linkTo[ReservaController].postar(null) }" method="post"
		id="formReserva">
		<input type="hidden" id="anoReserva" name="reserva.anoAns" value="" />
		<input type="hidden" id="semestreReserva" name="reserva.semestreAns"
			value="" />
		<div class="form-group col-xs-12">
			<div class="col-xs-12 col-md-6">
				<label>Selecione o Tipo de Reserva:</label> <select
					class="form-control select_auto_completar" style="width: 100%;"
					id="selecionarTipoReserva" name="">
					<option value="semestral" selected="selected">Semestral</option>
					<option value="exporadica">Exporádica</option>
				</select>
			</div>
			<!-- Div que demarca a inserção a especificação da reserva -->
			<div id="divTipoReserva"></div>
		</div>
		
		<!-- Script de AutoCompletar Select -->
		<div class="col-xs-12 col-md-6">
			<label>Selecione uma Sala:</label> <select
				class="form-control select_auto_completar" style="width: 100%;"
				id="selecionarSala" name="tipoReserva.idSala">
				<option value="">---Selecione uma Sala---</option>
			</select>
		</div>
		<div class="col-xs-12 col-md-6">
			<label>Selecione uma Curso:</label> <select
				class="form-control select_auto_completar" style="width: 100%;"
				id="selecionarCursoReserva" name="reserva.codigoCur">
				<option value="">---Selecione uma Curso---</option>
			</select>
		</div>
		<div class="col-xs-12 col-md-6">
			<label>Selecione uma Disciplina:</label> <select
				class="form-control select_auto_completar" style="width: 100%;"
				id="selecionarDisciplina" name="reserva.codigoDis">
				<option value="">---Selecione uma Disciplina---</option>
			</select>
		</div>
		
		<t:input valorPadrao="" divCol="col-xs-12 col-md-6" nomeLabel="Turma:"
			nome="reserva.turma" tipo="text" id="reservaTurma" classes="upperCase"></t:input>
		<div class="col-xs-12 col-md-6">
			<label>Selecione um Professor:</label> <select
				class="form-control select_auto_completar" style="width: 100%;"
				id="selecionarProfessor" name="reserva.codigoPro">
				<option value="">---Selecione um Professor---</option>
			</select>
		</div>
		<div class="col-xs-12 col-md-6" id="idObservacaoReserva">
		</div>
	</form>
</t:modal_geralLg>