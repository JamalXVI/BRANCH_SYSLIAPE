package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Arquivos;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class ArquivosDAO extends PersistenceJDBC<Arquivos> {
	private final String SQL_ULTIMO = "SELECT ID_ARQ FROM arquivos ORDER BY ID_ARQ DESC LIMIT 1";
	private final String SQL_ID = "SELECT * FROM arquivos WHERE ID_ARQ = ? AND ATIVO_ARQ = TRUE";
	private final String SQL_ATIVO = "SELECT * FROM arquivos WHERE ATIVO_ARQ = ?";
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public int ultimoId() {
		return (int) super.consultarParametros(SQL_ULTIMO);
	}
	public List<Arquivos> listarId(int id)
	{
		return consultarLista(SQL_ID, id);
	}
	public List<Arquivos> listarAtivo(boolean ativo)
	{
		return consultarLista(SQL_ATIVO, ativo);
	}
}
