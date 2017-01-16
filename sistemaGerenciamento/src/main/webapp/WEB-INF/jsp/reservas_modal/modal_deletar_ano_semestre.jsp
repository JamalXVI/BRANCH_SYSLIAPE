<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_sem_save nomeLabel="Você tem certeza que deseja deletar este Ano/Semestre?" nome="DeletarAnoSemestre" genero="o">
	<input type="hidden" name="ano" id="deletarAnoSemestreAno" />
	<input type="hidden" name="semestre" id="deletarAnoSemestreSemestre" />
	<div class="col-xs-12 text-center" style="font-size:20px;">
			<label>Tem certeza que deseja excluir?</label><br/>
			<button class="btn btn-danger" id="excluirAnoSemestreNao" data-dismiss="modal" >Não</button>
			<button class="btn btn-primary" id="excluirAnoSemestreSim" data-dismiss="modal" >Sim</button>
		</div>
</t:modal_sem_save>