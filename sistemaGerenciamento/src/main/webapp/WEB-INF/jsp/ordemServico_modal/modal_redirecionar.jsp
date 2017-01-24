<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geralLg nomeLabel="Repassar OrdemServiço" nome="RepasseOrdem" genero="o">
	<div class='row'>
		<form
			action="${linkTo[OrdemServicoController].redirecionar(null) }"
			method="post" id="formRepasse">
			<div class="col-xs-6">
				<input type='hidden' name='redirecionada.idOrs' id='idOrdemRedirecionada'/>
				<input type='hidden' name='redirecionada.idSubOrsAntiga' id='idSubOrsAntiga' />
				<label for="calendarioReserva">Data para ser Executada:</label> <input
					type="date" max="2050-01-01" min="2001-01-01"
					name="redirecionada.dataExecutada" id="dataExecutada"
					class="form-control text-center" />
			</div>
			<t:input divCol="col-xs-6 text-center" id="dataExecutadaHora"
				nome="redirecionada.dataExecutadaHora"
				nomeLabel="Hora para Ser Executada:" valorPadrao="" tipo="text"
				classes="formHora"></t:input>
			<div class="col-xs-6">
				<label for="selecionarAlvo">Ordem Destinada para:</label> <select
					class="form-control" id="selecionarAlvo"
					name="redirecionada.destinada">
					<option value="1">Turno</option>
					<option value="0">Usuário</option>
				</select>
			</div>
			<div class="col-xs-6 col-md-6 col-lg-3" id="tipoAlvo"></div>
			<div class="col-xs-12">
				<label>Justificativa de Repasse:</label>
				<textarea rows="5" class="form-control" name="redirecionada.justificativa" id="justificativaRedirecionada"></textarea>
			</div>
		</form>
	</div>
</t:modal_geralLg>