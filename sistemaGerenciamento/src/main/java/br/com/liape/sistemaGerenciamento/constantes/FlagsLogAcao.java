package br.com.liape.sistemaGerenciamento.constantes;

public enum FlagsLogAcao {
	CADASTRO_ANO_SEMESTRE(0, "Cadastro de Ano/Semestre"),
	REMOVER_ANO_SEMESTRE(1, "Remover Ano/Semestre"),
	ATUALIZAR_ANO_SEMESTRE(2, "Atualizar Ano/Semestre"),
	CADASTRAR_CURSO(3, "Cadastro de Curso"),
	REMOVER_CURSO(4, "Remover Curso"),
	ATUALIZAR_CURSO(5, "Atualizar Curso"),
	CADASTRAR_DISCIPLINA(6, "Cadastrar Disciplina"),
	REMOVER_DISCIPLINA(7, "Remover Disciplina"),
	ATUALIZAR_DISCIPLINA(8, "Atualizar Disciplina"),
	CADASTRAR_ESCALA(9, "Cadastrar Escala"),
	REMOVER_ESCALA(10, "Remover Escala"),
	ATUALIZAR_ESCALA(11, "Ataulizar Escalar"),
	CADASTRAR_FOTOPERFIL(12, "Cadastrar Foto de Perfil"),
	REMOVER_FOTOPERFIL(13, "Remover Foto de Perfil"),
	ATUALIZAR_FOTOPERFIL(14, "Atualizar  Foto de Perfil"),
	CADASTRAR_GRUPO(15, "Cadastrar Grupo"),
	REMOVER_GRUPO(16, "Remover Grupo"),
	ATUALIZAR_GRUPO(17, "Atualizar Grupo"),
	LOGAR_USUARIO(18, "Login de Usuario"),
	CADASTRAR_MENSAGEM(19, "Cadastrar Mensagem"),
	REMOVER_ENTRADA_MENSAGEM(20, "Remover Mensagem Caixa de Entrada"),
	REMOVER_ENVIADO_MENSAGEM(21, "Mensagem Enviada"),
	ATUALIZAR_MENSAGEM(22, "Mensagem Atualizada"),
	VISUALIZAR_MENSAGEM(23, "Visualizar Mensagem"),
	CADASTRAR_SEMESTRAL(24, "Cadastrar Reserva Semestral"),
	REMOVER_SEMESTRAL(25, "Remover Reserva Semestral"),
	ATUALIZAR_SEMESTRAL(26, "Atualizar Reserva Semestral"),
	CADASTRAR_EXPORADICO(27, "Cadastrar Reserva Exporádica"),
	REMOVER_EXPORADICO(28, "Remover Reserva Exporádica"),
	ATUALIZAR_EXPORADICO(29, "Atualiza Reserva Exporádica"),
	CADASTRAR_SALA(30,  "Cadastro de Sala"),
	REMOVER_SALA(31, "Remover Sala"),
	ATUALIZAR_SALA(32, "Atualizar Sala"),
	REMOVER_ORDEM(33, "Remover Ordem de Serviço"),
	FINAlIZAR_ORDEM(34, "Finalizar Ordem de Serviço"),
	CADASTRAR_ORDEM_SALA(35, "Cadastrar Ordem de  Serviço de Sala"),
	REMOVER_ORDEM_SALA(36, "Remover Ordem de Serviço de Sala"),
	ATUALIZAR_ORDEM_SALA(37, "Atualizar Ordem de Serviço de Sala"),
	CADASTRAR_ORDEM_COMPUTADOR(38, "Cadastrar Ordem de Serviço de Computador"),
	REMOVER_ORDEM_COMPUTADOR(39, "Remover Ordem de Serviço de Computador"),
	ATUALIZAR_ORDEM_COMPUTADOR(40, "Atualizar Ordem de Serviço de Computador"),
	CADASTRAR_SUBORDEM_TURNO(41, "Cadastrar SubOrdem de Turno"),
	REMOVER_SUBORDEM_TURNO(42, "Remover SubOrdem de Serviço de Turno"),
	ATUALIZAR_SUBORDEM_TURNO(43, "Atualizar SubOrdem de Turno"),
	CADASTRAR_SUBORDEM_USUARIO(44, "Cadastrar SubOrdem de Usuário"),
	REMOVER_SUBORDEM_USUARIO(45, "Remover SubOrdem de Usuário"),
	ATUALIZAR_SUBORDEM_USUARIO(46, "Atualizar SubOrdem de Usuário"),
	VISUALIZAR_SUBORDEM(47, "Visualizar SubOrdem"),
	REDIRECIONAR_SUBORDEM(48, "Redirecionar SubOrdem"),
	FINALIZAR_SUBORDEM(49, "Finalizar SubOrdem"),
	CADASTRAR_PROFESSOR(50, "Cadastrar Professor"),
	REMOVER_PROFESSOR(51, "Remover Professor"),
	ATUALIZAR_PROFESSOR(52, "Atualizar Professor"),
	CADASTRAR_TURNO(53, "Cadastrar Turno"),
	REMOVER_TURNO(54, "Remover Turno"),
	ATUALIZAR_TURNO(55, "Atualizar Turno"),
	CADASTRAR_USUARIO_TURNO(56, "Cadastro de Usuário Turno"),
	REMOVER_USUARIO_TURNO(57, "Remover Usuário Turno"),
	ATUALIZAR_USUARIO_TURNO(58, "Atualizar Usuário Turno"),
	CADASTRAR_USUARIO(59, "Cadastrar Usuário"),
	REMOVER_USUARIO(60, "Remover Usuário"),
	ATUALIZAR_USUARIO(61, "Atualizar Usuário"),
	CADASTRAR_ARQUIVO(62, "Cadastrar Arquivo"),
	REMOVER_ARQUIVO(63, "Remover Arquivo"),
	ATUALIZAR_ARQUIVO(64, "Atualizar Arquivo"),
	CADASTRAR_USUARIOAD(65, "Cadastrar usuário AD"),
	DELETAR_USUARIOAD(66, "Deletar Usuário AD"),
	VERIFICAR_USUARIOAD(67, "Verificar Usuário AD"),
	RESETAR_SENHA_USUARIOAD(68, "Resetar Senha Usuário AD");
	
	private int codigo;
	private String nome;
	
	private FlagsLogAcao(int codigo, String nome) {
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
	public static FlagsLogAcao encontrarId(int id)
	{
		for (FlagsLogAcao flag : FlagsLogAcao.values()) {
			if (flag.getCodigo() == id) {
				return flag;
			}
		}
		return null;
	}
	
}
