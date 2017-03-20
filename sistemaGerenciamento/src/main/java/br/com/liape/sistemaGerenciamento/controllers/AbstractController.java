package br.com.liape.sistemaGerenciamento.controllers;

import java.time.LocalDateTime;

import javax.inject.Inject;

import br.com.caelum.vraptor.Result;
import br.com.liape.sistemaGerenciamento.dao.LogAcaoDao;
import br.com.liape.sistemaGerenciamento.model.LogAcao;
import br.com.liape.sistemaGerenciamento.seguranca.UsuarioLogado;

public abstract class AbstractController {

	// CLASSES DE USO COMUM
	@Inject
	protected Result result;
	@Inject
	protected LogAcaoDao logAcaoDao;
	@Inject
	protected UsuarioLogado usuarioLogado;

	protected boolean registrarLog(int tipoLog, String detalhe) {
		LogAcao logAcao = new LogAcao();
		logAcao.setData(LocalDateTime.now());
		logAcao.setUsuario(usuarioLogado.getUsuario().getLogin());
		logAcao.setTipo(tipoLog);
		logAcao.setDetalhe(detalhe);
		return logAcaoDao.inserir(logAcao);
	}

}
