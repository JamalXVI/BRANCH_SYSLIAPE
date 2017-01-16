package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.liape.sistemaGerenciamento.model.Permissao;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class PermissaoDao extends PersistenceJDBC<Permissao> {

	private final String SQL_LISTAR = "SELECT * FROM permissao WHERE ID_PER = ?";
	private final String SQL_DELETAR_POR_ID = "DELETE FROM permissao WHERE ID_PER = ?";
	private final String SQL_PROXIMO_ID = "SELECT (MAX(ID_PER) + 1) FROM permissao";

	public PermissaoDao() {
	}

	public List<Permissao> listarPorId(int id) {
		return super.consultarLista(SQL_LISTAR, id);
	}

	public boolean deletarPorID(int id) {
		return super.inserir(SQL_DELETAR_POR_ID, id);
	}

	public long proximoId() {
		return (long) super.consultarParametros(SQL_PROXIMO_ID);
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}

}
