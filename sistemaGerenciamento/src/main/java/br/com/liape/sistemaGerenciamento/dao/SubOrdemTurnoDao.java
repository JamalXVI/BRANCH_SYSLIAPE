package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.SubOrdemTurno;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class SubOrdemTurnoDao extends PersistenceJDBC<SubOrdemTurno> {
	private static final String LISTAR_ID = "SELECT * FROM"
			+ " SUB_ORDEM_TURNO WHERE ID_SOR = ?";
	private static final String LISTAR_ID_TUR = "SELECT * FROM"
			+ " SUB_ORDEM_TURNO WHERE ID_TUR = ?";
	public SubOrdemTurnoDao() {
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<SubOrdemTurno> listarId(int id) {
		// TODO Auto-generated method stub
		return super.consultarLista(LISTAR_ID, id);
	}
	public List<SubOrdemTurno> listarIdTurno(int id) {
		// TODO Auto-generated method stub
		return super.consultarLista(LISTAR_ID_TUR, id);
	}
}
