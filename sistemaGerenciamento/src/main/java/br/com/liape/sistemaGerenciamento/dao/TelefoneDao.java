package br.com.liape.sistemaGerenciamento.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.liape.sistemaGerenciamento.model.Telefone;
import br.com.sistemaGerenciamento.conexao.ConnectionFactory;
import br.com.unaerp.jdbc.persistence.PersistenceJDBC;

public class TelefoneDao extends PersistenceJDBC<Telefone> {

	private final String SQL_LISTAR = "SELECT * FROM telefone WHERE ID_PES = ? AND ATIVO_TEL = ?";
	private final String SQL_EXITE = "SELECT * FROM telefone WHERE ID_PES = ? AND DDD_TEL = ? AND TELEFONE_TEL = ?";
	private final String SQL_DELETAR_POR_ID = "DELETE FROM telefone WHERE ID_PES = ?";

	public TelefoneDao() {
	}

	public List<Telefone> listarPorId(int id) {
		return super.consultarLista(SQL_LISTAR, id, true);
	}

	public boolean deletarPorID(int id) {
		return super.inserir(SQL_DELETAR_POR_ID, id);
	}

	@Override
	protected Connection getConnection() throws SQLException {
		return ConnectionFactory.getConnections();
	}

	public boolean verificarSeJaExiste(Telefone telefone, int idPes) {
		// TODO Auto-generated method stub
		return super.consultarLista(SQL_EXITE, idPes, telefone.getDdd(),
				telefone.getNumero()).size() > 0;
	}
}
