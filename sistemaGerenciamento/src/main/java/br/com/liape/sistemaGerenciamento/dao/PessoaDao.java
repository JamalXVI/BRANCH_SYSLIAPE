package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.liape.sistemaGerenciamento.model.Pessoa;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class PessoaDao extends PersistenceJDBC<Pessoa> {

	private final String SQL_LISTAR_ID = "SELECT * FROM pessoa WHERE ID_PES = ?";
	private final String SQL_DELETAR_POR_ID = "DELETE FROM pessoa WHERE ID_PES = ?";
	private final String SQL_PROXIMO_ID = "SELECT (MAX(ID_PES) + 1) FROM pessoa";
	private final String SQL_ULTIMO = "SELECT ID_PES FROM pessoa ORDER BY ID_PES DESC LIMIT 1";
	private final String SQL_LISTAR_TODOS = "SELECT * FROM pessoa WHERE ATIVO_PES = ? ORDER BY ID_PES DESC";
	private final String SQL_LISTAR_TODOS_ANIVERSARIO = "SELECT * FROM pessoa WHERE MONTH(DATA_NASCIMENTO_PES)"
			+ " = MONTH(now()) AND ATIVO_PES = ? ORDER BY ID_PES DESC;";
	public PessoaDao() {
	}

	public List<Pessoa> listarPorId(int id) {
		return super.consultarLista(SQL_LISTAR_ID, id);
	}

	public boolean deletarPorID(int id) {
		return super.inserir(SQL_DELETAR_POR_ID, id);
	}

	public int ultimoId() {
		return (int) super.consultarParametros(SQL_ULTIMO);
	}

	public long proximoId() {
		return (long) super.consultarParametros(SQL_PROXIMO_ID);
	}
	@Override
	public List<Pessoa> listar() {
		return super.consultarLista(SQL_LISTAR_TODOS, true);
	}
	public List<Pessoa> listar_aniversario() {
		return super.consultarLista(SQL_LISTAR_TODOS_ANIVERSARIO, true);
	}
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
}
