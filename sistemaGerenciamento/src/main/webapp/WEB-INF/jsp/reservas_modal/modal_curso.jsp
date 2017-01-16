<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:modal_geral nomeLabel="Curso" nome="Curso" genero="o">
        <form action="${linkTo[CursoController].postar(null) }"  method="post" id="formCurso">
	    <t:input divCol="col-xs-12 col-sm-6" nomeLabel="Nome do Curso:"
	     nome="curso.nome" tipo="text"
	    valorPadrao="" id="nomeCursoForm"></t:input>
	    
	    <t:input divCol="col-xs-12 col-sm-6" nomeLabel="C�digo do Curso:"
	     nome="curso.codigo" tipo="text"
	    valorPadrao="" id="codigoCursoForm" classes="upperCase"></t:input>
        </form>
</t:modal_geral>