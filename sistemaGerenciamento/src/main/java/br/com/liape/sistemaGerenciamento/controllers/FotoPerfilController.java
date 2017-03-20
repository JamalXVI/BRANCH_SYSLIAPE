package br.com.liape.sistemaGerenciamento.controllers;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.observer.upload.UploadSizeLimit;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.dao.FotoPerfilDao;
import br.com.liape.sistemaGerenciamento.model.FotoPerfil;
import br.com.liape.sistemaGerenciamento.outros.TransformaImagem;

@Controller
public class FotoPerfilController extends AbstractController{
	private FotoPerfilDao fotoPerfilDao;
	private TransformaImagem transformaImagem;

	@Inject
	public FotoPerfilController(FotoPerfilDao fotoPerfilDao,
			TransformaImagem transformaImagem) {
		this.fotoPerfilDao = fotoPerfilDao;
		this.transformaImagem = transformaImagem;
	}

	public FotoPerfilController() {
		this(null, null);
	}

	/*
	 * Sessão de Requisições Ajax
	 */
	// ENVIO DE CADASTRAMENTO DE UMA NOVA FOTO DE PERFIL
	@Post("/Cadastro/Foto/")
	@UploadSizeLimit(sizeLimit = 40 * 1024 * 1024, fileSizeLimit = 10 * 1024 * 1024)
	public void postar(UploadedFile foto) {
		FotoPerfil fotoPerfil = new FotoPerfil();
		fotoPerfil.setId_pes(usuarioLogado.getUsuario().getIdPes());
		fotoPerfil.setFoto(transformaImagem.renderizarImagem(foto));
		if (fotoPerfilDao.inserir(fotoPerfil)) {
			registrarLog(FlagsLogAcao.CADASTRAR_FOTOPERFIL.getCodigo(), usuarioLogado.getUsuario().getLogin());
			result.redirectTo(HomeController.class).index();
		}

	}

	/*
	 * FAZER A BUSCA DA FOTO DE PERFIL
	 */
	@Path("/Imagem/Usuario/{idPessoa}")
	public void imagem(int idPessoa) {
		List<FotoPerfil> fotos = fotoPerfilDao.listarPorIdPes(idPessoa);
		result.use(Results.json()).withoutRoot().from(transformaImagem.retornarImagem(fotos, idPessoa)).serialize();

	}

}
