<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geral nomeLabel="Disciplina" nome="Disciplina" genero="a">
	<form action="${linkTo[DisciplinaController].postar(null) }"
		method="post" id="formDisciplina">
		<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Nome da Disciplina:"
			nome="disciplina.nome" tipo="text" valorPadrao=""
			id="nomeDisciplinaForm"></t:input>

		<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Código da Disciplina:"
			nome="disciplina.codigo" tipo="text" valorPadrao=""
			id="codigoDisciplinaForm" classes="upperCase"></t:input>
	</form>
	<div class="col-xs-12">
		<div class="input-group">
			<span class="input-group-addon"><i class="fa fa-search"
				aria-hidden="true"></i></span> <input type="text" class="form-control"
				aria-label="Pesquisar Disciplina" id="pesquisarDisciplina">
		</div>
	</div>
	<div class="col-xs-12">
		<div class='table-responsive espaco_cima'>
			<table id='tabelaDisciplinas'
				class='table table-striped table-hover table-bordered'>
				<thead class='thead-inverse' style='background: #1e282c;'>
					<tr>
						<th class='my-table-label'>Código</th>
						<th class='my-table-label'>Nome</th>
					</tr>
				</thead>
				<tbody id='corpotabelaDisciplinas'>
				<tbody>
			</table>
		</div>
	</div>
</t:modal_geral>