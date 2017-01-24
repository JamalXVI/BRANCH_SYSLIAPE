package br.com.liape.sistemaGerenciamento.model;

public class Conexao {
	private String database;
	private String host;
	private String driver;
	private String usuario;
	private String password;
	public Conexao(String database, String host, String driver, String usuario, String password) {
		super();
		this.database = database;
		this.host = host;
		this.driver = driver;
		this.usuario = usuario;
		this.password = password;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
