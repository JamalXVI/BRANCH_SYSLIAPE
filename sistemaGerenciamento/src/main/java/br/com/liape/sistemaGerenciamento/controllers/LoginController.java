package br.com.liape.sistemaGerenciamento.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.liape.sistemaGerenciamento.dao.GrupoDao;
import br.com.liape.sistemaGerenciamento.dao.GrupoPermissaoDao;
import br.com.liape.sistemaGerenciamento.dao.LogLoginDao;
import br.com.liape.sistemaGerenciamento.dao.PermissaoDao;
import br.com.liape.sistemaGerenciamento.dao.PessoaDao;
import br.com.liape.sistemaGerenciamento.dao.UsuarioDao;
import br.com.liape.sistemaGerenciamento.model.GrupoPermissao;
import br.com.liape.sistemaGerenciamento.model.Login;
import br.com.liape.sistemaGerenciamento.model.Permissao;
import br.com.liape.sistemaGerenciamento.model.Usuario;
import br.com.liape.sistemaGerenciamento.seguranca.LoginFuncionario;

@Controller
public class LoginController extends AbstractController{

	private Validator validator;
	private UsuarioDao usuarioDao;
	private LogLoginDao logLoginDao;
	private PessoaDao pessoaDao;
	private GrupoDao grupoDao;
	private GrupoPermissaoDao grupoPermissaoDao;
	private PermissaoDao permissaoDao;

	@Inject
	public LoginController(Validator validator, UsuarioDao usuarioDao,
			LogLoginDao logLoginDao, PessoaDao pessoaDao, GrupoDao grupoDao,
			GrupoPermissaoDao grupoPermissaoDao, PermissaoDao permissaoDao) {
		this.validator = validator;
		this.usuarioDao = usuarioDao;
		this.logLoginDao = logLoginDao;
		this.pessoaDao = pessoaDao;
		this.grupoDao = grupoDao;
		this.grupoPermissaoDao = grupoPermissaoDao;
		this.permissaoDao = permissaoDao;
	}

	public LoginController() {
		this(null, null, null, null, null, null, null);
	}

	/*
	 * FORMULÁRIO DE LOGIN - CRIAÇÃO
	 */
	@LoginFuncionario // DEIXAR PÁGINA ABERTA PARA TODOS
	@Path("/Login")
	public void form() {

	}

	/*
	 * AÇÃO DE DESLOGAR
	 */
	@LoginFuncionario // DEIXAR PÁGINA ABERTA PARA TODOS
	@Path("/Login/Deslogar/")
	public void deslogar() {
		usuarioLogado.desloga();
		result.redirectTo(this).form();
	}

	/*
	 * FORMULÁRIO DE LOGIN - ENVIO
	 */
	@LoginFuncionario // DEIXAR PÁGINA ABERTA PARA TODOS
	@Post
	public void autentica(String usuario, String senha) {
		List<Usuario> collection = usuarioDao.logou(usuario, senha);
		if (collection.size() > 0) {
			Usuario u = collection.iterator().next();
			//INSERIR A PESSOA JUNTAMENTE COM O USUÁRIO
			u.setPessoa(pessoaDao.listarPorId(u.getIdPes()).get(0));
			if (u.getPessoa().getAtivo()) {
				//INSERIR O GRUPO DO USUÁRIO
				u.setGrupo(grupoDao.listarPorId(u.getIdGrupo()).get(0));
				//ASSOCIAR GRUPO E PERMISSÕES
				List<GrupoPermissao> grpPermissoes = grupoPermissaoDao.listarPermissoesGrupo(u.getIdGrupo());
				List<Permissao> permissoes = new ArrayList<>();
				for (GrupoPermissao grupoPermissao : grpPermissoes) {
					Permissao permissao = permissaoDao.listarPorId(grupoPermissao.getIdPer()).get(0);
					permissoes.add(permissao);
				}
				u.getGrupo().setPermissoes(permissoes);
				usuarioLogado.fazLogin(u);
				registrarLogin(u);
				result.redirectTo(HomeController.class).index();
			}else{
				validator.add(new SimpleMessage("login_invalido", "Login ou senha incorretos"));
				validator.onErrorRedirectTo(this).form();
			}
			
			
		} else {
			validator.add(new SimpleMessage("login_invalido", "Login ou senha incorretos"));
			validator.onErrorRedirectTo(this).form();
		}
	}

	/*
	 * FAZER O LOG DO LOGIN DO USUÁRIO
	 */
	private void registrarLogin(Usuario u) {
		Login login = new Login();
		login.setLoginUsr(u.getLogin());
		login.setData(LocalDateTime.now());
		logLoginDao.inserir(login);
	}
	
	
}
