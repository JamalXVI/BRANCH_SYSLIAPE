package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.AulasLiape;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class AulasLiapeDao extends PersistenceJDBC<AulasLiape> {
	
	private static String SQL_DELETAR_ID = "DELETE FROM AULAS_LIAPE WHERE ID_AUL = ?";
	private static String SQL_LISTA = "SELECT * FROM AULAS_LIAPE WHERE DATA_AUL = ?  ORDER BY SALA_AUL";
	
	private static String SQL_LISTA_ESP = "SELECT * FROM AULAS_LIAPE WHERE DATA_AUL = ? AND"
			+ " HORA_INICIO_AUL <= ? AND HORA_FIM_AUL >= ? ORDER BY SALA_AUL";
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}

	public boolean remover(int id) {
		return atualizar(SQL_DELETAR_ID, id);
	}

	public List<AulasLiape> listar(LocalDate data) {
		return consultarLista(SQL_LISTA, data);
	}
	public List<AulasLiape> listarEsp(LocalDate data, LocalTime hora) {
		return consultarLista(SQL_LISTA_ESP, data, hora, hora);
	}
}
