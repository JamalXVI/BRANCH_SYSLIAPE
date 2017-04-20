package br.com.liape.sistemaGerenciamento.constantes;

public enum FlagsMenuSysLiape {
	CADASTROS(0, "Cadastros"),
	ESCALAS(1, "Escalas"),
	MENSAGENS(2, "Mensagens"),
	PENDENCIAS(3, "Pendências"),
	RESERVAS(4, "Reservas"),
	TURNO(5, "Turnos"),
	OUTROS(6, "Outros");
	
	private int codigo;
	private String nome;

	private FlagsMenuSysLiape(int codigo, String nome) {
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
	
	public static FlagsMenuSysLiape getEnum(int codigo){
		for(FlagsMenuSysLiape flag:FlagsMenuSysLiape.values()){
			if(codigo == flag.getCodigo()){
				return flag;
			}
		}
		return null;
	}

}
