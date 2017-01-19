<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:modal_geralLg nomeLabel="Professor" nome="Professor" genero="o">
	<div class='row'>
		<form action="${linkTo[ProfessorController].postar(null, null, null) }"
			method="post" id="form">
			
		</form>
	</div>
</t:modal_geralLg>