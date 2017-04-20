package br.com.liape.sistemaGerenciamento.controllers;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.dao.CursoDao;
import br.com.liape.sistemaGerenciamento.model.Curso;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class CursoController extends AbstractController {
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
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 6 ou for administrador
	 */
	@NivelPermissao(idPermissao = 6)
	public void postar(@Valid Curso curso) {
		if (cursoDao.pesquisarCodCurso(curso.getCodigo()).size() > 0) {
			if (cursoDao.atualizar(curso)) {
				registrarLog(FlagsLogAcao.ATUALIZAR_CURSO.getCodigo(), curso.getCodigo());
				result.use(Results.json()).withoutRoot().from(cursoDao.listar()).serialize();
			}
		} else {
			if (cursoDao.inserir(curso)) {
				registrarLog(FlagsLogAcao.CADASTRAR_CURSO.getCodigo(), curso.getCodigo());
				result.use(Results.json()).withoutRoot().from(cursoDao.listar()).serialize();
			}
		}

	}

	// LISTA TODOS OS CURSOS
	@Post("/Listar/Curso/")
	public void listar() {
		List<Curso> cursos = cursoDao.listar();
		Collections.sort(cursos);
		result.use(Results.json()).withoutRoot().from(cursos).serialize();

	}
}
