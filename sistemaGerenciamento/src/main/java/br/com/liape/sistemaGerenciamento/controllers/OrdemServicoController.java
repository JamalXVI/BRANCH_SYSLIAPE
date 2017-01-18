package br.com.liape.sistemaGerenciamento.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.liape.sistemaGerenciamento.dao.ComputadorDao;
import br.com.liape.sistemaGerenciamento.dao.OrdemServicoComputadorDao;
import br.com.liape.sistemaGerenciamento.dao.OrdemServicoDao;
import br.com.liape.sistemaGerenciamento.dao.OrdemServicoSalaDao;
import br.com.liape.sistemaGerenciamento.dao.SalaDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemTurnoDao;
import br.com.liape.sistemaGerenciamento.dao.SubOrdemUsuarioDao;
import br.com.liape.sistemaGerenciamento.dao.TurnoDao;
import br.com.liape.sistemaGerenciamento.model.Computador;
import br.com.liape.sistemaGerenciamento.model.OrdemServico;
import br.com.liape.sistemaGerenciamento.model.OrdemServicoComputador;
import br.com.liape.sistemaGerenciamento.model.OrdemServicoSala;
import br.com.liape.sistemaGerenciamento.model.Sala;
import br.com.liape.sistemaGerenciamento.model.SubOrdem;
import br.com.liape.sistemaGerenciamento.model.SubOrdemTurno;
import br.com.liape.sistemaGerenciamento.model.SubOrdemUsuario;
import br.com.liape.sistemaGerenciamento.model.Turno;
import br.com.liape.sistemaGerenciamento.modelView.NovaOrdem;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
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
	@Inject
	public OrdemServicoController(Result result, OrdemServicoDao ordemServicoDao,
			SubOrdemDao subOrdemDao, SalaDao salaDao, ComputadorDao computadorDao,
			OrdemServicoComputadorDao ordemServicoComputadorDao,
			OrdemServicoSalaDao ordemServicoSalaDao, UsuarioLogado usuarioLogado,
			SubOrdemTurnoDao subOrdemTurnoDao, SubOrdemUsuarioDao subOrdemUsuarioDao,
			TurnoDao turnoDao) {
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
	}

	public OrdemServicoController() {
		this(null, null, null, null, null, null, null, null, null, null, null);
	}

	@Path("/Cadastro/Ordem/")
	public void nova()
	{
	}
	@Path("/Criar/Computadores/")
	public void criarPc()
	{
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
	@NivelPermissao(idPermissao=17)
	@Post("/Ordem/Enviar/")
	public void enviar(@Valid NovaOrdem ordem)
	{
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
			}else{
				inserir = inserirOrdemServicoSala(ordem, idOrdemServico);
			}
			inserirSubOrdem(ordem, inserir, idOrdemServico);
		}else{
			result.redirectTo(ErrosController.class).erro_operacao();
			
		}
	}

	private boolean inserirOrdemServicoSala(NovaOrdem ordem, int idOrdemServico) {
		boolean inserir;
		OrdemServicoSala ordemServicoSala = new OrdemServicoSala();
		ordemServicoSala.setIdOrs(idOrdemServico);
		ordemServicoSala.setIdSala(ordem.getIdTipo());
		inserir  = ordemServicoSalaDao.inserir(ordemServicoSala);
		return inserir;
	}

	private boolean inserirOrdemServicoComputador(NovaOrdem ordem, int idOrdemServico) {
		boolean inserir;
		OrdemServicoComputador ordemServicoComputador = new OrdemServicoComputador();
		ordemServicoComputador.setIdOrs(idOrdemServico);
		ordemServicoComputador.setIdSala(ordem.getIdTipo());
		ordemServicoComputador.setNumeroPc(ordem.getIdComputador());
		inserir  = ordemServicoComputadorDao.inserir(ordemServicoComputador);
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
				}else{
					inserir = inserirSubOrdemUsuario(ordem, idSubOrdem);
				}
				if (inserir) {
					result.redirectTo(HomeController.class).index();
				}else{
					result.redirectTo(ErrosController.class).erro_operacao();
				}
			}else{
				result.redirectTo(ErrosController.class).erro_operacao();
			}
		}else{
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
		}else{
			inserir = false;
		}
		return inserir;
	}
}