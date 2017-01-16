package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Curso;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class CursoDao extends PersistenceJDBC<Curso> {
	
	private static String SQL_COD_CURSO = "SELECT * FROM CURSO WHERE CURSO.CODIGO_CUR = ?";

	public CursoDao() {
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}

	public List<Curso> pesquisarCodCurso(String codCurso) {
		return super.consultarLista(SQL_COD_CURSO, codCurso);
	}
}
