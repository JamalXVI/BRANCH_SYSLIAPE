<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_sem_save nomeLabel="Tem Certeza que Deseja Confirmar a Ação?" nome="ConfirmarAcao" genero="o">
	<div class="container-fluid">
		<input type="hidden" name="tipoConfirmarAcao" id="tipoConfirmarAcao" />
		<div class="col-xs-12 text-center" style="font-size:20px;">
				<label>Escolha sua ação:</label><br/>
				<button class="btn btn-primary" id="confirmarAcaoNao" data-dismiss="modal">Não</button>
				<button class="btn btn-warning" id="confirmarAcaoSim" data-dismiss="modal">Sim</button>
		</div>
	</div>
</t:modal_sem_save>