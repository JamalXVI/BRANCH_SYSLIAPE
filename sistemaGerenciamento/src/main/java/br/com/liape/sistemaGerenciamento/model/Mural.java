package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.DateTime;
import br.com.unaerp.jdbc.anotation.Tabela;
@Tabela(nome = "Mural")
public class Mural implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1326235878606879102L;
	@Coluna(nome="ID_MUR")
	@NotNull
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome="DATA_REGISTRO_MUR")
	@DateTime
	private LocalDateTime data;	
	@Coluna(nome="DESCRICAO_MUR")
	private String descricao;
	@Coluna(nome="TITULO_MUR")
	private String titulo;
	@Coluna(nome="ID_TUR")
	private int idTurno;
	@Coluna(nome="ATIVO_MUR")
	private boolean ativo;
	
	
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
}
