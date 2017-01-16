package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.liape.sistemaGerenciamento.model.SubOrdemTurno;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class SubOrdemVisualizadaDao extends PersistenceJDBC<SubOrdemTurno> {
	public SubOrdemVisualizadaDao() {
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
}
