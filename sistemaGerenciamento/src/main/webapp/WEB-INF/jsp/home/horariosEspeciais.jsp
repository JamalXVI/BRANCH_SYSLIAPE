<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<!-- Importação do Cabecalho -->
<t:cabecalho>
<!-- Estilização da TAG select -->
<style>
.cor_exporadico{
	background-color: #E1BEE7 !important;
}
</style>
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Seção Principal -->
<div class="row" role="main">
<div class="col-xs-12">
		<div class='table-responsive espaco_cima'>
			<table id='tabelaUsuarios'
				class='table table-striped table-hover table-bordered'>
				<thead class='thead-inverse' style='background: #1e282c;'>
					<tr>
						<th class='my-table-label'>Horarios</th>
						<th class='my-table-label'>Segunda</th>
						<th class='my-table-label'>Terça</th>
						<th class='my-table-label'>Quarta</th>
						<th class='my-table-label'>Quinta</th>
						<th class='my-table-label'>Sexta</th>
					</tr>
				</thead>
				<tbody id='corpotabelaUsuarios'>
					<tr>
						<td>Adalberto</td>
						<td>14:30-18:30</td>
						<td>14:30-18:30</td>
						<td>14:30-18:30</td>
						<td>14:30-18:30</td>
						<td>07:00-10:50</td>
					</tr>
					<tr>
						<td>Caio</td>
						<td>07:00-12:30 / 18:15-22:30</td>
						<td>18:15-22:30</td>
						<td>18:15-22:30</td>
						<td>18:15-22:30</td>
						<td>18:15-22:30</td>
					</tr>
					<tr>
						<td>Gabriel</td>
						<td>12:30-18:00</td>
						<td>07:00-12:30</td>
						<td>12:30-18:00</td>
						<td>07:00-12:30</td>
						<td>12:30-18:00</td>
					</tr>
					<tr>
						<td>Juliana</td>
						<td>08:30-12:30</td>
						<td>07:00-08:50 / 10:30-12:30</td>
						<td>07:00-12:30</td>
						<td>07:00-08:00 / 11:30-12:30</td>
						<td>07:00-12:30</td>
					</tr>
					<tr>
						<td>Leandro</td>
						<td>18:00-22:00</td>
						<td>18:00-22:00</td>
						<td>18:00-22:00</td>
						<td>18:00-22:00</td>
						<td>18:00-22:00</td>
					</tr>
					<tr>
						<td>Luís</td>
						<td>07:00-12:30</td>
						<td>12:30-18:00</td>
						<td>12:30-18:00</td>
						<td>12:30-18:00</td>
						<td>12:30-18:00</td>
					</tr>
					<tr>
						<td>Nicolas</td>
						<td>07:00-12:30</td>
						<td>12:30-21:00</td>
						<td>07:00-12:30</td>
						<td>08:50-12:30</td>
						<td>17:15-22:30</td>
					</tr>
					<tr>
						<td>Reis</td>
						<td>19:00-22:30</td>
						<td>07:00-09:00 / 17:15-22:30</td>
						<td>07:00-09:00 / 19:00-22:30</td>
						<td>07:00-09:00 / 17:15-22:30</td>
						<td>07:00-10:50</td>
					</tr>
					<tr>
						<td>Vanessa</td>
						<td>13:00-18:00</td>
						<td>---</td>
						<td>13:00-18:00</td>
						<td>08:50-12:30</td>
						<td>---</td>
					</tr>
					<tr>
						<td>Vitor</td>
						<td>13:00-17:00</td>
						<td>13:00-17:00</td>
						<td>13:00-17:00</td>
						<td>13:00-17:00</td>
						<td>13:00-17:00</td>
					</tr>
				<tbody>
			</table>
		</div>
	</div>
</div>
<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>

</t:rodape>