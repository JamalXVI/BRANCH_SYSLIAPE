<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<div class="modal fade" id="opcoesGerais" role="dialog" aria-labelledby="opcoesGeraisLabel" data-focus-on="input:first">
  <div class="modal-dialog modal_espacado" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="opcoesGeraisLabel">Opções</h4>
      </div>
      <div class="modal-body">
      	<div class='row'>
				<div class="col-xs-12">
					<div class="col-xs-6 permissao">
						<a data-dismiss="modal" href="" href="#" id="btnslctReserva"
							class="btn btn-cinza btn-block"
							onclick="return aparecerModalReserva();">Cadastro Reserva</a>
					</div>
					<div class="col-xs-6 permissao">
						<a data-dismiss="modal" href="" href="#" id="btnslctReserva"
							class="btn btn-cinza btn-block"
							onclick="return verExporadicasMesModal();">Ver Exporádicas do Mês</a>
					</div>
					<div class="col-xs-6">
						<a data-dismiss="modal" href="" href="#" id="btnslctAno"
							class="btn btn-cinza btn-block"
							onclick="return aparecerModalSlctAnoSemestre();">
							Cadastro Ano/Semestre</a>
					</div>
					<div class="col-xs-6 permissaoSala">
						<a data-dismiss="modal" href="" href="#" id="btnAddSala"
							class="btn btn-cinza btn-block"
							onclick="return aparecerModalSala();">Cadastro de Sala</a>
					</div>
					<div class="col-xs-6 permissaoDisciplina">
						<a data-dismiss="modal" href="" href="#" id="btnAddDisciplina"
							class="btn btn-cinza btn-block"
							onclick="return aparecerModalDisciplina();">Cadastro de Disciplina</a>
					</div>
					<div class="col-xs-6 permissaoCurso">
						<a data-dismiss="modal" href="" href="#" id="btnAddCurso"
							class="btn btn-cinza btn-block"
							onclick="return aparecerModalCurso();">Cadastro Curso</a>
					</div>
					
					
				</div>
      		
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>