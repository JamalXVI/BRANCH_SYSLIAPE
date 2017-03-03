package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.liape.sistemaGerenciamento.model.SubOrdem;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class SubOrdemDao extends PersistenceJDBC<SubOrdem> {
	private static final String LISTAR_ULTIMO = "SELECT ID_SOR FROM SUB_ORDEM ORDER BY ID_SOR DESC LIMIT 1";
	private static final String LISTAR_ID = "SELECT * FROM"
			+ " SUB_ORDEM WHERE ID_ORS = ? ORDER BY ID_SOR DESC LIMIT 1";
	private static final String LISTAR_SUB_ID = "SELECT * FROM"
			+ " SUB_ORDEM WHERE ID_SOR = ? ORDER BY ID_SOR DESC LIMIT 1";
	private static final String LISTAR_SUB_ID_ESP = "SELECT SOR.* FROM SUB_ORDEM SOR INNER JOIN ORDEM_SERVICO ORS "
			+ "ON SOR.ID_ORS = ORS.ID_ORS WHERE ID_SOR = ? AND EXECUTADA_ORS = FALSE AND ATIVO_ORS = TRUE AND "
			+ "DATA_EXECUTADA_SOR IS NULL ORDER BY ID_SOR DESC LIMIT 1";
	private static final String LISTAR_SUB_ID_ESP_ORS = "SELECT SOR.* FROM SUB_ORDEM SOR INNER JOIN ORDEM_SERVICO ORS "
			+ "ON SOR.ID_ORS = ORS.ID_ORS WHERE ORS.ID_ORS = ? AND EXECUTADA_ORS = FALSE AND ATIVO_ORS = TRUE AND "
			+ "DATA_EXECUTADA_SOR IS NULL ORDER BY ID_SOR DESC LIMIT 1";
	
	public SubOrdemDao() {
	}
	
	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}
	public int listarUltimo() {
		// TODO Auto-generated method stub
		return (int) super.consultarParametros(LISTAR_ULTIMO);
	}
	public List<SubOrdem> listarId(int id) {
		return super.consultarLista(LISTAR_ID, id);
	}
	public List<SubOrdem> listarIdSub(int id) {
		return super.consultarLista(LISTAR_SUB_ID, id);
	}
	public List<SubOrdem> listarIdSubEsp(int id) {
		return super.consultarLista(LISTAR_SUB_ID_ESP, id);
	}
	public List<SubOrdem> listarIdSubEspOrs(int id) {
		return super.consultarLista(LISTAR_SUB_ID_ESP_ORS, id);
	}
}
