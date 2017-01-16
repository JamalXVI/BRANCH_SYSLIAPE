package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Disciplina;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class DisciplinaDao extends PersistenceJDBC<Disciplina> {
	
	private final String SQL_LISTAR_COD_DIS = "SELECT * FROM disciplina WHERE CODIGO_DIS = ?";

	public DisciplinaDao() {
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}

	public List<Disciplina> pesquisarDisciplinaCod(String codDis) {
		return super.consultarLista(SQL_LISTAR_COD_DIS, codDis);
	}
}
