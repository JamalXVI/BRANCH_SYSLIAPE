package br.com.liape.sistemaGerenciamento.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.joda.time.LocalDateTime;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.observer.download.FileDownload;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.constantes.FlagsMovimentacaoArquivos;
import br.com.liape.sistemaGerenciamento.dao.ArquivosDAO;
import br.com.liape.sistemaGerenciamento.dao.MovimentoArquivosDAO;
import br.com.liape.sistemaGerenciamento.infra.Configuracoes;
import br.com.liape.sistemaGerenciamento.model.Arquivos;
import br.com.liape.sistemaGerenciamento.model.MovimentoArquivos;
import br.com.liape.sistemaGerenciamento.modelView.MovimentoArquivoView;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;

@Controller
public class ArquivosController  extends AbstractController {

	private ArquivosDAO arquivosDAO;
	private Result result;
	private MovimentoArquivosDAO movimentoArquivosDAO;
	@Inject
	public ArquivosController(Result result, ArquivosDAO arquivosDAO, MovimentoArquivosDAO movimentoArquivosDAO) {
		this.result = result;
		this.arquivosDAO = arquivosDAO;
		this.movimentoArquivosDAO = movimentoArquivosDAO;
	}
	public ArquivosController() {
		this(null, null, null);
	}
	@Path("/Arquivos/")
	public void index() {
	}
	@Path("/Arquivos/Novo/")
	public void novo() {
	}
	
	@Post("/Listar/Arquivos/")
	public void listar()
	{
		  List<Arquivos> listar = arquivosDAO.listarAtivo(true);
		  result.use(Results.json()).withoutRoot().from(listar).serialize();
	}
	
	
	@Get("/Arquivos/Download/{id}")
	public Download download(int id) throws FileNotFoundException{
		List<Arquivos> arquivos = arquivosDAO.listar();
		File file = null;
		for (Arquivos arquivo : arquivos) {
			if (arquivo.getId() == id) {
				file = new File(arquivo.getCaminho());
			}
		}
		return new FileDownload(file, "Arquivo");
	}
	@Get("/Arquivos/Editar/{id}")
	public void editar(int id)
	{
		List<Arquivos> arquivos = arquivosDAO.listarId(id);
		if (arquivos.size() > 0) {
			Arquivos arquivo = arquivos.get(0);
			result.include("idArq",arquivo.getId());
			result.include("tituloArq",arquivo.getTitulo());
			result.include("descricaoArq",arquivo.getDescricao());
			result.redirectTo(this).novo();
		}else{
			result.redirectTo(ErrosController.class).erro_operacao();
		}
		
	}
	@Get("/Arquivos/Historico/{id}")
	public void historico(int id)
	{
		List<Arquivos> arquivos = arquivosDAO.listarId(id);
		if (arquivos.size() > 0) {
			Arquivos arquivo = arquivos.get(0);
			List<MovimentoArquivos> movimentacoes = movimentoArquivosDAO.listarIdArq(arquivo.getId());
			List<MovimentoArquivoView> movimentos = new ArrayList<>();
			for (MovimentoArquivos movimentoArquivos : movimentacoes) {
				MovimentoArquivoView movimentoArquivoView = new MovimentoArquivoView();
				movimentoArquivoView.setLogin(movimentoArquivos.getLoginUsr());
				movimentoArquivoView.setMovimentacao(
				FlagsMovimentacaoArquivos.getEnum(movimentoArquivos.getOperacao()).getNome()		
				);
				movimentoArquivoView.setData(
					Conversor.converterLocalDateTimeParaTimeStamp(movimentoArquivos.getData())
				);
				movimentos.add(movimentoArquivoView);
				
			}
			result.include("movimentacoes", movimentos);
		}else{
			result.redirectTo(ErrosController.class).erro_operacao();
		}
	}
	@Post("/Arquivos/Remover/")
	public void remover(int id)
	{
		MensagemSistema mensagemSistema = new MensagemSistema("Erro: Ao Apagar Arquivo");
		List<Arquivos> arquivos = arquivosDAO.listarId(id);
		if (arquivos.size() > 0) {
			Arquivos arquivo = arquivos.get(0);
			arquivo.setAtivo(false);
			boolean atualizar = arquivosDAO.atualizar(arquivo);
			if (atualizar) {
				mensagemSistema.setMensagem("Sucesso!");
				registrarLog(FlagsLogAcao.REMOVER_ARQUIVO.getCodigo(), arquivo.getCaminho());
				criarMovimentacao(arquivo.getId(), FlagsMovimentacaoArquivos.DELETAR.getCodigo());
			}
		}
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
	@Post("/Arquivos/Enviar/")
	@UploadSizeLimit(sizeLimit=1024 * 1024 * 1024, fileSizeLimit=1024 * 1024 * 1024)
	public void enviar(UploadedFile file, Arquivos arquivos) {
		MensagemSistema mensagemSistema = new MensagemSistema("Erro: Ao Criar Arquivo");
		LocalDateTime agora = LocalDateTime.now();
		//DEFINE O DESTINO DO CAMINHO
		String destino = definirUriDoCaminho(agora);
		//CRIA O FILE DO DESTINO PARA GERAR AS PASTAS
		File f = new File(destino);
		//FAZ A ARQUIVAÇÃO DO ARQUIVO
		String resultado =criarArquivo(file, destino, f);
		if (resultado != null) {
			//DEFINE O CAMINHO DO ARQUIVO NO MODELO
			arquivos.setCaminho(resultado);
			if (arquivos.getId() != 0) {
				atualizarArquivo(arquivos, mensagemSistema, resultado);
			}else{
				inserirArquivo(arquivos, mensagemSistema, resultado);
			}
		}
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
	private void inserirArquivo(Arquivos arquivos, MensagemSistema mensagemSistema, String resultado) {
		arquivos.setAtivo(true);
		boolean inserir = arquivosDAO.inserir(arquivos);
		registrarLog(FlagsLogAcao.CADASTRAR_ARQUIVO.getCodigo(), resultado);
		if (inserir) {
			mensagemSistema.setMensagem("Sucesso!");
			int ultimoId = arquivosDAO.ultimoId();
			criarMovimentacao(ultimoId, FlagsMovimentacaoArquivos.INSERIR.getCodigo());
		}
	}
	private void criarMovimentacao(int ultimoId, int movimentacao) {
		MovimentoArquivos movimentoArquivos = new MovimentoArquivos();
		movimentoArquivos.setIdArq(ultimoId);
		movimentoArquivos.setLoginUsr(usuarioLogado.getUsuario().getLogin());
		movimentoArquivos.setOperacao(movimentacao);
		movimentoArquivos.setData(java.time.LocalDateTime.now());
		movimentoArquivosDAO.inserir(movimentoArquivos);
	}
	private void atualizarArquivo(Arquivos arquivos, MensagemSistema mensagemSistema, String resultado) {
		arquivos.setAtivo(true);
		boolean atualizar = arquivosDAO.atualizar(arquivos);
		registrarLog(FlagsLogAcao.ATUALIZAR_ARQUIVO.getCodigo(), resultado);
		if (atualizar) {
			mensagemSistema.setMensagem("Sucesso!");
			criarMovimentacao(arquivos.getId(), FlagsMovimentacaoArquivos.ALTERAR.getCodigo());
		}
	}
	private String definirUriDoCaminho(LocalDateTime agora) {
		return Configuracoes.configuracao.getCaminhoSalvarArquivo()+String.valueOf(agora.getYear())
		+"/"+String.valueOf(agora.getMonthOfYear())+"/"+String.valueOf(agora.getDayOfMonth())+"/"
		+String.valueOf(agora.getHourOfDay())+String.valueOf(agora.getMinuteOfHour())
		+String.valueOf(agora.getSecondOfMinute());
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
}