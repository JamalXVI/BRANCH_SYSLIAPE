<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body class="nav-md">
    <div class="container body">
        <div class="main_container">
            <div class="col-md-3 left_col">
                <div class="left_col scroll-view">
                    <div class="navbar nav_title" id="div_titulo" style="border: 0;">
                        <div class="aparecerSm">
                        <form action="${linkTo[FotoPerfilController].postar(null, null) }"
								method="post" id="fotoPerfilForm" enctype="multipart/form-data">
									<div class="image-upload">
									<label for="file">
										<img src="<c:url value='assets/img/img.jpg' />
										alt="ImagemPerfil" class="imagem-perfil img-thumbnail profile_img" width="54px" height="auto"
										style="padding-left: 2px;padding-left: 2px;padding-top: 2px;padding-right: 2px;padding-bottom: 2px;margin-top: 0.8em;margin-right: 0em;margin-left: 0.4em;"> <!-- img-circle -->
									</label>
									<input id="file" name="foto" type="file"/>
									</div>
									<input type="hidden" name="idPessoa" value="${usuarioLogado.usuario.pessoa.id }" />
							</form>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <!-- Seção de Foto de Perfil: Cadastro / Alteração / Visualização -->
					<div class="profile">
						<div class="profile_pic">
                            
                             <form action="${linkTo[FotoPerfilController].postar(null, null) }"
								method="post" id="fotoPerfilForm" enctype="multipart/form-data">
									<div class="image-upload">
									<label for="file">
										<img src="<c:url value='assets/img/img.jpg' />
										alt="ImagemPerfil" class="imagem-perfil img-thumbnail profile_img" width="72px" height="auto"
										style="padding-left: 2px;padding-left: 2px;padding-top: 2px;padding-right: 2px;padding-bottom: 2px;margin-top: 0.8em;margin-right: 0em;margin-left: 0.4em;"> <!-- img-circle -->
									</label>
									<input id="file" name="foto" type="file"/>
									</div>
									<input type="hidden" name="idPessoa" value="${usuarioLogado.usuario.pessoa.id }" />
							</form>
                        </div>
						<div class="profile_info">
							<span>Bem-Vindo,</span>
							<h2>${usuarioLogado.usuario.pessoa.nome}</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->
                    <br />
                    <!-- sidebar menu -->
                    <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                        <div class="menu_section secao_menu">
                            <h3>Geral</h3>
                            <ul class="nav side-menu">
                                <li>
                                    <a><i class="fa fa-user"></i> Usuários <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li><a href='${linkTo[UsuarioController].cadastro()}'>Cadastro</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a><i class="fa fa-graduation-cap"></i> Professor <span class="fa fa-chevron-down"></span></a>
                                    <ul class="nav child_menu">
                                        <li><a href='${linkTo[ProfessorController].cadastro()}'>Cadastro	</a></li>
                                    </ul>
                                </li>
                        	</ul>
                        </div>
                        <div class="menu_section">
                            <h3>Interno</h3>
                            <ul class="nav side-menu">
                                <li>
                                    <a href='${linkTo[ReservasController].index()}'><i class="fa fa-calendar"></i> Reservas</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <!-- /sidebar menu -->
                    <!-- /menu footer buttons -->
                    <div class="sidebar-footer hidden-small">
                     <!--
                        <a data-toggle="tooltip" data-placement="top" title="Settings">
                            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                        </a>
                        <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                            <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
                        </a>
                        <a data-toggle="tooltip" data-placement="top" title="Lock">
                            <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
                        </a>
                        <a data-toggle="tooltip" data-placement="top" title="Logout">
                            <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
                        </a>
                          -->
                    </div>
                    <!-- /menu footer buttons -->
                </div>
            </div>
            <!-- top navigation -->
            <div class="top_nav">
                <div class="nav_menu">
                
                    <nav>
                        <div class="nav toggle">
                            <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                        </div>
                        
                        <ul class="nav navbar-nav navbar-right">
                            <li class="">
                                <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                    <img src="<c:url value='assets/img/img.jpg' />" class="imagem-perfil" alt="">${usuarioLogado.usuario.pessoa.nome}
                                    <span class=" fa fa-angle-down"></span>
                                </a>
                                <ul class="dropdown-menu dropdown-usermenu pull-right">
                                    <li><a href="javascript:;"> Perfil</a></li>
                                    <li>
                                        <a href="javascript:;">
                                            <span class="badge bg-red pull-right">50%</span>
                                            <span>Configurações</span>
                                        </a>
                                    </li>
                                    <li><a href="javascript:;">Ajuda!</a></li>
                                    <li><a href="${linkTo[LoginController].deslogar() }"><i class="fa fa-sign-out pull-right"></i> Log Out</a></li>
                                </ul>
                            </li>
                            <li role="presentation" class="dropdown">
                                <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                                    <i class="fa fa-envelope-o"></i>
                                    <span class="badge bg-green">6</span>
                                </a>
                                <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                                    <li>
                                        <a>
                                            <span class="image"><img src="<c:url value='assets/img/img.jpg' />" class="imagem-perfil" alt="Profile Image" /></span>
                                            <span>
                                                <span>John Smith</span>
                                                <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                                Film festivals used to be do-or-die moments for movie makers. They were where...
                                            </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a>
                                            <span class="image"><img src="<c:url value='assets/img/img.jpg' />" class="imagem-perfil" alt="Profile Image" /></span>
                                            <span>
                                                <span>John Smith</span>
                                                <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                                Film festivals used to be do-or-die moments for movie makers. They were where...
                                            </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a>
                                            <span class="image"><img src="<c:url value='assets/img/img.jpg' />" class="imagem-perfil" alt="Profile Image" /></span>
                                            <span>
                                                <span>John Smith</span>
                                                <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                                Film festivals used to be do-or-die moments for movie makers. They were where...
                                            </span>
                                        </a>
                                    </li>
                                    <li>
                                        <a>
                                            <span class="image"><img src="<c:url value='assets/img/img.jpg' />" class="imagem-perfil" alt="Profile Image" /></span>
                                            <span>
                                                <span>John Smith</span>
                                                <span class="time">3 mins ago</span>
                                            </span>
                                            <span class="message">
                                                Film festivals used to be do-or-die moments for movie makers. They were where...
                                            </span>
                                        </a>
                                    </li>
                                    <li>
                                        <div class="text-center">
                                            <a>
                                                <strong>See All Alerts</strong>
                                                <i class="fa fa-angle-right"></i>
                                            </a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                            <img src="<c:url value='/assets/img/liape.png' />" alt="Logo Liape" class="centralizar_logo"
                     style="width: 15%;display: inline;"/>
                        </ul>
                        
                    </nav>
                    
                </div>
            </div>
