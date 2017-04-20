<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importação do Cabecalho -->
<t:cabecalho>
	<!-- Estilização da TAG select -->
	<link
		href="<c:url value='/assets/css/cupertino/jquery-ui-1.7.2.custom.css' />"
		rel="stylesheet">
	<link href="<c:url value='/assets/css/select2.min.css' />"
		rel="stylesheet">
</t:cabecalho>
<!-- Importação do Menu Lateral e Superior -->
<c:import url="../menuSuperior.jsp"></c:import>

<!-- Seção Principal -->
<div class="row col-xs-12" role="main">
	<h1 class="page-header" style="margin-top: 0px;">
		Editar <small>Menu</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="${linkTo[HomeController].index()}">Página
				Inicial</a></li>
		<li class="active">Editar Menu</a>
		</li>
	</ol>
	<form action="${linkTo[HomeController].postarMenu(null) }" id="formOrdemMenu"
		method="POST">
		<c:forEach var="i" begin="0" end="${fn:length(menu)-1 }">
			<div class="col-xs-12">
				<label>Ordem ${i+1}</label> <select name="itemMenu[${i }].numero"
					class="form-control select_auto_completar menuItem">
					<c:forEach items="${menu }" var="itemMenu">
						<option value="${itemMenu.numero }"
							<c:if test="${itemMenu.ordem == i }">
						selected="selected"
					</c:if>>${itemMenu.nome }</option>
					</c:forEach>
				</select>
			</div>
		</c:forEach>
		<div class="col-xs-12">
			<button class="btn btn-primary" type="submit">Enviar</button>
		</div>
	</form>


</div>
<!-- Scripts Específicos e Importação do Rodapé -->
<t:rodape>
	<!-- Importação do Javascript de  Criação de Eventos da Agenda de Reservas-->
	<script
		src="<c:url value='/assets/js/jquery-ui-1.7.2.custom.min.js' /> "></script>
	<!-- Importação do Javascript de Autocompletar Select-->
	<script src="<c:url value='/assets/js/select2.full.min.js' /> "></script>
	<script type="text/javascript">

var regrasDoJquery = function(){
	$("#formOrdemMenu").validate(function(){});
	$(".menuItem").rules("add",{
		compararSelect: ".menuItem"
	});
}
$(document).on("ready", function(){
	jQuery.validator.addMethod("compararSelect", 
			function(value, element, params) {
				var valor = true;
				$(params).each(function(i,e){
					if (value == $(e).val() && e != element) {
						valor = false;
					}
				});
				return valor;
			},'Todos os selects devem ter valores diferentes');
	regrasDoJquery();
	$(".select_auto_completar").select2();
	
});
</script>
</t:rodape>