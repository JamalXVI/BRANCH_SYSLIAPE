package br.com.liape.sistemaGerenciamento.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.dao.GrupoDao;
import br.com.liape.sistemaGerenciamento.dao.GrupoPermissaoDao;
import br.com.liape.sistemaGerenciamento.model.Grupo;
import br.com.liape.sistemaGerenciamento.model.GrupoPermissao;
import br.com.liape.sistemaGerenciamento.model.Permissao;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class GrupoController extends AbstractController {
	private GrupoDao grupoDAO;
	private GrupoPermissaoDao grupoPermissaoDao;

	@Inject
	public GrupoController(GrupoDao grupoDAO, GrupoPermissaoDao grupoPermissaoDao) {
		this.grupoDAO = grupoDAO;
		this.grupoPermissaoDao = grupoPermissaoDao;
	}

	public GrupoController() {
		this(null, null);
	}

	/*
	 * Sessão de Requisições Ajax
	 */
	// LISTA DE TODOS OS GRUPOS
	@Post("/Listar/Grupo")
	public void listar() {
		result.use(Results.json()).withoutRoot().from(grupoDAO.listar()).serialize();
	}
	/*
	 * ENVIO DE CADASTRAMENTO DE GRUPO
	 */
	@Post("/Finalizar/Grupo")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id 3 
	 * ou for administrador
	 */
	@NivelPermissao(idPermissao=3)
	public void postar(@Valid Grupo grupo, List<Permissao> permissoes) {
		if (grupo.getId() == 0) {
			boolean inserir = grupoDAO.inserir(grupo);
			if (inserir) {
				int idGrp = grupoDAO.ultimoId();
				registrarLog(FlagsLogAcao.CADASTRAR_GRUPO.getCodigo(), String.valueOf(idGrp));

				grupo.setId(idGrp);
				inserirPermissoes(grupo, permissoes);
			}
		}else{
			boolean atualizar = grupoDAO.atualizar(grupo);
			if (atualizar) {
				registrarLog(FlagsLogAcao.ATUALIZAR_GRUPO.getCodigo(), String.valueOf(grupo.getId()));
				removerPermissoes(grupo);
				inserirPermissoes(grupo, permissoes);
			}
		}
		result.use(Results.json()).withoutRoot().from(grupoDAO.listar()).serialize();
	}

	private void inserirPermissoes(Grupo grupo, List<Permissao> permissoes) {
		for (Permissao permissao : permissoes) {
			if (permissao.getId() != 1 ||
					(permissao.getId() == 1 && verificarSeAdministrador()) ) {
				GrupoPermissao grupoPermissao = new GrupoPermissao();
				grupoPermissao.setIdGrp(grupo.getId());
				grupoPermissao.setIdPer(permissao.getId());
				grupoPermissaoDao.inserir(grupoPermissao);
			}
			
			
		}
	}
	private boolean verificarSeAdministrador()
	{
		for (Permissao permissao : usuarioLogado.pegarAutorizacao()) {
			if (permissao.getId() == 1) {
				return true;
			}
		}
		return false;
	}
	private boolean removerPermissoes(Grupo grupo) {
		boolean deletar = grupoPermissaoDao.deletar(grupo.getId());
		return deletar;
	}

}
