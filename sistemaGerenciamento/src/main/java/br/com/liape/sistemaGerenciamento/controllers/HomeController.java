package br.com.liape.sistemaGerenciamento.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.com.liape.sistemaGerenciamento.seguranca.LoginFuncionario;

@Controller
public class HomeController {

	@Inject
	private Result result;

	public HomeController() {
	}

	/*
	 * PÁGINA INICIAL DO PROJETO
	 */
	@Path("/")
	public void index() {
		// result.include("msg", "Message from your controller");
	}

	@Path("/Configurar/Wifi/Linux/")
	public void linux()
	{
		String[] passos = new String[7];
		passos[0] = "";
		passos[1] = "Clicar no Ícone de Wi-fi";
		passos[2] = "Selecionar a rede da Unaerp";
		passos[3] = "Colocar:</p><p>Segurança Wi-Fi: WPA &"
				+ " WPA2(Enterprise)</p><p>Autenticação Interna:MSCHAPv2";
		passos[4] = "Lembrar de Selecionar \"Nenhum Certificado CA nescessário\"";
		passos[5] = "Checar Status da Rede";
		passos[6] =  "Verificar se está conectada";
		result.include("passos", passos);
	}
	@Path("/Ramais")
	@LoginFuncionario
	public void ramais()
	{
		
	}
	@Path("/HorariosEspeciais/")
	public void horariosEspeciais()
	{
		
	}
}