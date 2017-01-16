package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Sala;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class SalaDao extends PersistenceJDBC<Sala> {
	private static String LISTAR_NOME = "SELECT * FROM SALA ORDER BY NOME_SAL";
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<Sala> listarNome()
	{
		return super.consultarLista(LISTAR_NOME);
	}
}
