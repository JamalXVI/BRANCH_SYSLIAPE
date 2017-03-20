package br.com.liape.sistemaGerenciamento.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.dao.GrupoDao;
import br.com.liape.sistemaGerenciamento.dao.PessoaDao;
import br.com.liape.sistemaGerenciamento.dao.TelefoneDao;
import br.com.liape.sistemaGerenciamento.dao.UsuarioDao;
import br.com.liape.sistemaGerenciamento.model.Grupo;
import br.com.liape.sistemaGerenciamento.model.Permissao;
import br.com.liape.sistemaGerenciamento.model.Pessoa;
import br.com.liape.sistemaGerenciamento.model.Telefone;
import br.com.liape.sistemaGerenciamento.model.Usuario;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
@RequestScoped
public class UsuarioController extends AbstractController{
	private Validator validator;
	private PessoaDao pessoaDao;
	private UsuarioDao usuarioDao;
	private TelefoneDao telefoneDao;
	private GrupoDao grupoDao;

	@Inject
	public UsuarioController(Validator validator, PessoaDao pessoaDao, UsuarioDao usuarioDao,
			TelefoneDao telefoneDao, GrupoDao grupoDao) {
		this.validator = validator;
		this.pessoaDao = pessoaDao;
		this.usuarioDao = usuarioDao;
		this.telefoneDao = telefoneDao;
		this.grupoDao = grupoDao;
	}

	public UsuarioController() {
		this(null, null, null, null, null);
	}

	/*
	 * CADASSTRAMENTO DE USUÁRIO - CRIAÇÃO DO FORMULÁRIO
	 */
	@Path("/Cadastro/Usuario/")
	public void cadastro(Usuario usuario) {
		if (usuario == null) {
			usuario = new Usuario();
		}
		result.include(usuario);
	}

	@Path("/Excluir/Usuario/") /*
								 * Impedir o Acesso à página caso o grupo não
								 * tenha acesso à permissão de id 2 ou for
								 * administrador
								 */
	@NivelPermissao(idPermissao = 2)
	public void excluir(String loginUsuario) {
		MensagemSistema sistema = new MensagemSistema("");
		Usuario usuario = usuarioDao.listarPorLogin(loginUsuario).get(0);
		usuario.setPessoa(pessoaDao.listarPorId(usuario.getIdPes()).get(0));
		usuario.getPessoa().setAtivo(false);
		if (verPermissoesIguais(usuario.getIdGrupo())) {
			if (pessoaDao.atualizar(usuario.getPessoa())) {
				sistema.setMensagem("Sucesso: ao Excluir Usuário!");
				registrarLog(FlagsLogAcao.REMOVER_USUARIO.getCodigo(), usuario.getLogin());
			} else {
				sistema.setMensagem("Erro: ao Excluir Usuário!");
			}
		}else{
			sistema.setMensagem("Erro: ao Excluir Usuário!");
		}
		result.use(Results.json()).withoutRoot().from(sistema).serialize();
	}
	@Path("/Listar/Usuario/Aniversario")
	public void aniversario()
	{
		List<Usuario> usuarios = new ArrayList<>();
		List<Pessoa> pessoas = pessoaDao.listar_aniversario();
		for (Pessoa pessoa : pessoas) {
			if (usuarioDao.listarIdPessoa(pessoa.getId()).size() > 0) {
				pessoa.setTelefones(telefoneDao.listarPorId(pessoa.getId()));
				// pessoa.setFotoPerfil(fotoPerfilDao.listarPorIdPes(pessoa.getId()).get(0));
				Usuario usuario = usuarioDao.listarIdPessoa(pessoa.getId()).get(0);
				usuario.setGrupo(grupoDao.listarPorId(usuario.getIdGrupo()).get(0));
				usuario.setPessoa(pessoa);
				usuarios.add(usuario);
			}

		}
		result.use(Results.json()).withoutRoot().from(usuarios).include("pessoa").include("grupo")
				.include("pessoa.datanascimento").include("pessoa.telefones").exclude("senha").serialize();
	}
	@Path("/Listar/Usuario/")
	public void listar() {
		List<Usuario> usuarios = new ArrayList<>();
		List<Pessoa> pessoas = pessoaDao.listar();
		for (Pessoa pessoa : pessoas) {
			if (usuarioDao.listarIdPessoa(pessoa.getId()).size() > 0) {
				pessoa.setTelefones(telefoneDao.listarPorId(pessoa.getId()));
				// pessoa.setFotoPerfil(fotoPerfilDao.listarPorIdPes(pessoa.getId()).get(0));
				Usuario usuario = usuarioDao.listarIdPessoa(pessoa.getId()).get(0);
				usuario.setGrupo(grupoDao.listarPorId(usuario.getIdGrupo()).get(0));
				usuario.setPessoa(pessoa);
				usuarios.add(usuario);
			}

		}
		Collections.sort(usuarios);
		result.use(Results.json()).withoutRoot().from(usuarios).include("pessoa").include("grupo")
				.include("pessoa.datanascimento").include("pessoa.telefones").exclude("senha").serialize();

	}

	/*
	 * CADASTRAMENTO DE USUÁRIO - ENVIO DE FORMULÁRIO
	 */
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id
	 * 2 ou for administrador
	 */
	@NivelPermissao(idPermissao = 2)
	@Post("/Finalizar/Usuario/")
	public void postar(@Valid Usuario usuario, @Valid List<Telefone> telefones,
			@Valid @NotEmpty String datanascimento) {
		MensagemSistema msg = new MensagemSistema("");
		validator.onErrorRedirectTo(this).cadastro(usuario);
		if (usuario.getIdPes() != 0) {
			atualizarUsuario(usuario, telefones, datanascimento, msg);
		} else {
			inserirUsuario(usuario, telefones, datanascimento, msg);
		}
		result.use(Results.json()).withoutRoot().from(msg).serialize();

	}

	private void atualizarUsuario(Usuario usuario, List<Telefone> telefones, String datanascimento,
			MensagemSistema msg) {
		usuario.getPessoa().setDatanascimento(Conversor.converterLocalDate(datanascimento));
		Pessoa pessoa = pessoaDao.listarPorId(usuario.getIdPes()).get(0);
		List<Telefone> telefonesAtu = telefoneDao.listarPorId(pessoa.getId());
		for (Telefone telefone : telefonesAtu) {
			telefone.setAtivo(false);
			telefoneDao.atualizar(telefone);
		}
		pessoa.setAtivo(true);
		pessoa.setDatanascimento(usuario.getPessoa().getDatanascimento());
		pessoa.setEmail(usuario.getPessoa().getEmail());
		pessoa.setNome(usuario.getPessoa().getNome());
		pessoa.setSobrenome(usuario.getPessoa().getSobrenome());
		Usuario usuario2 = usuarioDao.listarPorLogin(usuario.getLogin()).get(0);
		if (verificarSePodeAtualizar()) {
			usuario2.setIdGrupo(usuario.getIdGrupo());
			usuario2.setSenha(usuario.getSenha());
			pessoaDao.atualizar(pessoa);
			usuarioDao.atualizarGrupo(usuario.getIdGrupo(), usuario.getLogin());
			cadastrar_telefone(telefones, pessoa.getId());
			registrarLog(FlagsLogAcao.ATUALIZAR_USUARIO.getCodigo(), usuario.getLogin());
			msg.setMensagem("Atualizado com Sucesso!");
		} else {
			msg.setMensagem("Erro: Privilégio Insuficiente para Excluir Usuário!");
		}

	}

	private boolean verificarSePodeAtualizar() {
		for (Permissao permissao2 : usuarioLogado.pegarAutorizacao()) {
			if (permissao2.getId() == 1) {
				return true;
			}
		}
		return false;
	}

	private void inserirUsuario(Usuario usuario, List<Telefone> telefones, String datanascimento, MensagemSistema msg) {
		if (verPermissoesIguais(usuario.getIdGrupo())) {
			usuario.getPessoa().setDatanascimento(Conversor.converterLocalDate(datanascimento));
			usuario.getPessoa().setAtivo(true);

			if (pessoaDao.inserir(usuario.getPessoa())) {
				usuario.setIdPes(pessoaDao.ultimoId());
				usuarioDao.inserir(usuario);
				cadastrar_telefone(telefones, usuario.getIdPes());
				registrarLog(FlagsLogAcao.CADASTRAR_USUARIO.getCodigo(), usuario.getLogin());
				msg.setMensagem("Cadastro Sucesso!");
			} else {
				msg.setMensagem("Erro: Cadastramento Incorreto!");
			}
		}else{
			msg.setMensagem("Erro: Cadastramento Incorreto!");
		}
		
	}

	private boolean verPermissoesIguais(int idGrupo) {
		List<Grupo> listarPorId = grupoDao.listarPorId(idGrupo);
		if (listarPorId.size() > 0) {
			Grupo grupo = listarPorId.get(0);
			Grupo grpUsr = grupoDao.listarPorId(usuarioLogado.getUsuario().getIdGrupo()).get(0);
			if (grupo.getHierarquia() >= grpUsr.getHierarquia()) {
				return true;
			}
		}
		return false;
		
	}

	/*
	 * CADASTRAMENTO DE TELEFONE
	 */
	private void cadastrar_telefone(List<Telefone> telefones, int idPes) {
		if (telefones != null) {
			if (telefones.size() > 0) {
				for (Telefone telefone : telefones) {
					telefone.setIdPes(idPes);
					telefone.setAtivo(true);
					if (telefoneDao.verificarSeJaExiste(telefone, idPes)) {
						telefoneDao.atualizar(telefone);
					} else {
						telefoneDao.inserir(telefone);
					}

				}
			}
		}
	}

	@Path("/Perfil/Login/")
	@Post
	public void alterarPerfil(@Valid Usuario usuario, @Valid List<Telefone> telefones,
			@Valid @NotEmpty String datanascimento) {
		usuario.getPessoa().setDatanascimento(Conversor.converterLocalDate(datanascimento));
		Usuario usuario2 = usuarioDao.listarPorLogin(usuario.getLogin()).get(0);
		Pessoa pessoa = pessoaDao.listarPorId(usuario2.getIdPes()).get(0);
		List<Telefone> telefonesAtu = telefoneDao.listarPorId(pessoa.getId());
		for (Telefone telefone : telefonesAtu) {
			telefone.setAtivo(false);
			telefoneDao.atualizar(telefone);
		}
		pessoa.setAtivo(true);
		pessoa.setDatanascimento(usuario.getPessoa().getDatanascimento());
		pessoa.setEmail(usuario.getPessoa().getEmail());
		pessoa.setNome(usuario.getPessoa().getNome());
		pessoa.setSobrenome(usuario.getPessoa().getSobrenome());
		usuario2.setSenha(usuario.getSenha());
		pessoaDao.atualizar(pessoa);
		usuarioDao.atualizar(usuario2);
		cadastrar_telefone(telefones, pessoa.getId());
		result.redirectTo(this).perfil();

	}

	@Path("/Perfil")
	public void perfil() {

	}

	@Path("/Perfil/Telefones")
	public void listarTelefones() {
		List<Telefone> telefones = telefoneDao.listarPorId(usuarioLogado.getUsuario().getPessoa().getId());
		result.use(Results.json()).withoutRoot().from(telefones).serialize();
	}
}