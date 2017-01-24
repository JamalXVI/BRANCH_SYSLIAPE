<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_sem_save  nomeLabel="Finalizar Ordem Serviço:" nome="FinalizarOrdem" genero="o">
	<div class='row'> 
		<input type="hidden" id="finalizarSubOrdemId"  />
		<div class="col-xs-12 text-center" style="font-size:20px;">
			<label>Tem certeza que deseja marcar esta ordem como finalizada?</label><br/>
			<button class="btn btn-danger" id="finalizarOrdemNao" data-dismiss="modal" >Não</button>
			<button class="btn btn-primary" id="finalizarOrdemSim" data-dismiss="modal" >Sim</button>
		</div>
	</div>
</t:modal_sem_save>