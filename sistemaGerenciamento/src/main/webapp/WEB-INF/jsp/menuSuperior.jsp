<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
				<a class="navbar-brand" href="${linkTo[IndexController].index()}">
					Sistema de Gerenciamento </a>
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

					<!-- MENU CONFIGURAÇÃO - SE O USUARIO LOGADO FOR ADMINISTRATOR EXIBE O MENU DO USUARIO -->
					<%-- 					<c:if test="${logado.usuario.tipoUsuario == 'ADMINISTRADOR'}"> --%>
					<!-- 						<li><a href="javascript:;" data-toggle="collapse" -->
					<!-- 							data-target="#configuracao"> <i -->
					<!-- 								class="glyphicon glyphicon-cog"> </i> Configuração <i -->
					<!-- 								class="fa fa-fw fa-caret-down"> </i> -->
					<!-- 						</a> -->
					<!-- 							<ul id="configuracao" class="collapse"> -->
					<%-- 								<li><a href="${linkTo[AtuacaoController].index()}">Áreas --%>
					<!-- 										de Atuação</a></li> -->
					<!-- 							</ul></li> -->
					<%-- 					</c:if> --%>
					<!-- FIM MENU CONFIGURAÇÃO -->

					<!-- MENU USUARIO-->
					<li><a href="${linkTo[UsuarioController].cadastro()}"
						data-toggle="collapse" data-target="#usuario"> <i
							class="glyphicon glyphicon-user"> </i> Usuários
					</a></li>
					<!-- FIM MENU USUARIO -->


					
					<!-- FIM MENU MENSAGENS -->


					<!-- MENU PROFESSOR -->
					<li><a href="${linkTo[ProfessorController].cadastro()}"
						data-toggle="collapse" data-target="#professor"> <i
							class="glyphicon glyphicon-apple"> </i> Professor
					</a> <!-- FIM MENU NOTICIA --> <!-- MENU PUBLICAÇÕES -->
					<li><a href="${linkTo[ReservasController].index()}"
						data-toggle="collapse" data-target="#reserva"> <i
							class="glyphicon glyphicon-calendar"> </i> Reservas
					</a></li>

					<li><a href="javascript:;" data-toggle="collapse"
						data-target="#turno"> <i
							class="glyphicon glyphicon-hourglass"> </i> Turno <i
							class="fa fa-fw fa-caret-down"> </i>
					</a>
						<ul id="turno" class="collapse">
							<li><a href="${linkTo[TurnoController].index()}">
									Listar Turnos</a></li>
							<li><a href="${linkTo[MuralController].entrada()}">
									Meu Mural</a></li>
							<li><a href="${linkTo[MuralController].nova()}">
									Enviar Mensagem Mural</a></li>
						</ul></li>
					<!-- FIM MENU PUBLICAÇÕES -->

					<!-- MENU DE PENDÊNCIAS -->
					<li><a href="javascript:;" data-toggle="collapse"
						data-target="#evento"> <i class="fa fa-exclamation" aria-hidden="true"></i>
						Pendências <i class="fa fa-fw fa-caret-down"> </i>
					</a>
						<ul id="evento" class="collapse">
							<li><a href="${linkTo[OrdemServicoController].nova()}">
							Nova Ordem Serviço</a></li>
							<li><a href="${linkTo[OrdemServicoController].listarAtivas()}">
							Ver Ordens de Serviço Ativas</a></li>
							<li><a href="${linkTo[EscalaController].index()}">
							Escalas</a></li>
						</ul></li>
					<!-- MENU MENSAGENS -->
					<li><a href="javascript:;" data-toggle="collapse"
						data-target="#mensagem"> <i
							class="glyphicon glyphicon-envelope"> </i> Mensagens <i
							class="fa fa-fw fa-caret-down"> </i>
					</a>
						<ul id="mensagem" class="collapse">
							<li><a href="${linkTo[MensagemController].entrada()}">
									Minhas Mensagens</a></li>
							<li><a href="${linkTo[MensagemController].enviados()}">
									Itens Enviados</a></li>
							<li><a href="${linkTo[MensagemController].nova()}">
									Enviar Mensagem</a></li>
						</ul></li>
					<!-- MENU OUTRAS -->
					<li><a href="javascript:;" data-toggle="collapse"
						data-target="#outros"> <i
							class="fa fa-certificate"> </i> Outros <i
							class="fa fa-fw fa-caret-down"> </i>
					</a>
						<ul id="outros" class="collapse">
							<li><a href="${linkTo[HomeController].linux()}">
									Configurar Wi-fi Linux</a></li>
						</ul></li>
					
					<!-- FIM MENU PUBLICAÇÕES -->

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>

		<div id="page-wrapper">