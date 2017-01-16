package br.com.liape.sistemaGerenciamento.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.PermissaoDao;

@Controller
public class PermissaoController {
	private Validator validator;
	private Result result;
	private PermissaoDao permissaoDao;

	@Inject
	public PermissaoController(Validator validator, Result result, PermissaoDao permissaoDao) {
		this.validator = validator;
		this.result = result;
		this.permissaoDao = permissaoDao;
	}

	public PermissaoController() {
		this(null, null, null);
	}

	/*
	 * ******SESSÃO DE REQUISIÇÕES AJAX**********
	 */
	// LISTA DE TODAS AS PERMISSÕES
	@Post("/Listar/Permissao")
	public void listar() {
		result.use(Results.json()).withoutRoot().from(permissaoDao.listar()).serialize();
	}

}
