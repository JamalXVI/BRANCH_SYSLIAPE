package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.LogAcao;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class LogAcaoDao extends PersistenceJDBC<LogAcao> {
	private final String SQL_USUARIO = "SELECT * FROM log_acao WHERE LOGIN_USR=?";

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}

	public List<LogAcao> listarLogin(String login) {
		return consultarLista(SQL_USUARIO, login);
	}
}
