package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.liape.sistemaGerenciamento.model.OrdemServico;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class OrdemServicoDao extends PersistenceJDBC<OrdemServico> {
	private static final String LISTAR_ULTIMO = "SELECT ID_ORS FROM ORDEM_SERVICO ORDER BY ID_ORS DESC LIMIT 1";
	public OrdemServicoDao() {
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
