package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "menu_usuario_item")
@XStreamAlias("itemMenu")
public class MenuUsuarioItem implements Serializable, Comparable<MenuUsuarioItem> {
	private static final long serialVersionUID = 5365447692428499477L;
	@Coluna(nome="ID_MUI")
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome="ID_MU")
	private int idMenu;
	@Coluna(nome="NUMERO_MUI")
	@XStreamAlias("numero")
	private int numero;
	@Coluna(nome="ORDEM_MUI")
	@XStreamAlias("ordem")
	private int ordem;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdMenu() {
		return idMenu;
	}
	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}
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
	@Override
	public int compareTo(MenuUsuarioItem o) {
		return this.getOrdem() > o.getOrdem() ? 1 : -1;
	}
	
}
