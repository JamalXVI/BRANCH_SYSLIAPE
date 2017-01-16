package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.FotoPerfil;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class FotoPerfilDao extends PersistenceJDBC<FotoPerfil> {

	private final String SQL_ULTIMO = "SELECT ID_FOT FROM foto_perfil ORDER BY ID_FOT DESC LIMIT 1";
	private final String LISTAR_ID_PES = "SELECT * FROM foto_perfil where ID_PES = ? ORDER BY ID_FOT DESC";

	public FotoPerfilDao() {
	}

	public int ultimoId() {
		return (int) super.consultarParametros(SQL_ULTIMO);
	}
	/*
	 * LISTAGEM POR ID DA PESSOA;
	 */
	public List<FotoPerfil> listarPorIdPes(int idPes)
	{
		return super.consultarLista(LISTAR_ID_PES, idPes);
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
}
