package br.com.liape.sistemaGerenciamento.constantes;

public enum FlagsSalasLiape {
	AULA_NORMAL(0),
	SALA_LIVRE(1),
	MANUTENCAO(2);
	private int codigo;
	private FlagsSalasLiape(int codigo)
	{
		this.codigo = codigo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
