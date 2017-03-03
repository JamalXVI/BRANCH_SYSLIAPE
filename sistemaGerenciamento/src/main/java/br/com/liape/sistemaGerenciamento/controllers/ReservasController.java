package br.com.liape.sistemaGerenciamento.controllers;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.AnoSemestreDao;
import br.com.liape.sistemaGerenciamento.dao.ExporadicaDao;
import br.com.liape.sistemaGerenciamento.dao.ReservaDao;
import br.com.liape.sistemaGerenciamento.dao.SemestralDao;
import br.com.liape.sistemaGerenciamento.model.AnoSemestre;
import br.com.liape.sistemaGerenciamento.model.Exporadico;
import br.com.liape.sistemaGerenciamento.model.Reserva;
import br.com.liape.sistemaGerenciamento.model.Semestral;
import br.com.liape.sistemaGerenciamento.modelView.TipoReserva;
import br.com.liape.sistemaGerenciamento.modelView.TodasDaReserva;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;
import br.com.liape.sistemaGerenciamento.seguranca.UsuarioLogado;

@Controller
public class ReservasController {
	private Result result;
	private ReservaDao reservaDao;
	private SemestralDao semestralDao;
	private ExporadicaDao exporadicoDao;
	private UsuarioLogado usuarioLogado;
	private AnoSemestreDao anoSemestreDao;

	@Inject
	public ReservasController(Result result, ReservaDao reservaDao, SemestralDao semestralDao,
			ExporadicaDao exporadicoDao, UsuarioLogado usuarioLogado, AnoSemestreDao anoSemestreDao) {
		this.result = result;
		this.reservaDao = reservaDao;
		this.semestralDao = semestralDao;
		this.exporadicoDao = exporadicoDao;
		this.usuarioLogado = usuarioLogado;
		this.anoSemestreDao = anoSemestreDao;
	}

	public ReservasController() {
		this(null, null, null, null, null, null);
	}

	/*
	 * SEÇÃO DE RESERVAS - EXIBIÇÃO DA GRADE DE HORÁRIOS
	 */
	@Path("/Reserva/")
	public void index() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String dataAtual = format.format(Calendar.getInstance().getTime());
		System.out.println(dataAtual);
		result.include("agora", dataAtual);
	}

	@Post("/Listar/Reserva/Todas/")
	public void listarTodasdaReserva(int idRes) {
		TodasDaReserva todasDaReserva = new TodasDaReserva();
		Reserva reserva = reservaDao.listarPorId(idRes).get(0);
		todasDaReserva.setReserva(reserva);
		todasDaReserva.setExporadicos(exporadicoDao.listarIdReserva(idRes));
		todasDaReserva.setSemestrais(semestralDao.listaIdReserva(idRes));
		result.use(Results.json()).withoutRoot().from(todasDaReserva).include("reserva").include("exporadicos")
				.include("semestrais").include("semestrais.horaInicio").include("semestrais.horaFim")
				.include("exporadicos.horaInicio").include("exporadicos.horaFim").include("exporadicos.data")
				.serialize();
	}

	@Post("/Listar/Reservas/Proximas/")
	public void listarProximas() {
		List<TodasDaReserva> todasRes = retornarReservasProximas();
		result.use(Results.json()).withoutRoot().from(todasRes).include("reserva").include("exporadicos")
				.include("semestrais").include("semestrais.horaInicio").include("semestrais.horaFim")
				.include("exporadicos.horaInicio").include("exporadicos.horaFim").include("exporadicos.data")
				.serialize();
	}

	private List<TodasDaReserva> retornarReservasProximas() {
		LocalTime now = LocalTime.now();
		LocalDate localDate = LocalDate.now();
		List<TodasDaReserva> todasRes = new ArrayList<>();
		List<AnoSemestre> pesquisarPorData = anoSemestreDao.pesquisarPorData(localDate);
		if (pesquisarPorData.size() > 0) {
			AnoSemestre anoSemestre = pesquisarPorData.get(0);
			List<Reserva> proximasReservas = reservaDao.proximasReservas(anoSemestre.getAno(),
					anoSemestre.getSemestre());
			if (proximasReservas.size() > 0) {
				for (Reserva reserva : proximasReservas) {
					TodasDaReserva todas = new TodasDaReserva();
					todas.setSemestrais(new ArrayList<>());
					todas.setExporadicos(new ArrayList<>());
					todas.getSemestrais()
							.addAll(semestralDao.listarHora(now, now.plusMinutes(30), reserva.getId(), localDate));
					todas.getExporadicos()
							.addAll(exporadicoDao.listarPorHora(now, now.plusMinutes(30), reserva.getId(), localDate));
					todas.setReserva(reserva);
					if (todas.getExporadicos().size() > 0 || todas.getSemestrais().size() > 0) {
						todasRes.add(todas);
					}
				}
			}
		}
		return todasRes;
	}

	@Post("/Listar/Reserva/Exporadica/Mes/")
	public void listarMes(String data, int ano, int semestre) {
		if (!data.isEmpty()) {
			AnoSemestre anoSemestre = anoSemestreDao.pesquisarPorChave(ano, semestre).get(0);
			int mesEsc = Integer.valueOf(data.split("-")[1]);
			int anoEsc = Integer.valueOf(data.split("-")[0]);
			if (anoSemestre.getDataIni().getMonth().getValue() <= mesEsc
					&& anoSemestre.getDataFim().getMonth().getValue() >= mesEsc && ano == anoEsc) {
				List<Exporadico> listarMes = exporadicoDao.listarMes(mesEsc);
				for (Exporadico exporadico : listarMes) {
					exporadico.setReserva(reservaDao.listarPorId(exporadico.getIdRes()).get(0));
				}
				result.use(Results.json()).withoutRoot().from(listarMes).include("reserva").include("horaInicio")
						.include("horaFim").include("data").serialize();
			}

		}

	}

	@Post("/Listar/Reserva/Semestral/")
	public void listarSemestral(int diaSemana, int ano, int semestre) {
		List<Semestral> listarDiaSemana = semestralDao.listarDiaSemana(diaSemana);
		List<Semestral> removerSemestrais = new ArrayList<>();
		for (Semestral semestral : listarDiaSemana) {
			semestral.setReserva(reservaDao.listarPorId(semestral.getIdRes()).get(0));
			if (semestral.getReserva().getAnoAns() != ano || semestral.getReserva().getSemestreAns() != semestre) {
				removerSemestrais.add(semestral);
			}
		}
		listarDiaSemana.removeAll(removerSemestrais);
		result.use(Results.json()).withoutRoot().from(listarDiaSemana).include("horaInicio").include("horaFim")
				.include("reserva").serialize();

	}

	public void pesquisarHorariosProximos() {

	}

	@Post("/Listar/Reserva/Exporadica/")
	public void listarExporadica(String data) {
		try {
			if (dataValida(data)) {
				List<Exporadico> listarExporadico = exporadicoDao.listarDataMarcada(Conversor.converterLocalDate(data));
				for (Exporadico exporadico : listarExporadico) {
					exporadico.setReserva(reservaDao.listarPorId(exporadico.getIdRes()).get(0));
				}
				result.use(Results.json()).withoutRoot().from(listarExporadico).include("horaInicio").include("horaFim")
						.include("reserva").include("data").serialize();
			}
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		;
	}

	private boolean dataValida(String data) throws java.text.ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			df.parse(data);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int retornarTempo(LocalTime tempo) {
		return tempo.getHour() * 60 + tempo.getMinute();
	}

	public Boolean validarHorario(LocalTime entradaIni, LocalTime entradaFim, LocalTime existenteIni,
			LocalTime existenteFim) {
		int saidasaida = retornarTempo(entradaFim) - retornarTempo(existenteFim);
		int saidaentrada = retornarTempo(entradaFim) - retornarTempo(existenteIni);
		int entradaentrada = retornarTempo(entradaIni) - retornarTempo(existenteIni);
		int entradasaida = retornarTempo(entradaIni) - retornarTempo(existenteFim);

		if ((saidaentrada < 0 && entradaentrada < 0) || (saidasaida > 0 && entradasaida > 0)
				|| (entradaentrada < 0 && saidaentrada == 0) || (entradasaida == 0 && saidasaida > 0)) {

		} else {
			return true;
		}
		return false;
	}

	public String validar(TipoReserva tipoReserva, Reserva reserva) {

		String dataErro = "";
		List<Semestral> semestrais = new ArrayList<>();
		List<Exporadico> exporadicos = new ArrayList<>();
		AnoSemestre ans = anoSemestreDao.pesquisarPorChave(reserva.getAnoAns(), reserva.getSemestreAns()).get(0);
		int diaSemana = retornarDiaSemana(tipoReserva);
		if (tipoReserva.getDiaSemana() != null) {
			for (LocalDate dataEsc = LocalDate.now(); dataEsc
					.isBefore(ans.getDataFim()); dataEsc = dataEsc.plusDays((1))) {
				if (diaSemana == dataEsc.getDayOfWeek().getValue() - 1) {
					List<Exporadico> listarDataMarcada = exporadicoDao.listarDataMarcada(dataEsc);

					if (!listarDataMarcada.isEmpty()) {
						listarDataMarcada.get(0)
								.setReserva(reservaDao.listarPorId(listarDataMarcada.get(0).getIdRes()).get(0));
						if (verficarValidadeReserva(listarDataMarcada.get(0).getReserva(), ans, reserva,
								listarDataMarcada.get(0).getIdSal(), tipoReserva.getIdSala())) {
							exporadicos.add(listarDataMarcada.get(0));

						}
					}

				}
			}
		} else {
			LocalDate dataMarcadaEsc = Conversor.converterLocalDate(tipoReserva.getDataMarcada());
			if (!validarData(dataMarcadaEsc, ans)) {
				return "Erro: Data marcada inválida";
			}
			List<Exporadico> listarDataMarcada = exporadicoDao.listarDataMarcada(dataMarcadaEsc);

			if (!listarDataMarcada.isEmpty()) {
				listarDataMarcada.get(0).setReserva(reservaDao.listarPorId(listarDataMarcada.get(0).getIdRes()).get(0));
				if (verficarValidadeReserva(listarDataMarcada.get(0).getReserva(), ans, reserva,
						listarDataMarcada.get(0).getIdSal(), tipoReserva.getIdSala())) {
					exporadicos.add(listarDataMarcada.get(0));

				}
			}
		}

		List<Semestral> listarDiaSemana = semestralDao.listarDiaSemana(diaSemana);
		if (!listarDiaSemana.isEmpty()) {
			for (Semestral semestral : listarDiaSemana) {
				semestral.setReserva(reservaDao.listarPorId(semestral.getIdRes()).get(0));
				if (verficarValidadeReserva(semestral.getReserva(), ans, reserva, semestral.getIdSal(),
						tipoReserva.getIdSala())) {
					semestrais.add(semestral);
				}
			}
		}

		LocalTime horarioEntrada = Conversor.converterLocalTime(tipoReserva.getHoraInicio());
		LocalTime horarioSaida = Conversor.converterLocalTime(tipoReserva.getHoraFim());

		for (Exporadico exporadico : exporadicos) {
			if (validarHorario(horarioEntrada, horarioSaida, exporadico.getHoraInicio(), exporadico.getHoraFim())) {
				dataErro = exporadico.getData().toString();
				// System.out.println("Errou Aqui Sandra!");
				return "Erro: Já existe este cadastro para o Dia " + dataErro + " no horário das "
						+ retornarComZero(exporadico.getHoraInicio().getHour()) + ":"
						+ retornarComZero(exporadico.getHoraInicio().getMinute());
			}
		}
		for (Semestral semestral : semestrais) {
			if (validarHorario(horarioEntrada, horarioSaida, semestral.getHoraInicio(), semestral.getHoraFim())) {
				dataErro = String.valueOf(semestral.getDiaSemana());
				return "Erro: Já existe este cadastro para o Dia da Semana "
						+ retornaODiaDaSemana(Integer.valueOf(dataErro)) + " no horário das "
						+ retornarComZero(semestral.getHoraInicio().getHour()) + ":"
						+ retornarComZero(semestral.getHoraInicio().getMinute());
			}
		}
		return "Sucesso!";
	}

	private String retornaODiaDaSemana(int dia) {
		String esc = "";
		switch (dia) {
		case 0:
			esc = "Segunda";
			break;
		case 1:
			esc = "Terça";
			break;
		case 2:
			esc = "Quarta";
			break;
		case 3:
			esc = "Quinta";
			break;
		case 4:
			esc = "Sexta";
			break;
		case 5:
			esc = "Sábado";
			break;
		case 6:
			esc = "Domingo";
			break;
		}
		return esc;
	}

	private String retornarComZero(int horario) {
		return horario < 10 ? "0" + horario : String.valueOf(horario);
	}

	private Boolean verficarValidadeReserva(Reserva res, AnoSemestre ans, Reserva tipoReserva, int idSalTipoReserva,
			int idSal) {
		boolean anoValido = ans.getAno() == res.getAnoAns() ? true : false;
		boolean semestreValido = ans.getSemestre() == res.getSemestreAns() ? true : false;
		boolean salaValida = idSal == idSalTipoReserva ? true : false;
		return salaValida && anoValido && semestreValido;
	}

	private int retornarDiaSemana(TipoReserva tipoReserva) {
		int diaSemana = -1;
		if (tipoReserva.getDataMarcada() != null) {
			diaSemana = Conversor.converterLocalDate(tipoReserva.getDataMarcada()).getDayOfWeek().getValue() - 1;
		} else {
			diaSemana = Integer.valueOf(tipoReserva.getDiaSemana());
		}
		return diaSemana;
	}

	@Post("/Cadastro/Reserva/")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 4 ou for administrador
	 */
	@NivelPermissao(idPermissao = 4)
	public void postar(@Valid Reserva reserva, TipoReserva tipoReserva) {
		reserva.setAtivo(true);
		reserva.setLoginUsr(usuarioLogado.getUsuario().getLogin());
		String mensagem = "";
		MensagemSistema mensagemSistema;
		if (!verificarValidade(tipoReserva)) {
			mensagem = "Erro: Cadastro Inválido";
		} else {
			mensagem = validar(tipoReserva, reserva);

		}
		if (!mensagem.contains("Erro")) {
			boolean inserir = false;
			if (reservaDao.verificarSeExiste(reserva)) {
				inserir = true;
				reserva = reservaDao.retornarReserva(reserva).get(0);
			} else {
				inserir = reservaDao.inserir(reserva);
				reserva = reservaDao.ultimoId().get(0);
			}
			if (inserir) {
				if (tipoReserva.getDataMarcada() != null) {
					Exporadico exporadico = new Exporadico();
					exporadico.setData(Conversor.converterLocalDate(tipoReserva.getDataMarcada()));
					exporadico.setHoraInicio(Conversor.converterLocalTime(tipoReserva.getHoraInicio()));
					exporadico.setHoraFim(Conversor.converterLocalTime(tipoReserva.getHoraFim()));
					exporadico.setObservacao(tipoReserva.getObservacao());
					exporadico.setIdRes(reserva.getId());
					exporadico.setIdSal(tipoReserva.getIdSala());
					exporadico.setAtivo(true);
					if (exporadicoDao.verSeExite(exporadico.getIdRes(), exporadico.getData(),
							exporadico.getHoraInicio(), exporadico.getHoraFim()).size() > 0) {
						exporadicoDao.atualizar(exporadico);
					} else {
						exporadicoDao.inserir(exporadico);
					}

				} else {
					Semestral semestral = new Semestral();
					semestral.setDiaSemana(Integer.valueOf(tipoReserva.getDiaSemana()));
					semestral.setHoraInicio(Conversor.converterLocalTime(tipoReserva.getHoraInicio()));
					semestral.setHoraFim(Conversor.converterLocalTime(tipoReserva.getHoraFim()));
					semestral.setObservacao(tipoReserva.getObservacao());
					semestral.setIdRes(reserva.getId());
					semestral.setIdSal(tipoReserva.getIdSala());
					semestral.setAtivo(true);
					semestralDao.inserir(semestral);
				}
			}
			mensagemSistema = new MensagemSistema("Sucesso!");
		} else {
			mensagemSistema = new MensagemSistema(mensagem);
		}
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();

	}

	// VERIFICAR SE A RESERVA É VÁLIDA
	private boolean verificarValidade(TipoReserva tipoReserva) {
		if (tipoReserva.getDataMarcada() == null && tipoReserva.getDiaSemana() == null) {
			return false;
		}
		return true;
	}

	// Comparar data com o Ano Semestre
	public boolean validarData(LocalDate dataMarcada, AnoSemestre ans) {
		if (dataMarcada.isBefore(ans.getDataIni()) || dataMarcada.isAfter(ans.getDataFim())) {
			return false;
		}
		return true;
	}

	@Post("/Deletar/Reserva/Exporadico")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 4 ou for administrador
	 */
	// DELETAR RESERVA EXPORÁDICO
	@NivelPermissao(idPermissao = 4)
	public void deletarExp(int idResExp, String dataMarcada, String horaIni, String horaFim) {
		List<Exporadico> listarExporadico = exporadicoDao.listarExporadico(idResExp,
				Conversor.converterLocalDate(dataMarcada), Conversor.converterLocalTime(horaIni),
				Conversor.converterLocalTime(horaFim));
		Exporadico exporadico = listarExporadico.get(0);
		MensagemSistema msg;
		exporadico.setAtivo(false);
		if (exporadicoDao.atualizar(exporadico)) {
			msg = new MensagemSistema("Sucesso!");
		} else {
			msg = new MensagemSistema("Erro!");
		}
		result.use(Results.json()).withoutRoot().from(msg).serialize();
	}

	@Post("/Deletar/Reserva/Semestral")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 4 ou for administrador
	 */
	// DELETAR RESERVA SEMESTRAL
	@NivelPermissao(idPermissao = 4)
	public void deletarSem(int idResSem) {
		Semestral semestral = semestralDao.listaIdSem(idResSem).get(0);
		MensagemSistema msg;
		semestral.setAtivo(false);
		if (semestralDao.atualizar(semestral)) {
			msg = new MensagemSistema("Sucesso!");
		} else {
			msg = new MensagemSistema("Erro!");
		}
		result.use(Results.json()).withoutRoot().from(msg).serialize();
	}
}