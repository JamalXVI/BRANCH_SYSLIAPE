package br.com.liape.sistemaGerenciamento.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.ComputadorDao;
import br.com.liape.sistemaGerenciamento.dao.OrdemServicoComputadorDao;
import br.com.liape.sistemaGerenciamento.dao.OrdemServicoDao;
import br.com.liape.sistemaGerenciamento.dao.OrdemServicoSalaDao;
import br.com.liape.sistemaGerenciamento.dao.SalaDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemTurnoDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemUsuarioDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemVisualizadaDao;
import br.com.liape.sistemaGerenciamento.dao.TurnoDao;
import br.com.liape.sistemaGerenciamento.model.Computador;
import br.com.liape.sistemaGerenciamento.model.OrdemServico;
import br.com.liape.sistemaGerenciamento.model.OrdemServicoComputador;
import br.com.liape.sistemaGerenciamento.model.OrdemServicoSala;
import br.com.liape.sistemaGerenciamento.model.Sala;
import br.com.liape.sistemaGerenciamento.model.SubOrdem;
import br.com.liape.sistemaGerenciamento.model.SubOrdemTurno;
import br.com.liape.sistemaGerenciamento.model.SubOrdemUsuario;
import br.com.liape.sistemaGerenciamento.model.SubOrdemVisualizada;
import br.com.liape.sistemaGerenciamento.model.Turno;
import br.com.liape.sistemaGerenciamento.modelView.NovaOrdem;
import br.com.liape.sistemaGerenciamento.modelView.NovaOrdemRedirecionada;
import br.com.liape.sistemaGerenciamento.modelView.SubOrdemView;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;
import br.com.liape.sistemaGerenciamento.seguranca.UsuarioLogado;

@Controller
public class OrdemServicoController {

	private Result result;
	private OrdemServicoDao ordemServicoDao;
	private SubOrdemDao subOrdemDao;
	private SalaDao salaDao;
	private ComputadorDao computadorDao;
	private OrdemServicoComputadorDao ordemServicoComputadorDao;
	private OrdemServicoSalaDao ordemServicoSalaDao;
	private UsuarioLogado usuarioLogado;
	private SubOrdemTurnoDao subOrdemTurnoDao;
	private SubOrdemUsuarioDao subOrdemUsuarioDao;
	private TurnoDao turnoDao;
	private SubOrdemVisualizadaDao ordemVisualizadaDao;

	@Inject
	public OrdemServicoController(Result result, OrdemServicoDao ordemServicoDao, SubOrdemDao subOrdemDao,
			SalaDao salaDao, ComputadorDao computadorDao, OrdemServicoComputadorDao ordemServicoComputadorDao,
			OrdemServicoSalaDao ordemServicoSalaDao, UsuarioLogado usuarioLogado, SubOrdemTurnoDao subOrdemTurnoDao,
			SubOrdemUsuarioDao subOrdemUsuarioDao, TurnoDao turnoDao,
			SubOrdemVisualizadaDao ordemVisualizadaDao) {
		this.result = result;
		this.ordemServicoDao = ordemServicoDao;
		this.subOrdemDao = subOrdemDao;
		this.salaDao = salaDao;
		this.computadorDao = computadorDao;
		this.ordemServicoComputadorDao = ordemServicoComputadorDao;
		this.ordemServicoSalaDao = ordemServicoSalaDao;
		this.usuarioLogado = usuarioLogado;
		this.subOrdemTurnoDao = subOrdemTurnoDao;
		this.subOrdemUsuarioDao = subOrdemUsuarioDao;
		this.turnoDao = turnoDao;
		this.ordemVisualizadaDao = ordemVisualizadaDao;
	}

	public OrdemServicoController() {
		this(null, null, null, null, null, null, null, null, null, null, null, null);
	}

	@Path("/Cadastro/Ordem/")
	public void nova() {
	}

	@Path("/Listar/Ordem/Ativas/")
	public void listarAtivas() {
	}

	@Post("/Listar/Ordem/")
	public void listar() {
		result.use(Results.json()).withoutRoot().from(ordemServicoDao.listarExecucao(false)).serialize();

	}

	@Post("/Listar/Ordem/Sala/")
	public void listarSala(int idOrs) {
		System.out.println(idOrs);
		result.use(Results.json()).withoutRoot().from(ordemServicoSalaDao.listarId(idOrs)).serialize();
	}

	@Post("/Listar/Ordem/Computador/")
	public void listarComputador(int idOrs) {
		result.use(Results.json()).withoutRoot().from(ordemServicoComputadorDao.listarId(idOrs)).serialize();
	}

	@Post("/Listar/SubOrdem/")
	public void listarSubOrdem(int idOrs) {
		List<SubOrdem> listarId = subOrdemDao.listarId(idOrs);
		List<SubOrdemView> view = new ArrayList<>();
		for (SubOrdem subOrdem : listarId) {
			SubOrdemView subOrdemView = new SubOrdemView();
			subOrdemView.setId(subOrdem.getId());
			subOrdemView.setIdOrs(idOrs);
			subOrdemView.setDataGerada(Conversor.converterLocalDateTimeParaTimeStamp(subOrdem.getDataGerada()));
			subOrdemView.setDataParaSerExecutada(
					Conversor.converterLocalDateTimeParaTimeStamp(subOrdem.getDataParaSerExecutada()));
			subOrdemView.setJustificativa(subOrdem.getJustificativa());
			subOrdemView.setLoginUsr(subOrdem.getLogin());
			view.add(subOrdemView);
		}
		result.use(Results.json()).withoutRoot().from(view).include("dataGerada").include("dataParaSerExecutada")
				.serialize();

	}

	@Post("/Listar/SubOrdem/Turno/")
	public void listarTurno(int idSor) {
		result.use(Results.json()).withoutRoot().from(subOrdemTurnoDao.listarId(idSor)).serialize();
	}

	@Post("/Listar/SubOrdem/Usuario/")
	public void listarUsuario(int idSor) {
		result.use(Results.json()).withoutRoot().from(subOrdemUsuarioDao.listarId(idSor)).serialize();
	}

	@NivelPermissao(idPermissao = 1)
	@Path("/Criar/Computadores/")
	public void criarPc() {
		List<Sala> salas = salaDao.listar();
		for (Sala sala : salas) {
			for (int i = 1; i <= 36; i++) {
				Computador c = new Computador();
				c.setIdSal(sala.getId());
				c.setNumeroPc(i);
				computadorDao.inserir(c);
			}
		}
		result.redirectTo(this).nova();
	}

	@NivelPermissao(idPermissao = 17)
	@Post("/Ordem/Enviar/")
	public void enviar(@Valid NovaOrdem ordem) {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setAtivo(true);
		ordemServico.setDescricao(ordem.getDescricao());
		ordemServico.setExecutada(false);
		boolean inserir = ordemServicoDao.inserir(ordemServico);
		inserirOrdemServico(ordem, inserir);
	}

	private void inserirOrdemServico(NovaOrdem ordem, boolean inserir) {
		if (inserir) {
			int idOrdemServico = ordemServicoDao.listarUltimo();
			if (ordem.getTipo() == 1) {
				inserir = inserirOrdemServicoComputador(ordem, idOrdemServico);
			} else {
				inserir = inserirOrdemServicoSala(ordem, idOrdemServico);
			}
			inserirSubOrdem(ordem, inserir, idOrdemServico);
		} else {
			result.redirectTo(ErrosController.class).erro_operacao();

		}
	}

	private boolean inserirOrdemServicoSala(NovaOrdem ordem, int idOrdemServico) {
		boolean inserir;
		OrdemServicoSala ordemServicoSala = new OrdemServicoSala();
		ordemServicoSala.setIdOrs(idOrdemServico);
		ordemServicoSala.setIdSala(ordem.getIdTipo());
		inserir = ordemServicoSalaDao.inserir(ordemServicoSala);
		return inserir;
	}

	private boolean inserirOrdemServicoComputador(NovaOrdem ordem, int idOrdemServico) {
		boolean inserir;
		OrdemServicoComputador ordemServicoComputador = new OrdemServicoComputador();
		ordemServicoComputador.setIdOrs(idOrdemServico);
		ordemServicoComputador.setIdSala(ordem.getIdTipo());
		ordemServicoComputador.setNumeroPc(ordem.getIdComputador());
		inserir = ordemServicoComputadorDao.inserir(ordemServicoComputador);
		return inserir;
	}

	private void inserirSubOrdem(NovaOrdem ordem, boolean inserir, int idOrdemServico) {
		if (inserir) {
			SubOrdem subOrdem = new SubOrdem();
			subOrdem.setDataGerada(LocalDateTime.now());
			LocalDate date;
			LocalTime time;
			date = Conversor.converterLocalDate(ordem.getDataExecutada());
			time = Conversor.converterLocalTime(ordem.getDataExecutadaHora());
			subOrdem.setDataParaSerExecutada(LocalDateTime.of(date, time));
			subOrdem.setLogin(usuarioLogado.getUsuario().getLogin());
			subOrdem.setIdOrs(idOrdemServico);
			inserir = subOrdemDao.inserir(subOrdem);
			if (inserir) {
				int idSubOrdem = subOrdemDao.listarUltimo();
				if (ordem.getDestinada() == 1) {
					inserir = inserirSubOrdemTurno(ordem, idSubOrdem);
				} else {
					inserir = inserirSubOrdemUsuario(ordem, idSubOrdem);
				}
				if (inserir) {
					result.redirectTo(HomeController.class).index();
				} else {
					result.redirectTo(ErrosController.class).erro_operacao();
				}
			} else {
				result.redirectTo(ErrosController.class).erro_operacao();
			}
		} else {
			result.redirectTo(ErrosController.class).erro_operacao();
		}
	}

	private boolean inserirSubOrdemUsuario(NovaOrdem ordem, int idSubOrdem) {
		boolean inserir;
		SubOrdemUsuario subOrdemUsuario = new SubOrdemUsuario();
		subOrdemUsuario.setIdSor(idSubOrdem);
		subOrdemUsuario.setLoginUsr(ordem.getIdDestino());
		inserir = subOrdemUsuarioDao.inserir(subOrdemUsuario);
		return inserir;
	}

	private boolean inserirSubOrdemTurno(NovaOrdem ordem, int idSubOrdem) {
		boolean inserir;
		SubOrdemTurno subOrdemTurno = new SubOrdemTurno();
		subOrdemTurno.setIdSor(idSubOrdem);
		List<Turno> listarPeriodo = turnoDao.listarPeriodo(Integer.valueOf(ordem.getIdDestino()));
		if (listarPeriodo.size() > 0) {
			subOrdemTurno.setIdTur(listarPeriodo.get(0).getId());
			inserir = subOrdemTurnoDao.inserir(subOrdemTurno);
		} else {
			inserir = false;
		}
		return inserir;
	}

	/*
	 * EXCLUIR Ordem Serviço
	 */
	@Post("/Excluir/Ordem/")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 19 ou for administrador
	 */
	@NivelPermissao(idPermissao = 19)
	public void excluir(int idOrs) {
		MensagemSistema sistema = new MensagemSistema("");
		OrdemServico ordemServico = ordemServicoDao.listarId(idOrs).get(0);
		ordemServico.setAtivo(false);
		if (ordemServicoDao.atualizar(ordemServico)) {
			sistema.setMensagem("Sucesso ao Excluir Ordem de Serviço!");

		} else {
			sistema.setMensagem("Erro ao Excluir Ordem de Serviço!");
		}
		;
		result.use(Results.json()).withoutRoot().from(sistema).serialize();
	}
	@Post("/Visualizar/SubOrdem/")
	public void visualizar(int idSor) {
		MensagemSistema msg = new MensagemSistema("Sucesso");
		List<SubOrdemVisualizada> listarId = ordemVisualizadaDao.listarId(idSor);
		if (listarId.size() <= 0) {
			SubOrdemVisualizada subOrdemVisualizada = new SubOrdemVisualizada();
			subOrdemVisualizada.setIdSor(idSor);
			subOrdemVisualizada.setLoginUsr(usuarioLogado.getUsuario().getLogin());
			ordemVisualizadaDao.inserir(subOrdemVisualizada);
		}
		result.use(Results.json()).withoutRoot().from(msg).serialize();
	}
	@Post("/Redirecionar/Ordem/")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 18 ou for administrador
	 */
	@NivelPermissao(idPermissao = 19)
	public void redirecionar(NovaOrdemRedirecionada redirecionada) {
		MensagemSistema msg = new MensagemSistema("Sucesso");
		if (finalizarSubOrdemAntiga(redirecionada)) {
			SubOrdem subOrdem = cadastrarNovaSubOrdem(redirecionada);
			boolean inserir = subOrdemDao.inserir(subOrdem);
			if (inserir) {
				inserirNovaSubOrdem(redirecionada, msg);
			} else {
				msg.setMensagem("Erro ao Cadastrar!");
			}
		} else {
			msg.setMensagem("Erro ao Cadastrar!");
		}
		result.use(Results.json()).withoutRoot().from(msg).serialize();
	}

	private SubOrdem cadastrarNovaSubOrdem(NovaOrdemRedirecionada redirecionada) {
		SubOrdem subOrdem = new SubOrdem();
		subOrdem.setDataGerada(LocalDateTime.now());
		LocalDate date;
		LocalTime time;
		date = Conversor.converterLocalDate(redirecionada.getDataExecutada());
		time = Conversor.converterLocalTime(redirecionada.getDataExecutadaHora());
		subOrdem.setDataParaSerExecutada(LocalDateTime.of(date, time));
		subOrdem.setIdOrs(redirecionada.getIdOrs());
		subOrdem.setDataParaSerExecutada(LocalDateTime.of(date, time));
		subOrdem.setLogin(usuarioLogado.getUsuario().getLogin());
		subOrdem.setJustificativa(redirecionada.getJustificativa());
		return subOrdem;
	}

	private void inserirNovaSubOrdem(NovaOrdemRedirecionada redirecionada, MensagemSistema msg) {
		boolean inserir;
		int idSubOrdem = subOrdemDao.listarUltimo();
		NovaOrdem ordem = new NovaOrdem();
		ordem.setIdDestino(redirecionada.getIdDestino());
		if (redirecionada.getDestinada() == 1) {
			inserir = inserirSubOrdemTurno(ordem, idSubOrdem);
		} else {
			inserir = inserirSubOrdemUsuario(ordem, idSubOrdem);
		}
		if (inserir) {
			msg.setMensagem("Erro ao Cadastrar!");
		} else {
			msg.setMensagem("Erro ao Cadastrar!");
		}
	}

	private boolean finalizarSubOrdemAntiga(NovaOrdemRedirecionada redirecionada) {
		SubOrdem subOrdem = subOrdemDao.listarIdSub(redirecionada.getIdSubOrsAntiga()).get(0);
		subOrdem.setDataExecutada(LocalDateTime.now());
		return subOrdemDao.atualizar(subOrdem);
	}
}