package br.com.liape.sistemaGerenciamento.controllers;

import java.util.ArrayList;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.adServerModel.Cadastro;
import br.com.liape.sistemaGerenciamento.adServerModel.OrganizationalUnit;
import br.com.liape.sistemaGerenciamento.adServerModel.Pessoa;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.sistemaGerenciamento.conexao.WebService;

@Controller
public class CadastroAdController {

	@Inject
	private Result result;

	public CadastroAdController() {
	}

	@Path("/CadastroAd/")
	public void index() {

	}

	@Post("/Lista/CadastroAd/Cursos/")
	public void listarCursos() {
		ArrayList<OrganizationalUnit> cursos = WebService.ListarCursos();
		result.use(Results.json()).withoutRoot().from(cursos).serialize();
	}

	@Post("/Pesquisar/CadastroAd/Usuario/")
	public void novo(String pesquisa, int tipo) {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		switch (tipo) {
		case 1:
			pessoas = WebService.Listar(pesquisa, "Professores");
			break;
		case 2:
			pessoas = WebService.Listar(pesquisa, "Monitores");
			break;
		default:
			pessoas = WebService.Listar(pesquisa, "");
			break;
		}
		result.use(Results.json()).withoutRoot().from(pessoas).serialize();
	}

	@Post("/Finalizar/CadastroAd/Usuario/")
	public void postar(Cadastro usuario) {
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso!");
		if (usuario.getTipoUsuario() == null) {
			usuario.setTipoUsuario("");
		} else {
			if (usuario.getTipoUsuario().equals("Professores")) {
				usuario.setCurso("33");
			} else {
				usuario.setCurso("");
			}
		}
		WebService.Cadastrar(usuario);
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}

	@Post("/Resetar/CadastroAd/Senha/")
	public void resetar(String senha, String codigo) {

		String resp = WebService.Resetar(codigo, senha);
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso!");
		if (!resp.equals("Resetado com sucesso")) {
			mensagemSistema.setMensagem(resp);
		}
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
	@Post("/Deletar/CadastroAd/Usuario/")
	public void deletar(String codigo)
	{
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso!");
		String deletar = WebService.Deletar(codigo);
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
}