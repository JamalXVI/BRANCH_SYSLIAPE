package br.com.liape.sistemaGerenciamento.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.LogAcaoDao;
import br.com.liape.sistemaGerenciamento.model.LogAcao;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.UsuarioLogado;

@Controller
public class CadastroAdController {

	private Result result;
	private LogAcaoDao logAcaoDao;
	private UsuarioLogado usuarioLogado;
	@Inject
	public CadastroAdController(LogAcaoDao logAcaoDao, UsuarioLogado usuarioLogado, Result result ) {
		this.logAcaoDao = logAcaoDao;
		this.usuarioLogado = usuarioLogado;
		this.result = result;
	}
	public CadastroAdController() {
		this(null, null, null);
	}
	@Path("/CadastroAd/")
	public void index() {

	}
}