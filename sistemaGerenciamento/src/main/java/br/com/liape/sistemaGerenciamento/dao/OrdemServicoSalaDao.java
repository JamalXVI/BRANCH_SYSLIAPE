package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.OrdemServicoSala;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class OrdemServicoSalaDao extends PersistenceJDBC<OrdemServicoSala> {
	private static final String LISTAR_ID = "SELECT * FROM"
		+ " ORDEM_SERVICO_SALA WHERE ID_ORS = ?";
	public OrdemServicoSalaDao() {
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<OrdemServicoSala> listarId(int id) {
		return super.consultarLista(LISTAR_ID, id);
	}
}
