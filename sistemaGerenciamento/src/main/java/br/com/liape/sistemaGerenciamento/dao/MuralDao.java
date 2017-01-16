package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Mural;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class MuralDao extends PersistenceJDBC<Mural> {
	private static final String LISTAR_ULTIMO = "SELECT ID_MUR FROM mural ORDER BY ID_MUR DESC LIMIT 1";
	private static final String LISTAR_ID = "SELECT * FROM mural WHERE ID_MUR = ?";
	private static final String LISTAR_TURNO = "SELECT * FROM mural WHERE ID_TUR = ? AND ATIVO_MUR = TRUE";
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public int listarUltimo() {
		// TODO Auto-generated method stub
		return (int) super.consultarParametros(LISTAR_ULTIMO);
	}
	public List<Mural> listaId(int id)
	{
		return super.consultarLista(LISTAR_ID, id);
	}
	public List<Mural> listarTurno(String loginUsr)
	{
		return super.consultarLista(LISTAR_TURNO, loginUsr);
	}
}
