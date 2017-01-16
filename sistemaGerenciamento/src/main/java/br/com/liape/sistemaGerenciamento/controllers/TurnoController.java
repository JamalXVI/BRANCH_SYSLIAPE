package br.com.liape.sistemaGerenciamento.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.dao.FimTurnoDao;
import br.com.liape.sistemaGerenciamento.dao.PessoaDao;
import br.com.liape.sistemaGerenciamento.dao.TurnoDao;
import br.com.liape.sistemaGerenciamento.dao.UsuarioDao;
import br.com.liape.sistemaGerenciamento.dao.UsuarioTurnoDao;
import br.com.liape.sistemaGerenciamento.model.FimTurno;
import br.com.liape.sistemaGerenciamento.model.Turno;
import br.com.liape.sistemaGerenciamento.model.UsuarioTurno;
import br.com.liape.sistemaGerenciamento.modelView.TurnoUsuario;
import br.com.liape.sistemaGerenciamento.modelView.UsuarioTurnoView;
import br.com.liape.sistemaGerenciamento.outros.Conversor;
import br.com.liape.sistemaGerenciamento.outros.MensagemSistema;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class TurnoController {
	private Result result;
	private TurnoDao turnoDao;
	private FimTurnoDao fimTurnoDao;
	private UsuarioTurnoDao usuarioTurnoDao;
	private UsuarioDao usuarioDao;
	private PessoaDao pessoaDao;

	@Inject
	public TurnoController(Result result, TurnoDao turnoDao, FimTurnoDao fimTurnoDao,
			UsuarioTurnoDao usuarioTurnoDao, UsuarioDao usuarioDao, PessoaDao pessoaDao) {
		this.result = result;
		this.turnoDao = turnoDao;
		this.fimTurnoDao = fimTurnoDao;
		this.usuarioTurnoDao = usuarioTurnoDao;
		this.usuarioDao = usuarioDao;
		this.pessoaDao = pessoaDao;
	}

	public TurnoController() {
		this(null, null, null, null, null, null);
	}

	/*
	 * PÁGINA INICIAL DO PROJETO
	 */
	@Path("/Turno/")
	public void index() {

	}
	@Post("/Listar/Turno/Usuario/")
	public void listarUsuarioTurnoPorTurno(int idTurno)
	{
		List<UsuarioTurno> usuariosTurnos = usuarioTurnoDao.listarId(idTurno);
		List<UsuarioTurnoView> view =  new ArrayList<>();
		for (UsuarioTurno usuarioTurno : usuariosTurnos) {
			UsuarioTurnoView usr = new UsuarioTurnoView();
			usr.setIdTur(usuarioTurno.getIdTur());
			usr.setLoginUsr(usuarioTurno.getLoginUsr());
			usr.setUsuario(usuarioDao.listarPorLogin(usuarioTurno.getLoginUsr()).get(0));
			usr.getUsuario().setPessoa(pessoaDao.listarPorId(usr.getUsuario().getIdPes()).get(0));
			view.add(usr);
		}
		result.use(Results.json()).withoutRoot().from(view).include("usuario").include("usuario.pessoa")
		.serialize();
	}
	@Post("/Listar/Turno/")
	public void listar()
	{
		List<Turno> turnos = turnoDao.listar();
		for (Turno turno : turnos) {
			TurnoUsuario turnoUsuario = new TurnoUsuario();
			turnoUsuario.setTurno(turno);
			turnoUsuario.setUsuariosTurno(usuarioTurnoDao.listarId(turno.getId()));
			
		}
		result.use(Results.json()).withoutRoot().from(turnos)
		.include("horaFim").include("horaInicio").include("registro")
		.serialize();
	}
	@Post("/Excluir/Turno")
	public void excluir(int id){
		List<Turno> turnos = turnoDao.listarId(id);
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso!");
		if (turnos.size() > 0) {
			Turno turno = turnos.get(0);
			turno.setAtivo(false);
			FimTurno fimTurno = new FimTurno();
			fimTurno.setIdTur(id);
			fimTurno.setRegistro(LocalDate.now());
			fimTurnoDao.inserir(fimTurno);
			turnoDao.atualizar(turno);
		}else{
			mensagemSistema.setMensagem("Erro: ID inválido");
		}
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
	@Post("/Cadastro/Turno/Usuario")
	@NivelPermissao(idPermissao = 15)
	public void cadastrarUsuarioTurno(String login, int idTurno)
	{
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso!");
		List<UsuarioTurno> usuariosTurnos = usuarioTurnoDao.listarLogin(login);
		boolean temTurno = false;
		if (usuariosTurnos.size() > 0) {
			for (UsuarioTurno usuarioTurno : usuariosTurnos) {
				if (turnoDao.listarId(usuarioTurno.getIdTur()).get(0).isAtivo()) {
					temTurno = true;
					mensagemSistema.setMensagem("Erro: Usuário Já Cadastrado no Turno!");
				}
			}
			
		}
		if (!temTurno) {
			if (usuarioDao.listarPorLogin(login).size() > 0) {
				UsuarioTurno turno = new UsuarioTurno();
				turno.setIdTur(idTurno);
				turno.setLoginUsr(login);
				if (!usuarioTurnoDao.inserir(turno)) {
					mensagemSistema.setMensagem("Erro: Cadastro!");
					
				}
			}else{
				mensagemSistema.setMensagem("Erro: Login de Usuário Inválido!");
			}
		}
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}
	@Post("/Cadastro/Turno/")
	@NivelPermissao(idPermissao = 15)
	public void postar(Turno turno, String horaInicio, String horaFim) {
		MensagemSistema mensagemSistema = new MensagemSistema("Sucesso!");

		System.out.println(horaInicio);
		System.out.println(horaFim);
		System.out.println(turno.getPeriodo());
		if (turno.getPeriodo() != 0) {
			criarTurno(turno, horaInicio, horaFim, mensagemSistema);
		}else{
			mensagemSistema.setMensagem("Erro: Período Inválido!");
		}
		result.use(Results.json()).withoutRoot().from(mensagemSistema).serialize();
	}

	private void criarTurno(Turno turno, String horaInicio, String horaFim, MensagemSistema mensagemSistema) {
		LocalTime timeHoraInicio;
		LocalTime timeHoraFim;
		try {
			timeHoraInicio = Conversor.converterLocalTime(horaInicio);
			timeHoraFim = Conversor.converterLocalTime(horaFim);
			turno.setAtivo(true);
			turno.setHoraFim(timeHoraFim);
			turno.setHoraInicio(timeHoraInicio);
			turno.setRegistro(LocalDate.now());
			if(!turnoDao.inserir(turno)){
				mensagemSistema.setMensagem("Erro: Cadastro!");
			};
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mensagemSistema.setMensagem("Erro: Conversão de Data!");
		}
	}

}