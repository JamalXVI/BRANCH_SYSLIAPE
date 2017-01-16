<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body>
		<!-- MENU DE NAVEGAÇÃO -->
		<nav class="navbar navbar-inverse navbar-fixed-top fundo-menu"
			role="navigation">
			<div class="container teste">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="" href="${linkTo[IndexController].index()}">
					<img src="<c:url value='/assets/img/liape.png' />"
					alt="Logo Liape" style="height:40px;width:auto;margin-left:0.3em;margin-top:0.3em;" />
					</a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="services.html">Publicações</a></li>
						<li><a href="${linkTo[UsuarioController].cadastro()}">Usuários</a></li>
						<li><a href="${linkTo[ProfessorController].cadastro()}">Professor</a></li>
						<li><a href="${linkTo[ReservasController].index()}">Reservas</a></li>
						<li>
							<div class="profile_pic">
                            
                             <form action="${linkTo[FotoPerfilController].postar(null, null) }"
								method="post" id="fotoPerfilForm" enctype="multipart/form-data">
									<div class="image-upload">
									<label for="file">
										<img src="<c:url value='/assets/img/Desconhecido.png' />"
										
										alt="ImagemPerfil" class="imagem-perfil img-circle img-thumbnail profile_img perfil_imagem">
									
									</label>
									
									<input id="file" name="foto" type="file"/>
									</div>
									<input type="hidden" name="idPessoa" value="${usuarioLogado.usuario.pessoa.id }" />
							</form>
							
                        </div>
							
						</li>
						<li>
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						 
						<span class="texto_perfil">Perfil</span>
						<span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="${linkTo[LoginController].deslogar()}">Log Out</a></li>
							</ul>
						</li>
						</div>
<%-- 						<li><a href="${linkTo[AreaController].listar()}">Áreas</a></li> --%>
<%-- 						<li><a href="${linkTo[NoticiaController].listar()}">Notícias</a></li> --%>
<%-- 						<li><a href="${linkTo[UtilController].contato()}">Contato</a></li> --%>
<%-- 						<c:if test="${logado.usuario == null}"> --%>
<%-- 							<li><a href="${linkTo[LoginController].formulario()}">Área Restrita</a></li> --%>
<%-- 						</c:if> --%>
<%-- 						<c:if test="${logado.usuario != null}"> --%>
<%-- 							<li><a href="${linkTo[AdmController].index()}">Área Restrita</a></li> --%>
<%-- 						</c:if> --%>
						


<!-- 						<li class="dropdown"><a href="#" class="dropdown-toggle" -->
<!-- 							data-toggle="dropdown">Área Restrita <b class="caret"></b></a> -->
<!-- 							<ul class="dropdown-menu"> -->
<!-- 								<li><a href="portfolio-1-col.html">1 Column Portfolio</a></li> -->
<!-- 								<li><a href="portfolio-2-col.html">2 Column Portfolio</a></li> -->
<!-- 								<li><a href="portfolio-3-col.html">3 Column Portfolio</a></li> -->
<!-- 								<li><a href="portfolio-4-col.html">4 Column Portfolio</a></li> -->
<!-- 								<li><a href="portfolio-item.html">Single Portfolio Item</a> -->
<!-- 								</li> -->
<!-- 							</ul></li> -->
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container -->
			
		</nav>
	<div class="container">
