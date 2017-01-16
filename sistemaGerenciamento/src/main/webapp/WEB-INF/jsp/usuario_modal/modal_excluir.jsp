<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_sem_save  nomeLabel="Excluir Usuário:" nome="ExcluirUsuario" genero="o">
	<div class='row'> 
		<input type="hidden" id="excluirUsuarioId"  />
		<div class="col-xs-12 text-center" style="font-size:20px;">
			<label>Tem certeza que deseja excluir?</label><br/>
			<button class="btn btn-danger" id="excluirUsuarioNao" data-dismiss="modal" >Não</button>
			<button class="btn btn-primary" id="excluirUsuarioSim" data-dismiss="modal" >Sim</button>
		</div>
	</div>
</t:modal_sem_save>