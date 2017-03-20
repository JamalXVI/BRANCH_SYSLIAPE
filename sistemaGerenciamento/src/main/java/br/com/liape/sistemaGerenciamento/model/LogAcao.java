package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.DateTime;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "LOG_ACAO")
public class LogAcao implements Serializable{
	private static final long serialVersionUID = 6427047366988726080L;
	@Coluna(nome="ID_LOA")
	@NotNull
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome="TIPO_LOA")
	private int tipo;
	@Coluna(nome="LOGIN_USR")
	private String usuario;
	@Coluna(nome="DETALHE_LOA")
	private String detalhe;
	
	@Coluna(nome="DATA_LOA")
	@DateTime
	private LocalDateTime data;
	
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	
}
