package br.com.liape.sistemaGerenciamento.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;

@Controller
public class CadastroAdController extends AbstractController{


	@Path("/CadastroAd/")
	public void index() {

	}
	@Post("/CadastroAd/Deletar/")
	public void registroDeletar(String codigo)
	{
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso!");
		registrarLog(FlagsLogAcao.DELETAR_USUARIOAD.getCodigo(), codigo);
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
	@Post("/CadastroAd/Cadastrar/")
	public void registroCadastrar(String codigo)
	{
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso!");
		registrarLog(FlagsLogAcao.CADASTRAR_USUARIOAD.getCodigo(), codigo);
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
	@Post("/CadastroAd/Verificar/")
	public void registroVerificar(String codigo)
	{
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso!");
		registrarLog(FlagsLogAcao.VERIFICAR_USUARIOAD.getCodigo(), codigo);
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
	@Post("/CadastroAd/Resetar/")
	public void registroResetarSenha(String codigo)
	{
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso!");
		registrarLog(FlagsLogAcao.RESETAR_SENHA_USUARIOAD.getCodigo(), codigo);
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
}