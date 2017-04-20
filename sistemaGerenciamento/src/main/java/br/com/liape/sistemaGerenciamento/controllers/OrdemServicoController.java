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
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.dao.ComputadorDao;
import br.com.liape.sistemaGerenciamento.dao.GrupoDao;
import br.com.liape.sistemaGerenciamento.dao.GrupoPermissaoDao;
import br.com.liape.sistemaGerenciamento.dao.OrdemServicoComputadorDao;
import br.com.liape.sistemaGerenciamento.dao.OrdemServicoDao;
import br.com.liape.sistemaGerenciamento.dao.OrdemServicoSalaDao;
import br.com.liape.sistemaGerenciamento.dao.SalaDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemTurnoDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemUsuarioDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemVisualizadaDao;
import br.com.liape.sistemaGerenciamento.dao.TurnoDao;
import br.com.liape.sistemaGerenciamento.dao.UsuarioTurnoDao;
import br.com.liape.sistemaGerenciamento.model.Computador;
import br.com.liape.sistemaGerenciamento.model.Grupo;
import br.com.liape.sistemaGerenciamento.model.GrupoPermissao;
import br.com.liape.sistemaGerenciamento.model.OrdemServico;
import br.com.liape.sistemaGerenciamento.model.OrdemServicoComputador;
import br.com.liape.sistemaGerenciamento.model.OrdemServicoSala;
import br.com.liape.sistemaGerenciamento.model.Sala;
import br.com.liape.sistemaGerenciamento.model.SubOrdem;
import br.com.liape.sistemaGerenciamento.model.SubOrdemTurno;
import br.com.liape.sistemaGerenciamento.model.SubOrdemUsuario;
import br.com.liape.sistemaGerenciamento.model.SubOrdemVisualizada;
import br.com.liape.sistemaGerenciamento.model.Turno;
import br.com.liape.sistemaGerenciamento.model.UsuarioTurno;
import br.com.liape.sistemaGerenciamento.modelView.EditarOrdemView;
import br.com.liape.sistemaGerenciamento.modelView.NovaOrdem;
import br.com.liape.sistemaGerenciamento.modelView.NovaOrdemRedirecionada;
import br.com.liape.sistemaGerenciamento.modelView.SubOrdemView;
import br.com.liape.sistemaGerenciamento.modelView.VisualizarSubOrdem;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class OrdemServicoController extends AbstractController {

	private OrdemServicoDao ordemServicoDao;
	private SubOrdemDao subOrdemDao;
	private SalaDao salaDao;
	private ComputadorDao computadorDao;
	private OrdemServicoComputadorDao ordemServicoComputadorDao;
	private OrdemServicoSalaDao ordemServicoSalaDao;
	private SubOrdemTurnoDao subOrdemTurnoDao;
	private SubOrdemUsuarioDao subOrdemUsuarioDao;
	private TurnoDao turnoDao;
	private SubOrdemVisualizadaDao ordemVisualizadaDao;
	private UsuarioTurnoDao usuarioTurnoDao;
	private GrupoDao grupoDao;
	private GrupoPermissaoDao grupoPermissaoDao;

	@Inject
	public OrdemServicoController(OrdemServicoDao ordemServicoDao, SubOrdemDao subOrdemDao,
			SalaDao salaDao, ComputadorDao computadorDao, OrdemServicoComputadorDao ordemServicoComputadorDao,
			OrdemServicoSalaDao ordemServicoSalaDao, SubOrdemTurnoDao subOrdemTurnoDao,
			SubOrdemUsuarioDao subOrdemUsuarioDao, TurnoDao turnoDao, SubOrdemVisualizadaDao ordemVisualizadaDao,
			UsuarioTurnoDao usuarioTurnoDao, GrupoDao grupoDao, GrupoPermissaoDao grupoPermissaoDao) {
		this.ordemServicoDao = ordemServicoDao;
		this.subOrdemDao = subOrdemDao;
		this.salaDao = salaDao;
		this.computadorDao = computadorDao;
		this.ordemServicoComputadorDao = ordemServicoComputadorDao;
		this.ordemServicoSalaDao = ordemServicoSalaDao;
		this.subOrdemTurnoDao = subOrdemTurnoDao;
		this.subOrdemUsuarioDao = subOrdemUsuarioDao;
		this.turnoDao = turnoDao;
		this.ordemVisualizadaDao = ordemVisualizadaDao;
		this.usuarioTurnoDao = usuarioTurnoDao;
		this.grupoDao = grupoDao;
		this.grupoPermissaoDao = grupoPermissaoDao;
	}

	public OrdemServicoController() {
		this(null, null, null, null, null, null, null, null, null, null, null, null, null);
	}

	// CADASTRAR NOVA ORDEM DE SERVIÇO
	@Path("/Cadastro/Ordem/")
	@NivelPermissao(idPermissao = 17)
	public void nova() {
	}

	// LISTAR TODAS AS ORDEMS ATIVAS QUE NÃO FORAM EXECUTADAS
	@Path("/Listar/Ordem/Ativas/")
	public void listarAtivas() {
	}

	// LISTAR TODAS AS ORDEMS ATIVAS E NÃO EXECUTAS E SERIALIZAR EM JSON
	@Post("/Listar/Ordem/")
	public void listar() {
		result.use(Results.json()).withoutRoot().from(ordemServicoDao.listarExecucao(false))
		.exclude("descricao").serialize();

	}

	// LISTAR TODAS AS ORDEMS DE SERVIÇOS DIRECIONADAS PARA SALA
	@Post("/Listar/Ordem/Sala/")
	public void listarSala(int idOrs) {
		result.use(Results.json()).withoutRoot().from(ordemServicoSalaDao.listarId(idOrs)).serialize();
	}

	// LISTAR A ORDEM SERVIÇO COMPUTADOR DE UMA ORDEM DE SERVIÇO
	@Post("/Listar/Ordem/Computador/")
	public void listarComputador(int idOrs) {
		result.use(Results.json()).withoutRoot().from(ordemServicoComputadorDao.listarId(idOrs)).serialize();
	}

	// LISTAR A SUBORDEM ATIVA DE UMA ORDEM.
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
			subOrdemView.setLoginUsr(subOrdem.getLogin());
			view.add(subOrdemView);
		}
		result.use(Results.json()).withoutRoot().from(view).include("dataGerada").include("dataParaSerExecutada")
				.serialize();

	}

	@Post("/Listar/SubOrdem/Usuario/Ativo")
	public void listarParaUsuarioAtivo() {
		Turno turno = retornarTurnosUsuarioAtivo();
		List<SubOrdemView> view = new ArrayList<>();
		if (turno != null) {
			List<SubOrdemTurno> subOrdens = subOrdemTurnoDao.listarIdTurno(turno.getId());
			if (subOrdens.size() > 0) {
				for (SubOrdemTurno subOrdemTurno : subOrdens) {
					List<SubOrdem> listarIdSubEsp = subOrdemDao.listarIdSubEsp(subOrdemTurno.getIdSor());
					if (listarIdSubEsp != null) {
						if (listarIdSubEsp.size() > 0) {
							SubOrdem subOrdem = listarIdSubEsp.get(0);
							if (ordemVisualizadaDao
									.listarIdLogin(subOrdem.getId(), usuarioLogado.getUsuario().getLogin())
									.size() <= 0) {
								adicionarView(view, subOrdem);
							}

						}
					}

				}
			}
		}
		List<SubOrdemUsuario> subOrdens = subOrdemUsuarioDao.listarLogin(usuarioLogado.getUsuario().getLogin());
		if (subOrdens.size() > 0) {
			for (SubOrdemUsuario subOrdemUsuario : subOrdens) {
				List<SubOrdem> listarIdSubEsp = subOrdemDao.listarIdSubEsp(subOrdemUsuario.getIdSor());
				if (listarIdSubEsp != null) {
					if (listarIdSubEsp.size() > 0) {
						SubOrdem subOrdem = listarIdSubEsp.get(0);
						if (ordemVisualizadaDao.listarIdLogin(subOrdem.getId(), usuarioLogado.getUsuario().getLogin())
								.size() <= 0) {
							adicionarView(view, subOrdem);
						}
					}
				}
			}
		}
		result.use(Results.json()).withoutRoot().from(view).include("dataGerada").include("dataParaSerExecutada")
				.serialize();
	}

	// LISTAR SUBORDEM TURNO DE UMA SUBORDEM
	@Post("/Listar/SubOrdem/Turno/")
	public void listarTurno(int idSor) {
		result.use(Results.json()).withoutRoot().from(subOrdemTurnoDao.listarId(idSor)).serialize();
	}

	// LISTAR SUBORDEM USUÁRIO DE UMA SUBORDEM
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

	// ENVIAR PARA CADASTRAMENTO UMA ORDEM DE SERVIÇO
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
		if (excluirOrdemServico(idOrs)) {
			registrarLog(FlagsLogAcao.REMOVER_ORDEM.getCodigo(), String.valueOf(idOrs));
			sistema.setMensagem("Sucesso ao Excluir Ordem de Serviço!");

		} else {
			sistema.setMensagem("Erro ao Excluir Ordem de Serviço!");
		}
		;
		result.use(Results.json()).withoutRoot().from(sistema).serialize();
	}

	/*
	 * MARCAR ORDEM DE SERVIÇO COMO FINALIZADA
	 */
	@Post("/Finalizar/Ordem/")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 19 ou for administrador
	 */
	@NivelPermissao(idPermissao = 17)
	public void finalizar(int idSor) {
		finalizarSubOrdem(idSor);
	}

	@Post("/Finalizar/Ordem/Usuario/")
	public void finalizarUsuario(int idSor) {

		List<SubOrdemUsuario> subUsr = subOrdemUsuarioDao.listarId(idSor);
		if (subUsr.size() > 0) {
			SubOrdemUsuario subOrdemUsuario = subUsr.get(0);
			if (subOrdemUsuario.getLoginUsr().equals(usuarioLogado.getUsuario().getLogin())) {
				finalizarSubOrdem(idSor);
			} else {
				result.redirectTo(ErrosController.class).erro_operacao();
			}
		} else {
			result.redirectTo(ErrosController.class).erro_operacao();
		}
	}

	/*
	 * VISUALIZAR ORDEM DE SERVIÇO
	 */
	@Post("/Visualizar/SubOrdem/")
	public void visualizar(int idSor) {
		MensagemSistema msg = new MensagemSistema("Sucesso");
		List<SubOrdemVisualizada> listarId = ordemVisualizadaDao.listarIdLogin(idSor
				, usuarioLogado.getUsuario().getLogin());
		if (listarId.size() <= 0) {
			SubOrdemVisualizada subOrdemVisualizada = new SubOrdemVisualizada();
			subOrdemVisualizada.setIdSor(idSor);
			subOrdemVisualizada.setLoginUsr(usuarioLogado.getUsuario().getLogin());
			boolean inserir = ordemVisualizadaDao.inserir(subOrdemVisualizada);
			if (inserir) {
				registrarLog(FlagsLogAcao.VISUALIZAR_SUBORDEM.getCodigo(), String.valueOf(idSor));
			}
			
			
		}
		List<SubOrdem> listarIdSub = subOrdemDao.listarIdSub(idSor);
		if (listarIdSub.size() > 0) {
			SubOrdem subOrdem = listarIdSub.get(0);
			OrdemServico ordemServico = ordemServicoDao.listarId(subOrdem.getIdOrs()).get(0);
			VisualizarSubOrdem visualizarSubOrdem = new VisualizarSubOrdem();
			visualizarSubOrdem.setDescricao(ordemServico.getDescricao());
			visualizarSubOrdem.setJustificativa(subOrdem.getJustificativa());
			visualizarSubOrdem.setLogin(subOrdem.getLogin());
			result.include("ordem", visualizarSubOrdem);
			
		}else{
			result.redirectTo(ErrosController.class).erro_operacao();
		}
		
	}

	/*
	 * REDIRECIONAR ORDEM DE SERVIÇO PARA OUTRO TURNO
	 */
	@Post("/Redirecionar/Ordem/")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 19 ou for administrador
	 */
	@NivelPermissao(idPermissao = 19)
	public void redirecionar(NovaOrdemRedirecionada redirecionada) {
		redirecionarOrdem(redirecionada);
	}

	@Post("/Redirecionar/Ordem/Usuario/")
	public void redirecionarOrdemUsuario(NovaOrdemRedirecionada redirecionada) {
		List<SubOrdemUsuario> subUsr = subOrdemUsuarioDao.listarId(redirecionada.getIdSubOrsAntiga());
		if (subUsr.size() > 0) {
			SubOrdemUsuario subOrdemUsuario = subUsr.get(0);
			if (subOrdemUsuario.getLoginUsr().equals(usuarioLogado.getUsuario().getLogin())) {
				redirecionarOrdem(redirecionada);
			} else {
				result.redirectTo(ErrosController.class).erro_operacao();
			}
		} else {
			result.redirectTo(ErrosController.class).erro_operacao();
		}
	}

	@Post("/Editar/Ordem/")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 19 ou for administrador
	 */
	@NivelPermissao(idPermissao = 19)
	public void editarOrdem(int idOrs) {
		result.include("editandoOrdem", true);
		result.include("editarId", idOrs);
		result.redirectTo(this).nova();
	}

	@Post("/Editando/Ordem/")
	@NivelPermissao(idPermissao = 19)
	public void editandoOrdem(int idOrs) {
		EditarOrdemView editarOrdemView = new EditarOrdemView();
		List<OrdemServicoSala> orSalas = ordemServicoSalaDao.listarId(idOrs);
		if (orSalas.size() > 0) {
			OrdemServicoSala ordemServicoSala = orSalas.get(0);
			editarOrdemView.setEhSala(true);
			editarOrdemView.setIdSala(String.valueOf(ordemServicoSala.getIdSala()));
		} else {
			List<OrdemServicoComputador> orComputadores = ordemServicoComputadorDao.listarId(idOrs);
			if (orComputadores.size() > 0) {
				OrdemServicoComputador ordemServicoComputador = orComputadores.get(0);
				editarOrdemView.setEhSala(false);
				editarOrdemView.setIdSala(String.valueOf(ordemServicoComputador.getIdSala()));
				editarOrdemView.setIdComputador(String.valueOf(ordemServicoComputador.getNumeroPc()));

			}
		}
		List<SubOrdem> subOrdens = subOrdemDao.listarIdSubEspOrs(idOrs);
		if (subOrdens.size() > 0) {
			SubOrdem subOrdem = subOrdens.get(0);
			editarOrdemView.setDataParaSerExecutada(
					Conversor.converterLocalDateTimeParaTimeStamp(subOrdem.getDataParaSerExecutada()));
			List<SubOrdemUsuario> subUsuarios = subOrdemUsuarioDao.listarId(subOrdem.getId());
			if (subUsuarios.size() > 0) {
				SubOrdemUsuario subOrdemUsuario = subUsuarios.get(0);
				editarOrdemView.setEhUsuario(true);
				editarOrdemView.setAlvo(subOrdemUsuario.getLoginUsr());
			} else {
				List<SubOrdemTurno> subTurnos = subOrdemTurnoDao.listarId(subOrdem.getId());
				if (subTurnos.size() > 0) {
					SubOrdemTurno subOrdemTurno = subTurnos.get(0);
					editarOrdemView.setEhUsuario(false);
					int periodo = turnoDao.listarId(subOrdemTurno.getIdTur()).get(0).getPeriodo();
					editarOrdemView.setAlvo(String.valueOf(periodo));
				}
			}

		}
		OrdemServico ordemServico = ordemServicoDao.listarId(idOrs).get(0);
		editarOrdemView.setDescricao(ordemServico.getDescricao());
		if (!excluirOrdemServico(idOrs)) {
			result.redirectTo(ErrosController.class).erro_operacao();
		}
		result.use(Results.json()).withoutRoot().from(editarOrdemView)
		.include("dataParaSerExecutada").serialize();
	}
	/*
	 *******************************************************
	 *******************************************************
	 ************* MÉTODOS PRIVADOS*************************
	 *******************************************************
	 *******************************************************
	 */


	// ADICIONAR SUBORDEM NA VIEW COM UMA OUTRA CLASSE
	private void adicionarView(List<SubOrdemView> view, SubOrdem subOrdem) {
		SubOrdemView subOrdemView = new SubOrdemView();
		subOrdemView.setId(subOrdem.getId());
		subOrdemView.setIdOrs(subOrdem.getIdOrs());
		subOrdemView.setDataGerada(Conversor.converterLocalDateTimeParaTimeStamp(subOrdem.getDataGerada()));
		subOrdemView.setDataParaSerExecutada(
				Conversor.converterLocalDateTimeParaTimeStamp(subOrdem.getDataParaSerExecutada()));
		view.add(subOrdemView);
	}

	// RETORNAR TUDO PARA USUÁRIO ATIVO
	private Turno retornarTurnosUsuarioAtivo() {
		List<UsuarioTurno> listarLogin = usuarioTurnoDao.listarLogin(usuarioLogado.getUsuario().getLogin());
		for (UsuarioTurno usuarioTurno : listarLogin) {
			Turno turno = turnoDao.listarId(usuarioTurno.getIdTur()).get(0);
			if (turno.isAtivo()) {
				return turno;
			}
		}
		return null;
	}

	// INSERIR TIPOS DE ORDEMS DE SERVIÇO - RAMIFICAÇÃO DO ENVIAR
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

	// INSERIR ORDEM DE SERVIÇO DE UMA SALA - RAMIFICAÇÃO DE inserirOrdemServico
	private boolean inserirOrdemServicoSala(NovaOrdem ordem, int idOrdemServico) {
		boolean inserir;
		OrdemServicoSala ordemServicoSala = new OrdemServicoSala();
		ordemServicoSala.setIdOrs(idOrdemServico);
		ordemServicoSala.setIdSala(ordem.getIdTipo());
		inserir = ordemServicoSalaDao.inserir(ordemServicoSala);
		if (inserir) {
			registrarLog(FlagsLogAcao.CADASTRAR_ORDEM_SALA.getCodigo(), String.valueOf(idOrdemServico));
		}
		return inserir;
	}

	// INSERIR ORDEM DE SERVIÇO DE UM COMPUTADOR - REMIFACAÇÃO DE
	// inserirOrdemServico
	private boolean inserirOrdemServicoComputador(NovaOrdem ordem, int idOrdemServico) {
		boolean inserir;
		OrdemServicoComputador ordemServicoComputador = new OrdemServicoComputador();
		ordemServicoComputador.setIdOrs(idOrdemServico);
		ordemServicoComputador.setIdSala(ordem.getIdTipo());
		ordemServicoComputador.setNumeroPc(ordem.getIdComputador());
		inserir = ordemServicoComputadorDao.inserir(ordemServicoComputador);
		if (inserir) {
			registrarLog(FlagsLogAcao.CADASTRAR_ORDEM_COMPUTADOR.getCodigo(), String.valueOf(idOrdemServico));
		}
		return inserir;
	}

	// INSERIR SUB ORDEM DE SERVIÇO - RAMIFICAÇÃO DE inserirOrdemServico
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

	// INSERIR SUBORDEM DE USUÁRIO - RAMIFICAÇÃO DE inserirSubOrdem
	private boolean inserirSubOrdemUsuario(NovaOrdem ordem, int idSubOrdem) {
		boolean inserir;
		SubOrdemUsuario subOrdemUsuario = new SubOrdemUsuario();
		subOrdemUsuario.setIdSor(idSubOrdem);
		subOrdemUsuario.setLoginUsr(ordem.getIdDestino());
		inserir = subOrdemUsuarioDao.inserir(subOrdemUsuario);
		if (inserir) {
			registrarLog(FlagsLogAcao.CADASTRAR_SUBORDEM_USUARIO.getCodigo(), String.valueOf(idSubOrdem));
		}
		return inserir;
	}

	// INSERIR SUBORDEM DE TURNO - RAMIFICAÇÃO DE inserirSubOrdem
	private boolean inserirSubOrdemTurno(NovaOrdem ordem, int idSubOrdem) {
		boolean inserir;
		SubOrdemTurno subOrdemTurno = new SubOrdemTurno();
		subOrdemTurno.setIdSor(idSubOrdem);
		List<Turno> listarPeriodo = turnoDao.listarPeriodo(Integer.valueOf(ordem.getIdDestino()));
		if (listarPeriodo.size() > 0) {
			subOrdemTurno.setIdTur(listarPeriodo.get(0).getId());
			inserir = subOrdemTurnoDao.inserir(subOrdemTurno);
			if (inserir) {
				registrarLog(FlagsLogAcao.CADASTRAR_SUBORDEM_TURNO.getCodigo(), String.valueOf(idSubOrdem));
			}
		} else {
			inserir = false;
		}
		return inserir;
	}

	private boolean excluirOrdemServico(int idOrs) {
		OrdemServico ordemServico = ordemServicoDao.listarId(idOrs).get(0);
		ordemServico.setAtivo(false);
		boolean atualizou = ordemServicoDao.atualizar(ordemServico);
		if (atualizou) {

		}
		return atualizou;
	}

	private void redirecionarOrdem(NovaOrdemRedirecionada redirecionada) {
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

	private boolean usuarioAutorizadoRepassar(SubOrdem subOrdem) {
		boolean passou = false;
		List<Grupo> grupos = grupoDao.listarPorId(usuarioLogado.getUsuario().getIdGrupo());
		if (grupos.size() > 0) {
			Grupo grupo = grupos.get(0);
			for (GrupoPermissao permissao : grupoPermissaoDao.listarPermissoesGrupo(grupo.getId())) {
				if (permissao.getIdPer() == 1) {
					passou = true;
				}
			}

		}
		List<SubOrdemTurno> subOrdemTurnos = subOrdemTurnoDao.listarId(subOrdem.getId());
		if (subOrdemTurnos.size() > 0) {
			passou = verificarSeEDoTurno(passou, subOrdemTurnos);
		} else {
			passou = true;
		}

		return passou;
	}

	private void finalizarSubOrdem(int idSor) {
		MensagemSistema sistema = new MensagemSistema("");
		SubOrdem subOrdem = subOrdemDao.listarIdSub(idSor).get(0);
		subOrdem.setDataExecutada(LocalDateTime.now());
		OrdemServico ordemServico = ordemServicoDao.listarId(subOrdem.getIdOrs()).get(0);
		ordemServico.setAtivo(true);
		ordemServico.setExecutada(true);
		if (ordemServicoDao.atualizar(ordemServico) && subOrdemDao.atualizar(subOrdem)) {
			registrarLog(FlagsLogAcao.FINAlIZAR_ORDEM.getCodigo(), String.valueOf(ordemServico.getId()));
			sistema.setMensagem("Sucesso ao Excluir Ordem de Serviço!");

		} else {
			sistema.setMensagem("Erro ao Excluir Ordem de Serviço!");
		}
		;
		result.use(Results.json()).withoutRoot().from(sistema).serialize();
	}

	private boolean verificarSeEDoTurno(boolean passou, List<SubOrdemTurno> subOrdemTurnos) {
		SubOrdemTurno subOrdemTurno = subOrdemTurnos.get(0);
		List<UsuarioTurno> usuariosTurnos = usuarioTurnoDao.listarLogin(usuarioLogado.getUsuario().getLogin());
		if (usuariosTurnos.size() > 0) {
			for (UsuarioTurno usuarioTurno : usuariosTurnos) {
				if (usuarioTurno.getIdTur() == subOrdemTurno.getIdTur()) {
					passou = true;
				}

			}
		}
		return passou;
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
		} else {
			msg.setMensagem("Erro ao Cadastrar!");
		}
	}

	private boolean finalizarSubOrdemAntiga(NovaOrdemRedirecionada redirecionada) {

		SubOrdem subOrdem = subOrdemDao.listarIdSub(redirecionada.getIdSubOrsAntiga()).get(0);
		if (usuarioAutorizadoRepassar(subOrdem)) {
			subOrdem.setDataExecutada(LocalDateTime.now());
			
			boolean atualizar = subOrdemDao.atualizar(subOrdem);
			if (atualizar) {
				registrarLog(FlagsLogAcao.FINALIZAR_SUBORDEM.getCodigo(), String.valueOf(subOrdem.getId()));
			}
			return atualizar;
		}
		return false;

	}
}