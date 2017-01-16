package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Turno;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class TurnoDao extends PersistenceJDBC<Turno> {
	private static String SQL_LISTAR = "SELECT * FROM turno WHERE ATIVO_TUR = ?";
	private static String SQL_ID = "SELECT * FROM turno WHERE ID_TUR = ?";
	private static String SQL_PERIODO = "SELECT * FROM turno WHERE PERIODO_TUR = ? AND ATIVO_TUR = TRUE";
	public TurnoDao() {
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	@Override
	public List<Turno> listar() {
		return super.consultarLista(SQL_LISTAR, true);
	}
	public List<Turno> listarId(int id) {
		return super.consultarLista(SQL_ID, id);
	}
	public List<Turno> listarPeriodo(int periodo) {
		return super.consultarLista(SQL_PERIODO, periodo);
	}
}
