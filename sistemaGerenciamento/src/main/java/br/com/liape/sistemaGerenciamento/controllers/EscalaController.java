package br.com.liape.sistemaGerenciamento.controllers;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.EscalaDao;
import br.com.liape.sistemaGerenciamento.dao.UsuarioEscalaDao;
import br.com.liape.sistemaGerenciamento.model.Escala;
import br.com.liape.sistemaGerenciamento.model.UsuarioEscala;
import br.com.liape.sistemaGerenciamento.modelView.EnviarEscala;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class EscalaController {
	private Validator validator;
	private Result result;
	private UsuarioEscalaDao usuarioEscalaDao;
	private EscalaDao escalaDao;

	@Inject
	public EscalaController(Validator validator, Result result, EscalaDao escalaDao,
			UsuarioEscalaDao usuarioEscalaDao) {
		this.validator = validator;
		this.result = result;
		this.escalaDao = escalaDao;
		this.usuarioEscalaDao = usuarioEscalaDao;
	}

	public EscalaController() {
		this(null, null, null, null);
	}
	@Path("/Escala/")
	public void index(){
	}
	@Post("/Listar/Escala/")
	public void listar(){
		List<Escala> escalas = escalaDao.listar_ativo();
		result.use(Results.json()).withoutRoot().from(escalas).include("horaInicio")
		.include("horaFim").include("dataIni").include("dataFim").serialize();
	}
	@Post("/Listar/Escala/Usuario/")
	public void listar_usuario(int id){
		List<UsuarioEscala> escalas = usuarioEscalaDao.listar_ativo(id);
		result.use(Results.json()).withoutRoot().from(escalas).serialize();
	}
	@Post("/Cadastro/Escala/")
	@NivelPermissao(idPermissao = 15)
	public void postar(EnviarEscala escalaEnv)
	{
		MensagemSistema mensagemSistema = new MensagemSistema("Erro: Impossível Excluir");
		Escala escala = new Escala();
		try {
			inserirEscala(escalaEnv, escala, mensagemSistema);
		} catch (Exception e) {
		}
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}

	private void inserirEscala(EnviarEscala escalaEnv, Escala escala, MensagemSistema mensagemSistema) {
		escala.setDataIni(Conversor.converterLocalDate(escalaEnv.getDataInicio()));
		escala.setHoraInicio(Conversor.converterLocalTime(escalaEnv.getHoraInicio()));
		escala.setHoraFim(Conversor.converterLocalTime(escalaEnv.getHoraFim()));
		escala.setAtivo(true);
		try {
			if (!escalaEnv.getDataFim().equals("")) {
				escala.setDataFim(Conversor.converterLocalDate(escalaEnv.getDataFim()));
			}else{
				escala.setDataFim(escala.getDataIni());
			}
			
		} catch (Exception e) {
			escala.setDataFim(escala.getDataIni());
		}
		boolean inserir = escalaDao.inserir(escala);
		if (inserir) {
			inserirUsuarioEscala(escalaEnv, mensagemSistema);
		}else{
		}
	}
	
	private void inserirUsuarioEscala(EnviarEscala escalaEnv, MensagemSistema mensagemSistema) {
		int id = escalaDao.listarUltimo();
		for (String usuario : escalaEnv.getUsuarios()) {
			UsuarioEscala usuarioEscala = new UsuarioEscala();
			usuarioEscala.setIdEsc(id);
			usuarioEscala.setLoginUsr(usuario);
			usuarioEscalaDao.inserir(usuarioEscala);
			mensagemSistema.setMensagem("Sucesso!");
		}
	}
	@Post("/Remover/Escala/")
	@NivelPermissao(idPermissao = 15)
	public void apagar(int idEsc) {
		MensagemSistema mensagemSistema = new MensagemSistema("Erro: Impossível Excluir");
		List<Escala> escalas = escalaDao.listar_id(idEsc);
		if (escalas.size() > 0) {
			Escala escala = escalas.get(0);
			escala.setAtivo(false);
			escalaDao.atualizar(escala);
			mensagemSistema.setMensagem("Sucesso!");
		}
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
}
