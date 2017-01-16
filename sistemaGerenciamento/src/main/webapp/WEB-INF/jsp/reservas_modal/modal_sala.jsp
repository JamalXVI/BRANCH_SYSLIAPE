<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:modal_geral nomeLabel="Sala" nome="Sala" genero="a">
        <form action="${linkTo[SalaController].postar(null) }"  method="post" id="formSala">
       	
	    <t:input divCol="col-xs-12 col-sm-6" nomeLabel="Nome da Sala:"
	     nome="sala.nome" tipo="text"
	    valorPadrao="" id="nomeSalaForm"></t:input>
        </form>
</t:modal_geral>