<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geral nomeLabel="Cadastro de Escala" nome="Escala" genero="a" fecharSalvar="1">
	<div class='row col-xs-12'>
		<form action="${linkTo[EscalaController].postar() }" method="post"
			id="form">
			<input type="hidden" name="escalaEnv.id" id="idEscalaEnv" value="0" />
			<t:input divCol="col-xs-12 col-md-6 text-center" id="entrada"
				nome="escalaEnv.horaInicio" nomeLabel="Horário de Entrada:" valorPadrao=""
				tipo="text" classes="formHora"></t:input>
			<t:input divCol="col-xs-12 col-md-6 text-center" id="saida"
				nome="escalaEnv.horaFim" nomeLabel="Horário de Saída:" valorPadrao=""
				tipo="text" classes="formHora"></t:input>
			<div class="col-xs-6 col-md-6 text-center">
				<label for="calendarioReserva">Dia:</label> <input type="date"
					max="2050-01-01" min="2001-01-01" name="escalaEnv.dataInicio"
					id="diaInicio" class="form-control text-center" />
			</div>
			<div class="col-xs-6 col-md-6 text-center">
				<label for="calendarioReserva">Data de Término (Caso haja):</label>
				<input type="date" max="2050-01-01" min="2001-01-01"
					name="escalaEnv.dataFim" id="diaFim"
					class="form-control text-center" />
			</div>
			<div class="col-xs-12 col-md-6 text-center">
				<label>Selecione um Usuário:</label> <select
					class="form-control select_auto_completar" style="width: 100%;"
					id="selecionarUsuario" name="tipoReserva.idSala">
					<option value="">---Selecione um Usuário---</option>
				</select>
			</div>
			<div class="col-xs-12 col-md-6 text-center">
				<a href="" class="btn btn-primary" style="margin-top: 22px;"
					onclick="return addUsuarioEsc()">Adicionar</a>
			</div>
			<div class="col-xs-12">
				<div id="usuariosLogin">
					
				</div>
				<div class='panel panel-default' id='panel_paginacao_tabela'>
					<div class='panel-body'>
						<div class='table-responsive espaco_cima'>
							<table id='tabelaUsuarios'
								class='table table-striped table-hover table-bordered'>
								<thead class='thead-inverse' style='background: #1e282c;'>
									<tr>
										<th class='my-table-label'>Nome</th>
										<th class='my-table-label'>Ações</th>
									</tr>
								</thead>
								<tbody id='corpoTabelaUsuarios'>
								<tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</t:modal_geral>