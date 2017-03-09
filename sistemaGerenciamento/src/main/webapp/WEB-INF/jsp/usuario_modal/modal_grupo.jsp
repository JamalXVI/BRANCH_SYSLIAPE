<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<div class="modal fade" id="novoGrupo" tabindex="-1" role="dialog" aria-labelledby="novoGrupoLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="novoGrupoLabel">Novo Grupo:</h4>
      </div>
      <div class="modal-body">
     	 <div class='row'>
	        <form action="${linkTo[UsuarioController].postar(null) }"  method="post" id="formGrupo">
		        <input type="hidden" id="formGrupoId" name="grupo.id" value="0" />
		        <t:input valorPadrao="" divCol="col-xs-12" nomeLabel="Nome do Grupo"
		         nome="grupo.nome" tipo="text" id="nomeGrupo"></t:input>
		        <t:input valorPadrao="0" divCol="col-xs-12" nomeLabel="Hierarquia do Grupo"
		         nome="grupo.hierarquia" tipo="number" id="hierarquiaGrupo"
		          placeholder="Valor"></t:input>
		        <div class="col-xs-12">
		        	<label>Permissões</label>
		        </div>
		        <!-- Checkbox das Permissões -->
			    <div class="col-xs-12" id="permissoes">
			    </div>
			    <!-- Permissões para os Forms -->
			    <div class="col-xs-12" id="restoPermissao">
			    </div>
	        </form>
	       </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" onclick="cadastrarGrupo();">Salvar</button>
      </div>
    </div>
  </div>
</div>