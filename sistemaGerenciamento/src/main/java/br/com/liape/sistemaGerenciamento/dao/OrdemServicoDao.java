package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.OrdemServico;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class OrdemServicoDao extends PersistenceJDBC<OrdemServico> {
	private static final String LISTAR_ULTIMO = "SELECT ID_ORS FROM ORDEM_SERVICO ORDER BY ID_ORS DESC LIMIT 1";
	private static final String LISTAR_ATIVO = "SELECT * FROM ORDEM_SERVICO WHERE"
			+ " ATIVO_ORS = TRUE AND EXECUTADA_ORS = ?";
	private static final String LISTAR_ID = "SELECT * FROM ORDEM_SERVICO WHERE"
			+ " ID_ORS = ?";
	private static final String LISTAR_ATIVO_ID = "SELECT * FROM ORDEM_SERVICO WHERE"
			+ " ATIVO_ORS = TRUE AND EXECUTADA_ORS = FALSE AND ID_ORS = ?";
	public OrdemServicoDao() {
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public List<OrdemServico> listarExecucao(boolean executada) {
		// TODO Auto-generated method stub
		return super.consultarLista(LISTAR_ATIVO, executada);
	}
	public List<OrdemServico> listarExecucaoId(int id) {
		// TODO Auto-generated method stub
		return super.consultarLista(LISTAR_ATIVO_ID, id);
	}
	public List<OrdemServico> listarId(int idOrs) {
		// TODO Auto-generated method stub
		return super.consultarLista(LISTAR_ID, idOrs);
	}
	public int listarUltimo() {
		// TODO Auto-generated method stub
		return (int) super.consultarParametros(LISTAR_ULTIMO);
	}
}
