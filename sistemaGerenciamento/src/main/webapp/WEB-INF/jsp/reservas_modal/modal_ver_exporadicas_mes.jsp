<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:modal_sem_save nomeLabel="Ver Expor�dicas de M�s" nome="ExporadicaMes" genero="a">
<div class="row">
	<t:input divCol="col-xs-12" 
	id="mesVerExporadicas" nome="" nomeLabel="Selecione o M�s"
	 valorPadrao="" tipo="month" classes="text-center"></t:input>
</div>
<div id="reservaLerTodasDivExpMes">
		<table id="reservaLerTodasTableExpMes" class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th>
						Dia
					</th>
					<th>
						Hor�rio
					</th>
					<th>
						Sala
					</th>
				</tr>
			</thead>
			<tbody id="reservaLerTodasTBodyExpMes">
			</tbody>
		</table>
	</div>
</t:modal_sem_save>