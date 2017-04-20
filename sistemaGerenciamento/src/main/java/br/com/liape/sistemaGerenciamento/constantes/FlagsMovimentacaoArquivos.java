package br.com.liape.sistemaGerenciamento.constantes;

public enum FlagsMovimentacaoArquivos {
	INSERIR(0, "Inserido"), 
	ALTERAR(1, "Alterado"),
	DELETAR(2, "Deletado");
	
	private int codigo;
	private String nome;

	private FlagsMovimentacaoArquivos(int codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public static FlagsMovimentacaoArquivos getEnum(int codigo){
		for(FlagsMovimentacaoArquivos flag:FlagsMovimentacaoArquivos.values()){
			if(codigo == flag.getCodigo()){
				return flag;
			}
		}
		return null;
	}

}
