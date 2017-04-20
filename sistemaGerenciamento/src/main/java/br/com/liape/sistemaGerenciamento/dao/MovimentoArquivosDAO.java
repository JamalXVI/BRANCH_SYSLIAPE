package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.MovimentoArquivos;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class MovimentoArquivosDAO extends PersistenceJDBC<MovimentoArquivos> {

	private final String SQL_ID = "SELECT * FROM movimento_arquivos WHERE ID_ARQ = ?";
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<MovimentoArquivos> listarIdArq(int id)
	{
		return consultarLista(SQL_ID, id);
	}
}
