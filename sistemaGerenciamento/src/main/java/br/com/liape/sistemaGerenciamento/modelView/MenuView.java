package br.com.liape.sistemaGerenciamento.modelView;

import java.io.Serializable;

public class MenuView implements Serializable, Comparable<MenuView>{
	private static final long serialVersionUID = -4256885862517689750L;
	private int numero;
	private int ordem;
	private String nome;
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getOrdem() {
		return ordem;
	}
	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public int compareTo(MenuView o) {
		return this.getOrdem() > o.getOrdem() ? 1 : -1;
	}
	
}
