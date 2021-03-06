<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geral nomeLabel="Curso" nome="Curso" genero="o">
	<form action="${linkTo[CursoController].postar(null) }" method="post"
		id="formCurso">
		<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Nome do Curso:"
			nome="curso.nome" tipo="text" valorPadrao="" id="nomeCursoForm" maxLenght="144"></t:input>

		<t:input divCol="col-xs-12 col-sm-6" nomeLabel="C�digo do Curso:"
			nome="curso.codigo" tipo="text" valorPadrao="" id="codigoCursoForm"
			classes="upperCase"></t:input>
	</form>
	<div class="col-xs-12">
		<form>
			<label>Selecione a Pesquisa:</label>
			<label class="radio-inline">
			   <input type="radio" name="opcoesCursosPesquisa" id="cur-pesq-nome" value="1" >
			   Nome
			 </label>
			 <label class="radio-inline">
			   <input type="radio" name="opcoesCursosPesquisa" id="cur-pesq-codigo" value="0" checked>
			   Codigo
			 </label>
		</form>
	</div>
	<div class="col-xs-12">
		<div class='table-responsive espaco_cima'>
			<table id='tabelaCursos'
				class='table table-striped table-hover table-bordered'>
				<thead class='thead-inverse' style='background: #1e282c;'>
					<tr>
						<th class='my-table-label'>C�digo</th>
						<th class='my-table-label'>Nome</th>
					</tr>
				</thead>
				<tbody id='corpotabelaCursos'>
				<tbody>
			</table>
		</div>
	</div>
</t:modal_geral>