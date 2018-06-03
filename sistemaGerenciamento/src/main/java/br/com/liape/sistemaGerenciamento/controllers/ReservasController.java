package br.com.liape.sistemaGerenciamento.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.dao.AnoSemestreDao;
import br.com.liape.sistemaGerenciamento.dao.ExporadicaDao;
import br.com.liape.sistemaGerenciamento.dao.ReservaDao;
import br.com.liape.sistemaGerenciamento.dao.SemestralDao;
import br.com.liape.sistemaGerenciamento.logica.LogicaReserva;
import br.com.liape.sistemaGerenciamento.model.Exporadico;
import br.com.liape.sistemaGerenciamento.model.Reserva;
import br.com.liape.sistemaGerenciamento.model.Semestral;
import br.com.liape.sistemaGerenciamento.model.TipoInsercaoReserva;
import br.com.liape.sistemaGerenciamento.modelView.TipoReserva;
import br.com.liape.sistemaGerenciamento.modelView.TodasDaReserva;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.outros.RetornoMensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class ReservasController extends AbstractController {
	private LogicaReserva logicaReserva;

	@Inject
	public ReservasController(ReservaDao reservaDao, SemestralDao semestralDao, ExporadicaDao exporadicoDao,
			AnoSemestreDao anoSemestreDao) {
		logicaReserva = new LogicaReserva(anoSemestreDao, reservaDao, exporadicoDao, semestralDao);
	}

	public ReservasController() {
		this(null, null, null, null);
	}

	/*
	 * SEÇÃO DE RESERVAS - EXIBIÇÃO DA GRADE DE HORÁRIOS
	 */
	@Path("/Reserva/")
	public void index() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dataAtual = format.format(Calendar.getInstance().getTime());
		result.include("agora", dataAtual);
	}

	@Post("/Listar/Reserva/Todas/")
	public void listarTodasdaReserva(int idRes) {
		TodasDaReserva todasDaReserva = logicaReserva.listarTodasReservas(idRes);
		result.use(Results.json()).withoutRoot().from(todasDaReserva).include("reserva").include("exporadicos")
				.include("semestrais").include("semestrais.horaInicio").include("semestrais.horaFim")
				.include("exporadicos.horaInicio").include("exporadicos.horaFim").include("exporadicos.data")
				.serialize();
	}

	@Post("/Listar/Reservas/Proximas/")
	public void listarProximas() {
		List<TodasDaReserva> todasRes = logicaReserva.listarReservasProximas();
		result.use(Results.json()).withoutRoot().from(todasRes).include("reserva").include("exporadicos")
				.include("semestrais").include("semestrais.horaInicio").include("semestrais.horaFim")
				.include("exporadicos.horaInicio").include("exporadicos.horaFim").include("exporadicos.data")
				.serialize();
	}

	

	@Post("/Listar/Reserva/Exporadica/Mes/")
	public void listarMes(String data, int ano, int semestre) {
		List<Exporadico> listarMes = logicaReserva.listarReservasExporadicasMes(data, ano, semestre);
		result.use(Results.json()).withoutRoot().from(listarMes).include("reserva").include("horaInicio")
				.include("horaFim").include("data").serialize();

	}

	@Post("/Listar/Reserva/Semestral/")
	public void listarSemestral(int diaSemana, int ano, int semestre) {
		List<Semestral> listarDiaSemana = logicaReserva.listarReservasSemestrais(diaSemana, ano, semestre);
		result.use(Results.json()).withoutRoot().from(listarDiaSemana).include("horaInicio").include("horaFim")
				.include("reserva").serialize();

	}
	
	@Post("/Listar/Reserva/Exporadica/")
	public void listarExporadica(String data) {
		try {
			List<Exporadico> listarExporadico = logicaReserva.listarReservasExporadicas(data);
			result.use(Results.json()).withoutRoot().from(listarExporadico).include("horaInicio").include("horaFim")
					.include("reserva").include("data").serialize();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		;
	}

	@Post("/Cadastro/Reserva/")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 4 ou for administrador
	 */
	@NivelPermissao(idPermissao = 4)
	public void postar(@Valid Reserva reserva, TipoReserva tipoReserva) {
		RetornoMensagemSistema<Reserva> inserirReserva = logicaReserva.inserirReserva(reserva, tipoReserva,
				usuarioLogado);
		MensagemSistema msg = inserirReserva.mensagem;
		if (verificarSeNaoTemErroMensagemSistema(msg)) {
			RetornoMensagemSistema<TipoInsercaoReserva> inserirExporadica = logicaReserva.inserirReservaEsp(inserirReserva.objeto,
					tipoReserva, usuarioLogado, msg);
			msg = inserirExporadica.mensagem;
			TipoInsercaoReserva tipoIns = inserirExporadica.objeto;
			if (tipoIns.isAtualizar()) {
				if (tipoIns.isReservaExporadica()) {
					registrarLog(FlagsLogAcao.ATUALIZAR_EXPORADICO.getCodigo(), tipoIns.getMensagem());
				}
			} else {
				if (tipoIns.isReservaExporadica()) {
					registrarLog(FlagsLogAcao.CADASTRAR_EXPORADICO.getCodigo(), tipoIns.getMensagem());
				} else {
					registrarLog(FlagsLogAcao.CADASTRAR_SEMESTRAL.getCodigo(), tipoIns.getMensagem());
				}
			}
		}
		result.use(Results.json()).withoutRoot().from(msg).serialize();

	}

	@Post("/Deletar/Reserva/Exporadico")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 4 ou for administrador
	 */
	// DELETAR RESERVA EXPORÁDICO
	@NivelPermissao(idPermissao = 4)
	public void deletarExp(int idResExp, String dataMarcada, String horaIni, String horaFim) {
		RetornoMensagemSistema<String> res = logicaReserva.removerReservaExporadica(idResExp, dataMarcada, horaIni, horaFim);
		if(verificarSeNaoTemErroMensagemSistema(res.mensagem)){
			registrarLog(FlagsLogAcao.REMOVER_EXPORADICO.getCodigo(), res.objeto);
		}
		result.use(Results.json()).withoutRoot().from(res.mensagem).serialize();
	}

	@Post("/Deletar/Reserva/Semestral")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 4 ou for administrador
	 */
	// DELETAR RESERVA SEMESTRAL
	@NivelPermissao(idPermissao = 4)
	public void deletarSem(int idResSem) {
		RetornoMensagemSistema<String> res = logicaReserva.removerReservaSemestral(idResSem);
		if(verificarSeNaoTemErroMensagemSistema(res.mensagem)){
			registrarLog(FlagsLogAcao.REMOVER_SEMESTRAL.getCodigo(), res.objeto);
		}
		result.use(Results.json()).withoutRoot().from(res.mensagem).serialize();
	}

	@Post("/Retroceder/Reserva/Semestral")
	@NivelPermissao(idPermissao = 4)
	public void retrocederSem(int idResSem) {
		MensagemSistema msg = logicaReserva.retrocederSemestral(idResSem);
		result.use(Results.json()).withoutRoot().from(msg).serialize();
	}

	@Post("/Retroceder/Reserva/Exporadico")
	@NivelPermissao(idPermissao = 4)
	public void retrocederExp(int idResExp, String dataMarcada, String horaIni, String horaFim) {
		MensagemSistema msg = logicaReserva.retrocederExporadica(idResExp, dataMarcada, horaIni, horaFim);
		result.use(Results.json()).withoutRoot().from(msg).serialize();
	}
}