package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Escala;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class EscalaDao extends PersistenceJDBC<Escala> {
	
	private static final String LISTAR_ULTIMO = "SELECT ID_ESC FROM escalas ORDER BY ID_ESC DESC LIMIT 1";
	private static final String LISTAR_ATIVO = "SELECT * FROM escalas WHERE ATIVO_ESC = ? ORDER BY DIA_INI";
	private static final String LISTAR_ID = "SELECT * FROM escalas WHERE ID_ESC = ?";
	public EscalaDao() {
	}
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public int listarUltimo() {
		return (int) super.consultarParametros(LISTAR_ULTIMO);
	}
	public List<Escala> listar_ativo() {
		return consultarLista(LISTAR_ATIVO, true);
	}
	public List<Escala> listar_id(int id) {
		return consultarLista(LISTAR_ID, id);
	}
}
