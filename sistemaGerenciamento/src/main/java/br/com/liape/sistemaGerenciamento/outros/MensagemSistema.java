package br.com.liape.sistemaGerenciamento.outros;

import java.io.Serializable;

public class MensagemSistema implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1924559833433017345L;
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public MensagemSistema(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
