package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.liape.sistemaGerenciamento.model.Computador;
import br.com.liape.sistemaGerenciamento.model.SubOrdem;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class ComputadorDao extends PersistenceJDBC<Computador> {
	public ComputadorDao() {
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
}
