package br.com.liape.sistemaGerenciamento.adServerModel;

import java.io.Serializable;

public class Cadastro extends Pessoa implements Serializable{
	private static final long serialVersionUID = -3396531061174408550L;
	private String SobreNome;
	private String Senha;
	private String tipoUsuario ;
	private String Curso;
	
	public Cadastro(String Codigo, String Nome,String SobreNome,String Senha ,String Curso) {
		super(Codigo, Nome);
		this.SobreNome = SobreNome;
		this.Senha = Senha;
		this.Curso = Curso;
	}
	
	public Cadastro(){
		super("","");
		this.SobreNome = "";
		this.Senha = "";
		this.Curso = null;
		
	}

	public String getSobreNome() {
		return SobreNome;
	}

	public void setSobreNome(String sobreNome) {
		SobreNome = sobreNome;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	

	public String getCurso() {
		return Curso;
	}

	public void setCurso(String curso) {
		Curso = curso;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
