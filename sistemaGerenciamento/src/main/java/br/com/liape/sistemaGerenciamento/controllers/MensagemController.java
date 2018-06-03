package br.com.liape.sistemaGerenciamento.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.FileDownload;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.dao.ArquivosRecadosDAO;
import br.com.liape.sistemaGerenciamento.dao.PessoaDao;
import br.com.liape.sistemaGerenciamento.dao.RecadoDao;
import br.com.liape.sistemaGerenciamento.dao.RecadoUsuarioAlvoDao;
import br.com.liape.sistemaGerenciamento.dao.UsuarioDao;
import br.com.liape.sistemaGerenciamento.infra.Configuracoes;
import br.com.liape.sistemaGerenciamento.model.ArquivosRecados;
import br.com.liape.sistemaGerenciamento.model.ModeloMensagem;
import br.com.liape.sistemaGerenciamento.model.Pessoa;
import br.com.liape.sistemaGerenciamento.model.Recado;
import br.com.liape.sistemaGerenciamento.model.RecadoUsuarioAlvo;
import br.com.liape.sistemaGerenciamento.model.Usuario;
import br.com.liape.sistemaGerenciamento.modelView.Mensagem;
import br.com.liape.sistemaGerenciamento.modelView.RecadoUsuario;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.Email;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.tarefas.EnviarEmail;

@Controller
public class MensagemController extends AbstractController {

	private RecadoDao recadoDao;
	private RecadoUsuarioAlvoDao recadoUsuarioAlvoDao;
	private UsuarioDao usuarioDao;
	private ArquivosRecadosDAO arquivosRecadosDAO;
	private PessoaDao pessoaDao;

	@Inject
	public MensagemController(RecadoDao recadoDao, RecadoUsuarioAlvoDao recadoUsuarioAlvoDao, UsuarioDao usuarioDao,
			ArquivosRecadosDAO arquivosRecadosDAO, PessoaDao pessoaDao) {
		this.recadoDao = recadoDao;
		this.recadoUsuarioAlvoDao = recadoUsuarioAlvoDao;
		this.usuarioDao = usuarioDao;
		this.arquivosRecadosDAO = arquivosRecadosDAO;
		this.pessoaDao = pessoaDao;
	}

	public MensagemController() {
		this(null, null, null, null, null);
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
			List<Recado> recados = recadoDao.listaId(idRec);
			if (recados.get(0).getLogin().equals(usuarioLogado.getUsuario().getLogin())) {
				visualizarRecado(idRec, null);
			}
			for (RecadoUsuarioAlvo recadoUsuarioAlvo : recadosUsuario) {
				if (recadoUsuarioAlvo.getLogin().equals(usuarioLogado.getUsuario().getLogin())) {
					visualizarRecado(idRec, recadoUsuarioAlvo);
				}
			}

		} else

		{
			result.redirectTo(ErrosController.class).erro_operacao();
		}

	}

	private void visualizarRecado(int idRec, RecadoUsuarioAlvo recadoUsuarioAlvo) {
		Recado recado = recadoDao.listaId(idRec).get(0);
		if (recadoUsuarioAlvo != null) {
			recadoUsuarioAlvo.setVisualizado(true);
			recadoUsuarioAlvoDao.atualizar(recadoUsuarioAlvo);
		}
		result.include("recado", recado);
		int idArq = 0;
		List<ArquivosRecados> listaArquivos = arquivosRecadosDAO.listarIdRec(idRec);
		if (listaArquivos.size() > 0) {
			idArq = listaArquivos.get(0).getId();
		}
		result.include("idArq", idArq);
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
	@UploadSizeLimit(sizeLimit = 1024 * 1024 * 1024, fileSizeLimit = 1024 * 1024 * 1024)
	public void enviar(Mensagem mensagem, UploadedFile file) {

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
				criarArquivoRecado(idRecado, file);
				registrarLog(FlagsLogAcao.CADASTRAR_MENSAGEM.getCodigo(), String.valueOf(idRecado));
				String[] paraRecados = mensagem.getPara().split(", |,");
				List<String> usuariosEnviados = new ArrayList<>();
				for (String paraRecado : paraRecados) {
					if (!paraRecado.toLowerCase().equals("todos")) {
						List<Usuario> logins = usuarioDao.listarPorLogin(paraRecado);
						if (logins.size() > 0) {
							Usuario usuario = logins.get(0);
							List<Pessoa> pessoas = pessoaDao.listarPorId(usuario.getIdPes());
							if(pessoas.size() > 0){
								usuario.setPessoa(pessoas.get(0));
								if (!usuariosEnviados.contains(usuario.getLogin()) && usuario.getPessoa().getAtivo()) {
									String login = usuario.getLogin();
									enviarEmailMensagem(recado, usuario);
									novoRecadoUsuarioAlvo(idRecado, usuariosEnviados, login);
								}
							}

						}
					} else {
						List<Usuario> usuarios = usuarioDao.listar();
						for (Usuario usuario : usuarios) {
							enviarEmailMensagem(recado, usuario);
							novoRecadoUsuarioAlvo(idRecado, usuariosEnviados, usuario.getLogin());
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

	private void enviarEmailMensagem(Recado rec, Usuario loginAlvo) {
		List<Pessoa> pessoas = pessoaDao.listarPorId(loginAlvo.getIdPes());
		if (pessoas.size() > 0) {
			Pessoa pessoa = pessoas.get(0);
			HashMap<String, String> mapa = new HashMap<>();
			mapa.put("usuario", pessoa.getNome() + " " + pessoa.getSobrenome());
			mapa.put("remetente", usuarioLogado.getUsuario().getPessoa().getNome() + " "
					+ usuarioLogado.getUsuario().getPessoa().getSobrenome());
			mapa.put("titulo", rec.getTitulo());
			mapa.put("descricao", rec.getDescricao());
			String url = Configuracoes.configuracao.getUrlSite()+"Mensagens/Entrada/";
			mapa.put("urlSite", url);
			ModeloMensagem modelo = Email.RecebeuMensagemPessoal;
			String email = pessoa.getEmail();
			ExecutorService executorService = Executors.newSingleThreadExecutor();

			executorService.execute(new EnviarEmail(modelo, mapa, email));
			executorService.shutdown();
		}
	}

	private void criarArquivoRecado(int idRecado, UploadedFile file) {
		if (file != null) {
			org.joda.time.LocalDateTime agora = org.joda.time.LocalDateTime.now();
			// DEFINE O DESTINO DO CAMINHO
			String destino = definirUriDoCaminho(agora);
			// CRIA O FILE DO DESTINO PARA GERAR AS PASTAS
			File f = new File(destino);
			// FAZ A ARQUIVAÇÃO DO ARQUIVO
			String resultado = criarArquivo(file, destino, f);
			if (resultado != null) {
				ArquivosRecados arquivosRecados = new ArquivosRecados();
				arquivosRecados.setAtivo(true);
				arquivosRecados.setCaminho(resultado);
				arquivosRecados.setIdRec(idRecado);
				arquivosRecadosDAO.inserir(arquivosRecados);
			}
		}
	}

	private String criarArquivo(UploadedFile file, String destino, File f) {
		boolean mkdirs = f.mkdirs();
		if (mkdirs) {
			File arquivo = new File(destino, file.getFileName());

			try {
				IOUtils.copy(file.getFile(), new FileOutputStream(arquivo));
				return arquivo.getAbsolutePath();
			} catch (IOException e) {
				throw new RuntimeException("Erro ao copiar imagem", e);
			}
		}
		return null;
	}

	private String definirUriDoCaminho(org.joda.time.LocalDateTime agora) {
		return Configuracoes.configuracao.getCaminhoSalvarArquivo() + String.valueOf(agora.getYear()) + "/"
				+ String.valueOf(agora.getMonthOfYear()) + "/" + String.valueOf(agora.getDayOfMonth()) + "/"
				+ String.valueOf(agora.getHourOfDay()) + String.valueOf(agora.getMinuteOfHour())
				+ String.valueOf(agora.getSecondOfMinute());
	}

	private void novoRecadoUsuarioAlvo(int idRecado, List<String> usuariosEnviados, String login) {
		RecadoUsuarioAlvo recadoUsuarioAlvo = new RecadoUsuarioAlvo();
		recadoUsuarioAlvo.setApagado(false);
		recadoUsuarioAlvo.setVisualizado(false);
		recadoUsuarioAlvo.setLogin(login);
		recadoUsuarioAlvo.setIdRec(idRecado);
		usuariosEnviados.add(login);
		recadoUsuarioAlvoDao.inserir(recadoUsuarioAlvo);
	}

	private boolean verificarPreenchimento(Mensagem mensagem) {
		try {
			return !mensagem.getPara().isEmpty() || mensagem.getPara() == null;
		} catch (Exception e) {
			return false;
		}
	}

	@Get("/Mensagens/Anexo/Download/{id}")
	public Download download(int id) throws FileNotFoundException {
		List<ArquivosRecados> listaId = arquivosRecadosDAO.listarId(id);
		File file = null;
		if (listaId.size() > 0) {
			ArquivosRecados arquivo = listaId.get(0);
			int idRec = arquivo.getIdRec();
			List<RecadoUsuarioAlvo> usuarios = recadoUsuarioAlvoDao.listarRecadosUsuario(idRec);
			for (RecadoUsuarioAlvo recadoUsuarioAlvo : usuarios) {
				if (recadoUsuarioAlvo.getLogin().equals(usuarioLogado.getUsuario().getLogin())) {
					file = new File(arquivo.getCaminho());
					return new FileDownload(file, "Arquivo");
				}
			}
			List<Recado> recados = recadoDao.listaId(idRec);
			if (recados.size() > 0 && file == null) {
				if (usuarioLogado.getUsuario().getLogin().equals(recados.get(0).getLogin())) {
					file = new File(arquivo.getCaminho());
					return new FileDownload(file, "Arquivo");
				}
			}
		}
		if (file == null) {
			result.redirectTo(ErrosController.class).erro_acesso();
		}
		return null;
	}
}