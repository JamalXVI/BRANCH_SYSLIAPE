package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.MenuUsuarioItem;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class MenuUsuarioItemDao extends PersistenceJDBC<MenuUsuarioItem> {
	private static final String SQL_MENU_ID = "SELECT * FROM menu_usuario_item where ID_MU = ?";
	private static final String DELETAR_ID = "DELETE FROM menu_usuario_item where ID_MU = ?";

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}

	public List<MenuUsuarioItem> listarIdMenu(int id) {
		return consultarLista(SQL_MENU_ID, id);
	}

	public boolean remover(int id) {
		return atualizar(DELETAR_ID, id);
	}
}
