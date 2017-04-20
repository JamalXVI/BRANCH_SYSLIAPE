package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;
import java.sql.Timestamp;

public class MovimentoArquivoView implements Serializable{
	private static final long serialVersionUID = 9039022494499606517L;
	private String login;
	private Timestamp data;
	private String movimentacao;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	public String getMovimentacao() {
		return movimentacao;
	}
	public void setMovimentacao(String movimentacao) {
		this.movimentacao = movimentacao;
	}
	

}
