package br.com.liape.sistemaGerenciamento.adServerModel;

import java.io.Serializable;

public class Pessoa implements Serializable {
	private static final long serialVersionUID = -6847439837930001922L;
	private String Codigo;
	private String Nome;
	
	public Pessoa(String Codigo, String Nome){
		
		this.Codigo = Codigo;
		this.Nome = Nome;
	}
	
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
}
