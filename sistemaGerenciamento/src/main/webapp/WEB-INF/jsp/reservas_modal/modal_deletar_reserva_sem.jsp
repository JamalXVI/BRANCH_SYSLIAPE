<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_sem_save nomeLabel="Voc� tem certeza que deseja deletar a Reserva?" nome="DeletarReservaSem" genero="a">
	<input type="hidden" name="deletarIdReservaSem" id="deletarIdReservaSem" />
	<div class="col-xs-12 text-center" style="font-size:20px;">
			<label>Tem certeza que deseja excluir?</label><br/>
			<button class="btn btn-danger" id="excluirReservaSemNao" data-dismiss="modal" >N�o</button>
			<button class="btn btn-primary" id="excluirReservaSemSim" data-dismiss="modal" >Sim</button>
		</div>
</t:modal_sem_save>