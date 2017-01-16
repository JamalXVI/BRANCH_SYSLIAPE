package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.MuralTurno;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class MuralTurnoDao extends PersistenceJDBC<MuralTurno> {
	private static final String LISTAR_ULTIMO = "SELECT ID_MUR FROM turno_mural ORDER BY ID_MUR DESC LIMIT 1";
	private static final String LISTAR_ID = "SELECT * FROM turno_mural WHERE ID_MUR = ?";
	private static final String LISTAR_ID_TURNO = "SELECT * FROM turno_mural WHERE ID_MUR = ? AND ID_TUR = ?";
	private static final String LISTAR_TURNO = "SELECT * FROM turno_mural WHERE ID_TUR = ?";

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}

	public int listarUltimo() {
		return (int) super.consultarParametros(LISTAR_ULTIMO);
	}

	public List<MuralTurno> listaId(int id) {
		return super.consultarLista(LISTAR_ID, id);
	}

	public List<MuralTurno> listaIdeTurno(int id, int idTur) {
		return super.consultarLista(LISTAR_ID_TURNO, id, idTur);
	}

	public List<MuralTurno> listarTurno(int idTur) {
		return super.consultarLista(LISTAR_TURNO, idTur);
	}
}
