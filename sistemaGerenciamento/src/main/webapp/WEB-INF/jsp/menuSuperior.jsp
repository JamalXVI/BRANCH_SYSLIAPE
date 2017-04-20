<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<body>

	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top fundo-menu"
			role="navigation">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Menu de Navegação</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<%-- 				<a class="" href="${linkTo[IndexController].index()}"> --%>
				<%-- 					<img src="<c:url value='/assets/img/liape.png' />" --%>
				<!-- 					alt="Logo Liape" style="height:40px;width:auto;margin-left:0.3em;margin-top:0.3em;" /> -->
				<!-- 					</a> -->
				<div class="form-inline navbar-brand" id="logoLiape">
					<div class="form-group">
						<img src="<c:url value='/assets/img/sysliape.png' />"
						 alt="LOGO LIAPE" id="logoLiape"
						  class="logo_liape">
						  <a href="${linkTo[HomeController].index()}" id="linkSysliape">
						  SYSLIAPE</a>
					</div>
				</div>
			</div>
			<!-- Top Menu Items -->
			<ul class="nav navbar-right top-nav sumir_pequeno">
				<!--MENU SUPERIOR DIREITO COM O USUARIO -->
				<li class="dropdown">
					<c:import url="/WEB-INF/jsp/comum/imagem_perfil.jsp" />
				</li>

			</ul>

			<!-- MENU LATERAL PARA NAVEGAÇÃO -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav side-nav">
					<li class="dropdown" id="perfil_pequeno"><c:import
							url="/WEB-INF/jsp/comum/imagem_perfil.jsp" /></li>
					<li><a href="${linkTo[HomeController].index()}"> <i
							class="glyphicon glyphicon-home"> </i> Página Inicial
					</a></li>
					<c:forEach var="i" begin="0" end="${fn:length(menu)-1 }">
						<c:forEach items="${menu }" var="itemMenu">
							<c:if test="${itemMenu.ordem == i }">
								<t:item_menu numero="${itemMenu.numero }">
								</t:item_menu>
							</c:if>
						</c:forEach>
					</c:forEach>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>

		<div id="page-wrapper">