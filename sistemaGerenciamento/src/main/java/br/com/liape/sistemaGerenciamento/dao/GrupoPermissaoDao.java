package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.GrupoPermissao;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class GrupoPermissaoDao extends PersistenceJDBC<GrupoPermissao>{
	private final String LISTAR_POR_ID_GRP = "SELECT * FROM grupo_permissao WHERE ID_GRP = ?";
	private final String DELETAR_POR_ID_GRP = "DELETE FROM grupo_permissao WHERE ID_GRP = ?";
	public GrupoPermissaoDao() {
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<GrupoPermissao> listarPermissoesGrupo(int idGrp)
	{
		return super.consultarLista(LISTAR_POR_ID_GRP, idGrp);
	}
	public boolean deletar(int idGrp)
	{
		return super.deletar(DELETAR_POR_ID_GRP, idGrp);
	}

}
