package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.UsuarioTurno;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class UsuarioTurnoDao extends PersistenceJDBC<UsuarioTurno> {
	private static String SQL_LISTAR_TUR = "SELECT * FROM usuario_turno WHERE ID_TUR = ?";
	private static String SQL_LISTAR_USR = "SELECT * FROM usuario_turno WHERE LOGIN_USR = ?";

	public UsuarioTurnoDao() {
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<UsuarioTurno> listarId(int id) {
		// TODO Auto-generated method stub
		return super.consultarLista(SQL_LISTAR_TUR, id);
	}
	public List<UsuarioTurno> listarLogin(String login) {
		// TODO Auto-generated method stub
		return super.consultarLista(SQL_LISTAR_USR, login);
	}
}
