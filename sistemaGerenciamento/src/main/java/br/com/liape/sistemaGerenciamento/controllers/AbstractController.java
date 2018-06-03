package br.com.liape.sistemaGerenciamento.controllers;

import java.net.Authenticator;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.inject.Inject;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.simplemail.Mailer;
import br.com.liape.sistemaGerenciamento.dao.LogAcaoDao;
import br.com.liape.sistemaGerenciamento.model.LogAcao;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.UsuarioLogado;
public abstract class AbstractController {

	// CLASSES DE USO COMUM
	@Inject
	protected Result result;
	@Inject
	protected LogAcaoDao logAcaoDao;
	@Inject
	protected UsuarioLogado usuarioLogado;
	@Inject
	private Mailer mailer;
	
	protected boolean enviarEmail(String mensagem)
	{
		Email mail = new SimpleEmail();
		Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
		return false;
	}
	
	protected boolean registrarLog(int tipoLog, String detalhe) {
		LogAcao logAcao = new LogAcao();
		logAcao.setData(LocalDateTime.now());
		logAcao.setUsuario(usuarioLogado.getUsuario().getLogin());
		logAcao.setTipo(tipoLog);
		logAcao.setDetalhe(detalhe);
		return logAcaoDao.inserir(logAcao);
	}
	protected boolean verificarSeNaoTemErroMensagemSistema(MensagemSistema msg){
		return (!msg.getMensagem().contains("Erro") ?  true : false);
	}
}
