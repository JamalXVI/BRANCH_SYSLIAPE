<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_sem_save  nomeLabel="Excluir Ordem Serviço:" nome="ExcluirOrdem" genero="o">
	<div class='row'> 
		<input type="hidden" id="excluirOrdemId"  />
		<div class="col-xs-12 text-center" style="font-size:20px;">
			<label>Tem certeza que deseja excluir?</label><br/>
			<button class="btn btn-danger" id="excluirOrdemNao" data-dismiss="modal" >Não</button>
			<button class="btn btn-primary" id="excluirOrdemSim" data-dismiss="modal" >Sim</button>
		</div>
	</div>
</t:modal_sem_save>