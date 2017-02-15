package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.MuralVisualizado;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class MuralVisualizadoDao extends PersistenceJDBC<MuralVisualizado> {
	private static final String LISTAR_ULTIMO = "SELECT ID_MUR FROM mural_visualizado ORDER BY ID_MUR DESC LIMIT 1";
	private static final String LISTAR_ID = "SELECT * FROM mural_visualizado WHERE ID_MUR = ? AND LOGIN_USR = ?";
	private static final String LISTAR_TURNO = "SELECT * FROM mural_visualizado WHERE LOGIN_USR = ? AND APAGADO_MUV = FALSE";
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public int listarUltimo() {
		// TODO Auto-generated method stub
		return (int) super.consultarParametros(LISTAR_ULTIMO);
	}
	public List<MuralVisualizado> listaId(int id, String login)
	{
		return super.consultarLista(LISTAR_ID, id, login);
	}
	public List<MuralVisualizado> listarUsuario(String loginUsr)
	{
		return super.consultarLista(LISTAR_TURNO, loginUsr);
	}
}
