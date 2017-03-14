package br.com.liape.sistemaGerenciamento.controllers;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.AnoSemestreDao;
import br.com.liape.sistemaGerenciamento.dao.DisciplinaDao;
import br.com.liape.sistemaGerenciamento.model.AnoSemestre;
import br.com.liape.sistemaGerenciamento.model.Disciplina;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class DisciplinaController {
	private Validator validator;
	private Result result;
	private DisciplinaDao disciplinaDao;

	@Inject
	public DisciplinaController(Validator validator, Result result, DisciplinaDao disciplinaDao) {
		this.validator = validator;
		this.result = result;
		this.disciplinaDao = disciplinaDao;
	}

	public DisciplinaController() {
		this(null, null, null);
	}
	@Path("/Disciplina/")
	public void index()
	{
		
	}
	/*
	 * SEÇÃO DE REQUISIÇÕES AJAX
	 */
	

	// ENVIO DE CADASTRO DE ANO/SEMESTRE
	@Post("/Cadastro/Disciplina/")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id 8 
	 * ou for administrador
	 */
	@NivelPermissao(idPermissao=8)
	
	public void postar(@Valid Disciplina disciplina) {
		if (disciplinaDao.pesquisarDisciplinaCod(disciplina.getCodigo()).size() <= 0) {
			if (disciplinaDao.inserir(disciplina)) {
				result.use(Results.json()).withoutRoot().from(disciplinaDao.listar()).serialize();
			}
		}else{
			if (disciplinaDao.atualizar(disciplina)) {
				result.use(Results.json()).withoutRoot().from(disciplinaDao.listar()).serialize();
			}
		}
		
	}

	// LISTA TODOS OS ANOS/SEMESTRES
	@Post("/Listar/Disciplina/")
	public void listar() {
		List<Disciplina> disciplinas = disciplinaDao.listar();
		Collections.sort(disciplinas);
		result.use(Results.json()).withoutRoot().from(disciplinas).serialize();

	}
}
