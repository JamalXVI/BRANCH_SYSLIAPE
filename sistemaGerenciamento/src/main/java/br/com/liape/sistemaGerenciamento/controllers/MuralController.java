package br.com.liape.sistemaGerenciamento.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.MuralDao;
import br.com.liape.sistemaGerenciamento.dao.MuralTurnoDao;
import br.com.liape.sistemaGerenciamento.dao.MuralVisualizadoDao;
import br.com.liape.sistemaGerenciamento.dao.RecadoDao;
import br.com.liape.sistemaGerenciamento.dao.RecadoUsuarioAlvoDao;
import br.com.liape.sistemaGerenciamento.dao.TurnoDao;
import br.com.liape.sistemaGerenciamento.dao.UsuarioTurnoDao;
import br.com.liape.sistemaGerenciamento.model.Mural;
import br.com.liape.sistemaGerenciamento.model.MuralTurno;
import br.com.liape.sistemaGerenciamento.model.MuralVisualizado;
import br.com.liape.sistemaGerenciamento.model.Recado;
import br.com.liape.sistemaGerenciamento.model.RecadoUsuarioAlvo;
import br.com.liape.sistemaGerenciamento.model.Turno;
import br.com.liape.sistemaGerenciamento.model.UsuarioTurno;
import br.com.liape.sistemaGerenciamento.modelView.Mensagem;
import br.com.liape.sistemaGerenciamento.modelView.MuralUsuario;
import br.com.liape.sistemaGerenciamento.modelView.RecadoUsuario;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class MuralController extends AbstractController{

	private RecadoDao recadoDao;
	private RecadoUsuarioAlvoDao recadoUsuarioAlvoDao;
	private TurnoDao turnoDao;
	private MuralDao muralDao;
	private UsuarioTurnoDao usuarioTurnoDao;
	private MuralTurnoDao muralTurnoDao;
	private MuralVisualizadoDao muralVisualizadoDao;

	@Inject
	public MuralController(RecadoUsuarioAlvoDao recadoUsuarioAlvoDao,
			TurnoDao turnoDao, MuralDao muralDao, UsuarioTurnoDao usuarioTurnoDao,
			MuralTurnoDao muralTurnoDao, MuralVisualizadoDao muralVisualizadoDao) {
		this.recadoUsuarioAlvoDao = recadoUsuarioAlvoDao;
		this.turnoDao = turnoDao;
		this.muralDao = muralDao;
		this.usuarioTurnoDao = usuarioTurnoDao;
		this.muralTurnoDao = muralTurnoDao;
		this.muralVisualizadoDao = muralVisualizadoDao;
	}

	public MuralController() {
		this(null, null, null, null, null, null);
	}

	/*
	 * PÁGINA INICIAL DO PROJETO
	 */
	@NivelPermissao(idPermissao = 16)
	@Path("/Mural/Nova/")
	public void nova() {
		// result.include("msg", "Message from your controller");
	}

	@Path("/Mural/Entrada/")
	public void entrada() {

		// result.include("msg", "Message from your controller");
	}

	@Post("/Mural/Visualizar")
	public void visualizar(int idMur) {
		List<Mural> murais = muralDao.listaId(idMur);
		if (murais.size() > 0) {
			Mural mural = murais.get(0);
			MuralUsuario recado = new MuralUsuario();
			recado.setTitulo(mural.getTitulo());
			recado.setIdMur(mural.getId());
			recado.setTurnoRemetente(retornarNomeTurno(turnoDao.listarId(mural.getIdTurno()).get(0).getPeriodo()));
			recado.setData(Conversor.converterLocalDateTimeParaTimeStamp(mural.getData()));
			recado.setVisualizado(true);
			recado.setDescricao(mural.getDescricao());
			List<MuralVisualizado> visualizado = muralVisualizadoDao.listaId(idMur,
					usuarioLogado.getUsuario().getLogin());
			if (visualizado.size() <= 0) {
				MuralVisualizado muralVisualizado = new MuralVisualizado();
				muralVisualizado.setIdMur(idMur);
				muralVisualizado.setLogin(usuarioLogado.getUsuario().getLogin());
				muralVisualizado.setData(LocalDateTime.now());
				muralVisualizado.setApagado(false);
				muralVisualizadoDao.inserir(muralVisualizado);
			}
			result.include("recado", recado);

		} else {
			result.redirectTo(ErrosController.class).erro_operacao();
		}

	}

	@Post("/Listar/Mural/Usuario/")
	public void listarMensagens() {
		List<Mural> murais_selecionados = new ArrayList<>();
		List<MuralUsuario> recados = new ArrayList<>();
		Turno turno = retornarTurnosUsuarioAtivo();
		if (turno != null) {
			List<MuralTurno> turnosAlvo = muralTurnoDao.listarTurno(turno.getId());
			for (MuralTurno muralTurno : turnosAlvo) {
				List<Mural> murais = muralDao.listaId(muralTurno.getIdMur());
				if (murais.size() > 0) {
					Mural mural = murais.get(0);
					if (muralTurno.isAtivo()) {
						murais_selecionados.add(mural);
					}
				}
			}
			for (Mural mural : murais_selecionados) {
				MuralUsuario muralUsuario = new MuralUsuario();
				List<MuralVisualizado> visualizado = muralVisualizadoDao.listaId(mural.getId(),
						usuarioLogado.getUsuario().getLogin());
				boolean apagado = false;
				if (visualizado.size() > 0) {
					muralUsuario.setVisualizado(true);
					if (visualizado.get(0).isApagado()) {
						apagado = true;
					}
				} else {
					muralUsuario.setVisualizado(false);
				}
				muralUsuario.setTitulo(mural.getTitulo());
				muralUsuario.setTurnoRemetente(
						retornarNomeTurno(turnoDao.listarId(mural.getIdTurno()).get(0).getPeriodo()));
				muralUsuario.setData(Conversor.converterLocalDateTimeParaTimeStamp(mural.getData()));
				muralUsuario.setIdMur(mural.getId());
				if (!apagado) {
					recados.add(muralUsuario);
				}

			}
		}

		result.use(Results.json()).withoutRoot().from(recados).include("data").serialize();
	}

	@Post("/Listar/Mural/Usuario/Novas/")
	public void listarMensagensNovas() {
		List<Mural> murais_selecionados = new ArrayList<>();
		List<MuralUsuario> recados = new ArrayList<>();
		Turno turno = retornarTurnosUsuarioAtivo();
		if (turno != null) {
			List<MuralTurno> turnosAlvo = muralTurnoDao.listarTurno(turno.getId());
			for (MuralTurno muralTurno : turnosAlvo) {
				List<Mural> murais = muralDao.listaId(muralTurno.getIdMur());
				if (murais.size() > 0) {
					Mural mural = murais.get(0);
					if (muralTurno.isAtivo()) {
						murais_selecionados.add(mural);
					}
				}
			}
			for (Mural mural : murais_selecionados) {
				MuralUsuario muralUsuario = new MuralUsuario();
				List<MuralVisualizado> visualizado = muralVisualizadoDao.listaId(mural.getId(),
						usuarioLogado.getUsuario().getLogin());
				boolean apagado = false;
				if (visualizado.size() <= 0) {
					muralUsuario.setVisualizado(false);
					muralUsuario.setTitulo(mural.getTitulo());
					muralUsuario.setTurnoRemetente(
							retornarNomeTurno(turnoDao.listarId(mural.getIdTurno()).get(0).getPeriodo()));
					muralUsuario.setData(Conversor.converterLocalDateTimeParaTimeStamp(mural.getData()));
					muralUsuario.setIdMur(mural.getId());
					if (!apagado) {
						recados.add(muralUsuario);
					}
				}

			}
		}
		result.use(Results.json()).withoutRoot().from(recados).include("data").serialize();
	}

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

	@Post("/Listar/Mural/Usuario/Novas")
	public void listarNovasMensagens() {
		List<RecadoUsuario> recados = new ArrayList<>();
		List<RecadoUsuarioAlvo> listarRecadosUsuario = recadoUsuarioAlvoDao
				.listarNovas(usuarioLogado.getUsuario().getLogin());
		for (RecadoUsuarioAlvo recadoUsuarioAlvo : listarRecadosUsuario) {
			Recado recado = recadoDao.listaId(recadoUsuarioAlvo.getIdRec()).get(0);
			RecadoUsuario recadoUsuario = new RecadoUsuario();
			recadoUsuario.setTitulo(recado.getTitulo());
			recadoUsuario.setIdRec(recado.getId());
			recadoUsuario.setUsuarioRemetente(recado.getLogin());
			recadoUsuario.setData(Conversor.converterLocalDateTimeParaTimeStamp(recado.getData()));
			recadoUsuario.setVisualizado(recadoUsuarioAlvo.isVisualizado());
			recados.add(recadoUsuario);
		}
		result.use(Results.json()).withoutRoot().from(recados).include("data").serialize();
	}

	@Post("/Remover/Mural/")
	public void apagarMensagem(int idMur) {
		MensagemSistema msg = new MensagemSistema("Erro: Impossível Excluir");
		List<Mural> murais = muralDao.listaId(idMur);
		if (murais.size() > 0) {
			Mural mural = murais.get(0);
			List<MuralVisualizado> visualizado = muralVisualizadoDao.listaId(mural.getId(),
					usuarioLogado.getUsuario().getLogin());
			if (visualizado.size() <= 0) {
				MuralVisualizado muralVisualizado = new MuralVisualizado();
				muralVisualizado.setIdMur(idMur);
				muralVisualizado.setLogin(usuarioLogado.getUsuario().getLogin());
				muralVisualizado.setData(LocalDateTime.now());
				muralVisualizado.setApagado(true);
				muralVisualizadoDao.inserir(muralVisualizado);
			} else {
				MuralVisualizado muralVisualizado = visualizado.get(0);
				muralVisualizado.setApagado(true);
				muralVisualizadoDao.atualizar(muralVisualizado);
			}

			msg.setMensagem("Sucesso!");
		}
		result.use(Results.json()).withoutRoot().from(msg).serialize();
	}

	@NivelPermissao(idPermissao = 16)
	@Post("/Mural/Enviar/")
	public void enviar(Mensagem mensagem) {
		if (verificarPreenchimento(mensagem)) {
			Turno turno = retornarTurnosUsuarioAtivo();
			if (turno != null) {
				Mural mural = new Mural();
				mural.setAtivo(true);
				mural.setData(LocalDateTime.now());
				mural.setDescricao(mensagem.getMensagem());
				mural.setIdTurno(turno.getId());
				mural.setTitulo(mensagem.getTitulo());

				if (muralDao.inserir(mural)) {
					String[] paraRecados = mensagem.getPara().split(", |,");
					int idMur = muralDao.listarUltimo();
					for (String recado : paraRecados) {
						if (!recado.toLowerCase().equals("todos")) {
							List<Turno> turnosAlvo = turnoDao.listarPeriodo(retornarHorario(recado));
							if (turnosAlvo.size() > 0) {
								int idTur = turnosAlvo.get(0).getId();
								cadastrarMuralParaTurno(idMur, idTur);
							}
						}else{
							List<Turno> turnos = turnoDao.listar();
							for (Turno turnoAlvo : turnos) {
								cadastrarMuralParaTurno(idMur, turnoAlvo.getId());
							}
						}
						

					}
					result.redirectTo(this).entrada();

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

	private void cadastrarMuralParaTurno(int idMur, int idTur) {
		MuralTurno muralTurno = new MuralTurno();
		muralTurno.setIdTur(idTur);
		muralTurno.setIdMur(idMur);
		muralTurno.setAtivo(true);
		muralTurnoDao.inserir(muralTurno);
	}

	private String retornarNomeTurno(int turno) {
		String nomeTurno = "";
		switch (turno) {
		case 1:
			nomeTurno = "Manhã";
			break;
		case 2:
			nomeTurno = "Tarde";
			break;
		case 3:
			nomeTurno = "Noite";
			break;
		default:
			break;
		}
		return nomeTurno;
	}

	private int retornarHorario(String turno) {
		int valor;
		switch (turno.toLowerCase()) {
		case "tarde":
			valor = 1;
			break;
		case "noite":
			valor = 2;
			break;

		default:
			valor = 0;
			break;
		}
		return valor;
	}

	private boolean verificarPreenchimento(Mensagem mensagem) {
		try {
			return !mensagem.getPara().isEmpty() || mensagem.getPara() != null;
		} catch (Exception e) {
			return false;
		}
	}
}