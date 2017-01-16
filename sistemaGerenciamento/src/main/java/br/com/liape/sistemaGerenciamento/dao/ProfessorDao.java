package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.liape.sistemaGerenciamento.model.Professor;
import br.com.liape.sistemaGerenciamento.model.Telefone;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class ProfessorDao extends PersistenceJDBC<Professor> {

	private final String SQL_LISTAR = "SELECT * FROM professor WHERE CODIGO_PRO = ?";
	private final String SQL_LISTAR_ID_PES = "SELECT * FROM professor WHERE ID_PES = ?";
	private final String SQL_DELETAR_POR_ID = "DELETE FROM professor WHERE ID_PES = ?";

	public ProfessorDao() {
	}

	public List<Professor> listarPorCodProfessor(String codProf) {
		return super.consultarLista(SQL_LISTAR, codProf);
	}

	public boolean deletarPorID(int id) {
		return super.inserir(SQL_DELETAR_POR_ID, id);
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}

	public List<Professor> listarIdPessoa(int id) {
		return consultarLista(SQL_LISTAR_ID_PES, id);
	}

}
