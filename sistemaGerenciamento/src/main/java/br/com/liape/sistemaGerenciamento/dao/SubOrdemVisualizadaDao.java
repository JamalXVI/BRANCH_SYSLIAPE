package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.SubOrdemVisualizada;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class SubOrdemVisualizadaDao extends PersistenceJDBC<SubOrdemVisualizada> {
	private static final String LISTAR_ID = "SELECT * FROM"
			+ " SUBORDEM_VISUALIZADA WHERE ID_SOR = ?";
	private static final String LISTAR_ID_LOGIN = "SELECT * FROM"
			+ " SUBORDEM_VISUALIZADA WHERE ID_SOR = ? AND LOGIN_USR = ?";
	public SubOrdemVisualizadaDao() {
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<SubOrdemVisualizada> listarId(int id) {
		return super.consultarLista(LISTAR_ID, id);
	}
	public List<SubOrdemVisualizada> listarIdLogin(int id, String login) {
		return super.consultarLista(LISTAR_ID_LOGIN, id, login);
	}
}
