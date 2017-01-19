package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.OrdemServicoComputador;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class OrdemServicoComputadorDao extends PersistenceJDBC<OrdemServicoComputador> {
	private static final String LISTAR_ID = "SELECT * FROM"
			+ " ORDEM_SERVICO_COMPUTADOR WHERE ID_ORS = ?";
	public OrdemServicoComputadorDao() {
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<OrdemServicoComputador> listarId(int id) {
		return super.consultarLista(LISTAR_ID, id);
	}
}
