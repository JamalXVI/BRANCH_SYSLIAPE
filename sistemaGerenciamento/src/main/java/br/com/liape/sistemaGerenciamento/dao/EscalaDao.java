package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Escala;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class EscalaDao extends PersistenceJDBC<Escala> {
	
	private static final String LISTAR_ULTIMO = "SELECT ID_ESC FROM escalas ORDER BY ID_ESC DESC LIMIT 1";
	private static final String LISTAR_ATIVO = "SELECT * FROM escalas WHERE ATIVO_ESC = ? AND YEAR(DIA_INI) >= YEAR(?) ORDER BY DIA_INI DESC";
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
	public List<Escala> listar_ativo(LocalDate data) {
		return consultarLista(LISTAR_ATIVO, true, data);
	}
	public List<Escala> listar_id(int id) {
		return consultarLista(LISTAR_ID, id);
	}
}
