package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.liape.sistemaGerenciamento.model.FimTurno;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class FimTurnoDao extends PersistenceJDBC<FimTurno> {


	public FimTurnoDao() {
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
}
