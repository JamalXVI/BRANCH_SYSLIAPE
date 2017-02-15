package br.com.sistemaGerenciamento.conexao;

public class ConexaoAdServer {
	/*
	   * INFORMA��ES DO SERVIDOR NECESSARIO PARA A CONEX�O
	   */
	
	   private static String NAMESPACE = "localhost";
	   //private static String URL = "http://172.25.3.224/Android/";
	   private static String URL = "http://172.25.0.15/AdWebService.asmx";
	   private static String SOAP_ACTION = "localhost/";
	   
	   private static String ListarUsuario = "ListarUsuarios";
	   private static String CadastrarUsuario = "AdicionarUsuario";
	   private static String DeletarUsuario = "DeletarUsuario";
	   private static String ListarCursos = "ListarCursos";
	   private static String ResetarSenha = "resetarSenha";
	   private static String ListarPendencia = "ListarPendencia";
	   private static String ListarAulas = "Aulas";
	   private static String Login = "Login";
	   private static String AdicionarCfs = "adicionarCFS";
	   private static String Cadastrar = "AdicionarUsuario";
	   
	public String getNAMESPACE()
	{
		return NAMESPACE;
	}
	
	public  void setNAMESPACE(String nAMESPACE)
	{
		NAMESPACE = nAMESPACE;
	}
	
	public String getURL()
	{
		return URL;
	}
	
	public void setURL(String uRL)
	{
		URL = uRL;
	}
	
	public String getSOAP_ACTION()
	{
		return SOAP_ACTION;
	}
	
	public void setSOAP_ACTION(String sOAP_ACTION)
	{
		SOAP_ACTION = sOAP_ACTION;
	}
	
	public String getListarUsuario()
	{
		return ListarUsuario;
	}
	
	public void setListarUsuario(String listarUsuario) 
	{
		ListarUsuario = listarUsuario;
	}
	
	public String getDeletarUsuario()
	{
		return DeletarUsuario;
	}
	
	public void setDeletarUsuario(String deletarUsuario)
	{
		DeletarUsuario = deletarUsuario;
	}
	
	public String getCadastrarUsuario()
	{
		return CadastrarUsuario;
	}
	
	public void setCadastrarUsuario(String cadastrarUsuario)
	{
		CadastrarUsuario = cadastrarUsuario;
	}

	public String getListarCursos() {
		return ListarCursos;
	}

	public void setListarCursos(String listarCursos) {
		ListarCursos = listarCursos;
	}

	public static String getResetarSenha() {
		return ResetarSenha;
	}

	public static void setResetarSenha(String resetarSenha) {
		ResetarSenha = resetarSenha;
	}

	public String getListarPendencia() {
		return ListarPendencia;
	}

	public void setListarPendencia(String listarPendencia) {
		ListarPendencia = listarPendencia;
	}

	public String getListarAulas() {
		return ListarAulas;
	}

	public void setListarAulas(String listarAulas) {
		ListarAulas = listarAulas;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getAdicionarCfs() {
		return AdicionarCfs;
	}

	public void setAdicionarCfs(String adicionarCfs) {
		AdicionarCfs = adicionarCfs;
	}

	public  String getCadastrar() {
		return Cadastrar;
	}

	public void setCadastrar(String cadastrar) {
		Cadastrar = cadastrar;
	}
}
