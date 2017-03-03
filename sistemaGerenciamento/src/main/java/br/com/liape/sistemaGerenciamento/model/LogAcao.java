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
	private static final long serialVersionUID = -4330611508514036886L;
	@Coluna(nome="ID_LOA")
	@NotNull
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome="DESCRICAO_LOA")
	private String descricao;
	@Coluna(nome="DATA_LOA")
	@DateTime
	private LocalDateTime data;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	
}
