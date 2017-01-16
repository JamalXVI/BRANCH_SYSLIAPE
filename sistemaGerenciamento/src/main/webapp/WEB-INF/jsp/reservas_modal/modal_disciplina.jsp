<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:modal_geral nomeLabel="Disciplina" nome="Disciplina" genero="a">
        <form action="${linkTo[DisciplinaController].postar(null) }"  method="post" id="formDisciplina">
	    <t:input divCol="col-xs-12 col-sm-6" nomeLabel="Nome da Disciplina:"
	     nome="disciplina.nome" tipo="text"
	    valorPadrao="" id="nomeDisciplinaForm"></t:input>
	    
	    <t:input divCol="col-xs-12 col-sm-6" nomeLabel="Código da Disciplina:"
	     nome="disciplina.codigo" tipo="text"
	    valorPadrao="" id="codigoDisciplinaForm" classes="upperCase"></t:input>
        </form>
</t:modal_geral>