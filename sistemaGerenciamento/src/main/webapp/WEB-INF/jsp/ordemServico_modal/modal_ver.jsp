<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_sem_save nomeLabel="Detalhes da Ordem" nome="DetalhesOrdem" genero="o">
	<div class='row'>
		<div class="col-xs-12 text-center">
			<label for="descricao">Descrição</label>
			<textarea id="descricao" rows="3" class="form-control" readonly="readonly"></textarea>
		</div>
		<div class="col-xs-12 text-center">
			<label for="justificativa">Justificativa de Repasse (Caso Tenha)</label>
			<textarea id="justificativa" rows="3" class="form-control" readonly="readonly"></textarea>
		</div>
		<t:input divCol="col-xs-12" 
		id="pessoaRepasse" nome="" nomeLabel="Repassado Por" valorPadrao="" tipo="text" editavel="1">
		</t:input>
	</div>
</t:modal_sem_save>