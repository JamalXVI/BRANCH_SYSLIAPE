<!-- Importa��o JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importa��o das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importa��o do Cabecalho -->
<t:cabecalho>
	<!-- Estiliza��o da TAG select -->
</t:cabecalho>
<!-- Importa��o do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Se��o Principal -->
<div class="row" role="main">
	<c:forEach var="i" begin="1" end="6">
		<div class="container-fluid col-xs-12 col-sm-6 col-md-4">

			<div class="panel panel-default">
				<div class="panel-heading">Passo n�${i }</div>
				<div class="panel-body">
					<p>${passos[i] }</p>
					<img src="<c:url value='/assets/img/linux/0${i}.png' />"
						alt="Linux${i}" class="img-responsive img-thumbnail"
						/>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<!-- Scripts Espec�ficos e Importa��o do Rodap� -->
<t:rodape>

</t:rodape>