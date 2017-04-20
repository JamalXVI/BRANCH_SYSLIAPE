package br.com.liape.sistemaGerenciamento.controllers;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.view.Results;
import br.com.liape.sistemaGerenciamento.constantes.FlagsLogAcao;
import br.com.liape.sistemaGerenciamento.dao.SalaDao;
import br.com.liape.sistemaGerenciamento.model.Sala;
import br.com.liape.sistemaGerenciamento.seguranca.NivelPermissao;

@Controller
public class SalaController extends AbstractController {
	private SalaDao salaDao;

	@Inject
	public SalaController(SalaDao salaDao) {
		this.salaDao = salaDao;
	}

	public SalaController() {
		this(null);
	}

	/*
	 * SEÇÃO DE REQUISIÇÕES AJAX
	 */
	/*
	 * ENVIO DE CADASTRO DE SALAS
	 */
	/*
	 * Impedir o Acesso à página caso o grupo não tenha acesso à permissão de id 9 
	 * ou for administrador
	 */
	@NivelPermissao(idPermissao=9)
	@Post("/Cadastro/Sala/")
	public void postar(Sala sala) {
		if (verificarSeJaTemCadastroSala(sala)) {
			boolean inserir = salaDao.inserir(sala);
			if (inserir) {
				int idSal = salaDao.listarUltimo();
				registrarLog(FlagsLogAcao.CADASTRAR_SALA.getCodigo(), String.valueOf(idSal));
			}
		}
		result.use(Results.json()).withoutRoot().from(salaDao.listarNome()).serialize();
	}
	private boolean verificarSeJaTemCadastroSala(Sala sala){
		List<Sala> listar = salaDao.listar();
		for (Sala sala2 : listar) {
			if (sala2.getNome().contains(sala.getNome())) {
				return false;
			}
		}
		return true;
	}
	/*
	 * LISTA TODOS AS SALAS
	 */
	@Post("/Listar/Sala/")
	public void listar() {
		result.use(Results.json()).withoutRoot().from(salaDao.listarNome()).serialize();

	}
}
