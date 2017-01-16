<!-- Importação JSTLs -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Importação das Tags -->
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<!-- Importação do Cabecalho -->
<t:cabecalho>
	<!-- Estilização da TAG select -->
	<style>
	
/* body{ */
/* 	background-image: url("<c:url value='/assets/img/unaerp_fundo.png' />") ; */
/* 	background-repeat: no-repeat; */
/*     background-size: cover; */
/* } */
.tamanho_fonte{
	font-size:20px;
}
.tamanho_logo{
	width: 90px;
/* 	display: inline; */
}
@media (min-width: 640px) {
.tamanho_logo{
	width: 135px;
/* 	display: inline; */
}
.tamanho_fonte{
	font-size:25px;
}
}
.center_img {        
    display: block;
    margin-left: 0;
    margin-right: auto;
    width: auto;
    height: 40px;
    margin-top: 0.3em;
}
@media (min-width: px) {
.tamanho_logo{
	width: 150px;
/* 	display: inline; */
	
}
.tamanho_fonte{
	font-size:30px;
}

}
</style>
</t:cabecalho><!-- Page Heading/Breadcrumbs -->
<body>
<nav class="navbar navbar-inverse navbar-fixed-top fundo-menu"
			role="navigation">
			<div class="container teste">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<div class="text-center center_img">
					<a class="" href="${linkTo[IndexController].index()}">
					<img src="<c:url value='/assets/img/liape.png' />" class="center_img"
					alt="Logo Liape" />
					</a>
					</div>
				</div>
				</div>
		</nav>
	<div class="container">
<%-- <c:import url="../menuSuperior.jsp"></c:import> --%>
<div class="row">
<!-- /.row -->

<div class="row">
		<div class="main_container">
			<t:centralizarDiv divCol="6" divColmd="4" classes="jumbotron">
				<form action="${linkTo[LoginController].autentica(null, null)}"
					method="post">
<!-- 					<div class="col-xs-12 col-md-12 col-lg-12"> -->
<!-- 						<div class="col-xs-4"> -->
<%-- 							<img src="<c:url value='/assets/img/Unaerp.png' />" --%>
<!-- 								alt="Logo UNAERP" class="centralizar_logo tamanho_logo" -->
<!-- 								 /> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-4 text-center"> -->
<!-- 							<label class="text-center tamanho_fonte" style="">Autenticar:</label> -->
<!-- 						</div> -->
<!-- 						<div class="col-xs-4"> -->
<%-- 							<img src="<c:url value='/assets/img/liape.png' />" --%>
<!-- 								alt="Logo Liape" class="centralizar_logo pull-right tamanho_logo" -->
<!-- 								 /> -->
<!-- 						</div> -->
<!-- 					</div>	 -->
					<!-- 
					<div class="col-xs-12 col-md-4 col-lg-4"></div>
					 -->
					<div class="col-xs-12">
						<!-- 
						<label for="login">Autenticação</label> 
						 -->
						 <label for="login">LOGIN:</label>
						<input type="text" id="login" placeholder="Login"
							name="usuario" class="form-control" /> 
						<!-- 
						<label for="senha">Senha:</label>
						 -->
						 <label for="login">SENHA:</label>
						<input type="password" id="senha" name="senha" placeholder="Senha"
							class="form-control" />
						<div class="">
							<input type="submit" value="Entrar" id="autenticar"
								class="btn btn-primary" />
						</div>
					</div>
				</form>
			</t:centralizarDiv>
		</div>
<div class="col-xs-12">
<t:rodape></t:rodape>
</div>