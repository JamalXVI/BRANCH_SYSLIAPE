package br.com.liape.sistemaGerenciamento.model;

import java.time.LocalDateTime;
import br.com.unaerp.jdbc.anotation.*;

@Tabela(nome = "CHAMADO")
public class Chamado {

	@Chave
	@Coluna(nome="NUMERO_CHM")
	private int numeroChamado;
	@Coluna(nome="SERVICOS_CHM")
	private String servico;
	@Coluna(nome="DESCRICAO_CHM")
	private String descricao;
	@DateTime
	@Coluna(nome="DATA_GERADO_CHM")
	private LocalDateTime dataGerado;
	@DateTime
	@Coluna(nome="DATA_CHAMADO_CHM")
	private LocalDateTime dataChamado;
	@Coluna(nome="LOGIN_USR")
	private String loginUsuario;
	
	public Chamado() {
		this.numeroChamado = 0;
		this.servico = "";
		this.descricao = "";
		this.dataChamado = null;
		this.dataGerado = null;
		this.loginUsuario = "";
	}

	public int getNumeroChamado() {
		return numeroChamado;
	}

	public void setNumeroChamado(int numeroChamado) {
		this.numeroChamado = numeroChamado;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataGerado() {
		return dataGerado;
	}

	public void setDataGerado(LocalDateTime dataGerado) {
		this.dataGerado = dataGerado;
	}

	public LocalDateTime getDataChamado() {
		return dataChamado;
	}

	public void setDataChamado(LocalDateTime dataChamado) {
		this.dataChamado = dataChamado;
	}

	public String getLoginUsuario() {
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}
	
}