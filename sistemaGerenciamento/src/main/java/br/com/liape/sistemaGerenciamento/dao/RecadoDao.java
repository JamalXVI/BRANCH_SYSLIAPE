package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.Recado;
import br.com.liape.sistemaGerenciamento.model.Usuario;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class RecadoDao extends PersistenceJDBC<Recado> {
	private static final String LISTAR_ULTIMO = "SELECT ID_REC FROM recados ORDER BY ID_REC DESC LIMIT 1";
	private static final String LISTAR_ID = "SELECT * FROM recados WHERE ID_REC = ?";
	private static final String LISTAR_LOGIN = "SELECT * FROM recados WHERE LOGIN_USR = ? AND ATIVO_REC = TRUE";
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public int listarUltimo() {
		// TODO Auto-generated method stub
		return (int) super.consultarParametros(LISTAR_ULTIMO);
	}
	public List<Recado> listaId(int id)
	{
		return super.consultarLista(LISTAR_ID, id);
	}
	public List<Recado> listarUsuario(String loginUsr)
	{
		return super.consultarLista(LISTAR_LOGIN, loginUsr);
	}
}
