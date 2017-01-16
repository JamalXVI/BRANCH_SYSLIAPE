<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geral nomeLabel="Informação de Reserva" nome="InfoReserva" genero="a" semSalvar="1">
	<t:input divCol="col-xs-12 col-sm-6" id="reservaLerDisciplina"
		nome="reservaLerDisciplina" nomeLabel="Disciplina:" valorPadrao=""
		tipo="text" editavel="1"></t:input>
	<t:input divCol="col-xs-12 col-sm-6" id="reservaLerCurso"
		nome="reservaLerCurso" nomeLabel="Curso:" valorPadrao="" tipo="text"
		editavel="1"></t:input>
	<t:input divCol="col-xs-12 col-sm-6" id="reservaLerProfessor"
		nome="reservaLerProfessor" nomeLabel="Professor:" valorPadrao=""
		tipo="text" editavel="1"></t:input>
	<t:input valorPadrao="" divCol="col-xs-12 col-md-6" nomeLabel="Turma:"
		nome="reservaLerTurma" tipo="text" id="reservaLerTurma"
		classes="upperCase" editavel="1"></t:input>
	<!-- Tabela Contendo Todas as Reservas -->
	<div id="espaco" style="margin-top: 10em;">
	</div>
	<div id="reservaLerTodasDiv">
		<table id="reservaLerTodasTable" class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>
						Tipo Reserva
					</th>
					<th>
						Dia
					</th>
					<th>
						Horário
					</th>
					<th>
						Sala
					</th>
				</tr>
			</thead>
			<tbody id="reservaLerTodasTBody">
			</tbody>
		</table>
	</div>
</t:modal_geral>