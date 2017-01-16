<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:modal_geral nomeLabel="AnoSemestre" nome="AnoSemestre" genero="o">
        <form action="${linkTo[AnoSemestreController].postar(null, null, null) }"  method="post" id="formAnoSemestre">
       	
		    <t:input divCol="col-xs-12 col-sm-6" nomeLabel="Ano"
		     nome="ans.ano" tipo="text"
		    valorPadrao="" id="dataAnoAnoSemestreForm" classes="ano"></t:input>
		    <div class="col-xs-12 col-sm-6">
		    <label for="semestre">Semestre:</label>
	            <select id="semestre" name="ans.semestre" class="form-control" required>
	            <option value="0">1� Semestre</option>
	            <option value="1">2� Semestre</option>
	        </select>
		    </div>
		    
	       	<t:input divCol="col-xs-12 col-sm-6" nomeLabel="Data de In�cio:"
		     nome="ini" tipo="date"
		    valorPadrao="" id="dataIniAnoSemestreForm"></t:input>
		    <t:input divCol="col-xs-12 col-sm-6" nomeLabel="Data de T�rmino:"
		     nome="fim" tipo="date"
		    valorPadrao="" id="dataFimAnoSemestreForm"></t:input>
	        
        </form>
</t:modal_geral>