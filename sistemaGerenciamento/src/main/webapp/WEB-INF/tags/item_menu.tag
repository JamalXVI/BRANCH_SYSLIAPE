<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="numero" required="true" %>
<c:if test="${numero == 0 }">
	<li><a href="javascript:;" data-toggle="collapse"
		data-target="#cadastros"> <i class="glyphicon glyphicon-user">
		</i> Cadastros <i class="fa fa-fw fa-caret-down"> </i>
	</a>
		<ul id="cadastros" class="collapse">
			<li><a href="${linkTo[CadastroAdController].index()}"> AD</a></li>
			<li><a href="${linkTo[ProfessorController].cadastro()}">
					Professor</a></li>
			<li><a href="${linkTo[UsuarioController].cadastro()}">
					Usuário</a></li>
		</ul></li>
</c:if>
<c:if test="${numero == 1 }">
	<li><a href="${linkTo[EscalaController].index()}"
		data-toggle="collapse" data-target="#escala"> <i
			class="glyphicon glyphicon-list-alt"> </i> Escalas
	</a></li>
</c:if>
<c:if test="${numero == 2 }">
	<li><a href="javascript:;" data-toggle="collapse"
		data-target="#mensagem"> <i class="glyphicon glyphicon-envelope">
		</i> Mensagens <i class="fa fa-fw fa-caret-down"> </i>
	</a>
		<ul id="mensagem" class="collapse">
			<li><a href="${linkTo[MensagemController].entrada()}">
					Minhas Mensagens</a></li>
			<li><a href="${linkTo[MensagemController].enviados()}">
					Itens Enviados</a></li>
			<li><a href="${linkTo[MensagemController].nova()}"> Enviar
					Mensagem</a></li>
		</ul></li>
</c:if>
<c:if test="${numero == 3 }">
	<li><a href="javascript:;" data-toggle="collapse"
		data-target="#evento"> <i class="fa fa-exclamation"
			aria-hidden="true"></i> Pendências <i class="fa fa-fw fa-caret-down">
		</i>
	</a>
		<ul id="evento" class="collapse">
			<li><a href="${linkTo[OrdemServicoController].listarAtivas()}">
					Listar OS</a></li>
			<li><a href="${linkTo[OrdemServicoController].nova()}"> Nova
					OS</a></li>
		</ul></li>
</c:if>
<c:if test="${numero == 4 }">
	<li><a href="${linkTo[ReservasController].index()}"
		data-toggle="collapse" data-target="#reserva"> <i
			class="glyphicon glyphicon-calendar"> </i> Reservas
	</a></li>
</c:if>
<c:if test="${numero == 5 }">
	<li><a href="javascript:;" data-toggle="collapse"
		data-target="#turno"> <i class="glyphicon glyphicon-hourglass">
		</i> Turno <i class="fa fa-fw fa-caret-down"> </i>
	</a>
		<ul id="turno" class="collapse">
			<li><a href="${linkTo[MuralController].nova()}"> Enviar
					Mural</a></li>
			<li><a href="${linkTo[MuralController].entrada()}"> Meu
					Mural</a></li>
			<li><a href="${linkTo[TurnoController].index()}"> Listar
					Turnos</a></li>
		</ul></li>
</c:if>
<c:if test="${numero == 6 }">
	<li><a href="javascript:;" data-toggle="collapse"
		data-target="#outros"> <i class="fa fa-certificate"> </i> Outros <i
			class="fa fa-fw fa-caret-down"> </i>
	</a>
		<ul id="outros" class="collapse">
			<li><a href="${linkTo[HomeController].ramais()}"> Ramais</a></li>
			<li><a href="${linkTo[ArquivosController].index()}">
					Arquivos</a></li>
			<li><a href="${linkTo[HomeController].linux()}"> Configurar
					Wi-fi Linux</a></li>
			<li><a href="${linkTo[HomeController].horariosEspeciais()}">
					Horários Especiais </a></li>
			<li><a href="${linkTo[AulasLiapeController].cadastro()}">
					Painel de Informações </a></li>

		</ul></li>
</c:if>
<jsp:doBody></jsp:doBody>