package br.com.liape.sistemaGerenciamento.model;

import java.awt.image.BufferedImage;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Image;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "foto_perfil")
public class FotoPerfil {
	@Image
	@Coluna(nome = "FOTO_FOT")
	private BufferedImage foto;
	@Coluna(nome = "ID_FOT")
	@Chave
	@AutoIncrement
	private int id;
	@Coluna(nome = "ID_PES")
	private int id_pes;

	public BufferedImage getFoto() {
		return foto;
	}

	public void setFoto(BufferedImage foto) {
		this.foto = foto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_pes() {
		return id_pes;
	}

	public void setId_pes(int id_pes) {
		this.id_pes = id_pes;
	}
	
}
