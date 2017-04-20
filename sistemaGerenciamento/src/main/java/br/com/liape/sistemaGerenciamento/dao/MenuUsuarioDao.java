package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.MenuUsuario;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class MenuUsuarioDao extends PersistenceJDBC<MenuUsuario> {
	private static final String SQL_LOGIN = "SELECT * FROM menu_usuario where LOGIN_USR = ?";
	private final String SQL_ULTIMO = "SELECT ID_MU FROM menu_usuario ORDER BY ID_MU DESC LIMIT 1";
	private static final String DELETAR_ID = "DELETE FROM menu_usuario where ID_MU = ?";
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<MenuUsuario> listarLogin(String login)
	{
		return consultarLista(SQL_LOGIN, login);
	}

	public int ultimoId() {
		return (int) super.consultarParametros(SQL_ULTIMO);
	}

	public boolean remover(int id) {
		return atualizar(DELETAR_ID, id);
	}
}
