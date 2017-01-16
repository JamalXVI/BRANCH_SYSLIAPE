package br.com.liape.sistemaGerenciamento.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.CursoDao;
import br.com.liape.sistemaGerenciamento.dao.DisciplinaDao;
import br.com.liape.sistemaGerenciamento.model.Curso;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class CursoController {
	private Validator validator;
	private Result result;
	private CursoDao cursoDao;

	@Inject
	public CursoController(Validator validator, Result result, CursoDao cursoDao) {
		this.validator = validator;
		this.result = result;
		this.cursoDao = cursoDao;
	}

	public CursoController() {
		this(null, null, null);
	}

	/*
	 * SEÇÃO DE REQUISIÇÕES AJAX
	 */
	

	// ENVIO DE CADASTRO DE ANO/SEMESTRE
	@Post("/Cadastro/Curso/")

	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id 6 
	 * ou for administrador
	 */
	@NivelPermissao(idPermissao=6)
	public void postar(@Valid Curso curso) {
		System.out.println(curso.getNome());
		if (cursoDao.inserir(curso)) {
			result.use(Results.json()).withoutRoot().from(cursoDao.listar()).serialize();
		}
		
	}

	// LISTA TODOS OS ANOS/SEMESTRES
	@Post("/Listar/Curso/")
	public void listar() {
		result.use(Results.json()).withoutRoot().from(cursoDao.listar()).serialize();

	}
}
