package br.com.liape.sistemaGerenciamento.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.unaerp.jdbc.anotation.AutoIncrement;
import br.com.unaerp.jdbc.anotation.Chave;
import br.com.unaerp.jdbc.anotation.Coluna;
import br.com.unaerp.jdbc.anotation.Date;
import br.com.unaerp.jdbc.anotation.Tabela;

@Tabela(nome = "pessoa")
public class Pessoa implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6365010434849928992L;
	@Coluna(nome = "ID_PES")
	@AutoIncrement
	@Chave
	private int id;
	@Length(min = 5)
	@NotNull
	@NotEmpty
	@Coluna(nome = "NOME_PES")
	private String nome;
	@Length(min = 5)
	@NotNull
	@NotEmpty
	@Coluna(nome = "SOBRENOME_PES")
	private String sobrenome;
	@Email
	@NotNull
	@NotEmpty
	@Coluna(nome = "EMAIL_PES")
	private String email;
	@Coluna(nome = "DATA_NASCIMENTO_PES")
	@Date
	private LocalDate datanascimento;
	@Coluna(nome = "ATIVO_PES")
	private Boolean ativo;
	private List<Telefone> telefones;
	private FotoPerfil fotoPerfil;
	
	public FotoPerfil getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(FotoPerfil fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDatanascimento() {
		return datanascimento;
	}

	public void setDatanascimento(LocalDate datanascimento) {
		this.datanascimento = datanascimento;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
