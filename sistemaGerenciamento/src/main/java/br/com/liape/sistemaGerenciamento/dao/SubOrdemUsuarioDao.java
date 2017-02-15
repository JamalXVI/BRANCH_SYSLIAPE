package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.SubOrdemUsuario;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class SubOrdemUsuarioDao extends PersistenceJDBC<SubOrdemUsuario> {
	private static final String LISTAR_ID = "SELECT * FROM"
		+ " SUB_ORDEM_USUARIO WHERE ID_SOR = ?";
	private static final String LISTAR_LOGIN = "SELECT * FROM"
			+ " SUB_ORDEM_USUARIO WHERE LOGIN_USR = ?";
	public SubOrdemUsuarioDao() {
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<SubOrdemUsuario> listarId(int id) {
		return super.consultarLista(LISTAR_ID, id);
	}
	public List<SubOrdemUsuario> listarLogin(String login) {
		return super.consultarLista(LISTAR_LOGIN, login);
	}
}
