package br.com.liape.sistemaGerenciamento.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.SalaDao;
import br.com.liape.sistemaGerenciamento.model.Sala;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class SalaController {
	private Validator validator;
	private Result result;
	private SalaDao salaDao;

	@Inject
	public SalaController(Validator validator, Result result, SalaDao salaDao) {
		this.validator = validator;
		this.result = result;
		this.salaDao = salaDao;
	}

	public SalaController() {
		this(null, null, null);
	}

	/*
	 * SEÇÃO DE REQUISIÇÕES AJAX
	 */
	/*
	 * ENVIO DE CADASTRO DE SALAS
	 */
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id 9 
	 * ou for administrador
	 */
	@NivelPermissao(idPermissao=9)
	@Post("/Cadastro/Sala/")
	public void postar(Sala sala) {
		boolean inserir = salaDao.inserir(sala);
		if (inserir) {
			result.use(Results.json()).withoutRoot().from(salaDao.listarNome()).serialize();
		}
	}

	/*
	 * LISTA TODOS AS SALAS
	 */
	@Post("/Listar/Sala/")
	public void listar() {
		result.use(Results.json()).withoutRoot().from(salaDao.listarNome()).serialize();

	}
}
