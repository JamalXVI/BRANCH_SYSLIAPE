<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<a href="#" class="dropdown-toggle"
	style="padding-top: 0px; padding-bottom: 0px;"
					data-toggle="dropdown">
	<form action="${linkTo[FotoPerfilController].postar(null) }"
		method="post" id="fotoPerfilForm" enctype="multipart/form-data"
		class="form_imagem_perfil">
		<div class="image-upload">
			<label for="file"> <img
				src="<c:url value='/assets/img/Desconhecido.png' />"
				alt="ImagemPerfil"
				class="imagem-perfil img-circle img-thumbnail profile_img perfil_imagem">

			</label> <input id="file" name="foto" type="file" />
		</div>
	</form> <label class="my-profile-label">${usuarioLogado.usuario.pessoa.nome}
</label> <b class="caret"></b>
</a>
<ul class="dropdown-menu">
	<li><a href="${linkTo[UsuarioController].perfil()}"><i class="fa fa-fw fa-user"></i> Perfil</a></li>
<!-- 	<li class="divider"></li> -->
	<li><a href="${linkTo[LoginController].deslogar()}"><i class="fa fa-fw fa-power-off"></i> Log Out</a></li>
</ul>