package br.com.liape.sistemaGerenciamento.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.GrupoDao;
import br.com.liape.sistemaGerenciamento.dao.GrupoPermissaoDao;
import br.com.liape.sistemaGerenciamento.model.Grupo;
import br.com.liape.sistemaGerenciamento.model.GrupoPermissao;
import br.com.liape.sistemaGerenciamento.model.Permissao;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;
import br.com.liape.sistemaGerenciamento.seguranca.UsuarioLogado;

@Controller
public class GrupoController {
	private Validator validator;
	private GrupoDao grupoDAO;
	private Result result;
	private GrupoPermissaoDao grupoPermissaoDao;
	private UsuarioLogado usr;

	@Inject
	public GrupoController(Validator validator, GrupoDao grupoDAO,
			GrupoPermissaoDao grupoPermissaoDao, Result result, UsuarioLogado usr) {
		this.validator = validator;
		this.grupoDAO = grupoDAO;
		this.grupoPermissaoDao = grupoPermissaoDao;
		this.result = result;
		this.usr = usr;
	}

	public GrupoController() {
		this(null, null, null, null, null);
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

				grupo.setId(idGrp);
				inserirPermissoes(grupo, permissoes);
			}
		}else{
			boolean atualizar = grupoDAO.atualizar(grupo);
			if (atualizar) {
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
		for (Permissao permissao : usr.pegarAutorizacao()) {
			if (permissao.getId() == 1) {
				return true;
			}
		}
		return false;
	}
	private void removerPermissoes(Grupo grupo) {
		boolean deletar = grupoPermissaoDao.deletar(grupo.getId());
	}

}
