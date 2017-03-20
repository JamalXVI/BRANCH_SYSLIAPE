package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Sala;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class SalaDao extends PersistenceJDBC<Sala> {
	private static String LISTAR_NOME = "SELECT * FROM SALA ORDER BY NOME_SAL";
	private static String LISTAR_ID = "SELECT * FROM SALA WHERE ID_SAL = ?";
	private static String LISTAR_ULTIMO = "SELECT ID_SAL FROM SALA ORDER BY ID_SAL DESC LIMIT 1";
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<Sala> listarNome()
	{
		return super.consultarLista(LISTAR_NOME);
	}
	public List<Sala> listarId(int idSal)
	{
		return super.consultarLista(LISTAR_ID, idSal);
	}
	public int listarUltimo(){
		return (int)super.consultarParametros(LISTAR_ULTIMO);
	}
}
