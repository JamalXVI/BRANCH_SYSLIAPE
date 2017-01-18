package br.com.liape.sistemaGerenciamento.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.AnoSemestreDao;
import br.com.liape.sistemaGerenciamento.dao.ComputadorDao;
import br.com.liape.sistemaGerenciamento.model.AnoSemestre;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class ComputadorController {
	private Validator validator;
	private Result result;
	private ComputadorDao computadorDao;

	@Inject
	public ComputadorController(Validator validator, Result result, ComputadorDao computadorDao) {
		this.validator = validator;
		this.result = result;
		this.computadorDao = computadorDao;
	}

	public ComputadorController() {
		this(null, null, null);
	}

	/*
	 * SEÇÃO DE REQUISIÇÕES AJAX
	 */
	
	// LISTA TODOS OS ANOS/SEMESTRES
	@Post("/Listar/Computadores/")
	public void listar() {
		result.use(Results.json()).withoutRoot().from(computadorDao.listar()).serialize();

	}
}
