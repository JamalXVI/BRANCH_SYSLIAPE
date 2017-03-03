<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_sem_save  nomeLabel="Excluir Sala:" nome="ExcluirSala" genero="o">
	<div class='row'> 
		<input type="hidden" id="excluirSala"  />
		<div class="col-xs-12 text-center" style="font-size:20px;">
			<label id="mensagemAviso">Tem certeza que deseja excluir?</label><br/>
			<button class="btn btn-danger" id="excluirSalaNao" data-dismiss="modal" >Não</button>
			<button class="btn btn-primary" id="excluirSalaSim" data-dismiss="modal" >Sim</button>
		</div>
	</div>
</t:modal_sem_save>