package br.com.liape.sistemaGerenciamento.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.PessoaDao;
import br.com.liape.sistemaGerenciamento.dao.ProfessorDao;
import br.com.liape.sistemaGerenciamento.dao.TelefoneDao;
import br.com.liape.sistemaGerenciamento.model.Pessoa;
import br.com.liape.sistemaGerenciamento.model.Professor;
import br.com.liape.sistemaGerenciamento.model.Telefone;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
@RequestScoped
public class ProfessorController {
	private Result result;
	private Validator validator;
	private PessoaDao pessoaDao;
	private TelefoneDao telefoneDao;
	private ProfessorDao professorDao;

	@Inject
	public ProfessorController(Result result, Validator validator, PessoaDao pessoaDao, ProfessorDao professorDao,
			TelefoneDao telefoneDao) {
		this.result = result;
		this.validator = validator;
		this.pessoaDao = pessoaDao;
		this.professorDao = professorDao;
		this.telefoneDao = telefoneDao;
	}

	public ProfessorController() {
		this(null, null, null, null, null);
	}

	/*
	 * CADASTRAMENTO DE PROFESSOR - FORMULÁRIO
	 */
	@Path("/Cadastro/Professor/")
	
	public void cadastro(Professor professor) {
		if (professor == null) {
			professor = new Professor();
		}
		result.include(professor);
	}

	/*
	 * CADASTRAMENTO DE PROFESSOR - ENVIO DE FORMULÁRIO
	 */
	@Post("/Finalizar/Professor/")
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id 5
	 * ou for administrador
	 */
	@NivelPermissao(idPermissao=5)
	public void postar(@Valid Professor professor, @Valid List<Telefone> telefones,
			@Valid @NotEmpty String datanascimento) {
		MensagemSistema msg = new MensagemSistema("");
		validator.onErrorRedirectTo(this).cadastro(professor);
		if (professor.getIdPes() != 0) {
			atualizarProfessor(professor, telefones, datanascimento, msg);
		}else{
			inserirProfessor(professor, telefones, datanascimento, msg);
		}
		result.use(Results.json()).withoutRoot().from(msg).serialize();
		
	}
	private void atualizarProfessor(Professor professor,List<Telefone> telefones,
			String datanascimento, MensagemSistema msg){
		professor.getPessoa().setDatanascimento(Conversor.converterLocalDate(datanascimento));
		Pessoa pessoa = pessoaDao.listarPorId(professor.getIdPes()).get(0);
		List<Telefone> telefonesAtu = telefoneDao.listarPorId(pessoa.getId());
		for (Telefone telefone : telefonesAtu) {
			telefone.setAtivo(false);
			telefoneDao.atualizar(telefone);
		}
		pessoa.setAtivo(true);
		pessoa.setDatanascimento(professor.getPessoa().getDatanascimento());
		pessoa.setEmail(professor.getPessoa().getEmail());
		pessoa.setNome(professor.getPessoa().getNome());
		pessoa.setSobrenome(professor.getPessoa().getSobrenome());
		pessoaDao.atualizar(pessoa);
		cadastrar_telefone(telefones, pessoa.getId());
		msg.setMensagem("Atualizado com Sucesso!");
	}
	private void inserirProfessor(Professor professor,List<Telefone> telefones,
			String datanascimento, MensagemSistema msg){
		professor.getPessoa().setDatanascimento(Conversor.converterLocalDate(datanascimento));
		professor.getPessoa().setAtivo(true);
		if (pessoaDao.inserir(professor.getPessoa())) {
			professor.setIdPes(pessoaDao.ultimoId());
			professorDao.inserir(professor);
			cadastrar_telefone(telefones, professor.getIdPes());
			msg.setMensagem("Cadastro Sucesso!");
		} else {
			msg.setMensagem("Erro: Cadastramento Incorreto!");
		}
	}
	// LISTA TODOS OS Professor
		@Post("/Listar/Professor/")
		public void listar() {
			List<Professor> professores =  new ArrayList<>();
			List<Pessoa> pessoas = pessoaDao.listar();
			for (Pessoa pessoa : pessoas) {
				if (professorDao.listarIdPessoa(pessoa.getId()).size() > 0) {
					pessoa.setTelefones(telefoneDao.listarPorId(pessoa.getId()));
//					pessoa.setFotoPerfil(fotoPerfilDao.listarPorIdPes(pessoa.getId()).get(0));
					Professor professor = professorDao.listarIdPessoa(pessoa.getId()).get(0);
					professor.setPessoa(pessoa);
					professores.add(professor);
				}
			}
			Collections.sort(professores);
			result.use(Results.json()).withoutRoot().from(professores).include("pessoa")
			.include("pessoa.datanascimento").include("pessoa.telefones").serialize();

		}
	/*
	 * EXCLUIR PROFESSOR
	 */
		@Path("/Excluir/Professor/")
		/*
		 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id 5
		 * ou for administrador
		 */
		@NivelPermissao(idPermissao=5)
		public void excluir(String codProfessor) {
			MensagemSistema sistema = new MensagemSistema("");
			Professor professor = professorDao.listarPorCodProfessor(codProfessor).get(0);
			professor.setPessoa(pessoaDao.listarPorId(professor.getIdPes()).get(0));
			professor.getPessoa().setAtivo(false);
			if (pessoaDao.atualizar(professor.getPessoa())) {
				sistema.setMensagem("Sucesso ao Excluir Usuário!");
				
			}else{
				sistema.setMensagem("Erro ao Excluir Usuário!");
			};
			result.use(Results.json()).withoutRoot().from(sistema).serialize();
		}
	/*
	 * CADASTRAMENTO DE TELEFONE
	 */
	public void cadastrar_telefone(List<Telefone> telefones, int idPes) {
		if (telefones != null) {
			if (telefones.size() > 0) {
				for (Telefone telefone : telefones) {
					telefone.setIdPes(idPes);
					telefone.setAtivo(true);
					if (telefoneDao.verificarSeJaExiste(telefone, idPes)) {
						telefoneDao.inserir(telefone);
					}else{
						telefoneDao.atualizar(telefone);
					}
					telefoneDao.inserir(telefone);
				}
			}
		}
	}
}