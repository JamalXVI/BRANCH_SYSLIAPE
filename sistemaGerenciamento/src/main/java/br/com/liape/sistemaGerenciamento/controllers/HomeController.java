package br.com.liape.sistemaGerenciamento.controllers;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.liape.sistemaGerenciamento.dao.MenuUsuarioDao;
import br.com.liape.sistemaGerenciamento.dao.MenuUsuarioItemDao;
import br.com.liape.sistemaGerenciamento.model.MenuUsuario;
import br.com.liape.sistemaGerenciamento.model.MenuUsuarioItem;
import br.com.liape.sistemaGerenciamento.seguranca.LoginFuncionario;

@Controller
public class HomeController extends AbstractController {
	@Inject
	private MenuUsuarioDao menuUsuarioDao;
	@Inject
	private MenuUsuarioItemDao menuUsuarioItemDao;
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
	public void linux() {
		String[] passos = new String[7];
		passos[0] = "";
		passos[1] = "Clicar no Ícone de Wi-fi";
		passos[2] = "Selecionar a rede da Unaerp";
		passos[3] = "Colocar:</p><p>Segurança Wi-Fi: WPA &" + " WPA2(Enterprise)</p><p>Autenticação Interna:MSCHAPv2";
		passos[4] = "Lembrar de Selecionar \"Nenhum Certificado CA nescessário\"";
		passos[5] = "Checar Status da Rede";
		passos[6] = "Verificar se está conectada";
		result.include("passos", passos);
	}

	@Path("/Ramais")
	public void ramais() {

	}

	@Path("/HorariosEspeciais/")
	public void horariosEspeciais() {

	}

	@Path("/Editar/Menu/")
	public void menu() {
	}

	@Post("/Postar/Menu/")
	public void postarMenu(MenuUsuarioItem[] itemMenu) {
		boolean valor = true;
		for (MenuUsuarioItem menuUsuarioItem : itemMenu) {
			for (MenuUsuarioItem menuUsuarioItem2 : itemMenu) {
				if (menuUsuarioItem != menuUsuarioItem2
						&& menuUsuarioItem.getNumero() == menuUsuarioItem2.getNumero()) {
					valor = false;
				}
			}
		}
		if (!valor) {
			result.redirectTo(ErrosController.class).erro_operacao();
		} else {
			List<MenuUsuario> listarLogin = menuUsuarioDao.listarLogin(usuarioLogado.getUsuario().getLogin());
			if (listarLogin.size() > 0) {
				for (MenuUsuario menuUsuario : listarLogin) {
					menuUsuarioItemDao.remover(menuUsuario.getId());
					menuUsuarioDao.remover(menuUsuario.getId());
				}
			}
			MenuUsuario menuUsuario = new MenuUsuario();
			menuUsuario.setLoginUsr(usuarioLogado.getUsuario().getLogin());
			boolean inserir = menuUsuarioDao.inserir(menuUsuario);
			if (inserir) {
				int ultimoId = menuUsuarioDao.ultimoId();
				for (int i = 0; i < itemMenu.length; i++) {
					MenuUsuarioItem item = itemMenu[i];
					item.setOrdem(i);
					item.setIdMenu(ultimoId);
					menuUsuarioItemDao.inserir(item);
				}
				usuarioLogado.setItensMenu(null);
				usuarioLogado.setMenuViews(null);
				result.redirectTo(this).index();
			} else {
				result.redirectTo(ErrosController.class).erro_operacao();
			}
		}

	}
}