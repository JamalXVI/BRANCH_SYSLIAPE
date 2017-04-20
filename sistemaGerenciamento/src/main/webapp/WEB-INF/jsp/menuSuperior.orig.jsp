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
					<!-- FIM MENU CONFIGURAÇÃO -->
					<!-- MENU DE CADASTRO -->
					<li><a href="javascript:;" data-toggle="collapse"
						data-target="#cadastros"> <i
							class="glyphicon glyphicon-user"> </i> Cadastros <i
							class="fa fa-fw fa-caret-down"> </i>
					</a>
						<ul id="cadastros" class="collapse">
							<li><a href="${linkTo[CadastroAdController].index()}">
									AD</a></li>
							<li><a href="${linkTo[ProfessorController].cadastro()}">
									Professor</a></li>
							<li><a href="${linkTo[UsuarioController].cadastro()}">
									Usuário</a></li>
						</ul></li>	
					<!-- FIM MENU CADASTROS -->
					<!-- ESCALAS -->
					<li><a href="${linkTo[EscalaController].index()}"
						data-toggle="collapse" data-target="#escala"> <i
							class="glyphicon glyphicon-list-alt"> </i> Escalas
					</a></li>
					<!-- FIM MENU ESCALAS -->
					
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
					<!-- FIM MENU MENSAGENS -->
					<!-- MENU DE PENDÊNCIAS -->
					<li><a href="javascript:;" data-toggle="collapse"
						data-target="#evento"> <i class="fa fa-exclamation" aria-hidden="true"></i>
						Pendências <i class="fa fa-fw fa-caret-down"> </i>
					</a>
						<ul id="evento" class="collapse">
							<li><a href="${linkTo[OrdemServicoController].listarAtivas()}">
							Listar OS</a></li>
							<li><a href="${linkTo[OrdemServicoController].nova()}">
							Nova OS</a></li>
						</ul></li>
					<!-- FIM MENU PENDÊNCIAS -->
					<!-- MENU RESERVAS -->
					<li><a href="${linkTo[ReservasController].index()}"
						data-toggle="collapse" data-target="#reserva"> <i
							class="glyphicon glyphicon-calendar"> </i> Reservas
					</a></li>	
					<!-- FIM MENU RESERVAS -->
					<!-- CADASTRO DE TURNO -->
					<li><a href="javascript:;" data-toggle="collapse"
						data-target="#turno"> <i
							class="glyphicon glyphicon-hourglass"> </i> Turno <i
							class="fa fa-fw fa-caret-down"> </i>
					</a>
						<ul id="turno" class="collapse">
							<li><a href="${linkTo[MuralController].nova()}">
									Enviar Mural</a></li>
							<li><a href="${linkTo[MuralController].entrada()}">
									Meu Mural</a></li>
							<li><a href="${linkTo[TurnoController].index()}">
									Listar Turnos</a></li>
						</ul></li>

					<!-- FIM MENU TURNO -->
					<!-- MENU OUTRAS -->
					
					<li><a href="javascript:;" data-toggle="collapse"
						data-target="#outros"> <i
							class="fa fa-certificate"> </i> Outros <i
							class="fa fa-fw fa-caret-down"> </i>
					</a>
						<ul id="outros" class="collapse">
							<li><a href="${linkTo[HomeController].ramais()}">
									Ramais</a></li>
							<li><a href="${linkTo[ArquivosController].index()}">
									Arquivos</a></li>
							<li><a href="${linkTo[HomeController].linux()}">
									Configurar Wi-fi Linux</a></li>
							<li><a href="${linkTo[HomeController].horariosEspeciais()}">
									Horários Especiais
									 </a></li>
							<li><a href="${linkTo[AulasLiapeController].cadastro()}">
									Painel de Informações </a></li>
							
						</ul></li>
					
					<!-- FIM MENU PUBLICAÇÕES -->

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>

		<div id="page-wrapper">