<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_sem_save nomeLabel="Você tem certeza que deseja deletar a Reserva?" nome="DeletarReservaSem" genero="a">
	<div class="container-fluid">
	<input type="hidden" name="deletarIdReservaSem" id="deletarIdReservaSem" />
	<div class="col-xs-12 text-center" style="font-size:20px;">
			<label>Escolha sua ação:</label><br/>
			<button class="btn btn-primary" id="excluirReservaSemNao" data-dismiss="modal" >Cancelar</button>
			<button class="btn btn-danger" id="excluirReservaSemSim" data-dismiss="modal" >Remover</button>
			<button class="btn btn-warning" id="excluirReservaSemAlt" data-dismiss="modal" >Alterar</button>
		</div>
	</div>
</t:modal_sem_save>