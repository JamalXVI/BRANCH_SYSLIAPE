package br.com.liape.sistemaGerenciamento.model;

import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome="grupo_permissao")
public class GrupoPermissao {
	@Chave
	@Coluna(nome="ID_GRP")
	private int idGrp;
	@Chave
	@Coluna(nome="ID_PER")
	private int idPer;
	public int getIdGrp() {
		return idGrp;
	}
	public void setIdGrp(int idGrp) {
		this.idGrp = idGrp;
	}
	public int getIdPer() {
		return idPer;
	}
	public void setIdPer(int idPer) {
		this.idPer = idPer;
	}
	
}
