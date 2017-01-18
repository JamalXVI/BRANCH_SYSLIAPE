package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.liape.sistemaGerenciamento.model.SubOrdem;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class SubOrdemDao extends PersistenceJDBC<SubOrdem> {
	private static final String LISTAR_ULTIMO = "SELECT ID_SOR FROM SUB_ORDEM ORDER BY ID_SOR DESC LIMIT 1";
	public SubOrdemDao() {
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public int listarUltimo() {
		// TODO Auto-generated method stub
		return (int) super.consultarParametros(LISTAR_ULTIMO);
	}
}
