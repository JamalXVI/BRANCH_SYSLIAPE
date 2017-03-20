package br.com.liape.sistemaGerenciamento.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.dao.RecadoDao;
import br.com.liape.sistemaGerenciamento.dao.RecadoUsuarioAlvoDao;
import br.com.liape.sistemaGerenciamento.dao.UsuarioDao;
import br.com.liape.sistemaGerenciamento.model.Recado;
import br.com.liape.sistemaGerenciamento.model.RecadoUsuarioAlvo;
import br.com.liape.sistemaGerenciamento.model.Usuario;
import br.com.liape.sistemaGerenciamento.modelView.Mensagem;
import br.com.liape.sistemaGerenciamento.modelView.RecadoUsuario;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;

@Controller
public class MensagemController extends AbstractController{

	private RecadoDao recadoDao;
	private RecadoUsuarioAlvoDao recadoUsuarioAlvoDao;
	private UsuarioDao usuarioDao;

	@Inject
	public MensagemController(RecadoDao recadoDao, RecadoUsuarioAlvoDao recadoUsuarioAlvoDao,
			UsuarioDao usuarioDao) {
		this.recadoDao = recadoDao;
		this.recadoUsuarioAlvoDao = recadoUsuarioAlvoDao;
		this.usuarioDao = usuarioDao;
	}

	public MensagemController() {
		this(null, null, null);
	}

	/*
	 * PÁGINA INICIAL DO PROJETO
	 */
	@Path("/Mensagens/Nova/")
	public void nova() {
		// result.include("msg", "Message from your controller");
	}

	@Path("/Mensagens/Entrada/")
	public void entrada() {

		// result.include("msg", "Message from your controller");
	}

	@Path("/Mensagens/Enviados/")
	public void enviados() {

		// result.include("msg", "Message from your controller");
	}

	@Post("/Mensagens/Visualizar")
	public void visualizar(int idRec) {
		List<RecadoUsuarioAlvo> recadosUsuario = recadoUsuarioAlvoDao.listarRecadosUsuario(idRec);
		if (recadosUsuario.size() > 0) {
			registrarLog(FlagsLogAcao.VISUALIZAR_MENSAGEM.getCodigo(), String.valueOf(idRec));
			for (RecadoUsuarioAlvo recadoUsuarioAlvo : recadosUsuario) {
				if (recadoUsuarioAlvo.getLogin().equals(usuarioLogado.getUsuario().getLogin())) {
					Recado recado = recadoDao.listaId(idRec).get(0);
					recadoUsuarioAlvo.setVisualizado(true);
					recadoUsuarioAlvoDao.atualizar(recadoUsuarioAlvo);
					result.include("recado", recado);
				}
			}
			
		} else {
			result.redirectTo(ErrosController.class).erro_operacao();
		}

	}

	@Post("/Listar/Mensagens/Usuario/")
	public void listarMensagens() {
		List<RecadoUsuario> recados = new ArrayList<>();
		List<RecadoUsuarioAlvo> listarRecadosUsuario = recadoUsuarioAlvoDao
				.listarRecadosUsuario(usuarioLogado.getUsuario().getLogin());
		for (RecadoUsuarioAlvo recadoUsuarioAlvo : listarRecadosUsuario) {
			Recado recado = recadoDao.listaId(recadoUsuarioAlvo.getIdRec()).get(0);
			RecadoUsuario recadoUsuario = new RecadoUsuario();
			recadoUsuario.setTitulo(recado.getTitulo());
			recadoUsuario.setIdRec(recadoUsuarioAlvo.getIdRec());
			recadoUsuario.setUsuarioRemetente(recado.getLogin());
			recadoUsuario.setData(Conversor.converterLocalDateTimeParaTimeStamp(recado.getData()));
			recadoUsuario.setVisualizado(recadoUsuarioAlvo.isVisualizado());
			recados.add(recadoUsuario);
		}
		result.use(Results.json()).withoutRoot().from(recados).include("data").serialize();
	}

	@Post("/Listar/Mensagens/Usuario/Enviadas/")
	public void caixaEntrada() {
		List<RecadoUsuario> recados = new ArrayList<>();
		List<Recado> listarUsuario = recadoDao.listarUsuario(usuarioLogado.getUsuario().getLogin());
		for (Recado recado : listarUsuario) {
			RecadoUsuario recadoUsuario = new RecadoUsuario();
			recadoUsuario.setTitulo(recado.getTitulo());
			recadoUsuario.setIdRec(recado.getId());
			recadoUsuario.setUsuarioRemetente(recado.getLogin());
			recadoUsuario.setData(Conversor.converterLocalDateTimeParaTimeStamp(recado.getData()));
			recadoUsuario.setVisualizado(true);
			recados.add(recadoUsuario);
		}
		result.use(Results.json()).withoutRoot().from(recados).include("data").serialize();
	}

	@Post("/Listar/Mensagens/Usuario/Novas")
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

	@Post("/Remover/Mensagens/")
	public void apagarMensagem(int idRec) {
		List<RecadoUsuarioAlvo> listarRecadosUsuario = recadoUsuarioAlvoDao.listarRecadosUsuario(idRec);
		MensagemSistema msg = new MensagemSistema("Erro: Impossível Excluir");
		for (RecadoUsuarioAlvo recadoUsuarioAlvo : listarRecadosUsuario) {
			if (recadoUsuarioAlvo.getLogin().equals(usuarioLogado.getUsuario().getLogin())) {
				recadoUsuarioAlvo.setApagado(true);
				recadoUsuarioAlvoDao.atualizar(recadoUsuarioAlvo);
				registrarLog(FlagsLogAcao.REMOVER_ENTRADA_MENSAGEM.getCodigo(), String.valueOf(idRec));
				msg.setMensagem("Sucesso!");
			}
		}
		result.use(Results.json()).withoutRoot().from(msg).serialize();
	}

	@Post("/Mensagens/Remover/Enviado/")
	public void apagarMensagemEnviada(int idRec) {
		MensagemSistema msg = new MensagemSistema("Erro: Impossível Excluir");
		List<Recado> listaId = recadoDao.listaId(idRec);
		if (listaId.size() > 0) {
			Recado recado = listaId.get(0);
			recado.setAtivo(false);
			recadoDao.atualizar(recado);
			registrarLog(FlagsLogAcao.REMOVER_ENVIADO_MENSAGEM.getCodigo(), String.valueOf(idRec));
			msg.setMensagem("Sucesso!");
		}
		result.use(Results.json()).withoutRoot().from(msg).serialize();
	}

	@Post("/Mensagens/Enviar/")
	public void enviar(Mensagem mensagem) {
		if (verificarPreenchimento(mensagem)) {
			Recado recado = new Recado();
			recado.setData(LocalDateTime.now());
			recado.setDescricao(mensagem.getMensagem());
			recado.setAtivo(true);
			recado.setTitulo(mensagem.getTitulo());
			recado.setLogin(usuarioLogado.getUsuario().getLogin());
			boolean inserir = recadoDao.inserir(recado);
			if (inserir) {
				int idRecado = recadoDao.listarUltimo();
				registrarLog(FlagsLogAcao.CADASTRAR_MENSAGEM.getCodigo(), String.valueOf(idRecado));
				String[] paraRecados = mensagem.getPara().split(", |,");
				List<String> usuariosEnviados = new ArrayList<>();
				for (String paraRecado : paraRecados) {
					List<Usuario> logins = usuarioDao.listarPorLogin(paraRecado);
					if (logins.size() > 0) {
						Usuario usuario = logins.get(0);
						if (!usuariosEnviados.contains(usuario.getLogin())) {
							RecadoUsuarioAlvo recadoUsuarioAlvo = new RecadoUsuarioAlvo();
							recadoUsuarioAlvo.setApagado(false);
							recadoUsuarioAlvo.setVisualizado(false);
							recadoUsuarioAlvo.setLogin(usuario.getLogin());
							recadoUsuarioAlvo.setIdRec(idRecado);
							usuariosEnviados.add(usuario.getLogin());
							recadoUsuarioAlvoDao.inserir(recadoUsuarioAlvo);
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
		// result.include("msg", "Message from your controller");
	}

	private boolean verificarPreenchimento(Mensagem mensagem) {
		try {
			return !mensagem.getPara().isEmpty() || mensagem.getPara() == null;
		} catch (Exception e) {
			return false;
		}
	}
}