package br.com.liape.sistemaGerenciamento.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsSalasLiape;
import br.com.liape.sistemaGerenciamento.dao.AnoSemestreDao;
import br.com.liape.sistemaGerenciamento.dao.AulasLiapeDao;
import br.com.liape.sistemaGerenciamento.dao.ExporadicaDao;
import br.com.liape.sistemaGerenciamento.dao.PessoaDao;
import br.com.liape.sistemaGerenciamento.dao.ProfessorDao;
import br.com.liape.sistemaGerenciamento.dao.ReservaDao;
import br.com.liape.sistemaGerenciamento.dao.SalaDao;
import br.com.liape.sistemaGerenciamento.dao.SemestralDao;
import br.com.liape.sistemaGerenciamento.infra.Configuracoes;
import br.com.liape.sistemaGerenciamento.model.AnoSemestre;
import br.com.liape.sistemaGerenciamento.model.AulasLiape;
import br.com.liape.sistemaGerenciamento.model.Exporadico;
import br.com.liape.sistemaGerenciamento.model.Professor;
import br.com.liape.sistemaGerenciamento.model.Sala;
import br.com.liape.sistemaGerenciamento.model.Semestral;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.LoginFuncionario;

@Controller
public class AulasLiapeController {

	private AulasLiapeDao aulasLiapeDao;
	private SemestralDao semestralDao;
	private ExporadicaDao exporadicaDao;
	private AnoSemestreDao anoSemestreDao;
	private ReservaDao reservaDao;
	private ProfessorDao professorDao;
	private SalaDao salaDao;
	private Result result;
	private PessoaDao pessoaDao;

	@Inject
	public AulasLiapeController(AulasLiapeDao aulasLiapeDao, SemestralDao semestralDao, ExporadicaDao exporadicaDao,
			AnoSemestreDao anoSemestreDao, ReservaDao reservaDao, ProfessorDao professorDao, SalaDao salaDao,
			Result result, PessoaDao pessoaDao) {
		this.aulasLiapeDao = aulasLiapeDao;
		this.semestralDao = semestralDao;
		this.exporadicaDao = exporadicaDao;
		this.reservaDao = reservaDao;
		this.anoSemestreDao = anoSemestreDao;
		this.professorDao = professorDao;
		this.salaDao = salaDao;
		this.result = result;
		this.pessoaDao = pessoaDao;
	}

	public AulasLiapeController() {
		this(null, null, null, null, null, null, null, null, null);
	}

	@Path("/Cadastro/AulasLiape/")
	public void cadastro() {

	}

	@Post("/Remover/AulasLiape/")
	public void remover(int id) {
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso");
		if (!remove(id)) {
			mensagemSistema.setMensagem("Erro!");
		}
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}

	@Post("/Postar/AulasLiape/")
	public void postar(AulasLiape aula, String horaFim, String horaInicio) {
		aula.setHoraInicio(Conversor.converterLocalTime(horaInicio));
		aula.setHoraFim(Conversor.converterLocalTime(horaFim));
		aula.setData(LocalDate.now());
		aula.setAtivo(true);
		if (aula.getId() > 0) {
			if (!aulasLiapeDao.atualizar(aula)) {
				result.redirectTo(ErrosController.class).erro_operacao();
			} else {
				result.redirectTo(this).cadastro();
			}
		} else {
			if (!aulasLiapeDao.inserir(aula)) {
				result.redirectTo(ErrosController.class).erro_operacao();
			} else {
				result.redirectTo(this).cadastro();
			}
		}
	}

	@Post("/Listar/AulasLiape/Especial/")
	@LoginFuncionario
	public void listarEsp() {
		LocalTime now = LocalTime.now();
		int minutos = Configuracoes.configuracao.getTempoAntesDaAula();
		now = now.plusMinutes(minutos);
		List<AulasLiape> listar = aulasLiapeDao.listarEsp(LocalDate.now(), now);
		List<AulasLiape> novaLista = aulasLiapeDao.listar(LocalDate.now());
		if (novaLista.size() <= 0) {
			fazerRenovacao();
			listar = aulasLiapeDao.listarEsp(LocalDate.now(), now);
		}
		result.use(Results.json()).withoutRoot().from(listar).include("horaInicio").include("horaFim").serialize();
	}

	@Post("/Listar/AulasLiape/")
	public void lista() {
		listar();
	}

	@Post("/Renovar/AulasLiape/")
	public void renovar() {
		fazerRenovacao();
		listar();
	}

	private void fazerRenovacao() {
		aulasLiapeDao.deletarTodos();
		LocalDate localDate = LocalDate.now();
		List<AnoSemestre> pesquisarPorData = anoSemestreDao.pesquisarPorData(localDate);
		if (pesquisarPorData.size() > 0) {
			AnoSemestre anoSemestre = pesquisarPorData.get(0);
			adicionarSemestrais(localDate, anoSemestre);
			adicionarExporadicas(localDate);
		}
	}

	private void listar() {
		List<AulasLiape> listar = aulasLiapeDao.listar(LocalDate.now());
		result.use(Results.json()).withoutRoot().from(listar).include("horaInicio").include("horaFim").serialize();
	}

	private void adicionarSemestrais(LocalDate localDate, AnoSemestre anoSemestre) {
		List<Semestral> semestrais = listarSemestrais(localDate, anoSemestre);
		if (semestrais.size() > 0) {
			for (Semestral semestral : semestrais) {
				String codigoPro = semestral.getReserva().getCodigoPro();
				LocalTime horaInicio = semestral.getHoraInicio();
				LocalTime horaFim = semestral.getHoraFim();
				int idSal = semestral.getIdSal();
				adicionarAulas(codigoPro, horaInicio, horaFim, idSal);
			}
		}
	}

	private void adicionarExporadicas(LocalDate localDate) {
		List<Exporadico> listarExporadico = exporadicaDao.listarDataMarcada(localDate);
		if (listarExporadico.size() > 0) {
			for (Exporadico exporadico : listarExporadico) {
				exporadico.setReserva(reservaDao.listarPorId(exporadico.getIdRes()).get(0));
				String codigoPro = exporadico.getReserva().getCodigoPro();
				LocalTime horaInicio = exporadico.getHoraInicio();
				LocalTime horaFim = exporadico.getHoraFim();
				int idSal = exporadico.getIdSal();
				adicionarAulas(codigoPro, horaInicio, horaFim, idSal);
			}
		}
	}

	private void adicionarAulas(String codigoPro, LocalTime horaInicio, LocalTime horaFim, int idSal) {
		AulasLiape aulasLiape = new AulasLiape();
		aulasLiape.setAtivo(true);
		aulasLiape.setHoraFim(horaFim);
		aulasLiape.setHoraInicio(horaInicio);
		aulasLiape.setStatus(FlagsSalasLiape.AULA_NORMAL.getCodigo());
		aulasLiape.setData(LocalDate.now());
		adicionarCodigoProfessor(aulasLiape, codigoPro);
		adicionarSala(aulasLiape, idSal);
		aulasLiapeDao.inserir(aulasLiape);
	}

	private void adicionarSala(AulasLiape aulasLiape, int idSal) {
		List<Sala> salas = salaDao.listarId(idSal);
		if (salas.size() > 0) {
			Sala sala = salas.get(0);
			aulasLiape.setSala(sala.getNome());
		}
	}

	private void adicionarCodigoProfessor(AulasLiape aulasLiape, String codigoPro) {
		List<Professor> listarPorCodProfessor = professorDao.listarPorCodProfessor(codigoPro);
		if (listarPorCodProfessor.size() > 0) {
			Professor professor = listarPorCodProfessor.get(0);
			professor.setPessoa(pessoaDao.listarPorId(professor.getIdPes()).get(0));
			aulasLiape.setDescricao(professor.getPessoa().getNome() + " " + professor.getPessoa().getSobrenome());
		}
	}

	private List<Semestral> listarSemestrais(LocalDate localDate, AnoSemestre anoSemestre) {
		List<Semestral> listarDiaSemana = semestralDao.listarDiaSemanaProx(localDate);
		List<Semestral> removerSemestrais = new ArrayList<>();
		for (Semestral semestral : listarDiaSemana) {
			semestral.setReserva(reservaDao.listarPorId(semestral.getIdRes()).get(0));
			if (semestral.getReserva().getAnoAns() != anoSemestre.getAno()
					|| semestral.getReserva().getSemestreAns() != anoSemestre.getSemestre()) {
				removerSemestrais.add(semestral);
			}
		}
		listarDiaSemana.removeAll(removerSemestrais);
		return listarDiaSemana;

	}

	private boolean remove(int id) {
		return aulasLiapeDao.remover(id);
	}
}