<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:modal_geral nomeLabel="Selecione Ano/Semestre:" nome="SlctAnoSemestre" genero="o" semSalvar="">
       <div class="col-xs-10">
		    	<label>Selecione Ano/Semestre:</label>
		    </div>
		    <div id="divAnoSemestre" class="col-xs-9">
		    <select class="form-control" id="selecionarAnoSemestre" name="anoSemestre">
		    <option value="">---Selecione um Ano Semestre---</option>
		    </select>
		</div>
		<div class="col-xs-1">
		   <a href=""
		    href="#" id="btnAddAnoSemestre"
		   class="fa fa-plus-square-o fa-3x botao_confirmar permissaoAnoSemestre"
		   style="color: green;" data-dismiss="modal" data-target="#novoAnoSemestre" data-toggle="modal" ></a>
		   
		   <!-- onclick="return aparecerModalAnoSemestre();" -->
		</div>
		<div class="col-xs-1">
		<a href="" id="btnExcluirAnoSemestre"
		   class="fa fa-trash-o fa-3x botao_confirmar permissaoAnoSemestre"
		   style="color: red;" data-dismiss="modal" onclick="return excluirAnoSemestre()"></a>
		</div>
</t:modal_geral>