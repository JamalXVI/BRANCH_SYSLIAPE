package br.com.liape.sistemaGerenciamento.seguranca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.liape.sistemaGerenciamento.constantes.FlagsMenuSysLiape;
import br.com.liape.sistemaGerenciamento.dao.MenuUsuarioDao;
import br.com.liape.sistemaGerenciamento.dao.MenuUsuarioItemDao;
import br.com.liape.sistemaGerenciamento.infra.Configuracoes;
import br.com.liape.sistemaGerenciamento.model.MenuUsuario;
import br.com.liape.sistemaGerenciamento.model.MenuUsuarioItem;
import br.com.liape.sistemaGerenciamento.model.Permissao;
import br.com.liape.sistemaGerenciamento.model.Usuario;
import br.com.liape.sistemaGerenciamento.modelView.MenuView;

@SessionScoped
@Named
public class UsuarioLogado implements Serializable {
	private static final long serialVersionUID = 9160216232419149786L;
	private Usuario usuario;
	private Calendar horario_login;
	private List<MenuUsuarioItem> itensMenu;
	private List<MenuView> menuViews;

	public UsuarioLogado() {
	}

	public void fazLogin(Usuario usuario) {
		this.horario_login = Calendar.getInstance();
		this.usuario = usuario;
	}

	public void desloga() {
		this.usuario = null;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public boolean isLogado() {
		return this.usuario != null;
	}

	public List<Permissao> pegarAutorizacao() {
		return this.usuario.getGrupo().getPermissoes();
	}
	public boolean ehAdmin()
	{
		for (Permissao permissao : this.usuario.getGrupo().getPermissoes()) {
			if (permissao.getId() == 1) {
				return true;
			}
		}
		return false;
	}
	public Calendar getHorario_login() {
		return horario_login;
	}

	public void setHorario_login(Calendar horario_login) {
		this.horario_login = horario_login;
	}

	public List<MenuUsuarioItem> getItensMenu() {
		MenuUsuarioDao menuUsuarioDao = new MenuUsuarioDao();
		MenuUsuarioItemDao menuUsuarioItemDao = new MenuUsuarioItemDao();
		if (itensMenu == null) {
			List<MenuUsuario> listarLogin = menuUsuarioDao.listarLogin(getUsuario().getLogin());
			if (listarLogin.size() > 0) {
				MenuUsuario menuUsuario = listarLogin.get(0);
				List<MenuUsuarioItem> menu = menuUsuarioItemDao.listarIdMenu(menuUsuario.getId());
				if (menu.size() <= 0 || menu.size() != Configuracoes.configuracao.getMenuOrdens().size()) {
					menuUsuarioItemDao.remover(menuUsuario.getId());
					setItensMenu(refazerMenu(menuUsuario.getId()));
				} else {
					setItensMenu(menu);
				}
			} else {
				MenuUsuario menuUsuario = new MenuUsuario();
				menuUsuario.setLoginUsr(getUsuario().getLogin());
				menuUsuarioDao.inserir(menuUsuario);
				int ultimoId = menuUsuarioDao.ultimoId();
				setItensMenu(refazerMenu(ultimoId));
			}
			Collections.sort(itensMenu);
		}
		return itensMenu;
	}

	private List<MenuUsuarioItem> refazerMenu(int id) {
		MenuUsuarioItemDao menuUsuarioItemDao = new MenuUsuarioItemDao();
		List<MenuUsuarioItem> menuOrdens = Configuracoes.configuracao.getMenuOrdens();
		for (MenuUsuarioItem menuUsuarioItem : menuOrdens) {
			menuUsuarioItem.setIdMenu(id);
			menuUsuarioItemDao.inserir(menuUsuarioItem);
		}
		return menuOrdens;
	}

	public void setItensMenu(List<MenuUsuarioItem> itensMenu) {
		this.itensMenu = itensMenu;
	}

	public List<MenuView> getMenuViews() {
		if (menuViews == null) {
			List<MenuView> views = new ArrayList<>();
			List<MenuUsuarioItem> menu = getItensMenu();
			for (MenuUsuarioItem menuUsuarioItem : menu) {
				MenuView menuView = new MenuView();
				menuView.setNome(FlagsMenuSysLiape.getEnum(menuUsuarioItem.getNumero()).getNome());
				menuView.setNumero(menuUsuarioItem.getNumero());
				menuView.setOrdem(menuUsuarioItem.getOrdem());
				views.add(menuView);
			}
			menuViews = views;
			Collections.sort(menuViews);
		}
		return menuViews;
	}

	public void setMenuViews(List<MenuView> menuViews) {
		this.menuViews = menuViews;
	}
}