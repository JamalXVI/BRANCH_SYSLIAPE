<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="/WEB-INF/jsp/publico/header.jsp" />
<%@taglib tagdir="/WEB-INF/tags" prefix="erro"%>

<!-- Page Heading/Breadcrumbs -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
			Área Restrita <small>Login</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="${linkTo[IndexController].index()}">Página Inicial</a></li>
			<li class="active">Login</li>
		</ol>
	</div>
</div>
<!-- /.row -->

<div class="row">

	<div class="col-lg-12">
		<div class="col-lg-4"></div>
		<div class="jumbotron col-lg-4 ">
			<form action="${linkTo[LoginController].logar(null,null)}"
				method="post">
				<label for="login">LOGIN:</label> <input type="text" name="login"
					id="login" class="form-control" /> <label for="senha">SENHA:</label>
				<input type="password" name="senha" id="senha" class="form-control" />
				<erro:ValidationMessage name="login_invalido"></erro:ValidationMessage>
				<input class="btn btn-primary  btn-block btn-espaco" type="submit"
					value="Entrar" />

			</form>
		</div>
		<div class="col-lg-4"></div>
	</div>

</div>

<c:import url="/WEB-INF/jsp/publico/footer.jsp" />