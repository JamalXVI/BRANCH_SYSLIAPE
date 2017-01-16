package br.com.liape.sistemaGerenciamento.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

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
	//	result.include("msg", "Message from your controller");
	}
}