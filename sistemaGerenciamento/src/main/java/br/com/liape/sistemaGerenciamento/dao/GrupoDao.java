package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Grupo;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class GrupoDao extends PersistenceJDBC<Grupo> {

	private final String SQL_PROXIMO_ID = "SELECT (MAX(ID_GRP) + 1) FROM grupo";
	private final String SQL_ULTIMO = "SELECT ID_GRP FROM GRUPO ORDER BY ID_GRP DESC LIMIT 1";
	private final String LISTAR_ID = "SELECT * FROM grupo WHERE ID_GRP = ?";

	public GrupoDao() {
	}

	public long proximoId() {
		return (long) super.consultarParametros(SQL_PROXIMO_ID);
	}

	public int ultimoId() {
		return (int) super.consultarParametros(SQL_ULTIMO);
	}

	public List<Grupo> listarPorId(int idGrp) {
		return super.consultarLista(LISTAR_ID, idGrp);
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
}
